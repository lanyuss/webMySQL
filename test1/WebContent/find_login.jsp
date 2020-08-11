<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%
request.setCharacterEncoding("UTF-8");
//String filename = request.getParameter("filename");
String filename="2018_06_21_20_58_57";
//String pass = request.getParameter("pass");

	session.setAttribute("filename" , filename);
	out.println("连接成功");
	out.write("这是find_login文件名字是"+filename);
%>