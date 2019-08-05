package com.risk.dao;

import java.util.List;

import com.risk.model.Customer;

public interface CustomerDao {
	
	Customer findById(int id);
	
	void saveCustomer(Customer customer);
	
	Customer findCustomerByPhone(String phone);
	
	void deleteAllRows();
}
