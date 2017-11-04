package com.amc.web.models.extension;

import com.amc.model.models.Product;
import com.amc.web.models.ProductEditModel;

public class ProductModelExtension {
	public static Product toProduct(ProductEditModel productEditModel){
		Product ret=new Product();
		//ret.setId(vendorEditModel.getId());
		ret.setproductId(productEditModel.getproductId());
		ret.setproductName(productEditModel.getproductName());
		ret.setproductType(productEditModel.getproductType());
		ret.setproductUnit(productEditModel.getproductUnit());
		ret.setsafeStock(productEditModel.getsafeStock());
		ret.setnote(productEditModel.getnote());
		return ret;
	}
	
	public static ProductEditModel toProductEditModel(Product model){
		ProductEditModel ret=new ProductEditModel();
		//ret.setId(model.getId());
		ret.setproductId(model.getproductId());
		ret.setproductName(model.getproductName());
		ret.setproductType(model.getproductType());
		ret.setproductUnit(model.getproductUnit());
		ret.setsafeStock(model.getsafeStock());		
		ret.setnote(model.getnote());
		
		return ret;
	}
}
