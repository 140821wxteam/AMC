package com.amc.service.interfaces;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.amc.dao.IOrderDao;
import com.amc.model.models.Inventory;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.web.jsonmodels.OrderdetailJson;
import com.amc.web.jsonmodels.RegionsalesJson;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IOrderService extends IEnableEntityService<Integer, Order, IOrderDao> {

	public PageList<Order> listPage(String orderId, String customerId, String status, int pageNo, int pageSize);
	
	public PageList<Orderdetail> listAllPage(String productId, String productName, int pageNo, int pageSize);//显示所有的库存状况

	public void saveOrder(Order order) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updateOrder(Order order) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Orderfigure(String orderId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

	public Orderdetail listsale(String productId);//返回某一产品的最新库存状况
	public List<OrderdetailJson> listsalebyOrderdetail(String productId,String productName,int pageNo, int pageSize, IOrderdetailService orderdetailService);

	public int getpredict(String productId) throws IOException;
    //首页的地图所需数据
	public List<RegionsalesJson> listbyregion(Date startTime,Date endTime) throws ParseException;

}