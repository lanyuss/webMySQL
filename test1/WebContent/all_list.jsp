<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>查找全部数据</title>
</head>
<body>
 
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/world?useUnicode=true&characterEncoding=utf-8"
     user="root"  password="lanyuss"/>


<sql:query dataSource="${snapshot}" var="result">
SELECT * from city limit 60;
</sql:query>

<% 
// 设置每隔5秒自动刷新
//response.setIntHeader("Refresh", 3);

%> 


<table border="1" width="100%">
<tr>
   <th>ID</th>
   <th>城市名</th>
   <th>代码</th>
   <th>所属地区</th>
   <th>人口数</th>
</tr>
<c:forEach var="row" items="${result.rows}">

<tr>
	<c:if test="${row.Population>=1000000}">
		<td> <font color="#FF0000" size=4> <c:out value="${row.ID}"/></font> </td>
		<td> <font color="#FF0000" size=4> <c:out value="${row.Name}"/></font> </td>
		<td> <font color="#FF0000" size=4> <c:out value="${row.CountryCode}"/> </font> </td>
		<td> <font color="#FF0000" size=4> <c:out value="${row.District}"/></font> </td>
		<td> <font color="#FF0000" size=4> <c:out value="${row.Population}"/></font> </td>
    	
   
	</c:if>
	<c:if test="${row.Population<1000000}">
   		<td><c:out value="${row.ID}"/></td>
   		<td><c:out value="${row.Name}"/></td>
  		<td><c:out value="${row.CountryCode}"/></td>
   		<td><c:out value="${row.District}"/></td>
   		<td><c:out value="${row.Population}"/></td>
   </c:if>
</tr>

</c:forEach>
</table>


</body>
</html>