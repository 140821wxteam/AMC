package com.amc.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.amc.model.models.Product;
import com.amc.service.interfaces.IProductService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.ProductEditModel;
import com.amc.web.models.ProductSearchModel;
import com.amc.web.models.extension.ProductModelExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

import net.sf.json.JSONObject;
@Controller
@RequestMapping(value = "/basedata")
public class ProductController extends BaseController{
	@Autowired
    @Qualifier("ProductService")
	private IProductService productService;
	
	@AuthPassport
	@RequestMapping(value="/product", method = {RequestMethod.GET})
    public String product(HttpServletRequest request, Model model, ProductSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", productService.listPage(searchModel.getproductId(), searchModel.getproductName(), pageNo, pageSize));
        return "basedata/product";
    }
	
	@AuthPassport
	@RequestMapping(value = "/productadd", method = RequestMethod.GET)
	public String productadd(HttpServletRequest request, Model model){	
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new ProductEditModel());
		}
        return "basedata/productadd";	
	}
	@RequestMapping(value="/productadd", method = {RequestMethod.POST})
	public String productadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") ProductEditModel productEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, FileUploadException{
		//System.out.println(productEditModel.getproductId()+" "+productEditModel.getimages());
		String images = productEditModel.getimages();
		if (images.startsWith(",")) 
			productEditModel.setimages(images.substring(1, images.length()));
		if (images.endsWith(",")) 
			productEditModel.setimages(images.substring(0, images.length()-1));
		productService.saveProduct(ProductModelExtension.toProduct(productEditModel));
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="basedata/product";
    	return "redirect:"+returnUrl; 	
	}
	
	@AuthPassport
	@RequestMapping(value = "/productedit/{id}", method = {RequestMethod.GET})
	public String productedit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			ProductEditModel productEditModel=ProductModelExtension.toProductEditModel(productService.get(id));
			productEditModel.setId(id);
			model.addAttribute("contentModel", productEditModel);
		}

        return "basedata/productedit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/productedit/{id}", method = {RequestMethod.POST})
    public String productedit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") ProductEditModel editModel, @PathVariable(value="id") Integer id, BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return productedit(request, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Product product=ProductModelExtension.toProduct(editModel);
        product.setId(editModel.getId());
        //System.out.println(editModel.getId());
        String images = editModel.getimages();
        
		if (images.startsWith(",")) 
			product.setimages(images.substring(1, images.length()));
		if (images.endsWith(",")) 
			product.setimages(images.substring(0, images.length()-1));
		//System.out.println(images);
        productService.updateProduct(product);
        
    if(returnUrl==null)
        returnUrl="basedata/product";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/productdelete/{id}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		productService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="basedata/product";
        return "redirect:"+returnUrl;	
	}
	//上传图片
	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 9;  // 9MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 100; // 100MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 100; // 100MB
	@AuthPassport
	@RequestMapping(value = "/productadd/uploadimage")
	@ResponseBody
	public void uploadFile(HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException {
	        // 检测是否为多媒体上传
	        if (!ServletFileUpload.isMultipartContent(request)) {
	            // 如果不是则停止
	            PrintWriter writer = response.getWriter();
	            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
	            writer.flush();
	            
	        }
	        
	        // 配置上传参数
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // 设置临时存储目录
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         
	        // 设置最大文件上传值
	        upload.setFileSizeMax(MAX_FILE_SIZE);
	         
	        // 设置最大请求值 (包含文件和表单数据)
	        upload.setSizeMax(MAX_REQUEST_SIZE);

	        // 中文处理
	        upload.setHeaderEncoding("UTF-8"); 

	        // 构造临时路径来存储上传的文件
	        // 这个路径相对当前应用的目录
	        //System.out.println("path:"+request.getSession().getServletContext().getRealPath(""));
	        String uploadPath = "/Volumes/wenxin/AMC/WebContent/WEB-INF/images/" + File.separator + UPLOAD_DIRECTORY;
	       
	         
	        // 如果目录不存在则创建
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	        List<String> fileNameslist = new ArrayList<>();
	        try {
	            // 解析请求的内容提取文件数据
	            
	            List<FileItem> formItems = upload.parseRequest(request);
	            //System.out.println(formItems.size());
	            if (formItems != null && formItems.size() > 0) {
	                // 迭代表单数据
	                for (FileItem item : formItems) {
	                	//System.out.println(item.getName().toString());
	                    // 处理不在表单中的字段
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        String filePath = uploadPath + File.separator + fileName;
	                        File storeFile = new File(filePath);
	                        fileNameslist.add(fileName);
	                        // 保存文件到硬盘
	                        item.write(storeFile);
	                    }
	                }
	            }
	            
	        } catch (Exception ex) {
	        		
	        }
	        //JSONObject jsonObject = new JSONObject();    
	        //jsonObject.put("result", "ok");  
	        //response.getWriter().write(jsonObject.toString());
	        
	        ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
	        
	        String json = mapper.writeValueAsString(fileNameslist);    //将list中的对象转换为Json格式的数组
	        
	        //将json数据返回给客户端
	        response.setContentType("text/html; charset=utf-8");
	        response.getWriter().write(json);
	        
	    }
	@AuthPassport
	@RequestMapping(value = "/productedit/{id}/uploadimage2")
	@ResponseBody
	public void uploadFile1(HttpServletRequest request,
	        HttpServletResponse response,@PathVariable(value="id") Integer id) throws ServletException, IOException {
	        // 检测是否为多媒体上传
			
	        if (!ServletFileUpload.isMultipartContent(request)) {
	            // 如果不是则停止
	            PrintWriter writer = response.getWriter();
	            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
	            writer.flush();
	            
	        }
	        
	        // 配置上传参数
	        DiskFileItemFactory factory = new DiskFileItemFactory();
	        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
	        factory.setSizeThreshold(MEMORY_THRESHOLD);
	        // 设置临时存储目录
	        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
	 
	        ServletFileUpload upload = new ServletFileUpload(factory);
	         
	        // 设置最大文件上传值
	        upload.setFileSizeMax(MAX_FILE_SIZE);
	         
	        // 设置最大请求值 (包含文件和表单数据)
	        upload.setSizeMax(MAX_REQUEST_SIZE);

	        // 中文处理
	        upload.setHeaderEncoding("UTF-8"); 

	        // 构造临时路径来存储上传的文件
	        // 这个路径相对当前应用的目录
	        //System.out.println("path:"+request.getSession().getServletContext().getRealPath(""));
	        String uploadPath = "/Volumes/wenxin/AMC/WebContent/WEB-INF/images/" + File.separator + UPLOAD_DIRECTORY;
	       
	         
	        // 如果目录不存在则创建
	        File uploadDir = new File(uploadPath);
	        if (!uploadDir.exists()) {
	            uploadDir.mkdir();
	        }
	        List<String> fileNameslist = new ArrayList<>();
	        try {
	            // 解析请求的内容提取文件数据
	            
	            List<FileItem> formItems = upload.parseRequest(request);
	            //System.out.println(formItems.size());
	            if (formItems != null && formItems.size() > 0) {
	                // 迭代表单数据
	                for (FileItem item : formItems) {
	                	//System.out.println(item.getName().toString());
	                    // 处理不在表单中的字段
	                    if (!item.isFormField()) {
	                        String fileName = new File(item.getName()).getName();
	                        String filePath = uploadPath + File.separator + fileName;
	                        File storeFile = new File(filePath);
	                        fileNameslist.add(fileName);
	                        // 保存文件到硬盘
	                        item.write(storeFile);
	                    }
	                }
	            }
	            
	        } catch (Exception ex) {
	        		
	        }
	        //JSONObject jsonObject = new JSONObject();    
	        //jsonObject.put("result", "ok");  
	        //response.getWriter().write(jsonObject.toString());
	        
	        ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
	        
	        String json = mapper.writeValueAsString(fileNameslist);    //将list中的对象转换为Json格式的数组
	        
	        //将json数据返回给客户端
	        response.setContentType("text/html; charset=utf-8");
	        response.getWriter().write(json);
	        
	    }
}
