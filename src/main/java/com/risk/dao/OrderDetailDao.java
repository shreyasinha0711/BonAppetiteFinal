package com.risk.dao;

import java.util.List;

import com.risk.model.OrderDetail;

public interface OrderDetailDao {
	
	OrderDetail findById(int id);
	
	List<OrderDetail> getOrderDetaiList();

	void SaveOrderDetail(OrderDetail orderDetail);
	
	void deleteOrderDetail(OrderDetail orderDetail);
}
