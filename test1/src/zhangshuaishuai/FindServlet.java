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
import org.apache.catalina.servlet4preview.RequestDispatcher; 

  
@WebServlet(name="findServlet",urlPatterns="/findServlet")  
  
public class FindServlet extends HttpServlet {  
  
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

request.setCharacterEncoding("utf-8");
response.setContentType("text/html;charset=utf-8");
PrintWriter out = response.getWriter();
String sql = null;  
DBHelper db1 = null;  
ResultSet ret = null;  


String filename=request.getParameter("filename");

//String username=(String) request.getSession().getAttribute("filename");
System.out.println("这是FindServlet要查询的表名字是："+"filename = " + filename);
//sql = "select * from 2018_06_19_15_59_28 order by name   limit 60";

sql=String.format("select * from %s order by name  limit 70" ,filename);

System.out.println(sql);
db1 = new DBHelper(sql);
int i =  0;
int uid= 0;
int ufname = 0;
//List data = new ArrayList(); 
//List ll = new ArrayList();  
int [] data= new int[70];
int [] ll=new int[70];
try {  
        ret = db1.pst.executeQuery();
        
        while (ret.next()) {  
            uid = Integer.parseInt(ret.getString(1));  
            ufname = Integer.parseInt(ret.getString(2));
            //data.add(ufname);
            //ll.add(uid); 
            data[i]=ufname;
            ll[i]=uid;

            i++;
           //String temp = uid+","+ufname+" ";

            //System.out.println(uid + "\t" + ufname + "\t");  
        }
       
        ret.close();  
        db1.close();
        
    } catch (SQLException e) {  
        e.printStackTrace();  
    } 

/**
String str = "";  

str += "{\"title\":\"实时更新的数据\",\"data\":"+data+",\"xAxis\":"+ll+"}";  
  // System.out.println(str);
   out.write(str.toString());  
   out.flush();  
   out.close();  
   **/
   request.setAttribute("data", data);
   request.setAttribute("xAxis", ll);
   request.getRequestDispatcher("/find.jsp").forward(request, response); 
}  
  
}