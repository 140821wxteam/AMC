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
   <title>AMC | 产品管理</title>
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta content="width=device-width, initial-scale=1.0" name="viewport" />
   <meta content="" name="description" />
   <meta content="" name="author" />
   <meta name="MobileOptimized" content="320">
   
   <%@ include file="../shared/importCss.jsp"%>
   <%@ include file="../shared/importJs.jsp"%>
   <!-- BEGIN PAGE LEVEL STYLES -->
   <link rel="stylesheet" href="<c:url value='/css/fileinput.css'/>" type="text/css"/>
   <link href="/plugins/bootstrap-fileupload/themes/explorer-fa/theme.css" media="all" rel="stylesheet" type="text/css"/>
   
   <!-- END PAGE LEVEL STYLES -->
   <!-- BEGIN PAGE LEVEL SCRIPTS -->
   <script type="text/javascript" src="<c:url value='/js/jquery.treeLite.js?ver=10'/>"></script>
   <script type="text/javascript" src="<c:url value='/js/app.js'/>"></script> 
   <script type="text/javascript" src="<c:url value='/js/jquery-1.11.0.min.js'/>"></script>
   <script type="text/javascript" src="<c:url value='/plugins/bootstrap-fileupload/fileinput.js'/>"></script> 
   <script type="text/javascript" src="<c:url value='/plugins/bootstrap-fileupload/bootstrap-fileupload-zh.js'/>"></script>
   <script src="/plugins/bootstrap-fileupload/themes/explorer-fa/theme.js" type="text/javascript"></script>
   <script src="/plugins/bootstrap-fileupload/themes/fa/theme.js" type="text/javascript"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" type="text/javascript"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" type="text/javascript"></script>
    
   
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
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品编号</label>
                              <div class="col-md-10">
                                 <form:input path="productId" class="form-control" placeholder="产品编号"/>                                 
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品名称</label>
                              <div class="col-md-10">
                                 <form:input path="productName" class="form-control" placeholder="产品名称"/>                               
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品类型</label>
                              <div class="col-md-10">
                                 <!--<form:input path="productType" class="form-control" placeholder="产品类型"/>-->
                                 <form:select path="productType" class="form-control" placeholder="产品类型">
                                 	<option value="">请选择产品类型</option>
                                 	<option value="G">G</option>
                                 	<option value="M">M</option>
                                 	<option value="S">S</option>
                                 </form:select>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品规格</label>
                              <div class="col-md-10">
                                 <form:input path="productSpecification" class="form-control" placeholder="产品规格"/>                               
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">原厂编号</label>
                              <div class="col-md-10">
                                 <form:input path="productOrigin" class="form-control" placeholder="原厂编号"/>                               
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">计量单位</label>
                              <div class="col-md-10">
                                 <form:input path="productUnit" class="form-control" placeholder="计量单位"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">安全库存</label>
                              <div class="col-md-10">
                                 <form:input path="safeStock" class="form-control" placeholder="安全库存"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">备注</label>
                              <div class="col-md-10">
                                 <form:input path="note" class="form-control" placeholder="备注"/>
                              </div>
                           </div>
                           <div class="form-group" style="display:none">
                              <label  class="col-md-2 control-label">产品图片</label>
                              <div class="col-md-10">
                                 <form:input path="images" id="imagesname" class="form-control" placeholder="产品图片"/>
                              </div>
                           </div>
                           <div class="form-group">
                              <label  class="col-md-2 control-label">产品图片</label>
                              <div class="col-md-10">
                              <!--<form enctype="multipart/form-data">  -->
								<div class="file-loading">
									<input id="images" name="images" class="file" type="file"/>
            							<!--<input id="image" class="file" type="file">  -->
        							</div>
        						  <!--</form>  -->
        							<!--<button type="submit" class="btn btn-primary">上传</button>
        							<button type="reset" class="btn btn-default">重置</button>  -->
							</div>
                           </div>
                                                               
                        </div>
                        <div class="form-actions fluid">
                           <div class="col-md-offset-6 col-md-6">
                              <button type="submit" class="btn btn-success">保存</button>                             
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
   	$("#images").fileinput({
        language: 'zh', //设置语言
        uploadUrl:"../basedata/productadd/uploadimage", //上传的地址
       	allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadAsync: true, //默认异步上传
        showUpload:false, //是否显示上传按钮
        showRemove :false, //显示移除按钮
        showPreview :true, //是否显示预览
        showCaption:true,//是否显示标题
        showBrowse: true,
        layoutTemplates :{
            //actionDelete:'', //去除上传预览的缩略图中的删除图标
            //actionUpload:'',//去除上传预览缩略图中的上传图片；
            actionZoom:''   //去除上传预览缩略图中的查看详情预览的缩略图标。
        },
        //initialPreview:[],
        //allowedPreviewTypes:[ 'image' ],
        browseOnZoneClick: true,
        browseClass:"btn btn-primary", //按钮样式    
       	dropZoneEnabled: false,//是否显示拖拽区域
       //minImageWidth: 50, //图片的最小宽度
       //minImageHeight: 50,//图片的最小高度
       //maxImageWidth: 1000,//图片的最大宽度
       //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize:0,//单位为kb，如果为0表示不限制文件大小
       //minFileCount: 0,
        maxFileCount:5,//表示允许同时上传的最大文件个数
        previewFileType:['image'],
        enctype:'multipart/form-data',
       	validateInitialCount:true,
       	previewFileIcon: '<i class="fa fa-file"></i>',
        //allowedPreviewTypes: null, // set to empty, null or false to disable preview for all types
        previewFileIconSettings: {
            'docx': '<i class="fa fa-file-word-o text-primary"></i>',
            'xlsx': '<i class="fa fa-file-excel-o text-success"></i>',
            'pptx': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
            'jpg': '<i class="fa fa-file-photo-o text-warning"></i>',
            'pdf': '<i class="fa fa-file-pdf-o text-danger"></i>',
            'zip': '<i class="fa fa-file-archive-o text-muted"></i>',
        },
       msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
   }).on("fileuploaded", function (event, data, previewId, index){
	   var result = data.response;
	   for(var i=0;i<result.length;i++){
		   var imagename = result[i];
	   }
	   //alert(imagename);
	   var in_now = document.getElementById("imagesname").value;
	   if(in_now.length==0||in_now==null){
		   var imagenamenew = imagename;
	   }
	   else var imagenamenew = in_now+","+imagename;
	   
	   $('#imagesname').val(imagenamenew);
         
});
//   	$('#image').on('filebatchselected', function(event, files) {  
//   	  $('#image').fileinput('upload');//上传操作  
//   	});
   	$('#images').on('filesuccessremove', function(event, id) {//点击删除后立即执行  
   	  $('#images').fileinput('refresh');//文件框刷新操作
   	  $('#imagesname').val("");
   	});
  //点击删除按钮后，会跳入重置的方法
   	$('#images').on('filereset', function(event){
   		$('#images').fileinput('refresh');
   		$('#imagesname').val("");
   	});
  
   	  
   </script>
   <!-- END JAVASCRIPTS -->   
</body>
<!-- END BODY -->
</html>