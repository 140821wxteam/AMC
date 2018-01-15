package com.amc.web.models.extension;

import com.amc.model.models.Customers;
import com.amc.model.models.Vendor;
import com.amc.web.models.CustomersEditModel;
import com.amc.web.models.VendorEditModel;

public class CustomerModelExtension {
	public static Customers toCustomer(CustomersEditModel customerEditModel){
		Customers ret=new Customers();
		ret.setcustomerId(customerEditModel.getcustomerId());
		ret.setcustomerName(customerEditModel.getcustomerName());
		ret.setprovince(customerEditModel.getprovince());
		ret.setcustomerAddr(customerEditModel.getcustomerAddr());
		ret.setcontactPerson(customerEditModel.getcontactPerson());
		ret.setcustomerTele(customerEditModel.getcustomerTele());
		ret.setcustomerEmail(customerEditModel.getcustomerEmail());
		ret.setnote(customerEditModel.getnote());
		return ret;
	}
	
	public static CustomersEditModel toCustomerEditModel(Customers model){
		CustomersEditModel ret=new CustomersEditModel();
		//ret.setId(model.getId());
		ret.setcustomerId(model.getcustomerId());
		ret.setcustomerName(model.getcustomerName());
		ret.setprovince(model.getprovince());
		ret.setcustomerAddr(model.getcustomerAddr());
		ret.setcontactPerson(model.getcontactPerson());
		ret.setcustomerTele(model.getcustomerTele());
		ret.setcustomerEmail(model.getcustomerEmail());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
