package com.company.services;

import com.company.model.GeneralSequenceNumber;
import com.company.model.Order;
import com.company.repo.OrdersRepo;
import org.hibernate.CacheMode;
import org.hibernate.jpa.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Component
public class OrdersService implements MyService<Order>{

	@Autowired
	EntityManager entityManager;


	@Autowired
	private OrdersRepo ordersRepo;


//	{
//		return ordersRepo.findAll();
//	}


	public Iterable<Order> getAll() {
		return ordersRepo.findAll();
	}


	public Optional<Order> show(long id) {
		return ordersRepo.findById(id);
	}



//	public Optional<Department> findByName(String name) {
//		return depsRepo.findByDepName(name);
//	}

	@Transactional
	long getNextUserId() {
//		TypedQuery<SimpleEntity> q =
//				em.createQuery("select t from SimpleEntity t", SimpleEntity.class);
//		EntityTransaction et = entityManager.getTransaction();
//		et.begin();


		Query query1 = entityManager.createNativeQuery("SELECT MAX(u_id) FROM general_sequence_number");
		BigInteger r = (BigInteger) query1.getResultList().get(0);
		r = r.add(BigInteger.ONE);

		Query query2 = entityManager.createNativeQuery("DELETE FROM general_sequence_number");
		query2.executeUpdate();

		Query query3 = entityManager.createNativeQuery("INSERT INTO general_sequence_number (u_id) VALUES (?)");
		query3.setParameter(1, r.intValue());
		query3.executeUpdate();

		//		et.commit();

		Query query = entityManager.createQuery("SELECT g.uId FROM GeneralSequenceNumber g");
		//entityManager.lock(GeneralSequenceNumber.class, LockModeType.OPTIMISTIC);
		//query.setLockMode(LockModeType.OPTIMISTIC);
		//query.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
		long res = (long) query.getResultList().get(0);
		return res;
	}

	public Order save(Order order) {
		long userId = getNextUserId();
		order.setUserId(userId);
		return ordersRepo.save(order);
	}


	public void delete(long id) {
		ordersRepo.deleteById(id);
	}


//	public List<OrdersViewModel> getView() {
//		return null;// ordersRepo.getView();
//	}

}
