
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
   <title>AMC | 催款单管理</title>
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
							   <div class="col-md-6">
								  <div class="form-group">
									 <label class="control-label col-md-3">催款单编号</label>
									 <div class="col-md-9">
										<form:input path="cuikuanId" class="form-control placeholder-no-fix" autocomplete="off" placeholder="催款单编号"/>
									 </div>
								  </div>
							   </div>
							   <!--/span-->
							   <div class="col-md-6">
								  <div class="form-group">
									 <label class="control-label col-md-3">顾客编号</label>
									 <div class="col-md-9">
										<form:input path="customerId" class="form-control placeholder-no-fix" autocomplete="off" placeholder="顾客编号"/>
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
		                              <th>催款单编号</th>
		                              <th>发货单编号</th>
		                              <th>催款单对象</th>
		                              <th>顾客编号</th>
		                              <th>订单编号</th>
		                              <th>订单收到日期</th>
		                              <th>订单总金额</th>
		                              <th>创建日期</th>
		                              <th>备注</th>		                             
		                              <th>是否支付状态</th>
		                           </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach items="${contentModel.items}" var="item">
							        <tr class="odd gradeX">
							        	<td class="check_cell">
									        <input type="checkbox" class="checkboxes" name="id" value="${item.id}" />
									    </td>
							            <td>${item.cuikuanId}</td>
							            <td>${item.deliverId}</td>
							            
							            <!--<td>${item.cuikuanObjection}</td>  -->
							            <c:if test="${item.cuikuanObjection eq 1}">
							            		<td>顾客</td>
							            </c:if>
							            <c:if test="${item.cuikuanObjection eq 0}">
							            		<td>本公司</td>
							            </c:if>
							            <td>${item.customerId}</td>
							            <td>${item.orderId}</td>
							            <td>${item.orderReceiveDate.getTime().toLocaleString()}</td>
							            <td>${item.amountMoney}</td>
							            <td>${item.createTime.getTime().toLocaleString()}</td>
							            <td>${item.remark}</td>	
							            <c:if test="${item.status eq '未支付'}">
							            		<td style="color:red;">${item.status}</td>
							            </c:if>
							            <c:if test="${item.status eq '已支付'}">
							            		<td style="color:green;">${item.status}</td>
							            </c:if>
							        </tr>
							        </c:forEach>
		                        </tbody>
		                     </table>
	                     </div>
	                     <c:import url = "../shared/paging.jsp">
	        				<c:param name="pageModelName" value="contentModel"/>
	        				<c:param name="urlAddress" value="/financial/cuikuan"/>
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
                 { link: true, display: "查看详细", css: "icon-zoom-in", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/financial/cuikuandetail/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
	               selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！"},
                 { splitter: true },
                 { link: true, display: "记应付账", css: "icon-wrench", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/financial/changereceivable/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                     selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！",confirm: "确认对该订单记应付账吗？"},
                 { splitter: true }, 
                 { link: true, display: "失信处理", css: "icon-wrench", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/financial/editreputation/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                     selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！",confirm: "确认对顾客进行失信处理吗？"},
                 { splitter: true }, 
                 { link: true, display: "修改支付状态", css: "icon-check", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/financial/changestatus/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                     selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！",confirm: "确认修改支付状态吗？"},
                 { splitter: true }, 
                 { link: true, display: "开发票", css: "icon-arrow-right", showIcon: true, url: "<%=UrlHelper.resolveWithReturnUrl("/financial/toinvoice/{0}", request.getAttribute("requestUrl"), request.getAttribute("requestQuery"), pageContext)%>", 
                     selector: "#data-table .checkboxes", mustSelect: "请先选择数据！", singleSelect: "该操作只支持单选！",confirm: "确认开发票吗？"},
                 { splitter: true }, 
             ]
         });
      });
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>