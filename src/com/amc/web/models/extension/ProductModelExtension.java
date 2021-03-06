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
		ret.setproductSpecification(productEditModel.getproductSpecification());
		ret.setproductOrigin(productEditModel.getproductOrigin());
		ret.setproductUnit(productEditModel.getproductUnit());
		ret.setsafeStock(productEditModel.getsafeStock());
		ret.setnote(productEditModel.getnote());
		ret.setimages(productEditModel.getimages());
		return ret;
	}
	
	public static ProductEditModel toProductEditModel(Product model){
		ProductEditModel ret=new ProductEditModel();
		//ret.setId(model.getId());
		ret.setproductId(model.getproductId());
		ret.setproductName(model.getproductName());
		ret.setproductType(model.getproductType());
		ret.setproductSpecification(model.getproductSpecification());
		ret.setproductOrigin(model.getproductOrigin());
		ret.setproductUnit(model.getproductUnit());
		ret.setsafeStock(model.getsafeStock());		
		ret.setnote(model.getnote());
		ret.setimages(model.getimages());
		
		return ret;
	}
}
