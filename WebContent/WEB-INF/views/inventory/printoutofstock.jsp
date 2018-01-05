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
   <title>AMC | 订单缺件表管理</title>
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
                  AMC <small>${requestScope.permissionMenu.subName}</small>
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
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN SAMPLE FORM PORTLET-->   
               <div class="portlet ">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-edit"></i>${requestScope.permissionMenu.curName}</div>
                  </div>
                  <div class="portlet-body form">
                  <form:form modelAttribute="outofstockModel" class="form-horizontal">
                     <table class="table table-bordered">
					  <caption style="padding:10px 10px;font-size:20px">订单缺件表</caption>
					  <tr>
					  	<th>订单编号</th>
					   	<th colspan=2><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					   	<th>顾客自编号</th>
					   	<th colspan=2><form:input path="customerId" style="border: 0px; width: 160px;" readonly="true"/></th>
					   	<th>订单收到日期</th>
					  	<th colspan=1><form:input path="customerId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  </tr>
					  <tr>
					  	<th>顾客姓名</th>
					   	<th colspan=3><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					   	<th>联系地址</th>
					   	<th colspan=3><form:input path="customerId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  </tr>
					  <tr>
					  	<th>收货人</th>
					   	<th colspan=3><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					   	<th>收货地址</th>
					   	<th colspan=3><form:input path="customerId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  </tr>
					  <tr>
					  	<th>信用情况</th>
					   	<th colspan=7><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  </tr>
					  <tr>
					  	<th>订单项目数</th>
					   	<th><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  	<th>完全满足订单项目数</th>
					  	<th><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  	<th>部分满足订单项目数</th>
					   	<th><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  	<th>完全缺货项目数</th>
					  	<th><form:input path="orderId" style="border: 0px; width: 160px;" readonly="true"/></th>
					  </tr>
					  
					</table>
				</form:form>
                        
                  </div>
               </div>
               <!-- END SAMPLE FORM PORTLET-->
            </div>
         </div>
         
         <!-- BEGIN PAGE CONTENT-->
         <div class="row">
            <div class="col-md-12">
               
               
               
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
         
         
      });
   	  
   	  
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>