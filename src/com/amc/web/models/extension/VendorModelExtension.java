package com.amc.web.models.extension;

import com.amc.model.models.Vendor;
import com.amc.web.models.VendorEditModel;

public class VendorModelExtension {
	public static Vendor toVendor(VendorEditModel vendorEditModel){
		Vendor ret=new Vendor();
		ret.setvendorId(vendorEditModel.getvendorId());
		ret.setvendorName(vendorEditModel.getvendorName());
		ret.setvendorAddr(vendorEditModel.getvendorAddr());
		ret.setcontactPerson(vendorEditModel.getcontactPerson());
		ret.setvendorTele(vendorEditModel.getvendorTele());
		ret.setvendorEmail(vendorEditModel.getvendorEmail());
		ret.setnote(vendorEditModel.getnote());
		return ret;
	}
}
