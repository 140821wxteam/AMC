package com.amc.web.models.extension;

import com.amc.model.models.Outofstockdetail;
import com.amc.web.models.OutofstockdetailEditModel;

public class OutofstockdetailModelExtension {
	public static Outofstockdetail toOutofstockdetail(OutofstockdetailEditModel outofstockdetailEditModel){
		Outofstockdetail ret=new Outofstockdetail();
		
		ret.setoutofstockdetailId(outofstockdetailEditModel.getoutofstockdetailId());
		ret.setoutofstockId(outofstockdetailEditModel.getoutofstockId());
		ret.setproductId(outofstockdetailEditModel.getproductId());
		ret.setquantityDemand(outofstockdetailEditModel.getquantityDemand());
		ret.setquantitySupplied(outofstockdetailEditModel.getquantitySupplied());
		ret.setquantityNeeded(outofstockdetailEditModel.getquantityNeeded());
		ret.setoperatorName(outofstockdetailEditModel.getoperatorName());
		ret.setoperateTime(outofstockdetailEditModel.getoperateTime());
		ret.setstatus(outofstockdetailEditModel.getstatus());
		ret.setnote(outofstockdetailEditModel.getnote());
		return ret;
	}
	
	public static OutofstockdetailEditModel toOutofstockdetailEditModel(Outofstockdetail model){
		OutofstockdetailEditModel ret=new OutofstockdetailEditModel();
		//ret.setId(model.getId());
		ret.setoutofstockdetailId(model.getoutofstockdetailId());
		ret.setoutofstockId(model.getoutofstockId());
		ret.setproductId(model.getproductId());
		ret.setquantityDemand(model.getquantityDemand());
		ret.setquantitySupplied(model.getquantitySupplied());
		ret.setquantityNeeded(model.getquantityNeeded());
		ret.setoperatorName(model.getoperatorName());
		ret.setoperateTime(model.getoperateTime());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
