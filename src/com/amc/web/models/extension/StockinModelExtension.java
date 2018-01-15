package com.amc.web.models.extension;

import com.amc.model.models.Stockin;
import com.amc.web.models.StockinEditModel;

public class StockinModelExtension {
	public static Stockin toStockin(StockinEditModel stockinEditModel){
		Stockin ret=new Stockin();
		ret.setStockinId(stockinEditModel.getStockinId());
		ret.setProductId(stockinEditModel.getProductId());
		ret.setAmount(stockinEditModel.getAmount());
		ret.setVendorId(stockinEditModel.getVendorId());
		ret.setCreateTime(stockinEditModel.getCreateTime());
		ret.setStatus(stockinEditModel.getStatus());
		ret.setNote(stockinEditModel.getNote());
		return ret;
	}
	
	public static StockinEditModel toStockinEditModel(Stockin model){
		StockinEditModel ret=new StockinEditModel();
		ret.setStockinId(model.getStockinId());
		ret.setProductId(model.getProductId());
		ret.setAmount(model.getAmount());
		ret.setVendorId(model.getVendorId());
		ret.setCreateTime(model.getCreateTime());
		ret.setStatus(model.getStatus());
		ret.setNote(model.getNote());
		
		return ret;
	}
}
