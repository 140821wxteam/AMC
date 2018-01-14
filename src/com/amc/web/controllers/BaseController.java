package com.amc.web.controllers;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amc.service.interfaces.IAccountService;
import com.amc.service.interfaces.IAccountTableService;
import com.amc.service.interfaces.IAuthorityService;
import com.amc.service.interfaces.ICuikuanService;
import com.amc.service.interfaces.ICustomersService;
import com.amc.service.interfaces.IDeliverDetailService;
import com.amc.service.interfaces.IDeliverService;
import com.amc.service.interfaces.IInventoryService;
import com.amc.service.interfaces.IInvoiceService;
import com.amc.service.interfaces.IOrderService;

import com.amc.service.interfaces.IOrderdetailService;
import com.amc.service.interfaces.IOrganizationService;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.service.interfaces.IPrepareService;
import com.amc.service.interfaces.IPreparedetailService;
import com.amc.service.interfaces.IProductService;
import com.amc.service.interfaces.IPurchaseAdviceService;
import com.amc.service.interfaces.IRoleService;
import com.amc.service.interfaces.IVendorService;
import com.amc.service.services.InvoiceService;

public abstract class BaseController {  

	protected final String searchModelName="searchModel";
	protected final String treeDataSourceName="treeDataSource";
	protected final String selectDataSourceName="selectDataSource";
	
	@Autowired
    @Qualifier("RoleService")
    protected IRoleService roleService;
	
	@Autowired
    @Qualifier("AccountService")
    protected IAccountService accountService;
	
	@Autowired
    @Qualifier("AuthorityService")
	protected IAuthorityService authorityService;
	
	@Autowired
    @Qualifier("OrganizationService")
	protected IOrganizationService organizationService;
	
	@Autowired
    @Qualifier("CustomerService")
    protected ICustomersService customerService;
	
	@Autowired
    @Qualifier("VendorService")
    protected IVendorService vendorService;
	
	@Autowired
    @Qualifier("OrderdetailService")
    protected IOrderdetailService orderdetailService;
	

	@Autowired
    @Qualifier("OrderService")
    protected IOrderService orderService;
	
	@Autowired
    @Qualifier("ProductService")
    protected IProductService productService;
	
	@Autowired
    @Qualifier("InventoryService")
    protected IInventoryService inventoryService;
	
	@Autowired
    @Qualifier("OutofstockService")
    protected IOutofstockService outofstockService;
	
	@Autowired
    @Qualifier("OutofstockdetailService")
    protected IOutofstockdetailService outofstockdetailService;
	
	@Autowired
    @Qualifier("PrepareService")
    protected IPrepareService prepareService;
	
	@Autowired
    @Qualifier("PreparedetailService")
    protected IPreparedetailService preparedetailService;
	
	@Autowired
    @Qualifier("DeliverService")
    protected IDeliverService deliverService;
	
	@Autowired
    @Qualifier("DeliverDetailService")
    protected IDeliverDetailService deliverDetailService;
	
	@Autowired
    @Qualifier("CuikuanService")
    protected ICuikuanService cuikuanService;

	@Autowired
    @Qualifier("AccountTableService")
    protected IAccountTableService accountTableService;

	@Autowired
    @Qualifier("PurchaseAdviceService")
    protected IPurchaseAdviceService purchaseadviceService;
	
	@Autowired
    @Qualifier("InvoiceService")
    protected IInvoiceService invoiceService;
	
	@ExceptionHandler  
    public String exception(HttpServletRequest request, Exception e) {  
          
        request.setAttribute("exceptionMessage", e.getMessage());  
          
        // 根据不同错误转向不同页面  
        if(e instanceof SQLException) 
            return "testerror";   
        else
            return "error";  
    }  
	
}  
