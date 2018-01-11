<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../shared/taglib.jsp"%>

<html>

<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <title>AMC | 首页</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   
   <%@ include file="../shared/importCss.jsp"%>
   <!-- BEGIN PAGE LEVEL PLUGIN STYLES --> 
   <link href="<c:url value='/plugins/fullcalendar/fullcalendar/fullcalendar.css'/>" rel="stylesheet" type="text/css"/>
   <link rel="stylesheet" href="<c:url value='/plugins/data-tables/DT_bootstrap.css'/>" type="text/css"/>
   <!--<link rel="stylesheet" href="<c:url value='/plugins/jqvmap/jquery-jvectormap-2.0.3.css'/>" type="text/css" media="screen"/>-->
   <link href="<c:url value='/plugins/jqvmap/jqvmap/jqvmap.css'/>" media="screen" rel="stylesheet" type="text/css" />   
   <link href="<c:url value='/plugins/bootstrap-datepicker/css/datepicker.css'/>" rel="stylesheet" type="text/css" />   
   <!--<link href="<c:url value='/plugins/bootstrap/css/bootstrap.css'/>" rel="stylesheet" type="text/css" />  -->   
   
   <!-- END PAGE LEVEL PLUGIN STYLES -->
   
   <link rel="shortcut icon" href="favicon.ico" />
   
   
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
   
   <%@ include file="../shared/pageHeader.jsp"%>
   
   <div class="clearfix"></div>
   <!-- BEGIN CONTAINER -->
   <div class="page-container">
      
      <%@ include file="../shared/sidebarMenu.jsp"%>
      
      <!-- BEGIN PAGE -->
      <div class="page-content">
         
         <%@ include file="../shared/styleSet.jsp"%>
           
         <!-- BEGIN PAGE HEADER-->
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN PAGE TITLE & BREADCRUMB-->
               <h3 class="page-title">
                  AMC <small>${requestScope.permissionMenu.subName}</small>
               </h3>
               <ul class="page-breadcrumb breadcrumb">
                  <li>
                     <i class="icon-home"></i>
                     ${requestScope.permissionMenu.rootName}
                     <i class="icon-angle-right"></i>
                  </li>
                  <li>${requestScope.permissionMenu.subName}</li>
                  <li class="pull-right">
                     <div id="dashboard-report-range" class="dashboard-date-range tooltips" data-placement="top" data-original-title="Change dashboard date range">
                        <i class="icon-calendar"></i>
                        <span></span>
                        <i class="icon-angle-down"></i>
                     </div>
                  </li>
               </ul>
               <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
         </div>
         <!-- END PAGE HEADER-->
         <!-- BEGIN PAGE BODY -->
		
		
		<div class="portlet">
		<div class="portlet-title">
				<div class="caption">
					<i class="icon-bell"></i>全国销售情况一览
				</div>
				
		</div>
		
				<div class="portlet-body">
				<div class="btn-toolbar margin-bottom-10">
					    <div class="btn-group" style="height:70px">
							
							<div class="input-group input-medium date-picker input-daterange">
								
								
	    							<input id="dtBegin" class="form-control" style="font-size: 13px;" type="text" value="">
	    							<span class="input-group-addon">到</span>
	    							<input id="dtEnd" class="form-control" style="font-size: 13px;" type="text" value="">
							</div>
							
							
										
						</div>
						<div class="btn-group pull-right">
							<button type="button" class="btn btn-success" onclick="getresult()">搜索</button> 
						</div>
				</div>
				
					<div id="main" style="height:500px">					
					</div>
				</div>
				
	   </div>
		
		
					
	
         <!--END PAGE BODY -->                   
         <div class="clearfix"></div>
         <div class="row">
            
            
         </div>
         
            <%-- <label>${requestScope['javax.servlet.forward.request_uri']}</label><br/>
			<label>${pageContext.request.queryString}</label><br/>
			<label>${PageContract.DEFAULT_PAGE_SIZE}</label><br/>
			
			<label>getScheme <%=request.getScheme() %></label><br/>
			<label>getServerName <%=request.getServerName() %></label><br/>
			<label>getRequestURI <%=request.getRequestURI() %></label><br/>
			
			<label>getContextPath <%=request.getContextPath() %></label><br/>
			<label>getServletPath <%=request.getServletPath() %></label><br/>
			
			<label>getQueryString <%=request.getQueryString() %></label><br/> --%>
    
      </div>
      <!-- END PAGE -->
   </div>
   <!-- END CONTAINER -->
   <!-- BEGIN FOOTER -->
   <div class="footer">
      <div class="footer-inner">
         2017 &copy; Conquer by AMC.
      </div>
      <div class="footer-tools">
         <span class="go-top">
         <i class="icon-angle-up"></i>
         </span>
      </div>
   </div>
   <!-- END FOOTER -->
   
   <%@ include file="../shared/importJs.jsp"%>
   <!-- BEGIN PAGE LEVEL PLUGINS -->
   <script src="<c:url value='/plugins/gritter/js/jquery.gritter.js'/>" type="text/javascript"></script>
   <!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
   <script src="<c:url value='/plugins/fullcalendar/fullcalendar/fullcalendar.min.js'/>" type="text/javascript"></script>
   <script src="<c:url value='/plugins/jquery.sparkline.min.js'/>" type="text/javascript"></script>  
   <!-- END PAGE LEVEL PLUGINS -->
   <!-- BEGIN PAGE LEVEL SCRIPTS -->
   <script src="<c:url value='/js/app.js'/>" type="text/javascript"></script>
   <script src="<c:url value='/js/index.js'/>" type="text/javascript"></script> 
   <script type="text/javascript" src="<c:url value='/plugins/data-tables/jquery.dataTables.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/plugins/data-tables/DT_bootstrap.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/plugins/uniform/jquery.uniform.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/jquery.toolbarlite.js?ver=10'/>"></script> 
   <script type="text/javascript" src="<c:url value='/js/app.js'/>"></script> 
   <script type="text/javascript" src="<c:url value='/js/jquery.tableManaged.js'/>"></script>
   <!--<script type="text/javascript" src="<c:url value='/js/jquery-1.11.0.min.js'/>"></script>--> 
   
   <!--<script src="<c:url value='/plugins/jqvmap/jquery-jvectormap-2.0.3.min.js'/>" type="text/javascript"></script>-->
   <!--<script src="<c:url value='/plugins/jqvmap/jquery-jvectormap-cn-mill.js'/>" type="text/javascript"></script>-->
   <script src="<c:url value='/plugins/jqvmap/jqvmap/jquery.vmap.js'/>" type="text/javascript"></script>
   <script src="<c:url value='/plugins/jqvmap/jqvmap/maps/jquery.vmap.europe.js'/>" type="text/javascript"></script>
   <script src="http://echarts.baidu.com/build/dist/echarts-all.js"></script> 
   <script type="text/javascript" src="<c:url value='/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js'/>" ></script>
   <script type="text/javascript" src="<c:url value='/plugins/bootstrap/js/locales/bootstrap.js'/>"></script>
   
    <!-- END PAGE LEVEL SCRIPTS -->  

   <script type="text/javascript">
      jQuery(document).ready(function() {    
         App.init(); // initlayout and core plugins
         Index.init();
         Index.initCalendar(); // init index page's custom scripts
         Index.initPeityElements();
         Index.initKnowElements();
         Index.initDashboardDaterange();

      });
      
      $('#dtBegin').datepicker({format: 'yyyy-mm-dd'});
      $('#dtEnd').datepicker({format: 'yyyy-mm-dd'});
      
      
      var myChart = echarts.init(document.getElementById('main')); 
      
      var option = {
    		    title : {
    		        text: '全国销售额统计',
    		        subtext: '时间',
    		        x:'center'
    		    },
    		    tooltip : {
    		        trigger: 'item'
    		    },
    		    legend: {
    		        orient: 'vertical',
    		        x:'left',
    		        data:['地区总销售额（元）']
    		    },
    		    dataRange: {
    		        min: 0,
    		        max: 2500,
    		        x: 'left',
    		        y: 'bottom',
    		        text:['高','低'],           // 文本，默认为数值文本
    		        calculable : true
    		    },
    		    toolbox: {
    		        show: true,
    		        orient : 'vertical',
    		        x: 'right',
    		        y: 'center',
    		        feature : {
    		            mark : {show: true},
    		            dataView : {show: true, readOnly: false},
    		            restore : {show: true},
    		            saveAsImage : {show: true}
    		        }
    		    },
    		    roamController: {
    		        show: true,
    		        x: 'right',
    		        mapTypeControl: {
    		            'china': true
    		        }
    		    },
    		    series : [
    		        {
    		            name: '地区总销售额（元）',
    		            type: 'map',
    		            mapType: 'china',
    		            roam: false,
    		            itemStyle:{
    		                normal:{label:{show:true}},
    		                emphasis:{label:{show:true}}
    		            },
    		            data:[
    		                
    		            ]
    		        }
    		        
    		    ]
    		};

      // 为echarts对象加载数据 
      myChart.setOption(option); 
      
      function getresult(){
    	  //var curTime = new Date();
    	  var startTime = document.getElementById("dtBegin").value;
    	  var endTime = document.getElementById("dtEnd").value;
    	
    	  var start = new Date(Date.parse(startTime));
    	  var end = new Date(Date.parse(endTime));
	  //alert(start);
    	  if(end<=start) alert("结束日期必须大于开始日期！");
    	  else{
      //通过Ajax获取数据  
      $.ajax({  
          type : "get",
          contentType: "application/json",
          async : false, //异步执行  
          url : "../sales/regioncollect/"+start+"/"+end,
          dataType : "json", //返回数据形式为json  
          success : function(result) {
              //请求成功时执行该函数内容，result即为服务器返回的json对象
              if (result) {
            		  var json = [];
                      for (var i=0;i<result.length;i++){
                          var j = {};
                          j.name = result[i].province;
                          j.value = result[i].salesamount;
                          
                          json.push(j);
                      }
                      var dataset = JSON.stringify(json);
                      var datas = eval('('+dataset+')');
                      myChart.setOption(
                    		  {
                      		    title : {
                      		        text: '全国销售额统计',
                      		        subtext: '时间',
                      		        x:'center'
                      		    },
                      		    tooltip : {
                      		        trigger: 'item'
                      		    },
                      		    legend: {
                      		        orient: 'vertical',
                      		        x:'left',
                      		        data:['地区总销售额（元）']
                      		    },
                      		    dataRange: {
                      		        min: 0,
                      		        max: 2500,
                      		        x: 'left',
                      		        y: 'bottom',
                      		        text:['高','低'],           // 文本，默认为数值文本
                      		        calculable : true
                      		    },
                      		    toolbox: {
                      		        show: true,
                      		        orient : 'vertical',
                      		        x: 'right',
                      		        y: 'center',
                      		        feature : {
                      		            mark : {show: true},
                      		            dataView : {show: true, readOnly: false},
                      		            restore : {show: true},
                      		            saveAsImage : {show: true}
                      		        }
                      		    },
                      		    roamController: {
                      		        show: true,
                      		        x: 'right',
                      		        mapTypeControl: {
                      		            'china': true
                      		        }
                      		    },
                      		    series : [
                      		        {
                      		            name: '地区总销售额（元）',
                      		            type: 'map',
                      		            mapType: 'china',
                      		            roam: false,
                      		            itemStyle:{
                      		                normal:{label:{show:true}},
                      		                emphasis:{label:{show:true}}
                      		            },
                      		            data:datas
                      		            
                      		        }
                      		        
                      		    ]
                      		}
                    		  );
            	  
            	  }
              		   
              		   
         },  
          error : function(errorMsg) {  
              alert("请求数据失败");  
          }  
      });
    	  }
      }
  
    //  $('#maps').vectorMap({map:'cn_mill'});
   </script>
   <!-- END JAVASCRIPTS -->
</body>
</html>