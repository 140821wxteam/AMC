package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IOrderDao;
import com.amc.model.models.Order;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IOrderService extends IEnableEntityService<Integer, Order, IOrderDao> {

	public PageList<Order> listPage(String orderId, String customerId, String status, int pageNo, int pageSize);
	
	public void saveOrder(Order order) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updateOrder(Order order) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Orderfigure(String orderId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}