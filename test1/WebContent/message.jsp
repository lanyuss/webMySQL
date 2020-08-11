<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息提示</title>
</head>
<body>
	${message}
	<a href="${pageContext.request.contextPath}/upload.jsp">返回上传界面</a>
<%String file_name=(String)request.getAttribute("file_name");
out.write(file_name);
session.setAttribute("file_name",file_name);
%>
</body>
</html>