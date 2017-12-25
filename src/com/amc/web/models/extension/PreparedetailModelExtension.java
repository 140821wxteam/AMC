package com.amc.web.models.extension;

import com.amc.model.models.Preparedetail;
import com.amc.web.models.PreparedetailEditModel;

public class PreparedetailModelExtension {
	public static Preparedetail toPreparedetail(PreparedetailEditModel preparedetailEditModel){
		Preparedetail ret=new Preparedetail();
		//ret.setId(vendorEditModel.getId());
		ret.setpreparedetailId(preparedetailEditModel.getpreparedetailId());
		ret.setprepareId(preparedetailEditModel.getprepareId());
		ret.setproductId(preparedetailEditModel.getproductId());
		ret.setproductName(preparedetailEditModel.getproductName());
		ret.setfactoryId(preparedetailEditModel.getfactoryId());
		ret.setamount(preparedetailEditModel.getamount());
		ret.setsize(preparedetailEditModel.getsize());
		ret.setpreparePers(preparedetailEditModel.getpreparePers());
		ret.setstatus(preparedetailEditModel.getstatus());
		ret.setnote(preparedetailEditModel.getnote());
		return ret;
	}
	
	public static PreparedetailEditModel toPreparedetailEditModel(Preparedetail model){
		PreparedetailEditModel ret=new PreparedetailEditModel();
		//ret.setId(model.getId());
		ret.setpreparedetailId(model.getpreparedetailId());
		ret.setprepareId(model.getprepareId());
		ret.setproductId(model.getproductId());
		ret.setfactoryId(model.getfactoryId());
		ret.setproductName(model.getproductName());
		ret.setamount(model.getamount());
		ret.setsize(model.getsize());
		ret.setpreparePers(model.getpreparePers());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
