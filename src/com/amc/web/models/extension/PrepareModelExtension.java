package com.amc.web.models.extension;

import com.amc.model.models.Prepare;
import com.amc.web.models.PrepareEditModel;

public class PrepareModelExtension {
	public static Prepare toPrepare(PrepareEditModel prepareEditModel){
		Prepare ret=new Prepare();
		//ret.setId(vendorEditModel.getId());
		ret.setprepareId(prepareEditModel.getprepareId());
		ret.setcreateTime(prepareEditModel.getcreateTime());
		ret.setcustomerId(prepareEditModel.getcustomerId());
		ret.setorderNum(prepareEditModel.getorderNum());
		ret.setfitNum(prepareEditModel.getfitNum());
		ret.setpartfitNum(prepareEditModel.getpartfitNum());
		ret.setoutofstockNum(prepareEditModel.getoutofstockNum());
		ret.setstatus(prepareEditModel.getstatus());
		ret.setnote(prepareEditModel.getnote());
		return ret;
	}
	
	public static PrepareEditModel toPrepareEditModel(Prepare model){
		PrepareEditModel ret=new PrepareEditModel();
		//ret.setId(model.getId());
		ret.setprepareId(model.getprepareId());
		ret.setcreateTime(model.getcreateTime());
		ret.setcustomerId(model.getcustomerId());
		ret.setorderNum(model.getorderNum());
		ret.setfitNum(model.getfitNum());
		ret.setpartfitNum(model.getpartfitNum());
		ret.setoutofstockNum(model.getoutofstockNum());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
