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
   <title>AMC | 采购订单管理</title>
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
         <!-- BEGIN PAGE CONTENT-->
         <div class="row">
            <div class="col-md-12">
               <!-- BEGIN SAMPLE FORM PORTLET-->   
               <div class="portlet ">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-edit"></i>${requestScope.permissionMenu.curName}</div>
                  </div>
                  <div class="portlet-body form">
                     <form:form modelAttribute="contentModel" class="form-horizontal" method="POST">
                        <div class="form-body">
                        <div class="form-group" style="display:none">
                              <label  class="col-md-2 control-label">id</label>
                              <div class="col-md-10">
                                 <form:input path="id" name="id" class="form-control" placeholder="id"/>                                 
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">订单编号</label>
                              <div class="col-md-10">
                                 <form:input path="orderId" name="orderId" class="form-control" placeholder="订单编号"/>                                 
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">订单明细编号</label>
                              <div class="col-md-10">
                                 <form:input path="orderdetailId" name="orderdetailId" class="form-control" placeholder="订单明细编号"/>                               
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品编号</label>
                              <div class="col-md-10">
                                 <!--<form:input path="productId" name="productId" class="form-control" placeholder="产品编号"/>-->
                                 <form:select path="productId" id="productId" class="form-control" onchange="getproductName()">
									<form:options items="${productIds}"/> 
                                 </form:select>                               
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品名称</label>
                              <div class="col-md-10">
                                 <form:input path="productName" name="productName" class="form-control" placeholder="产品名称"/>                               
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">订购数量</label>
                              <div class="col-md-10">
                                 <form:input path="quantity" name="quantity" id="quantity" class="form-control" placeholder="订购数量" onchange="figure()"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">单价</label>
                              <div class="col-md-10">
                                 <form:input path="unitPrice" name="unitPrice" id="unitPrice" class="form-control" placeholder="单价" onchange="figure()"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">总价</label>
                              <div class="col-md-10">
                                 <form:input path="totalPrice" name="totalPrice" id="totalPrice" class="form-control" placeholder="总价"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">备注</label>
                              <div class="col-md-10">
                                 <form:input path="note" name="note" class="form-control" placeholder="备注"/>
                              </div>
                           </div>                                     
                        </div>
                        <div class="form-actions fluid">
                            <div class="text-center">
                                 <button type="submit" class="btn btn-success">保存</button>
                                 <!--<button type="button" class="btn btn-success" onclick="javascript:history.go(-1);">返回</button>-->                            
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
   	function figure(){
 	   var quantity =parseInt(document.getElementById("quantity").value);
 	   var unitPrice =parseFloat(document.getElementById("unitPrice").value);
 	   var tp = quantity*unitPrice;
 	   document.getElementById("totalPrice").value = tp;
	 }
   	function getproductName(){
   		var productId =document.getElementById("productId").value;
   		var orderdetailId =document.getElementById("orderdetailId").value;
   		
   		$.ajax({
   			type : "post",
            contentType: "application/json",  
            url : "../purchasedetailedit/"+orderdetailId+"/getProductName/"+productId,  
            dataType : "json",
   	        success:function(result){
   	        		for (var i = 0; i < result.length; i++) { 
   	        		 { 	
   	        			$("#productName").val(result[i].productName);
   	        		 } 	
   	        }},
   	        error:function(){$("#productName").val("");}
   		});
   	}
   	  
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>