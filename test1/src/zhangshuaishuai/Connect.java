package zhangshuaishuai;

import java.sql.*;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
  
public class Connect{  
    Connection con;   
    public static final String url="jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false";  
    public static final String name="com.mysql.jdbc.Driver";  
    public static final String user="root";  
    public static final String password="lanyuss";  
      
      
      
      
    public Connection getConnection(){  
        try{  
            Class.forName(name);  
            con=DriverManager.getConnection(url,user,password);  
              
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        return con;  
    }  
      
    }  