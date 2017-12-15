package com.amc.web.models.extension;

import com.amc.model.models.Outofstock;
import com.amc.web.models.OutofstockEditModel;

public class OutofstockModelExtension {
	public static Outofstock toOutofstock(OutofstockEditModel outofstockEditModel){
		Outofstock ret=new Outofstock();
		
		ret.setoutofstockId(outofstockEditModel.getoutofstockId());
		ret.setorderId(outofstockEditModel.getorderId());
		ret.setcustomerId(outofstockEditModel.getcustomerId());
		ret.setorderNum(outofstockEditModel.getorderNum());
		ret.setfitNum(outofstockEditModel.getfitNum());
		ret.setpartfitNum(outofstockEditModel.getpartfitNum());
		ret.setoutofstockNum(outofstockEditModel.getoutofstockNum());
		ret.setcreateTime(outofstockEditModel.getcreateTime());
		ret.setstatus(outofstockEditModel.getstatus());
		ret.setnote(outofstockEditModel.getnote());
		return ret;
	}
	
	public static OutofstockEditModel toOutofstockEditModel(Outofstock model){
		OutofstockEditModel ret=new OutofstockEditModel();
		//ret.setId(model.getId());
		ret.setoutofstockId(model.getoutofstockId());
		ret.setorderId(model.getorderId());
		ret.setcustomerId(model.getcustomerId());
		ret.setorderNum(model.getorderNum());
		ret.setfitNum(model.getfitNum());
		ret.setpartfitNum(model.getpartfitNum());
		ret.setoutofstockNum(model.getoutofstockNum());
		ret.setcreateTime(model.getcreateTime());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
