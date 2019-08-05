package com.risk.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.risk.model.Customer;

@Repository("customerDao")
public class CustomerDaoImpl extends AbstractDao<String, Customer> implements CustomerDao {
	
	@Override
	public void saveCustomer(Customer customer) {
		persist(customer);
	}

	/*
	 * @SuppressWarnings("unchecked") public List<Customer>
	 * findCustomerByPhone(String phone) { Query query =
	 * getSession().createQuery("from CustomerDetails where phone = :phone");
	 * query.setParameter("phone", "phone"); List<Customer> list = query.list();
	 * return list; }
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public Customer findCustomerByPhone(String phone) {

	
//	Query query = getSession().createSQLQuery("select * from BonAppetite.CustomerDetails where phone=:phone").setParameter("phone", phone);
	
	 Object uniqueResult = getSession().createQuery("from Customer c where c.phone=:phone").setParameter("phone", phone).uniqueResult();

	return (Customer) uniqueResult;
	}

	@Override
	public Customer findById(int customerId) {
		
		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("customerId", customerId));
		return (Customer)criteria.uniqueResult();
	}

	@Override
	public void deleteAllRows() {
		Query query = getSession().createSQLQuery("delete from OrderDetails");
		query.executeUpdate();
	}
	
    
			 
	
}