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
   <title>AMC | 备货单管理</title>
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
				  <form:form modelAttribute="contentModel" id="prepareinfo" name="prepareinfo" class="form-horizontal" method="POST">
				   	<div class="form-body">
                           <div class="form-group">
                              <label  class="col-md-2 control-label">备货单编号</label>
                              <div class="col-md-10">
                                 <form:input path="prepareId" id="prepareId" name="prepareId" class="form-control" placeholder="备货单编号"/>
                              </div>
                           </div>
                 <!--          <div class="form-group">
                              <label  class="col-md-2 control-label">订单编号</label>
                              <div class="col-md-10">
                                 <form:input path="orderId" id="orderId" name="orderId" class="form-control" placeholder="订单编号"/>
                              </div>
                           </div>  -->
                           <div class="form-group">
                              <label  class="col-md-2 control-label">顾客编号</label>
                              <div class="col-md-10">
                                 <form:input path="customerId" name="customerId" class="form-control" placeholder="顾客编号"/>
                                                            
                              </div>
                           </div> 
                           
                           <div class="form-group">
                              <label  class="col-md-2 control-label">备注</label>
                              <div class="col-md-10">
                                 <form:input path="note" name="note" class="form-control" placeholder="备注"/>
                                 
                              </div>
                           
                           </div>                                      
                        </div>
                     
                    <div class="form-group">
                              <label  class="col-md-2 control-label">收货人</label>
                              <div class="col-md-10">
                                 <form:input path="customerId" name="customerId" class="form-control" placeholder="收货人"/>
                                                            
                              </div>
                           </div> 
                            <div class="form-group">
                              <label  class="col-md-2 control-label">收货地址</label>
                              <div class="col-md-10">
                                 <form:input path="customerId" name="customerId" class="form-control" placeholder="收货地址"/>
                                                            
                              </div>
                           </div> 
                            <div class="form-group">
                              <label  class="col-md-2 control-label">信用情况</label>
                              <div class="col-md-10">
                                 <form:input path="credit" name="credit" class="form-control" placeholder="信用情况"/>
                                                            
                              </div>
                           </div>
                            <div class="form-group">
                              <label  class="col-md-2 control-label">订单项目数</label>
                              <div class="col-md-10">
                                 <form:input path="orderNum" name="orderNum" class="form-control" placeholder="订单项目数"/>
                                                            
                              </div>
                           </div> 
                            <div class="form-group">
                              <label  class="col-md-2 control-label">完全满足项目数</label>
                              <div class="col-md-10">
                                 <form:input path="fitNum" name="fitNum" class="form-control" placeholder="完全满足项目数"/>
                                                            
                              </div>
                           </div>  
                            <div class="form-group">
                              <label  class="col-md-2 control-label">部分满足项目数</label>
                              <div class="col-md-10">
                                 <form:input path="partfitNum" name="partfitNum" class="form-control" placeholder="部分满足项目数"/>
                                                            
                              </div>
                           </div> 
                            <div class="form-group">
                              <label  class="col-md-2 control-label">完全缺货项目数</label>
                              <div class="col-md-10">
                                 <form:input path="outofstockNum" name="outofstockNum" class="form-control" placeholder="完全缺货项目数"/>
                                                            
                              </div>
                           </div> 
                        <div class="form-actions fluid">
                           <div class="col-md-offset-4 col-md-6">
                             <!-- <button type="button" class="btn btn-success" onclick="Preparefigure()">刷新总金额</button> --> 
                              <button type="button" class="btn btn-success" onclick="Preparedetail()">增加明细</button>
                              <button type="button" class="btn btn-success" onclick="savePrepare()">保存</button>
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
   	  function Preparedetail(){
   		  var prepareId = document.getElementById("prepareId").value;
   		  window.location.href = '../inventory/preparedetail/'+prepareId;
   	  }
   	function savePrepare(){
   		document.getElementById("prepareinfo").submit();
 	  }
   	  //function Preparefigure(){
   	//	var prepareId = document.getElementById("prepareId").value;
   	//	window.location.href = '../sales/preparefigure/'+prepareId; 
   	//  }
   	
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>