package com.amc.web.models.extension;

import com.amc.model.models.Vendor;
import com.amc.web.models.VendorEditModel;

public class VendorModelExtension {
	public static Vendor toVendor(VendorEditModel vendorEditModel){
		Vendor ret=new Vendor();
		//ret.setId(vendorEditModel.getId());
		ret.setvendorId(vendorEditModel.getvendorId());
		ret.setvendorName(vendorEditModel.getvendorName());
		ret.setvendorAddr(vendorEditModel.getvendorAddr());
		ret.setcontactPerson(vendorEditModel.getcontactPerson());
		ret.setvendorTele(vendorEditModel.getvendorTele());
		ret.setvendorEmail(vendorEditModel.getvendorEmail());
		ret.setnote(vendorEditModel.getnote());
		return ret;
	}
	
	public static VendorEditModel toVendorEditModel(Vendor model){
		VendorEditModel ret=new VendorEditModel();
		//ret.setId(model.getId());
		ret.setvendorId(model.getvendorId());
		ret.setvendorName(model.getvendorName());
		ret.setvendorAddr(model.getvendorAddr());
		ret.setcontactPerson(model.getcontactPerson());
		ret.setvendorTele(model.getvendorTele());
		ret.setvendorEmail(model.getvendorEmail());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
