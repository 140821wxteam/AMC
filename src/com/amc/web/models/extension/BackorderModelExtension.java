package com.amc.web.models.extension;

import com.amc.model.models.Order;
import com.amc.web.models.OrderEditModel;

public class BackorderModelExtension {
	public static Order toOrder(OrderEditModel orderEditModel){
		Order ret=new Order();
		//ret.setId(vendorEditModel.getId());
		ret.setorderId(orderEditModel.getorderId());
		ret.setcreateTime(orderEditModel.getcreateTime());
		ret.setcustomerId(orderEditModel.getcustomerId());
		ret.settotalPrice(orderEditModel.gettotalPrice());
		ret.setstatus(orderEditModel.getstatus());
		ret.setnote(orderEditModel.getnote());
		return ret;
	}
	
	public static OrderEditModel toOrderEditModel(Order model){
		OrderEditModel ret=new OrderEditModel();
		//ret.setId(model.getId());
		ret.setorderId(model.getorderId());
		ret.setcreateTime(model.getcreateTime());
		ret.setcustomerId(model.getcustomerId());
		ret.settotalPrice(model.gettotalPrice());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
