<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%> 
     <%@ page import="java.sql.DriverManager"%>
 <%@ page import="java.sql.Connection"%>
 <%@ page import="java.sql.ResultSet"%>
 <%@ page import="java.sql.SQLException"%>
 <%@ page import="java.sql.Statement"%>
 <%@ page import="java.io.*,java.util.*" %>
 
 <%@ page import="java.io.IOException"%>
  <%@ page import="java.io.PrintWriter"%>
   <%@ page import="java.io.IOException"%>
    <%@ page import="java.util.ArrayList"%>
     <%@ page import="java.util.HashMap"%>
     <%@ page import="java.util.List"%>
     <%@ page import="java.util.Map"%>
     <%@ page import="java.util.Set"%>
 
<%@ page import="javax.servlet.http.*,javax.servlet.*" %> 

<%
//设置5秒定时刷新
  // 设置每隔3秒自动刷新
   response.setIntHeader("Refresh", 20);
   // 获取当前时间
   Calendar calendar = new GregorianCalendar();
   String am_pm;
   int hour = calendar.get(Calendar.HOUR);
   int minute = calendar.get(Calendar.MINUTE);
   int second = calendar.get(Calendar.SECOND);
   if(calendar.get(Calendar.AM_PM) == 0)
      am_pm = "AM";
   else
      am_pm = "PM";
   String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
   out.println("当前时间: " + CT + "<br>");
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>  
<script type="text/javascript" src="https://cdn.hcharts.cn/highcharts/highcharts.js"></script>  
<script src="http://code.highcharts.com/modules/exporting.js"></script>


<title>Insert title here</title>  
</head> 
<body>

<div style="background-color:WHITE;width:1200px;height:1500px;">
 

<canvas id="canvas"  width=1200px height=1500px ;></canvas>  
<script type="text/javascript">  
var hello=0;
var temp=0;



var canvas = document.getElementById("canvas");         
var context = canvas.getContext("2d");  
     
//皮带标注
context.beginPath();
context.moveTo(450,265);
context.lineTo(601,230);
context.lineTo(596,211);
context.lineTo(660,220);
context.lineTo(615,267);
context.lineTo(609,250);
context.lineTo(458,289);
context.closePath();
//context.fillStyle= "rgba(0,0,0,1)"; //以上面定义的渐变填充
//context.fill(); //闭合形状并且以填充方式绘制出来
//var grd = context.createLinearGradient(450,270,660,230);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
//grd.addColorStop(0,'rgb(255,0,0)'); //起始颜色
//grd.addColorStop(1,'rgb(0,0,0)'); //终点颜色
//context.fillStyle=grd; //以上面定义的渐变填充
//context.fill(); //闭合形状并且以填充方式绘制出来

context.strokeStyle = "#F22D0D";
context.lineWidth = "2";
context.stroke();

//绘制圆1
context.beginPath();
context.arc(100,270,40,0,2*Math.PI);
context.stroke();
//绘制圆2
context.beginPath();
context.arc(1000,70,40,0,2*Math.PI);
context.stroke();
//绘制小圆1
context.beginPath();
context.arc(100,270,7,0,2*Math.PI);
context.stroke();
//绘制小圆2
context.beginPath();
context.arc(1000,70,7,0,2*Math.PI);
context.stroke();
//圆1轱1
context.beginPath();
 context.moveTo(95, 265);
 // 添加一条连接到右上角的线段
 context.lineTo(95, 230);
 context.moveTo(104, 230);
 // 添加一条连接到右上角的线段
 context.lineTo(104, 265);
 context.strokeStyle ="rgba(10,10,10,1)";
 context.lineWidth = "1";
 context.stroke();
//圆1轱2
 context.beginPath();
 //（200，200）（100，270）
  context.moveTo(92, 270);
  // 添加一条连接到右上角的线段
  context.lineTo(66, 290);
  context.moveTo(97, 277);
  // 添加一条连接到右上角的线段
  context.lineTo(72, 298);
  context.strokeStyle ="rgba(10,10,10,1)";
  context.lineWidth = "1";
  context.stroke();
//圆1轱3
 context.beginPath();
 //（200，200）（100，270）
  context.moveTo(107, 270);
  // 添加一条连接到右上角的线段
  context.lineTo(134, 290);
  context.moveTo(102, 277);
  // 添加一条连接到右上角的线段
  context.lineTo(129, 297);
  context.strokeStyle ="rgba(10,10,10,1)";
  context.lineWidth = "1";
  context.stroke();
//圆2轱1
  context.beginPath();
  //(600,100)(1000,70)
   context.moveTo(995, 65);
   // 添加一条连接到右上角的线段
   context.lineTo(995, 30);
   context.moveTo(1004, 30);
   // 添加一条连接到右上角的线段
   context.lineTo(1004, 65);
   context.strokeStyle ="rgba(10,10,10,1)";
   context.lineWidth = "1";
   context.stroke();
 //圆2轱2
  //(600,100)(1000,70)
   context.beginPath();
    context.moveTo(992, 70);
    // 添加一条连接到右上角的线段
    context.lineTo(966, 90);
    context.moveTo(997, 77);
    // 添加一条连接到右上角的线段
    context.lineTo(972, 98);
    context.strokeStyle ="rgba(10,10,10,1)";
    context.lineWidth = "1";
    context.stroke();
  //圆2轱3
  //(600,100)(1000,70)
    context.beginPath();
     context.moveTo(1007, 70);
     // 添加一条连接到右上角的线段
     context.lineTo(1034, 90);
     context.moveTo(1002, 77);
     // 添加一条连接到右上角的线段
     context.lineTo(1029, 97);
     context.strokeStyle ="rgba(10,10,10,1)";
     context.lineWidth = "1";
     context.stroke();
//皮带上层
     //(200,200)(600,100)-->(100,270),(1000,70)
    context.beginPath();
     context.moveTo(95, 230);
     // 添加一条连接到右上角的线段
     context.lineTo(995, 30);
     context.moveTo(85, 222);
     // 添加一条连接到右上角的线段
     context.lineTo(992, 21);
     context.strokeStyle ="rgba(10,10,10,1)";
     context.lineWidth = "1";
     context.stroke();
//皮带下层
//(200,200)(600,100))-->(100,270),(1000,70)
     context.beginPath();
     context.moveTo(110, 310);
     context.lineTo(1010, 110);
     context.moveTo(115, 318);
     // 添加一条连接到右上角的线段
     context.lineTo(1010, 119);
     context.strokeStyle ="rgba(10,10,10,1)";
     context.lineWidth = "1";
     context.stroke();
//绘制半圆1
     context.beginPath();
     context.arc(100,270,50,7/18*Math.PI,13/9*Math.PI,false);
     context.stroke();
//绘制半圆2
     context.beginPath();
     context.arc(1000,70,50,13/9*Math.PI,4/9*Math.PI,false);
     context.stroke();    



//绘制入料口
//绘制大矩形
context.fillStyle = "rgba(75,0,130,0.8)";
context.strokeStyle ="rgba(10,10,10,0.1)";
context.fillRect(40, 20, 110, 110);
context.strokeRect(40, 20, 110, 110);
//绘制小矩形1

context.beginPath();
context.moveTo(45,20);
context.lineTo(45,130);
context.lineTo(145,130);
context.lineTo(145,20);
//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
//grd.addColorStop(0,"#4CE8B2"); //起始颜色
//grd.addColorStop(1,"#EFD458"); //终点颜色
context.fillStyle="rgba(140,0,140,1)"; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来

//绘制小矩形2
context.beginPath();
context.moveTo(45,120);
context.lineTo(45,130);
context.lineTo(145,130);
context.lineTo(145,120);
context.fillStyle= "rgba(100,100,100,1)"; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来



//绘制三角形1
context.beginPath();
//var height = 200*Math.sin(Math.PI/3);//计算等边三角形的高
context.moveTo(40,130); //从A（100,0）开始
context.lineTo(85,190);//从A(100,0)开始，画到B (0,173)结束
context.lineTo(60,130); //B(0,173)-C(200,173)

context.fillStyle="rgba(140,0,140,1)"; ; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来
//绘制三角形2
context.beginPath();
//var height = 200*Math.sin(Math.PI/3);//计算等边三角形的高
context.moveTo(150,130); //从A（100,0）开始
context.lineTo(105,190);//从A(100,0)开始，画到B (0,173)结束
context.lineTo(130,130); //B(0,173)-C(200,173)

context.fillStyle="rgba(140,0,140,1)"; ; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来
//绘制等腰梯形
context.beginPath();
//var height = 200*Math.sin(Math.PI/3);//计算等边三角形的高
context.moveTo(60,130); //从A（100,0）开始
context.lineTo(85,190);//从A(100,0)开始，画到B (0,173)结束
context.lineTo(105,190); //B(0,173)-C(200,173)
context.lineTo(130,130); //B(0,173)-C(200,173)

context.fillStyle="rgba(10,10,10,1)"; ; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来





//绘制出料口
//绘制大矩形
context.fillStyle = "rgba(210,105,30,0.8)";
context.strokeStyle ="rgba(10,10,10,0.1)";
context.fillRect(1010, 150, 110, 110);
context.strokeRect(1010, 150, 110, 110);
//绘制小矩形1

context.beginPath();
context.moveTo(1015,150);
context.lineTo(1115,150);
context.lineTo(1115,250);
context.lineTo(1015,250);
//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
//grd.addColorStop(0,"#4CE8B2"); //起始颜色
//grd.addColorStop(1,"#EFD458"); //终点颜色
context.fillStyle="rgba(128,128,0,1)"; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来
//绘制小矩形2

context.beginPath();
context.moveTo(1010,250);
context.lineTo(1120,250);
context.lineTo(1120,260);
context.lineTo(1010,260);
context.fillStyle= "rgba(100,100,100,1)"; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来



//绘制图示    
context.fillStyle='#00FF00';
context.fillRect(50,1220,50,50);    
//设置字体样式
context.font = "30px Courier New";
//设置字体填充颜色
context.fillStyle = "blue";
//从坐标点(50,50)开始绘制文字
context.fillText("正常", 100, 1250);

context.fillStyle='#FF00FF';
context.fillRect(170,1220,50,50);    
//设置字体样式
context.font = "30px Courier New";
//设置字体填充颜色
context.fillStyle = "blue";
//从坐标点(50,50)开始绘制文字
context.fillText("异常", 220, 1250);

context.fillStyle='#FF0000';
context.fillRect(290,1220,50,50);    
//设置字体样式
context.font = "30px Courier New";
//设置字体填充颜色
context.fillStyle = "blue";
//从坐标点(50,50)开始绘制文字
context.fillText("故障",340, 1250);

context.fillStyle='#477979';
context.fillRect(410,1220,50,50);    
//设置字体样式
context.font = "30px Courier New";
//设置字体填充颜色
context.fillStyle = "blue";
//从坐标点(50,50)开始绘制文字
context.fillText("未接入",460, 1250);



//绘制三角形1
context.beginPath();
//var height = 200*Math.sin(Math.PI/3);//计算等边三角形的高
context.moveTo(1010,260); //从A（100,0）开始
context.lineTo(1055,320);//从A(100,0)开始，画到B (0,173)结束
context.lineTo(1030,260); //B(0,173)-C(200,173)

context.fillStyle="rgba(128,128,0,1)"; ; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来
//绘制三角形2
context.beginPath();
//var height = 200*Math.sin(Math.PI/3);//计算等边三角形的高
context.moveTo(1120,260); //从A（100,0）开始
context.lineTo(1075,320);//从A(100,0)开始，画到B (0,173)结束
context.lineTo(1100,260); //B(0,173)-C(200,173)

context.fillStyle="rgba(128,128,0,1)"; ; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来
//绘制等腰梯形
context.beginPath();
//var height = 200*Math.sin(Math.PI/3);//计算等边三角形的高
context.moveTo(1030,260); //从A（100,0）开始
context.lineTo(1100,260);//从A(100,0)开始，画到B (0,173)结束
context.lineTo(1075,320); //B(0,173)-C(200,173)
context.lineTo(1055,320); //B(0,173)-C(200,173)

context.fillStyle="rgba(10,10,10,1)"; ; //以上面定义的渐变填充
context.fill(); //闭合形状并且以填充方式绘制出来


//表示距离1
context.strokeStyle="#ff0000";

context.lineWidth=3;
context.beginPath();
context.moveTo(50, 480);
context.lineTo(50, 510);
context.moveTo(1100, 480);
context.lineTo(1100, 510);
context.moveTo(50, 500);
context.lineTo(540,500);
context.moveTo(660, 500);
context.lineTo(1100,500);

context.stroke();
context.fillStyle = "#00f";
context.font = "italic 20px sans-serif";
var txt="0m-150m"
context.fillText(txt , 545, 505);
//表示距离2
context.strokeStyle="#ff0000";

context.lineWidth=3;
context.beginPath();
context.moveTo(50, 650);
context.lineTo(50, 680);
context.moveTo(1100, 650);
context.lineTo(1100, 680);
context.moveTo(50, 670);
context.lineTo(540,670);
context.moveTo(660, 670);
context.lineTo(1100,670);

context.stroke();
context.fillStyle = "#00f";
context.font = "italic 20px sans-serif";
var txt="150m-300m"
context.fillText(txt , 545, 675);
//表示距离3
context.strokeStyle="#ff0000";

context.lineWidth=3;
context.beginPath();
context.moveTo(50, 820);
context.lineTo(50, 850);
context.moveTo(1100, 820);
context.lineTo(1100, 850);
context.moveTo(50, 840);
context.lineTo(540,840);
context.moveTo(660, 840);
context.lineTo(1100,840);

context.stroke();
context.fillStyle = "#00f";
context.font = "italic 20px sans-serif";
var txt="300m-450m"
context.fillText(txt , 545, 845);
//表示距离4
context.strokeStyle="#ff0000";

context.lineWidth=3;
context.beginPath();
context.moveTo(50, 990);
context.lineTo(50, 1020);
context.moveTo(1100, 990);
context.lineTo(1100, 1020);
context.moveTo(50, 1010);
context.lineTo(540,1010);
context.moveTo(660, 1010);
context.lineTo(1100,1010);

context.stroke();
context.fillStyle = "#00f";
context.font = "italic 20px sans-serif";
var txt="450m-600m"
context.fillText(txt , 545, 1015);
//表示距离5
context.strokeStyle="#ff0000";

context.lineWidth=3;
context.beginPath();
context.moveTo(50, 1160);
context.lineTo(50, 1190);
context.moveTo(750, 1160);
context.lineTo(750, 1190);
context.moveTo(50, 1180);
context.lineTo(340,1180);
context.moveTo(460, 1180);
context.lineTo(750,1180);

context.stroke();
context.fillStyle = "#00f";
context.font = "italic 20px sans-serif";
var txt="600m-700m"
context.fillText(txt , 345, 1185);
//设置纯色
//context.fillStyle = "rgba(100,100,100,1)";
//context.strokeStyle = "blue";
//context.fillRect(50, 500, 300, 20);
//context.strokeRect(120, 120, 100, 100);

var hello=0;
var temp=0;
var x=50;
var y=400;
var x1=50;
var y1=570;
var x2=50;
var y2=740;
var x3=50;
var y3=910;
var x4=50;
var y4=1080;
$(document).ready(function() {  
           $.ajax({  
               url: "timelineServlet",  
               type: "GET",  
               async:false,
               success: function(data){  
            	 
                console.debug(data)  
                
                var a = eval('(' + data + ')'); 
                    //alert(a.xAxis);
                    hello=a.data;
                   //alert(hello);
               
                    console.debug(a);  
               
               }  
           }); 
           
           for(var i=0;i<15;i++){
        	   
        	   if(hello[i]==0){
        			
        			//y=y+30;
        		context.beginPath();
        		context.moveTo(x,y);
        		context.lineTo(x,y+70);
        		context.lineTo(x+70,y+70);
        		context.lineTo(x+70,y);
        		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
        		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
        		//grd.addColorStop(1,"#EFD458"); //终点颜色
        		context.fillStyle="rgba(0,255,0,1)"; //以上面定义的渐变填充
        		context.fill(); //闭合形状并且以填充方式绘制出来
        		context.font = "italic 30px sans-serif";
        		context.fillText(i+1 , x+15, y-5);
        		x=x+70;    
        	   	   }
        	   else if(hello[i]==1){
        			
        			//y=y+30;
        		context.beginPath();
        		context.moveTo(x,y);
        		context.lineTo(x,y+70);
        		context.lineTo(x+70,y+70);
        		context.lineTo(x+70,y);
        		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
        		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
        		//grd.addColorStop(1,"#EFD458"); //终点颜色
        		context.fillStyle="rgba(255,0,255,1)"; //以上面定义的渐变填充
        		context.fill(); //闭合形状并且以填充方式绘制出来
        		context.font = "italic 30px sans-serif";
        		context.fillText(i+1 , x+15, y-5);
        		x=x+70;  
        		  }
        	   else if(hello[i]==2){
       			
       			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x,y);
	       		context.lineTo(x,y+70);
	       		context.lineTo(x+70,y+70);
	       		context.lineTo(x+70,y);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(255,0,0,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x+15, y-5);
	       		x=x+70;  
	       		  }
        	   else if(hello[i]==3){
       			
	       			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x,y);
	       		context.lineTo(x,y+70);
	       		context.lineTo(x+70,y+70);
	       		context.lineTo(x+70,y);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(47,79,79,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x+15, y-5);
	       		x=x+70;  
	       		  }
        	   
        }    
           
          
           for(var i=15;i<30;i++)
           {
        	   
        	   if(hello[i]==0){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x1,y1);
       		context.lineTo(x1,y1+70);
       		context.lineTo(x1+70,y1+70);
       		context.lineTo(x1+70,y1);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(0,255,0,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x1+15, y1-5);
       		x1=x1+70;    
       	   	   }
       	   else if(hello[i]==1){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x1,y1);
       		context.lineTo(x1,y1+70);
       		context.lineTo(x1+70,y1+70);
       		context.lineTo(x1+70,y1);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(255,0,255,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x1+15, y1-5);
       		x1=x1+70;  
       		  }
       	   else if(hello[i]==2){
      			
      			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x1,y1);
	       		context.lineTo(x1,y1+70);
	       		context.lineTo(x1+70,y1+70);
	       		context.lineTo(x1+70,y1);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(255,0,0,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x1+15, y1-5);
	       		x1=x1+70;  
	       		  }
       	   else if(hello[i]==3){
      			
	       			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x1,y1);
	       		context.lineTo(x1,y1+70);
	       		context.lineTo(x1+70,y1+70);
	       		context.lineTo(x1+70,y1);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(47,79,79,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x1+15, y1-5);
	       		x1=x1+70;  
	       		  }
        	   
        } 
           for(var i=30;i<45;i++){
        	   
        	   if(hello[i]==0){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x2,y2);
       		context.lineTo(x2,y2+70);
       		context.lineTo(x2+70,y2+70);
       		context.lineTo(x2+70,y2);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(0,255,0,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x2+15, y2-5);
       		x2=x2+70;    
       	   	   }
       	   else if(hello[i]==1){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x2,y2);
       		context.lineTo(x2,y2+70);
       		context.lineTo(x2+70,y2+70);
       		context.lineTo(x2+70,y2);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(255,0,255,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x2+15, y2-5);
       		x2=x2+70;  
       		  }
       	   else if(hello[i]==2){
      			
      			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x2,y2);
	       		context.lineTo(x2,y2+70);
	       		context.lineTo(x2+70,y2+70);
	       		context.lineTo(x2+70,y2);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(255,0,0,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x2+15, y2-5);
	       		x2=x2+70;  
	       		  }
       	   else if(hello[i]==3){
      			
	       			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x2,y2);
	       		context.lineTo(x2,y2+70);
	       		context.lineTo(x2+70,y2+70);
	       		context.lineTo(x2+70,y2);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(47,79,79,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x2+15, y2-5);
	       		x2=x2+70;  
	       		  }
        	   
        }   
           for(var i=45;i<60;i++){
        	   
        	   if(hello[i]==0){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x3,y3);
       		context.lineTo(x3,y3+70);
       		context.lineTo(x3+70,y3+70);
       		context.lineTo(x3+70,y3);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(0,255,0,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x3+15, y3-5);
       		x3=x3+70;    
       	   	   }
       	   else if(hello[i]==1){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x3,y3);
       		context.lineTo(x3,y3+70);
       		context.lineTo(x3+70,y3+70);
       		context.lineTo(x3+70,y3);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(255,0,255,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x3+15, y3-5);
       		x3=x3+70;  
       		  }
       	   else if(hello[i]==2){
      			
      			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x3,y3);
	       		context.lineTo(x3,y3+70);
	       		context.lineTo(x3+70,y3+70);
	       		context.lineTo(x3+70,y3);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(255,0,0,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x3+15, y3-5);
	       		x3=x3+70;  
	       		  }
       	   else if(hello[i]==3){
      			
	       			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x3,y3);
	       		context.lineTo(x3,y3+70);
	       		context.lineTo(x3+70,y3+70);
	       		context.lineTo(x3+70,y3);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(47,79,79,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x3+15, y3-5);
	       		x3=x3+70;  
	       		  }
        	   
        }
           for(var i=60;i<70;i++){
        	   
        	   if(hello[i]==0){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x4,y4);
       		context.lineTo(x4,y4+70);
       		context.lineTo(x4+70,y4+70);
       		context.lineTo(x4+70,y4);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(0,255,0,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x4+15, y4-5);
       		x4=x4+70;    
       	   	   }
       	   else if(hello[i]==1){
       			
       			//y=y+30;
       		context.beginPath();
       		context.moveTo(x4,y4);
       		context.lineTo(x4,y4+70);
       		context.lineTo(x4+70,y4+70);
       		context.lineTo(x4+70,y4);
       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
       		//grd.addColorStop(1,"#EFD458"); //终点颜色
       		context.fillStyle="rgba(255,0,255,1)"; //以上面定义的渐变填充
       		context.fill(); //闭合形状并且以填充方式绘制出来
       		context.font = "italic 30px sans-serif";
       		context.fillText(i+1 , x4+15, y4-5);
       		x4=x4+70;  
       		  }
       	   else if(hello[i]==2){
      			
      			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x4,y4);
	       		context.lineTo(x4,y4+70);
	       		context.lineTo(x4+70,y4+70);
	       		context.lineTo(x4+70,y4);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(255,0,0,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x4+15, y4-5);
	       		x4=x4+70;  
	       		  }
       	   else if(hello[i]==3){
      			
	       			//y=y+30;
	       		context.beginPath();
	       		context.moveTo(x4,y4);
	       		context.lineTo(x4,y4+70);
	       		context.lineTo(x4+70,y4+70);
	       		context.lineTo(x4+70,y4);
	       		//var grd = context.createLinearGradient(0,0,660,270);//使用渐变颜色填充,从(0,0)到(200,0) （左到右）
	       		//grd.addColorStop(0,"#4CE8B2"); //起始颜色
	       		//grd.addColorStop(1,"#EFD458"); //终点颜色
	       		context.fillStyle="rgba(47,79,79,1)"; //以上面定义的渐变填充
	       		context.fill(); //闭合形状并且以填充方式绘制出来
	       		context.font = "italic 30px sans-serif";
	       		context.fillText(i+1 , x4+15, y4-5);
	       		x4=x4+70;  
	       		  }
        	   
        }
          
           

       });  


</script>
</div>



</body>
</html>