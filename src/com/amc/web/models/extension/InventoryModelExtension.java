package com.amc.web.models.extension;

import com.amc.model.models.Inventory;
import com.amc.model.models.Product;
import com.amc.web.models.InventoryEditModel;
import com.amc.web.models.ProductEditModel;

public class InventoryModelExtension {
	public static Inventory toInventory(InventoryEditModel inventoryEditModel){
		Inventory ret=new Inventory();
		//ret.setId(vendorEditModel.getId());
			
		ret.setproductId(inventoryEditModel.getproductId());
		ret.setproductName(inventoryEditModel.getproductName());
		ret.setinventoryLevel(inventoryEditModel.getinventoryLevel());
		ret.setcreateTime(inventoryEditModel.getcreateTime());	
		ret.setstatus(inventoryEditModel.getstatus());
		ret.setnote(inventoryEditModel.getnote());
		return ret;
	}
	
	public static InventoryEditModel toInventoryEditModel(Inventory model){
		InventoryEditModel ret=new InventoryEditModel();
		//ret.setId(model.getId());
		ret.setproductId(model.getproductId());
		ret.setproductName(model.getproductName());
		ret.setinventoryLevel(model.getinventoryLevel());		
		ret.setcreateTime(model.getcreateTime());
		ret.setstatus(model.getstatus());
		ret.setnote(model.getnote());
		
		return ret;
	}
}
