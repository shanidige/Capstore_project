package com.capgemini.capstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.capstore.bean.CustomerTemporaryBean;
import com.capgemini.capstore.bean.OrderDetailsBean;
import com.capgemini.capstore.bean.OrderHistoryBean;
import com.capgemini.capstore.bean.ProductBean;
import com.capgemini.capstore.exception.FetchNullListException;
import com.capgemini.capstore.exception.UnableToUpdateException;

@Repository
public class OrderDAOImplementation implements OrderDAO {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	ProductBean productBean = new ProductBean();
	CustomerTemporaryBean customerTemporaryBean = new CustomerTemporaryBean();

	@Override
	public List<OrderHistoryBean> getOrderHistory() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<OrderHistoryBean> orderHistory = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			String jpql = "FROM OrderHistoryBean";
			Query query = entityManager.createQuery(jpql);
			orderHistory = query.getResultList();
			entityManager.close();

		} catch (Exception e) {
			throw new FetchNullListException();
		}
		return orderHistory;
	}

	public boolean cancelOrder(int orderId) {
		boolean orderCancelled = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpqlDeleteData = "delete from OrderDetailsBean where orderId=:orderId";
//		String jpqlInsertData = "insert into OrderBean where orderId=:orderId";
		try {
			Query queryDeleteData = entityManager.createQuery(jpqlDeleteData);
			queryDeleteData.setParameter("orderId", orderId);
			entityTransaction.begin();
			int result = queryDeleteData.executeUpdate();
			entityTransaction.commit();
			if (result > 0) {
				orderCancelled = true;
//		try {
//		entityTransaction = entityManager.getTransaction();
//		//Query jpqlInsertData = entityManager.createQuery(jpqlDeleteData);
//		entityTransaction.begin();
////		OrderBean orderBean =(OrderBean) entityManager.find(OrderHistoryBean.class, orderId);
////		OrderBean  orderBean
////		entityManager.persist(orderBean);
////		entityTransaction.commit();
//		} catch (Exception ex){
//			throw new UnableToAddException();
//		}
			} else {
				orderCancelled = false;
			}
		} catch (Exception e) {
//		    throw new UnableToDeleteException();
			System.err.println("Delete Exception");
		}
		return orderCancelled;
	}

	public OrderDetailsBean shippingDetils(OrderDetailsBean orderDetailsBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		double bill;
		int quantity = orderDetailsBean.getProductQuantity();

		double price = productBean.getProductPrice();
		bill = quantity * price;
		orderDetailsBean.setEmail(customerTemporaryBean.getEmail());
		orderDetailsBean.setProductId(productBean.getProductId());
		orderDetailsBean.setAddress(orderDetailsBean.getAddress());
		orderDetailsBean.setPincode(orderDetailsBean.getPincode());
		orderDetailsBean.setPhoneNumber(orderDetailsBean.getPhoneNumber());
		orderDetailsBean.setProductQuantity(quantity);
		orderDetailsBean.setTotalBill(bill);
		try {
			entityTransaction.begin();
			entityManager.persist(orderDetailsBean);
			entityTransaction.commit();
		} catch (Exception e) {
			// throw new UnableToAddException();
			System.out.println("Exception");
		}
		return orderDetailsBean;
	}

	@Override
	public List<OrderDetailsBean> getOrderList() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		List<OrderDetailsBean> listOrder = null;
		try {
			entityManager = entityManagerFactory.createEntityManager();
			String jpql = "from OrderDetailsBean";
			Query query = entityManager.createQuery(jpql);
			listOrder = query.getResultList();
			entityManager.close();
		} catch (Exception e) {
			System.out.println("Exception");
		}
		return listOrder;
	}

	@Override
	public boolean updateShippingDetails(OrderDetailsBean orderDetailsBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		OrderDetailsBean existingData = entityManager.find(OrderDetailsBean.class, orderDetailsBean.getOrderId());
		boolean updated = false;
		if (existingData != null) {

			String email = orderDetailsBean.getEmail();
			if (email != null) {
				existingData.setEmail(email);
			}

			String address = orderDetailsBean.getAddress();
			if (address != null) {
				existingData.setAddress(address);
			}

			String pinCode = orderDetailsBean.getPincode();
			if (pinCode != null) {
				existingData.setPincode(pinCode);
			}

			String phoneNumber = orderDetailsBean.getPhoneNumber();
			if (phoneNumber != null) {
				existingData.setPhoneNumber(phoneNumber);
			}
			int productQuantity = orderDetailsBean.getProductQuantity();
			if (productQuantity > 0) {
				existingData.setProductQuantity(productQuantity);
			}
			double totalBill = orderDetailsBean.getTotalBill();
			if (totalBill > 0) {
				existingData.setTotalBill(totalBill);
			}
			try {
				entityTransaction = entityManager.getTransaction();
				entityTransaction.begin();
				entityManager.persist(existingData);
				entityTransaction.commit();
				updated = true;
			} catch (Exception e) {
				throw new UnableToUpdateException();
			}
			entityManager.close();
		}
		return updated;

	}

	@Override
	public boolean removeCart(int cartId) {
		boolean cartDeleted = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpqlDeleteData = "delete from CartBean where cartId=:cartId";
		try {
			Query queryDeleteData = entityManager.createQuery(jpqlDeleteData);
			queryDeleteData.setParameter("cartId", cartId);
			entityTransaction.begin();
			int result = queryDeleteData.executeUpdate();
			entityTransaction.commit();
			if (result > 0) {
				cartDeleted = true;
			} else {
				cartDeleted = false;
			}
		} catch (Exception e) {
//		    throw new UnableToDeleteException();
			System.err.println("Delete Exception");
		}
		return cartDeleted;
	}

}
