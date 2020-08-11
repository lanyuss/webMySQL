package zhangshuaishuai;


import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Set;  
  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
@WebServlet(name="findtodayServlet",urlPatterns="/findtodayServlet")  
  
public class findtodayServlet extends HttpServlet {  
  
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
response.setIntHeader("refresh", 10);
PrintWriter out = response.getWriter();
String sql1 = null;  
String sql2= null;
DBHelper db1 = null; 
DBHelper db2= null;
ResultSet rs = null; 
ResultSet rs2= null;
sql1 = "select table_name from information_schema.tables where table_schema='test'";
db1 = new DBHelper(sql1);
String name=null;
String name2=null;
int length=0;
int uid= 0;
int ufname = 0;
int normalcount=0;
int abnormalcount=0;
int faultcount=0;
int disconnectcount=0;
List data_new=new ArrayList(); 
List data = new ArrayList(); 
List ll = new ArrayList();
try {  
        rs = db1.pst.executeQuery();
        
        while (rs.next()) {  
            name=rs.getString("table_name");
            
            data_new.add(name);
              
        }
        rs.close();
        db1.close();
    } catch (SQLException e) {  
        e.printStackTrace();  
    } 
  length=data_new.size();
  name2=(String)data_new.get(length-1);
  //out.println("这是完整的数据表"+data_new+"<br>");
 // out.println("这是要查的数据："+name2);
  //out.println("<br>");
  
  sql2=String.format("select * from %s order by name  limit 70" ,name2);
  
  db2 = new DBHelper(sql2);
  try {
	rs2 = db2.pst.executeQuery();
    while (rs2.next()) {  
        uid = Integer.parseInt(rs2.getString(1));  
        ufname = Integer.parseInt(rs2.getString(2));
        if(ufname==0) {
        	normalcount++;
        }if(ufname==1) {
        	abnormalcount++;
        }if(ufname==2) {
        	faultcount++;
        }if(ufname==3) {
        	disconnectcount++;
        }
        data.add(ufname);
        ll.add(uid); 
    }
    rs2.close();
    db2.close();
} catch (SQLException e) {
	
	e.printStackTrace();
}
  String str = "";  

  str += "{\"title\":\"实时数据\",\"data\":"+data+",\"xAxis\":"+ll+",\"normalcount\":"+normalcount+",\"abnormalcount\":"+abnormalcount+",\"faultcount\":"+faultcount+",\"disconnectcount\":"+disconnectcount+"}";  
    // System.out.println(str);
  
   out.write(str.toString());   
   out.flush();  
   out.close(); 
   
}  
  
}