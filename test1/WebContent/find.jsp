<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%
int [] testid = (int [])request.getAttribute("xAxis");
int [] testname = (int [])request.getAttribute("data");
%>
<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
   <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/4.0.1/highcharts.js"></script>
   <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
   <script>
	 $(function () {
		
	    $('#container').highcharts({
	    	chart: {  
                renderTo: 'container',
                type:'spline',
            }, 
            credits :{
                enabled:false//不显示highCharts版权信息
          },
	        
	        title: {
	            text: '传感器工况'
	        },
	        xAxis: {
	           categories: ['1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70']
	       		 
	        },
	        yAxis: {
	            title: {
	                text: '分数'
	            }
	        },
	        series: [{
	            name: '传感器编号',
	            //data: [1,2,3]
	        	
	          data: [<%out.println(testname[0]);%>,<%out.println(testname[1]);%>,<%out.println(testname[2]);%>,<%out.println(testname[3]);%>,<%out.println(testname[4]);%>,<%out.println(testname[5]);%>,<%out.println(testname[6]);%>,<%out.println(testname[7]);%>,<%out.println(testname[8]);%>,<%out.println(testname[9]);%>
	          ,<%out.println(testname[10]);%>,<%out.println(testname[11]);%>,<%out.println(testname[12]);%>,<%out.println(testname[13]);%>,<%out.println(testname[14]);%>,<%out.println(testname[15]);%>,<%out.println(testname[16]);%>,<%out.println(testname[17]);%>,<%out.println(testname[18]);%>,<%out.println(testname[19]);%>
	          ,<%out.println(testname[20]);%>,<%out.println(testname[21]);%>,<%out.println(testname[22]);%>,<%out.println(testname[23]);%>,<%out.println(testname[24]);%>,<%out.println(testname[25]);%>,<%out.println(testname[26]);%>,<%out.println(testname[27]);%>,<%out.println(testname[28]);%>,<%out.println(testname[29]);%>
	          ,<%out.println(testname[30]);%>,<%out.println(testname[31]);%>,<%out.println(testname[32]);%>,<%out.println(testname[33]);%>,<%out.println(testname[34]);%>,<%out.println(testname[35]);%>,<%out.println(testname[36]);%>,<%out.println(testname[37]);%>,<%out.println(testname[38]);%>,<%out.println(testname[39]);%>
	          ,<%out.println(testname[40]);%>,<%out.println(testname[41]);%>,<%out.println(testname[42]);%>,<%out.println(testname[43]);%>,<%out.println(testname[44]);%>,<%out.println(testname[45]);%>,<%out.println(testname[46]);%>,<%out.println(testname[47]);%>,<%out.println(testname[48]);%>,<%out.println(testname[49]);%>
	          ,<%out.println(testname[50]);%>,<%out.println(testname[51]);%>,<%out.println(testname[52]);%>,<%out.println(testname[53]);%>,<%out.println(testname[54]);%>,<%out.println(testname[55]);%>,<%out.println(testname[56]);%>,<%out.println(testname[57]);%>,<%out.println(testname[58]);%>,<%out.println(testname[59]);%>
	          ,<%out.println(testname[60]);%>,<%out.println(testname[61]);%>,<%out.println(testname[62]);%>,<%out.println(testname[63]);%>,<%out.println(testname[64]);%>,<%out.println(testname[65]);%>,<%out.println(testname[66]);%>,<%out.println(testname[67]);%>,<%out.println(testname[68]);%>,<%out.println(testname[69]);%>]
	        }] 
	    });
	});
   </script>
</head>
  
 <body>




<div id="container" style="min-width:800px;height:400px;"></div>
</body>
   



</html>