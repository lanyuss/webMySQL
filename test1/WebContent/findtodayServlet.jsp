<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
    <%
//设置5秒定时刷新
  // 设置每隔3秒自动刷新
   response.setIntHeader("Refresh", 10);
   // 获取当前时间
  
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


<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"> </div>  

<script type="text/javascript">  
$(document).ready(function() {  
           $.ajax({  
               url: "findtodayServlet",  
               type: "GET",  
               success: function(data){  
            	 
                console.debug(data)  
                
                var a = eval('(' + data + ')');  
                
                  console.debug(a);  
                  

                 
                    
                  var chart = new Highcharts.Chart({  
                      chart: {  
                          renderTo: 'container',
                          type:'column',
                      }, 
                      credits :{
                          enabled:false//不显示highCharts版权信息
                    },
                    
                    exporting:{
                        enabled:true,//默认为可用，当设置为false时，图表的打印及导出功能失效
                        buttons:{    //配置按钮选项
                            printButton:{    //配置打印按钮
                                width:50,
                                symbolSize:20,
                                borderWidth:2,
                                borderRadius:0,
                                hoverBorderColor:'red',
                                height:30,
                                symbolX:25,
                                symbolY:15,
                                x:-200,
                                y:20
                            },
                            exportButton:{    //配置导出按钮
                                width:50,
                                symbolSize:20,
                                borderWidth:2,
                                borderRadius:0,
                                hoverBorderColor:'red',
                                height:30,
                                symbolX:25,
                                symbolY:15,
                                x:-150,
                                y:20
                            },
                        },
                        filename:'firstDay',//导出的文件名
                        type:'image/png',//导出的文件类型
                        width:800    //导出的文件宽度
                      },                   
                      
                      xAxis: {  
                    	  
                          categories: ['正常','异常','故障','未连接']
                      },  
                      title : {  
                      text : '今日传感器工作统计'  
                      },  
                   
                    yAxis:{
                        title:{
                      	  text:'次数'
                        }
                    }, 
                      series: [{  
                    	  name:'工作情况',
                          data:[a.normalcount,a.abnormalcount,a.faultcount,a.disconnectcount]
                      }]  
                  });  
                    
               }  
           });  
       });  
</script>  
</body>  

</html>  
