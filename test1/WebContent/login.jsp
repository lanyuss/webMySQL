<%@ page contentType="text/html; charset=UTF-8" language="java" errorPage="" %>
<%
request.setCharacterEncoding("UTF-8");
String name = request.getParameter("name");
String pass = request.getParameter("pass");
if(name.equals("zhangshuaishuai")
	&& pass.equals("shu"))
{	session.setAttribute("user" , name);
	out.println("登录成功");
}
else
{
	out.println("登录失败！");
}
%>