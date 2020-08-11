package zhangshuaishuai;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.sql.*;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.io.BufferedReader;    
import java.io.FileReader;  
public class UploadHandleServlet extends HttpServlet {

	private static final long serialVersionUID = 7635523134411232049L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
    public String findFileSavePathByFileName(String filename,String saveRootPath){
//        int hashcode = filename.hashCode();
//        int dir1 = hashcode&0xf;  //0--15
//        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //上传时生成的临时文件保存目录
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdir();
        }
        
        //消息提示
        String message = "";
        String file_name ="";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
            factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
            //设置上传时生成的临时文件的保存目录
            factory.setRepository(tmpFile);
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //监听文件上传进度
            upload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                }
            });
             //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8"); 
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
                //按照传统方式获取数据
                return;
            }
            
            //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
            upload.setFileSizeMax(1024*1024);
            //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
            upload.setSizeMax(1024*1024*10);
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是："+fileExtName);
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //得到文件保存的名称
                    String saveFilename = makeFileName(filename);
                    //得到文件的保存目录
                    String realSavePath = makePath(saveFilename, savePath);
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    //item.delete();
                    DecimalFormat mFormat= new DecimalFormat("00");
                    
                    
                    Calendar cal=Calendar.getInstance();      
                    int y=cal.get(Calendar.YEAR);      
                    int m=cal.get(Calendar.MONTH)+1;   
                    int d=cal.get(Calendar.DATE);      
                    int h=cal.get(Calendar.HOUR_OF_DAY);      
                    int mi=cal.get(Calendar.MINUTE);      
                    int s=cal.get(Calendar.SECOND);
                    
                    mFormat.setRoundingMode(RoundingMode.DOWN);
                    String db_name = mFormat.format(Double.valueOf(y))+"_"+mFormat.format(Double.valueOf(m))
                    +"_"+mFormat.format(Double.valueOf(d))
                    +"_"+mFormat.format(Double.valueOf(h))
                    +"_"+mFormat.format(Double.valueOf(mi))
                    +"_"+mFormat.format(Double.valueOf(s));
                    
                    //String db_name = y+"_"+m+"_"+d+"_"+h+"_"+mi+"_"+s;
                    System.out.println("上传的文件叫做"+db_name);
                   file_name=db_name;
                   // request.setAttribute("filename",db_name);
                    String creatsql = "CREATE TABLE"+" "+db_name+ "("
                            + "name int(4) not null,"
                            + "Channel1 int(4) not null"
                            + ")charset=utf8;";

                    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                        //指定连接数据库的url
                    String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        
                    //mysql用户名
                    String name = "root";
                        //mysql密码
                    String pwd = "lanyuss";
                    Connection conn = null;
                    Statement stmt = null;
                    try
                    {
                        //注册jdbc驱动
                        Class.forName(JDBC_DRIVER);
                        //打开连接
                        System.out.println("连接数据库");
                        conn = DriverManager.getConnection(DB_URL,name,pwd);
                        //执行创建表
                        System.out.println("创建表");
                        stmt = conn.createStatement();
                       if(0 == stmt.executeLargeUpdate(creatsql))
                       {
                           System.out.println("成功创建表！");
                       }
                       else
                       {
                           System.out.println("创建表失败！");
                       }
                       //
                        stmt.close();
                        conn.close();
                        System.out.println("关闭资源");
                    }
                    catch(Exception e)
                    {
                        System.out.println("创建表失败！");
                        e.printStackTrace();
                    }
                  
                    if(fileExtName.equals("csv")) {
                    	 try { 
                    	     String fileName =filename;  
                    	     fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
                    	     int hashcode = fileName.hashCode();
                    	     System.out.println(hashcode);
                    	     System.out.println(fileName); 
                    	     System.out.println(URLEncoder.encode(fileName, "UTF-8"));
                    		 String fileSaveRootPath=this.getServletContext().getRealPath("/WEB-INF/upload");
                    	        //通过文件名找出文件的所在目录
                    	     String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
                    	        //得到要下载的文件
                    	     System.out.println(path); 
                    	    
                    	     String realname = fileName.substring(fileName.indexOf("_")+1);
                    	     System.out.println(realname); 
                    	     System.out.println(path + "\\" + saveFilename); 
                    	     File file = new File(path + "\\" + saveFilename);
                             BufferedReader reader = new BufferedReader(new FileReader(file));//换成你的文件名   
                             reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉   
                             String line = null;    
                             while((line=reader.readLine())!=null){    
                                 String items[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分  
                                 Connection con;  
                                 PreparedStatement sql;  
                                 ResultSet ret1=null;  
                                 Connect c=new Connect();
                                 con=c.getConnection();  
                                 sql=con.prepareStatement("insert into"+" "+db_name+" "+"("+"name,"+"channel1"+")values("+"?"+","+"?"+")");
                                 sql.setInt(1,Integer.parseInt(items[0]));  
                                 sql.setInt(2,Integer.parseInt(items[1]));  
                                 
        
                                 sql.executeUpdate();  
                                 sql.close();  
                                 con.close();  
//                                 for(int i=0;i<items.length;i++) {
//                                	 System.out.println(items[i]); 
//                                 } 
//                                 System.out.println('\n'); 
//                                 String last = items[items.length-1];//这就是你要的数据了   
                                 //int value = Integer.parseInt(last);//如果是数值，可以转化为数值   
//                                 System.out.println(last); 
                                 
                             
                    		 
                            } 
                             reader.close();
                         } catch (Exception e) {    
                             e.printStackTrace();    
                             
                         
                    	
                    }
                    	 System.out.println("你果然上传了一个csv文件");
                    		
               }
                    message = "文件上传成功！";
                    
                }
            }
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "单个文件超出最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();
        }
        request.setAttribute("file_name", file_name);
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
}

	/**
	* @Method: makeFileName
	* @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
	* @param filename 文件的原始名称
	* @return uuid+"_"+文件的原始名称
	*/ 
	private String makeFileName(String filename){  //2.jpg
		//为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
		return UUID.randomUUID().toString() + "_" + filename;
	}

	/**
	* @Method: makePath
	* @Description: 为防止一个目录下面出现太多文件，要使用hash算法打散存储
	* @param filename 文件名，要根据文件名生成存储目录
	* @param savePath 文件存储路径
	* @return 新的存储目录
	*/ 
	private String makePath(String filename,String savePath){
		//得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
//		int hashcode = filename.hashCode();
//		int dir1 = hashcode&0xf;  //0--15
//		int dir2 = (hashcode&0xf0)>>4;  //0-15
		//构造新的保存目录
		String dir = savePath;  //upload\2\3  upload\3\5
		//File既可以代表文件也可以代表目录
		File file = new File(dir);
		//如果目录不存在
		if(!file.exists()){
		    //创建目录
		    file.mkdirs();
		}
			return dir;
	}
}
