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
         <!-- BEGIN PAGE CONTENT-->
         <div class="row">
            <div class="col-md-12">
            
				<div class="portlet box light-grey">
				   <div class="portlet-title">
					  <div class="caption"><i class="icon-search"></i>数据检索</div>
				   </div>
				   <div class="portlet-body form">
					  <!-- BEGIN FORM-->
					  <form:form modelAttribute="searchModel" class="form-horizontal" method="GET">
						 <div class="form-body">
							<div class="row">
							   <div class="col-md-4">
								  <div class="form-group">
									 <label class="control-label col-md-3">订单编号</label>
									 <div class="col-md-8">
										<form:input path="orderId" class="form-control placeholder-no-fix" autocomplete="off" placeholder="订单编号"/>
									 </div>
								  </div>
							   </div>
							   <!--/span-->
							   <div class="col-md-4">
								  <div class="form-group">
									 <label class="control-label col-md-4">供应商名称</label>
									 <div class="col-md-7">
										<form:select path="vendorName" class="form-control">
										  <form:option value="" disabled="disabled">请选择供应商</form:option>
										  <form:options items="${vendors}"/> 
                                        </form:select>
									 </div>
								  </div>
							   </div>
							   <!--/span-->
							   <div class="col-md-4">
								  <div class="form-group">
									 <label class="control-label col-md-3">订单状态</label>
									 <div class="col-md-8">
										<form:select path="status" class="form-control">
										  <form:option value="" disabled="disabled">请选择订单状态</form:option>
										  <form:option value="待审核">待审核</form:option>
                                          <form:option value="已审核待发送">已审核待发送</form:option>
                                          <form:option value="已发送待收货">已发送待收货</form:option>
                                          <form:option value="已收货">已收货</form:option>
                                        </form:select>
									 </div>
								  </div>
							   </div>
							</div>
						 </div>
						 <div class="form-actions">
							<div class="row">
							   <div class="col-md-12">
								  <div class="text-center">
									 <button type="submit" class="btn btn-success">搜索</button>                            
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
		                              <th>订单编号</th>
		                              <th>供应商名称</th>
		                              <th>总金额</th>
		                              <th>订单日期</th>
		                              <th>备注</th>		                             
		                              <th>订单状态</th>
		                           </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${contentModel.items}" var="item">
							        <tr class="odd gradeX">
							        	<td class="check_cell">
									        <input type="checkbox" class="checkboxes" name="id" value="${item.id}" />
									    </td>
							            <td>${item.orderId}</td>
							            <td>${item.vendorName}</td>
							            <td>${item.totalPrice}</td>
							            <td>${item.orderDate.toString()}</td>
							            <td>${item.note}</td>
							            <c:if test="${item.status eq '待审核'}">
							            		<td style="color:red;">${item.status}</td>
							            </c:if>
							            <c:if test="${item.status eq '已审核待发送'}">
							            		<td style="color:red;">${item.status}</td>
							            </c:if>
							            <c:if test="${item.status eq '已发送待收货'}">
							            		<td style="color:red;">${item.status}</td>
							            </c:if>
							            <c:if test="${item.status eq '已收货'}">
							            		<td style="color:green;">${item.status}</td>
							            </c:if>
							        </tr>
							        </c:forEach>
		                        </tbody>
		                     </table>
	                     </div>
	                     <c:import url = "../shared/paging.jsp">
	        				<c:param name="pageModelName" value="contentModel"/>
	        				<c:param name="urlAddress" value="/purchase/purchaseorder"/>
	       				 </c:import>
       				 </div>
                  </div>
               </div>
               <!-- END EXAMPLE TABLE PORTLET-->
               
               <!-- BEGIN EXAMPLE TABLE PORTLET-->
               <div class="portlet box light-grey">
                  <div class="portlet-title">
                     <div class="caption"><i class="icon-table"></i>采购建议</div>
                  </div>
                  <div class="portlet-body">
                     <!--<div class="table-toolbar"></div>  -->
                     <div class="dataTables_wrapper form-inline" role="grid">
	                     <div class="table-scrollable">
		                     <table class="table table-striped table-bordered table-hover" id="data-table1">
		                        <thead>
		                           <tr>
		                              <th class="table-checkbox"><input type="checkbox" class="group-checkable"/></th>
		                              <th>产品编号</th>
		                              <th>产品名称</th>
		                              <th>库存水平</th>
		                              <th>需求量</th>
		                              <th>建议订货量</th>		                             
		                              
		                           </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${adviceModel.items}" var="item">
							        <tr class="odd gradeX">
							        	<td class="check_cell">
									        <input type="checkbox" class="checkboxes" name="id" value="${item.id}" />
									    </td>
							            <td>${item.productId}</td>
							            <td>${item.productName}</td>
							            <td>${item.inventoryLevel}</td>
							            <td>${item.demand}</td>
							            <td>${item.advice}</td>
							            
							        </tr>
							        </c:forEach>
		                        </tbody>
		                     </table>
	                     </div>
	                     <c:import url = "../shared/paging.jsp">
	        				<c:param name="pageModelName" value="adviceModel"/>
	        				<c:param name="urlAddress" value="/purchase/purchaseorder"/>
	       				 </c:import>
       				 </div>
                  </div>
               </div>
               <!-- END EXAMPLE TABLE PORTLET-->
               
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
            	 { link: true, display: "新建", css: "icon-plus", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/purchase/purchaseorderadd", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>" },
                 { splitter: true }, 
                 { link: true, display: "编辑", css: "icon-edit", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/purchase/purchaseorderedit/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                   	selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！"},
                 { splitter: true },
                 { link: true, display: "查看", css: "icon-zoom-in", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/purchase/purchasedetailview/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                    selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！"},
                  { splitter: true },
                 { link: true, display: "删除", css: "icon-trash", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/purchase/purchaseorderdelete/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                   	selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", confirm: "确认删除所选数据吗？", singleSelect: "该操作只支持单选！"},
                 { link: true, display: "审核通过", css: "icon-check", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/purchase/orderconfirm/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                    selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", confirm: "确认审核通过所选订单吗？", singleSelect: "该操作只支持单选！"},
                 { link: true, display: "发送订单", css: "icon-arrow-right", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/purchase/ordersend/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                    selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", confirm: "确认发送所选订单吗？", singleSelect: "该操作只支持单选！"},
                 { link: true, display: "到货确认，生成进货单", css: "icon-check", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/purchase/productreceived/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                    selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", confirm: "确认所选订单已到货,并生成进货单吗？", singleSelect: "该操作只支持单选！"}
             ]
         });
      });
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>