package com.amc.web.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.amc.model.models.Inventory;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IInventoryService;
import com.amc.service.interfaces.IPurchaseAdviceService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.jsonmodels.InventoryJson;
import com.amc.web.models.InventoryEditModel;
import com.amc.web.models.InventorySearchModel;
import com.amc.web.models.extension.InventoryModelExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/purchase")
public class PurchaseAdviceController extends BaseController{
	@Autowired
    @Qualifier("PurchaseAdviceService")
	private IPurchaseAdviceService purchaseadviceService;
	
	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String purchaseadvice(HttpServletRequest request, Model model){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        //model.addAttribute("searchModel", searchModel);
       // model.addAttribute("productIds",productService.listproductId());
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", purchaseadviceService.listPage( pageNo, pageSize));
        return "purchase/purchaseorder";
    }

}
