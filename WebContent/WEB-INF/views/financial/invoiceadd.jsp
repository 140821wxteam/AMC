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
   <title>AMC | 发票管理</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   
   <%@ include file="../shared/importCss.jsp"%>
   <%@ include file="../shared/importJs.jsp"%>
   <!-- BEGIN PAGE LEVEL SCRIPTS -->
   <script type="text/javascript" src="<c:url value='/js/jquery.treeLite.js?ver=10'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/app.js'/>"></script> 
   <script type="text/javascript" src="<c:url value='/js/jqurey-1.10.2.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/plugins/data-tables/jquery.dataTables.js'/>"></script>  
   <script type="text/javascript" src="<c:url value='/plugins/table/bootstrap-table.js'/>"></script>
   <!-- END PAGE LEVEL SCRIPTS -->
   <link rel="stylesheet" href="<c:url value='/plugins/data-tables/DT_bootstrap.css'/>" type="text/css"/>
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
                  AMC <small><c:if test="${empty requestScope.permissionMenu.subName}">销售订单管理</c:if>${requestScope.permissionMenu.subName}</small>
               </h3>
               <ul class="page-breadcrumb breadcrumb">
                  <li>
                     <i class="icon-home"></i>
                     <a href="<c:url value='/home/index'/>">首页</a> 
                     <i class="icon-angle-right"></i>
                  </li>
                  <li>
                     <span><c:if test="${empty requestScope.permissionMenu.rootName}">财务子系统</c:if>${requestScope.permissionMenu.rootName}</span>
                     <i class="icon-angle-right"></i>
                  </li>
                  <li><span><c:if test="${empty requestScope.permissionMenu.subName}">发票管理</c:if>${requestScope.permissionMenu.subName}</span></li>
               </ul>
               <!-- END PAGE TITLE & BREADCRUMB-->
            </div>
         </div>
         <!-- END PAGE HEADER-->
         <!-- BEGIN PAGE CONTENT-->
          
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN SAMPLE FORM PORTLET-->
               <div class="portlet ">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-edit"></i><c:if test="${empty requestScope.permissionMenu.curName}">增加订单</c:if>${requestScope.permissionMenu.curName}</div>
                  </div>
                  <div class="portlet-body form">
				  <form:form modelAttribute="contentModel" id="invoiceinfo" name="invoiceinfo" class="form-horizontal" method="POST">
				   	<div class="form-body">
                           <div class="form-group">
                              <label  class="col-md-2 control-label">发票编号</label>
                              <div class="col-md-10">
                                 <form:input path="invoiceId" id="invoiceId" name="invoiceId" class="form-control" placeholder="发票编号"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">订单编号</label>
                              <div class="col-md-10">
                                 <form:input path="orderId" id="orderId" name="orderId" class="form-control" placeholder="订单编号"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">总金额</label>
                              <div class="col-md-10">
                                 <form:input path="amountMoney" id="amountMoney" name="amountMoney" class="form-control" placeholder="总金额"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">备注</label>
                              <div class="col-md-10">
                                 <form:input path="remark" name="remark" class="form-control" placeholder="备注"/>
                              </div>
                           </div>       
                           <div class="form-group">
                              <label  class="col-md-2 control-label">状态</label>
                              <div class="col-md-10">
                                 <form:input path="status" name="status" class="form-control" placeholder="状态"/>
                              </div>
                           </div>  
                           <%-- <div class="form-group">
                              <label  class="col-md-2 control-label">产品编号</label>
                              <div class="col-md-10">
                                 <form:input path="productId" name="productId" class="form-control" placeholder="产品编号"/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品名称</label>
                              <div class="col-md-10">
                                 <form:input path="productName" name="productName" class="form-control" placeholder="产品名称"/>
                              </div>
                           </div>  
                           <div class="form-group">
                              <label  class="col-md-2 control-label">工厂编号</label>
                              <div class="col-md-10">
                                 <form:input path="factoryId" name="factoryId" class="form-control" placeholder="工厂编号"/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品数量</label>
                              <div class="col-md-10">
                                 <form:input path="num" name="num" class="form-control" placeholder="产品数量"/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品单价</label>
                              <div class="col-md-10">
                                 <form:input path="price" name="price" class="form-control" placeholder="产品单价"/>
                              </div>
                           </div> 
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品金额</label>
                              <div class="col-md-10">
                                 <form:input path="money" name="money" class="form-control" placeholder="产品金额"/>
                              </div>
                           </div>  --%>
                                                     
                        </div>
                        <div class="form-actions fluid">
                           <div class="col-md-offset-4 col-md-6">
                              <!--<button type="button" class="btn btn-success" onclick="Orderfigure()">刷新总金额</button>-->
                              <button type="button" class="btn btn-success" onclick="saveInvoice()">保存</button>
                              <button type="button" class="btn btn-success" onclick="javascript:history.back(-1);">返回</button>                             
                           </div>
                        	</div>
                     
                     </form:form>	
                  </div>
               </div>
               <!-- END SAMPLE FORM PORTLET-->
                     
               

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
   	 function saveInvoice(){
   		//alert(document.getElementById("orderId").value);
   		document.getElementById("invoiceinfo").submit();
 	  }

   	
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>