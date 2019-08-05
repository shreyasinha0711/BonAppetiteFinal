package com.risk.service;

import java.util.List;

import com.risk.model.OrderDetail;

public interface OrderDetailService {

	List<OrderDetail> getOrderDetailList();

	void SaveOrderDetail(OrderDetail orderDetail);

	void deleteOrderDetail(OrderDetail orderDetail);

	OrderDetail findById(int id);

	void updateOrderDetail(OrderDetail orderDetail);
}
