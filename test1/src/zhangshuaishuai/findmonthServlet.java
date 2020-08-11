package zhangshuaishuai;


import java.io.IOException;  
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;  
  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;  
  
@WebServlet(name="findmonthServlet",urlPatterns="/findmonthServlet")  
  
public class findmonthServlet extends HttpServlet {  
  
private static final long serialVersionUID = 366512729238484827L;  
  
@Override  
protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
throws ServletException, IOException {  
// TODO Auto-generated method stub  
this.doPost(req, resp);  
}  
  
@Override  
protected void doPost(HttpServletRequest request, HttpServletResponse response)  
throws ServletException, IOException {  
//response.setCharacterEncoding("utf-8");	
response.setContentType("text/html;charset=utf-8");
response.setIntHeader("refresh", 60);
PrintWriter out = response.getWriter();	
	

DecimalFormat findFormat=new DecimalFormat("00");	
Calendar findcal=Calendar.getInstance();
int findy=findcal.get(Calendar.YEAR);
int findm=findcal.get(Calendar.MONTH)+1;
int findd=findcal.get(Calendar.DATE);
int findh=findcal.get(Calendar.HOUR_OF_DAY);
int findmin=findcal.get(Calendar.MINUTE);
int findsec=findcal.get(Calendar.SECOND);
System.out.println("当前时刻："+findy+":"+findm+":"+findd+":"+findh+":"+findmin+":"+findsec);

findFormat.setRoundingMode(RoundingMode.DOWN);
String finddb_name=findFormat.format(Double.valueOf(findy))+"_"+findFormat.format(Double.valueOf(findm));
System.out.println("上传的文件叫做"+finddb_name); 

String findcreatsql = "CREATE TABLE IF NOT EXISTS"+" "+finddb_name+ "("
		 + "name int(4) not null,"
		 + "Channel1 int(4) not null"
		 + ")charset=utf8;";

String findJDBC_DRIVER= "com.mysql.jdbc.Driver";
String findDB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";
String findname= "root";
String findnpwd = "lanyuss";
Connection findconn=null;
Statement findstmt=null;
try
{
    //注册jdbc驱动
    Class.forName(findJDBC_DRIVER);
    //打开连接
    System.out.println("连接数据库");
    findconn = (Connection) DriverManager.getConnection(findDB_URL,findname,findnpwd);
    //执行创建表
    System.out.println("创建表");
    findstmt = (Statement) findconn.createStatement();
   if(0 == findstmt.executeLargeUpdate(findcreatsql))
   {
       System.out.println("成功创建表！");
   }
   else
   {
       System.out.println("创建表失败！");
   }
   //
    findstmt.close();
    findconn.close();
    System.out.println("关闭资源");
}
catch(Exception e)
{
    System.out.println("创建表失败！");
    e.printStackTrace();
}





String sql1 = null;  
String sql2= null;
String sql3=null;
String sql4=null;
String sql5=null;
String sql6=null;


DBHelper db1 = null; 
DBHelper db2= null;
DBHelper db3= null;
DBHelper db4= null;
DBHelper db5= null;
DBHelper db6=null;


ResultSet rs1 = null; 
ResultSet rs2 =null;
ResultSet rs3 =null;
ResultSet rs4 =null;
ResultSet rs5 =null;
ResultSet rs6 =null;

sql1 = "select table_name from information_schema.tables where table_schema='test'";
db1 = new DBHelper(sql1);
String name=null;
String name2=null;
int length=0;
int uid= 0;
int ufname = 0;

List data_month=new ArrayList();
List data_new=new ArrayList(); 
List dataabnormal = new ArrayList();
List datafault = new ArrayList();
List datadisconnect = new ArrayList();

List ll_month=new ArrayList();
List llabnormal = new ArrayList();
List llfault=new ArrayList();
List lldisconnect=new ArrayList();

try {  
        rs1 = db1.pst.executeQuery();
        
        while (rs1.next()) {  
            name=rs1.getString("table_name");
            
            data_new.add(name);
              
        }
        rs1.close();
        db1.close();
    } catch (SQLException e) {  
        e.printStackTrace();  
    } 
  length=data_new.size();
  name2=(String)data_new.get(length-1);
  System.out.println(name2);
  
  
  if((findh==13)&&(findmin==56)) {
	//System.out.println("success");
	sql2=String.format("insert into %s (name,Channel1) select name,Channel1 from %s",finddb_name,name2);

	//System.out.println(sql2);
	db2=new DBHelper(sql2);
	
	try {
		int result = db2.pst.executeUpdate();

		sql6=String.format("select * from %s " ,finddb_name);
		//System.out.println(sql6);
		db6=new DBHelper(sql6);
		rs6 = db6.pst.executeQuery();
	    while (rs6.next()) {  
	        uid = Integer.parseInt(rs6.getString(1));  
	        ufname = Integer.parseInt(rs6.getString(2));


	        data_month.add(ufname);
	        ll_month.add(uid); 
	    }
	    rs6.close();
	    db6.close();
	} catch (SQLException e) {
	
		e.printStackTrace();
	}

  }
  

  //统计异常情况
  sql3=String .format("select name, count(*) from %s where Channel1='1' group by name",finddb_name);              

     
  //sql2=String.format("select * from %s order by name  limit 70" ,name2);
  
  db3 = new DBHelper(sql3);
  try {
	rs3 = db3.pst.executeQuery();
    while (rs3.next()) {  
        uid = Integer.parseInt(rs3.getString(1));  
        ufname = Integer.parseInt(rs3.getString(2));

        dataabnormal.add(ufname);
        llabnormal.add(uid); 
    }
    rs3.close();
    db3.close();
} catch (SQLException e) {
	
	e.printStackTrace();
}
  //统计故障情况
  sql4=String .format("select name, count(*) from %s where Channel1='2' group by name",finddb_name);
  db4 = new DBHelper(sql4);
  try {
	rs4 = db4.pst.executeQuery();
    while (rs4.next()) {  
        uid = Integer.parseInt(rs4.getString(1));  
        ufname = Integer.parseInt(rs4.getString(2));

        datafault.add(ufname);
        llfault.add(uid); 
    }
    rs4.close();
    db4.close();
} catch (SQLException e) {
	
	e.printStackTrace();
}
  //统计未接入情况
  sql5=String .format("select name, count(*) from %s where Channel1='3' group by name",finddb_name);
  db5 = new DBHelper(sql5);
  try {
	rs5 = db5.pst.executeQuery();
    while (rs5.next()) {  
        uid = Integer.parseInt(rs5.getString(1));  
        ufname = Integer.parseInt(rs5.getString(2));

        datadisconnect.add(ufname);
        lldisconnect.add(uid); 
    }
    rs5.close();
    db5.close();
} catch (SQLException e) {
	
	e.printStackTrace();
}
  
  String str = "";  

  str += "{\"title\":\"实时数据\",\"abnormalcount\":"+llabnormal+",\"faultcount\":"+llfault+",\"disconnectcount\":"+lldisconnect+",\"dataabnormal\":"+dataabnormal+",\"datafault\":"+datafault+",\"datadisconnect\":"+datadisconnect+",\"月份表中的传感器编号\":"+ll_month+",\"月份的传感器正常与否\":"+data_month+"}";  
    System.out.println(str);
  
   out.write(str.toString());   
   out.flush();  
   out.close(); 
   
}  
  
}