package com.capgemini.capstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.capgemini.capstore.bean.CommonFeedbackBean;
import com.capgemini.capstore.exception.CapstoreException;


	@Repository
	public class CustomerOperationsDaoImpl implements CustomerOperationsDao {

		@PersistenceUnit
		private EntityManagerFactory entityManagerFactory;

		@Override
		public boolean insertCommonFeedback(CommonFeedbackBean commonFeedbackBean) {
			boolean result = false;
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = entityManager.getTransaction();
			try {
				transaction.begin();
				entityManager.persist(commonFeedbackBean);
				transaction.commit();
				result = true;
			} catch (Exception e) {
				throw new CapstoreException("Something went wrong... feedback can't be updated");
			}
			return result;
		}

		@Override
		public List<CommonFeedbackBean> getCommonFeedbackList() {
			// TODO Auto-generated method stub
			return null;
		}

	}

