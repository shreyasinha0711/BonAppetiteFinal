package com.risk.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.risk.model.OrderDetail;

@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends AbstractDao<Integer, OrderDetail> implements OrderDetailDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<OrderDetail> getOrderDetaiList() {
		Criteria criteria = createEntityCriteria();
		return (List<OrderDetail>) criteria.list();
	}

	@Override
	public void SaveOrderDetail(OrderDetail orderDetail) {
		persist(orderDetail);
	}

	@Override
	public void deleteOrderDetail(OrderDetail orderDetail) {
		delete(orderDetail);
	}

	@Override
	public OrderDetail findById(int id) {
		return getByKey(id);
	}

}
