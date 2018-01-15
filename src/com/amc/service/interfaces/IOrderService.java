package com.amc.service.interfaces;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
<<<<<<< HEAD
=======
import java.text.ParseException;
import java.util.Date;
>>>>>>> 1584fb891c2e4c7343a38e9312bfeb1b4db25a82
import java.util.List;

import com.amc.dao.IOrderDao;
import com.amc.model.models.Inventory;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.web.jsonmodels.OrderdetailJson;
<<<<<<< HEAD
=======
import com.amc.web.jsonmodels.RegionsalesJson;
>>>>>>> 1584fb891c2e4c7343a38e9312bfeb1b4db25a82
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
<<<<<<< HEAD
	public Orderdetail listsale(String productId);//返回某一产品的最新库存状况
	public List<OrderdetailJson> listsalebyOrderdetail(String productId,String productName,int pageNo, int pageSize, IOrderdetailService orderdetailService);

	public int getpredict(String productId) throws IOException;

=======
	public Orderdetail listsale(String productId);
	public List<OrderdetailJson> listsalebyOrderdetail(String productId,String productName,int pageNo, int pageSize, IOrderdetailService orderdetailService);
	//首页的地图所需数据
	public List<RegionsalesJson> listbyregion(Date startTime,Date endTime) throws ParseException;
	
	public int getpredict(String productId);
>>>>>>> 1584fb891c2e4c7343a38e9312bfeb1b4db25a82
}