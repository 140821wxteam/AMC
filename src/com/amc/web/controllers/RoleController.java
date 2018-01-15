package com.amc.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

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
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.extension.ArrayHelper;
import com.infrastructure.project.common.extension.StringHelper;
import com.infrastructure.project.common.utilities.PageListUtil;
import com.amc.model.models.Authority;
import com.amc.model.models.Role;
import com.amc.model.models.Vendor;
import com.amc.service.interfaces.IAuthorityService;
import com.amc.service.interfaces.IRoleService;
import com.amc.service.interfaces.IVendorService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.RoleBindModel;
import com.amc.web.models.RoleEditModel;
import com.amc.web.models.RoleSearchModel;
import com.amc.web.models.TreeModel;
import com.amc.web.models.VendorEditModel;
import com.amc.web.models.extension.RoleBindModelExtension;
import com.amc.web.models.extension.RoleModelExtension;
import com.amc.web.models.extension.TreeModelExtension;
import com.amc.web.models.extension.VendorModelExtension;

@Controller
//@Scope("prototype")// 设置返回实例的方式 prototype or singleton
@RequestMapping(value = "/role")
public class RoleController extends BaseController {  
	@Autowired
    @Qualifier("RoleService")
	private IRoleService roleService;
	
	
	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String list(HttpServletRequest request, Model model, @ModelAttribute RoleSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute(searchModelName, searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", roleService.listPage(RoleModelExtension.toRoleSearch(searchModel), pageNo, pageSize));
        return "role/list";
    }
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.GET})
	public String add(HttpServletRequest request, Model model){	
		if(!model.containsAttribute("contentModel"))
		{
			RoleEditModel roleEditModel=new RoleEditModel();
			model.addAttribute("contentModel", roleEditModel);
		}
		
        return "role/edit";	  
	}
	
	@AuthPassport
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
    public String add(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") RoleEditModel editModel, BindingResult result) throws EntityOperateException, ValidatException {
        if(result.hasErrors())
            return add(request, model);
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        Role role=RoleModelExtension.toRole(editModel);role.setEnable(true);
        roleService.save(role);
        
        if(returnUrl==null)
        	returnUrl="role/list";
    	return "redirect:"+returnUrl;     	
    }
	
	@AuthPassport
	@RequestMapping(value="/bind/{id}", method = {RequestMethod.GET})
	public String bind(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, IOException{
		Role role=roleService.get(id);
		if(!role.getEnable()) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
			out.println("alert('尚未启用！');");
			out.println("history.back();");
			out.println("</script>");
		}
		List<Integer> checkedAuthorityIds = new ArrayList<Integer>();
		
		if(role.getAuthorities()!=null){	
			List<Authority> roleAuthorities=role.getAuthorities();
			for(Authority item : roleAuthorities){
				checkedAuthorityIds.add(item.getId());
			}			
		}
		
		if(!model.containsAttribute("contentModel")){
			RoleBindModel roleBindModel=RoleBindModelExtension.toRoleBindModel(role);
			Integer[] checkedAuthorityIdsArray=new Integer[checkedAuthorityIds.size()];
			checkedAuthorityIds.toArray(checkedAuthorityIdsArray);
			roleBindModel.setAuthorityIds(checkedAuthorityIdsArray);
			model.addAttribute("contentModel", roleBindModel);
		}
		
		String expanded = ServletRequestUtils.getStringParameter(request, "expanded", null);
		List<TreeModel> children=TreeModelExtension.ToTreeModels(authorityService.listChain(), null, checkedAuthorityIds, StringHelper.toIntegerList( expanded, ","));		
		List<TreeModel> treeModels=new ArrayList<TreeModel>(Arrays.asList(new TreeModel(null,null,"根节点",false,false,false,children)));
		model.addAttribute(treeDataSourceName, JSONArray .fromObject(treeModels, new JsonConfig()).toString());

		return "role/bind";
	}
	
	@AuthPassport
	@RequestMapping(value="/bind/{id}", method = {RequestMethod.POST})
	public String bind(HttpServletRequest request, HttpServletResponse response,Model model, @Valid @ModelAttribute("contentModel") RoleBindModel roleBindModel, @PathVariable(value="id") Integer id, BindingResult result) throws ValidatException, EntityOperateException, IOException{
        if(result.hasErrors())
            return bind(request, response, model, id);
        
        roleService.saveAuthorize(id, ArrayHelper.removeArrayItem(roleBindModel.getAuthorityIds(), new Integer(0)));      
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="role/list";
    	return "redirect:"+returnUrl; 
	}
	@AuthPassport
	@RequestMapping(value = "/edit/{id}", method = {RequestMethod.GET})
	public String edit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			Role role=roleService.get(id);
			RoleEditModel roleeditModel=RoleModelExtension.toRoleEditModel(role);
			model.addAttribute("contentModel", roleeditModel);
		}

        return "role/edit";	
	}
	@AuthPassport
	@RequestMapping(value = "/edit/{id}", method = {RequestMethod.POST})
    public String edit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") RoleEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return edit(request, model, id);
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Role role_init=roleService.get(id);
        List<Authority> authority = new ArrayList<>();
        authority = role_init.getAuthorities();
        //System.out.println(role_init.getAuthorities().get(1));
        Role role=RoleModelExtension.toRole(editModel);
        //role.setEnable(true);
        role.setAuthorities(authority);
        roleService.updateRole(role);
        
    if(returnUrl==null)
        returnUrl="role/list";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/roledisable/{id}", method = {RequestMethod.GET})
    public String roledisable(HttpServletRequest request, @PathVariable(value="id") Integer id) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        Role role_init=roleService.get(id);
        role_init.setEnable(false);
        roleService.updateRole(role_init);
    if(returnUrl==null)
    		returnUrl="role/list";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/roleenable/{id}", method = {RequestMethod.GET})
    public String roleenable(HttpServletRequest request, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException {
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        Role role_init=roleService.get(id);
        role_init.setEnable(true);
        roleService.updateRole(role_init);
        
    if(returnUrl==null)
        returnUrl="role/list";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/roledelete/{id}", method = {RequestMethod.GET})
	public String roledelete(HttpServletRequest request,HttpServletResponse response,  Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, IOException{		
		//Role role = roleService.get(id);

		roleService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="role/list";
        return "redirect:"+returnUrl;	
	}
}  
