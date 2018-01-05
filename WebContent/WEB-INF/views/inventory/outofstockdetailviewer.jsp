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
                              <label  class="col-md-2 control-label">订单缺件表编号</label>
                              <div class="col-md-10">
                                 <form:input path="outofstockId" name="outofstockId" class="form-control" readonly="true" placeholder="订单缺件表编号"/>                                 
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">订单缺件表明细编号</label>
                              <div class="col-md-10">
                                 <form:input path="outofstockdetailId" name="outofstockdetailId" class="form-control" readonly="true" placeholder="订单缺件表明细编号"/>                               
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品编号</label>
                              <div class="col-md-10">
                                 <form:input path="productId" name="productId" class="form-control" readonly="true" placeholder="产品编号"/>                               
                              </div>
                           </div>
                           
                           <div class="form-group">
                              <label  class="col-md-2 control-label">需求数量</label>
                              <div class="col-md-10">
                                 <form:input path="quantityDemand" name="quantityDemand" class="form-control" readonly="true" placeholder="需求数量" onchange="figure()"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">已满足数量</label>
                              <div class="col-md-10">
                                 <form:input path="quantitySupplied" name="quantitySupplied" class="form-control" readonly="true" placeholder="已满足数量"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">尚缺数量</label>
                              <div class="col-md-10">
                                 <form:input path="quantityNeeded" name="quantityNeeded" class="form-control" readonly="true" placeholder="尚缺数量"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">操作员姓名</label>
                              <div class="col-md-10">
                                 <form:input path="operatorName" name="operatorName" class="form-control" readonly="true" placeholder="操作员姓名"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">操作时间</label>
                              <div class="col-md-10">
                                 <form:input path="operateTime" name="operateTime" value="${contentModel.operateTime.getTime().toLocaleString()}" class="form-control" readonly="true" placeholder="操作时间"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">备注</label>
                              <div class="col-md-10">
                                 <form:input path="note" name="note" class="form-control" readonly="true" placeholder="备注"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">订单缺件表明细状态</label>
                              <div class="col-md-10">
                                 <form:input path="status" name="status" class="form-control" readonly="true" placeholder="状态"/>
                              </div>
                           </div>                                      
                        </div>
                        <div class="form-actions fluid">
                           <div class="col-md-offset-6 col-md-6">
                              
                              <button type="button" class="btn btn-success" onclick="javascript:history.go(-1);">返回</button>                             
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
   	
   	  
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>