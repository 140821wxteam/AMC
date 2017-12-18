<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="com.infrastructure.project.common.extension.UrlHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ include file="../shared/taglib.jsp"%>

<html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
   <meta charset="utf-8" />
   <title>AMC | 库存信息</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   
   <%@ include file="../shared/importCss.jsp"%>
   <!-- BEGIN PAGE LEVEL STYLES -->
   <link rel="stylesheet" href="<c:url value='/plugins/data-tables/DT_bootstrap.css'/>" type="text/css"/>
   <!-- END PAGE LEVEL STYLES -->
   
   <%@ include file="../shared/importJs.jsp"%>
   <!-- BEGIN PAGE LEVEL SCRIPTS -->
   <script type="text/javascript" src="<c:url value='/plugins/data-tables/jquery.dataTables.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/plugins/data-tables/DT_bootstrap.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/plugins/uniform/jquery.uniform.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/jquery.toolbarlite.js?ver=10'/>"></script> 
   <script type="text/javascript" src="<c:url value='/js/app.js'/>"></script> 
   <script type="text/javascript" src="<c:url value='/js/jquery.tableManaged.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/echarts.js'/>"></script>
   
   <!-- END PAGE LEVEL SCRIPTS -->

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
                  AMC <small>库存信息</small>
               </h3>
               <ul class="page-breadcrumb breadcrumb">
                  <li>
                     <i class="icon-home"></i>
                     <a href="<c:url value='/home/index'/>">首页</a> 
                     <i class="icon-angle-right"></i>
                  </li>
                  <li>
                     <span>${requestScope.permissionMenu.rootName}</span>
                     <i class="icon-angle-right"></i>
                  </li>
                  <li><span>${requestScope.permissionMenu.subName}</span></li>
               </ul>
               <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
         </div>
         <!-- END PAGE HEADER-->
         <!-- BEGIN PAGE CONTENT-->
         <div class="row">
            <div class="col-md-12">
            
				<div class="portlet box light-grey" style="display:none">
				   <div class="portlet-title">
					  <div class="caption"><i class="icon-search"></i>数据检索</div>
				   </div>
				   <div class="portlet-body form">
					  <!-- BEGIN FORM-->
					  <form:form modelAttribute="searchModel" class="form-horizontal" method="GET">
						 <div class="form-body">
							<div class="row">
							   <div class="col-md-6">
								  <div class="form-group">
									 <label class="control-label col-md-3">产品编号</label>
									 <div class="col-md-9">
										<form:input id="productId" name="productId" path="productId" class="form-control placeholder-no-fix" autocomplete="off" placeholder="产品编号"/>
									 </div>
								  </div>
							   </div>
							   <!--/span-->
							   <div class="col-md-6">
								  <div class="form-group">
									 <label class="control-label col-md-3">产品名称</label>
									 <div class="col-md-9">
										<form:input path="productName" class="form-control placeholder-no-fix" autocomplete="off" placeholder="产品名称"/>
									 </div>
								  </div>
							   </div>
							   <!--/span-->
							</div>
						 </div>
						 <div class="form-actions">
							<div class="row">
							   <div class="col-md-12">
								  <div class="col-md-offset-5">
									 <button type="submit" class="btn btn-success">搜索</button> 
									 <button id="chartview" type="button" class="btn btn-success">统计图</button>                           
								  </div>
							   </div>
							</div>
						 </div>
					  </form:form>
					  <!-- END FORM-->                
				   </div>
				</div>
               
               <!-- BEGIN EXAMPLE TABLE PORTLET-->
               <div class="portlet box light-grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-table"></i>${requestScope.permissionMenu.curName}</div>
                  </div>
                  <div class="portlet-body">
                     <div class="table-toolbar"></div>
                     <div class="dataTables_wrapper form-inline" role="grid">
	                     <div class="table-scrollable">
		                     <table class="table table-striped table-bordered table-hover" id="data-table">
		                        <thead>
		                           <tr>
		                              <th class="table-checkbox"><input type="checkbox" class="group-checkable"/></th>
		                              <th>产品编号</th>
		                              <th >产品名称</th>
		                              <th >库存数量</th>
		                              <th >创建时间</th>
		                              <th >库存状态</th>		                             
		                              <th >备注</th>
		                           </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${contentModel.items}" var="item">
							        <tr class="odd gradeX">
							        	<td class="check_cell">
									        <input type="checkbox" class="checkboxes" name="id" value="${item.id}" />
									    </td>
							            <td>${item.productId}</td>
							            <td>${item.productName}</td>
							            <td>${item.inventoryLevel}</td>
							            <td>${item.createTime.getTime().toLocaleString()}</td>							            
							            <td>${item.status}</td>							            
							            <td>${item.note}</td>
							        </tr>
							        </c:forEach>
		                        </tbody>
		                     </table>
	                     </div>
	                     <c:import url = "../shared/paging.jsp">
	        				<c:param name="pageModelName" value="contentModel"/>
	        				<c:param name="urlAddress" value="/inventory/list"/>
	       				 </c:import>
       				 </div>
                  </div>
                  
               </div>
               
               <div id="chart" class="portlet box light-grey" style="display:none">
               	<div class="portlet-title">
               		<div class="caption"><i class="icon-signal"></i>统计图</div>
               	</div>
               	<div class="portlet-body">
					<div id="main" style="width: 1000px;height:800px;"></div>
               	</div>
               </div>
            </div>
         </div>
         <!-- END PAGE CONTENT-->    
      </div>
      <!-- END PAGE -->  
   </div>
   <!-- END CONTAINER -->
   <%@ include file="../shared/pageFooter.jsp"%>
     
  <script type="text/javascript">
   	  $(function() {   
         App.init();
         
         $("#data-table").tableManaged();
         
         $(".table-toolbar").toolbarLite({
             items: [
            	 { link: true, display: "显示统计图", css: "icon-signal", showIcon: true, click:function(){
			$("#chart").css('display','block');
             // 基于准备好的dom，初始化echarts实例
           var myChart = echarts.init(document.getElementById('main'));
       		  
           // 指定图表的配置项和数据
			myChart.setOption({
			    title: {
			        text: '库存变化情况'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    legend: {
			        data:['库存量','安全库存']
			    },
			    toolbox: {
			        show: true,
			        feature: {
			            dataZoom: {
			                yAxisIndex: 'none'
			            },
			            dataView: {readOnly: false},			            
			            restore: {},
			            saveAsImage: {}
			        }
			    },
			    xAxis:  {
			        type: 'category',
			        boundaryGap: true,
			        data: []
			    },
			    yAxis: {
			        type: 'value',
			        
			    },
			    series: [
			        {
			            name:'库存量',
			            type:'line',
			            data:[]
			          
			        },
			        {
			            name:'安全库存',
			            type:'line',
			            data:[]
			          
			        } 
			    ]
			});
					myChart.showLoading(); 
					var productId=document.getElementById("productId").value;
					
			        //通过Ajax获取数据  
			        $.ajax({  
			            type : "post",
			            contentType: "application/json",
			            async : false, //异步执行  
			            url : "../listchanging/"+productId,  
			            dataType : "json", //返回数据形式为json  
			            success : function(result) {
			                //请求成功时执行该函数内容，result即为服务器返回的json对象
			                if (result) {
			                		   
			                		   var calendar_x=[];
								   var inventorylevel_y=[];
								   var safestock_y=[];
			                		   
			                       for(var i=0;i<result.length;i++){       
			                    	   calendar_x.push(result[i].createTime);    
			                        }
			                       
			                       for(var i=0;i<result.length;i++){       
			                    	   inventorylevel_y.push(result[i].inventoryLevel);    
			                         }
			                       for(var i=0;i<result.length;i++){       
			                    	   safestock_y.push(result[i].safestock);    
			                         }
			                       
			                       myChart.hideLoading();    //隐藏加载动画
			                       myChart.setOption({        //加载数据图表
			                           xAxis: {
			                               data: calendar_x
			                           },
			                           series: [{
			                               // 根据名字对应到相应的系列
			                               name: '库存量',
			                               data: inventorylevel_y
			                           },
			                           {
			                               // 根据名字对应到相应的系列
			                               name: '安全库存',
			                               data: safestock_y
			                               
			                           }]
			                       });
			                       
			                }
			            
			           },  
			            error : function(errorMsg) {  
			                alert("请求数据失败");  
			            }  
			        });
                 myChart.setOption(option);} },
                 { splitter: true }
                 
             ]
         });

      });

   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>