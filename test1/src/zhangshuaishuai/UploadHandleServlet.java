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
            //����Ŀ¼
            file.mkdirs();
        }
        return dir;
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
        String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
        //�ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
        String tempPath = this.getServletContext().getRealPath("/WEB-INF/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //������ʱĿ¼
            tmpFile.mkdir();
        }
        
        //��Ϣ��ʾ
        String message = "";
        String file_name ="";
        try{
            //ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
            //1������һ��DiskFileItemFactory����
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�
            factory.setSizeThreshold(1024*100);//���û������Ĵ�СΪ100KB�������ָ������ô�������Ĵ�СĬ����10KB
            //�����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
            factory.setRepository(tmpFile);
            //2������һ���ļ��ϴ�������
            ServletFileUpload upload = new ServletFileUpload(factory);
            //�����ļ��ϴ�����
            upload.setProgressListener(new ProgressListener() {
                public void update(long pBytesRead, long pContentLength, int arg2) {
                    System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ���" + pBytesRead);
                }
            });
             //����ϴ��ļ�������������
            upload.setHeaderEncoding("UTF-8"); 
            //3���ж��ύ�����������Ƿ����ϴ���������
            if(!ServletFileUpload.isMultipartContent(request)){
                //���մ�ͳ��ʽ��ȡ����
                return;
            }
            
            //�����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
            upload.setFileSizeMax(1024*1024);
            //�����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
            upload.setSizeMax(1024*1024*10);
            //4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
                //���fileitem�з�װ������ͨ�����������
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //�����ͨ����������ݵ�������������
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                    System.out.println(name + "=" + value);
                }else{//���fileitem�з�װ�����ϴ��ļ�
                    //�õ��ϴ����ļ����ƣ�
                    String filename = item.getName();
                    System.out.println(filename);
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    //ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺  c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
                    //�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    //�õ��ϴ��ļ�����չ��
                    String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
                    //�����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
                    System.out.println("�ϴ����ļ�����չ���ǣ�"+fileExtName);
                    //��ȡitem�е��ϴ��ļ���������
                    InputStream in = item.getInputStream();
                    //�õ��ļ����������
                    String saveFilename = makeFileName(filename);
                    //�õ��ļ��ı���Ŀ¼
                    String realSavePath = makePath(saveFilename, savePath);
                    //����һ���ļ������
                    FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
                    //����һ��������
                    byte buffer[] = new byte[1024];
                    //�ж��������е������Ƿ��Ѿ�����ı�ʶ
                    int len = 0;
                    //ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
                    while((len=in.read(buffer))>0){
                        //ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
                        out.write(buffer, 0, len);
                    }
                    //�ر�������
                    in.close();
                    //�ر������
                    out.close();
                    //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
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
                    System.out.println("�ϴ����ļ�����"+db_name);
                   file_name=db_name;
                   // request.setAttribute("filename",db_name);
                    String creatsql = "CREATE TABLE"+" "+db_name+ "("
                            + "name int(4) not null,"
                            + "Channel1 int(4) not null"
                            + ")charset=utf8;";

                    String JDBC_DRIVER = "com.mysql.jdbc.Driver";
                        //ָ���������ݿ��url
                    String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        
                    //mysql�û���
                    String name = "root";
                        //mysql����
                    String pwd = "lanyuss";
                    Connection conn = null;
                    Statement stmt = null;
                    try
                    {
                        //ע��jdbc����
                        Class.forName(JDBC_DRIVER);
                        //������
                        System.out.println("�������ݿ�");
                        conn = DriverManager.getConnection(DB_URL,name,pwd);
                        //ִ�д�����
                        System.out.println("������");
                        stmt = conn.createStatement();
                       if(0 == stmt.executeLargeUpdate(creatsql))
                       {
                           System.out.println("�ɹ�������");
                       }
                       else
                       {
                           System.out.println("������ʧ�ܣ�");
                       }
                       //
                        stmt.close();
                        conn.close();
                        System.out.println("�ر���Դ");
                    }
                    catch(Exception e)
                    {
                        System.out.println("������ʧ�ܣ�");
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
                    	        //ͨ���ļ����ҳ��ļ�������Ŀ¼
                    	     String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
                    	        //�õ�Ҫ���ص��ļ�
                    	     System.out.println(path); 
                    	    
                    	     String realname = fileName.substring(fileName.indexOf("_")+1);
                    	     System.out.println(realname); 
                    	     System.out.println(path + "\\" + saveFilename); 
                    	     File file = new File(path + "\\" + saveFilename);
                             BufferedReader reader = new BufferedReader(new FileReader(file));//��������ļ���   
                             reader.readLine();//��һ����Ϣ��Ϊ������Ϣ������,�����Ҫ��ע�͵�   
                             String line = null;    
                             while((line=reader.readLine())!=null){    
                                 String items[] = line.split(",");//CSV��ʽ�ļ�Ϊ���ŷָ����ļ���������ݶ����з�  
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
//                                 String last = items[items.length-1];//�������Ҫ��������   
                                 //int value = Integer.parseInt(last);//�������ֵ������ת��Ϊ��ֵ   
//                                 System.out.println(last); 
                                 
                             
                    		 
                            } 
                             reader.close();
                         } catch (Exception e) {    
                             e.printStackTrace();    
                             
                         
                    	
                    }
                    	 System.out.println("���Ȼ�ϴ���һ��csv�ļ�");
                    		
               }
                    message = "�ļ��ϴ��ɹ���";
                    
                }
            }
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "�����ļ��������ֵ������");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            request.setAttribute("message", "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }catch (Exception e) {
            message= "�ļ��ϴ�ʧ�ܣ�";
            e.printStackTrace();
        }
        request.setAttribute("file_name", file_name);
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
}

	/**
	* @Method: makeFileName
	* @Description: �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
	* @param filename �ļ���ԭʼ����
	* @return uuid+"_"+�ļ���ԭʼ����
	*/ 
	private String makeFileName(String filename){  //2.jpg
		//Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
		return UUID.randomUUID().toString() + "_" + filename;
	}

	/**
	* @Method: makePath
	* @Description: Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
	* @param filename �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼
	* @param savePath �ļ��洢·��
	* @return �µĴ洢Ŀ¼
	*/ 
	private String makePath(String filename,String savePath){
		//�õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
//		int hashcode = filename.hashCode();
//		int dir1 = hashcode&0xf;  //0--15
//		int dir2 = (hashcode&0xf0)>>4;  //0-15
		//�����µı���Ŀ¼
		String dir = savePath;  //upload\2\3  upload\3\5
		//File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
		File file = new File(dir);
		//���Ŀ¼������
		if(!file.exists()){
		    //����Ŀ¼
		    file.mkdirs();
		}
			return dir;
	}
}
