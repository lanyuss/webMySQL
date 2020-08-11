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
  
@WebServlet(name="lineServlet",urlPatterns="/lineServlet")  
  
public class LineServlet extends HttpServlet {  
  
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
PrintWriter out = response.getWriter();
String sql = null;  
DBHelper db1 = null;  
ResultSet ret = null;  


//String file_name=(String) request.getSession().getAttribute("file_name");
//System.out.println("这里是LineServlet要查询的表名字是："+file_name);
sql = "select * from 2018_06_08_13_30_18 order by name   limit 60";

//sql=String.format("select * from %s order by name  limit 60" ,file_name);

System.out.println("这里是LineServlet:"+sql);
db1 = new DBHelper(sql);
int i =  0;
int uid= 0;
int ufname = 0;
List data = new ArrayList(); 
List ll = new ArrayList();  
try {  
        ret = db1.pst.executeQuery();
        
        while (ret.next()) {  
            uid = Integer.parseInt(ret.getString(1));  
            ufname = Integer.parseInt(ret.getString(2));
            data.add(ufname);
            ll.add(uid); 

            //i++;
           //String temp = uid+","+ufname+" ";

            //System.out.println(uid + "\t" + ufname + "\t");  
        }
        ret.close();  
        db1.close();
        
    } catch (SQLException e) {  
        e.printStackTrace();  
    } 
  
String str = "";  

str += "{\"title\":\"实时更新的数据\",\"data\":"+data+",\"xAxis\":"+ll+"}";  
  // System.out.println(str);
   out.write(str.toString());  
   out.flush();  
   out.close();  
}  
  
}