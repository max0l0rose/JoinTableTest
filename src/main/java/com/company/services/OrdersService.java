package com.company.services;

import com.company.model.GeneralSequenceNumber;
import com.company.model.Order;
import com.company.repo.GeneralSequenceNumberRepo;
import com.company.repo.OrdersRepo;
import com.sun.org.apache.xml.internal.serialize.LineSeparator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Component
@Getter
public class OrdersService implements MyService<Order>{

	//@Autowired
	@PersistenceContext
	EntityManager entityManager;


	@Autowired
	private OrdersRepo ordersRepo;

	@Autowired
	private GeneralSequenceNumberRepo genSeqNumRepo;

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


////	@Transactional(
////			//isolation = Isolation.READ_UNCOMMITTED
////			propagation = Propagation.REQUIRES_NEW
////	)
//	GeneralSequenceNumber getNextUserId() {
////		TypedQuery<SimpleEntity> q =
////				em.createQuery("select t from SimpleEntity t", SimpleEntity.class);
////		EntityTransaction et = entityManager.getTransaction();
////		et.begin();
//
//
//		Query query1 = entityManager.createQuery("SELECT g FROM GeneralSequenceNumber g");
//		GeneralSequenceNumber number = (GeneralSequenceNumber)query1.getResultList().get(0);
//		entityManager.detach(number);
//
//		Query query2 = entityManager.createQuery("DELETE FROM GeneralSequenceNumber");
//		query2.executeUpdate();
////
////////		Query query3 = entityManager.createQuery("INSERT INTO general_sequence_number (u_id) VALUES (?)");
////////		r = r.add(BigInteger.ONE);
////////		query3.setParameter(1, r.intValue());
////////		query3.executeUpdate();
////
////		GeneralSequenceNumber number2 = new GeneralSequenceNumber();
////		number2.setVal(number.getVal() + 1);
////		entityManager.persist(number2);
//		//		et.commit();
//
//		//Query query3 = entityManager.createQuery("SELECT g.uId FROM GeneralSequenceNumber g");
//		//entityManager.lock(GeneralSequenceNumber.class, LockModeType.OPTIMISTIC);
//		//query.setLockMode(LockModeType.OPTIMISTIC);
//		//query.setHint(QueryHints.HINT_CACHE_MODE, CacheMode.IGNORE);
//		//long res = (long) query3.getResultList().get(0);
//
//		return number;
//	}


	public Order save(Order order) {
		//GeneralSequenceNumber number = getNextUserId();
		List<GeneralSequenceNumber> seqList = genSeqNumRepo.findAll();
		//GeneralSequenceNumber number = seqList.size()>0 ? (GeneralSequenceNumber)seqList.get(0) : new GeneralSequenceNumber();//findFirstByOrderByVal();
		GeneralSequenceNumber number = seqList.stream().findFirst().orElseGet(() -> {
			GeneralSequenceNumber num = new GeneralSequenceNumber();
			genSeqNumRepo.save(num);
			return num;
		});//findFirstByOrderByVal();
		order.setUserId(number.getUId());
		//boolean contains = entityManager.contains(number);

		genSeqNumRepo.deleteAll();
		GeneralSequenceNumber number2 = new GeneralSequenceNumber();
		//number2.setVal(number.getVal() + 1);
		genSeqNumRepo.save(number2);

//		//GeneralSequenceNumber number3 = genSeqNumRepo.findFirstByOrderByVal(); // getNextUserId();
//		//boolean contains2 = entityManager.contains(number);

		return ordersRepo.save(order);
	}


	public void delete(long id) {
		ordersRepo.deleteById(id);
	}


//	public List<OrdersViewModel> getView() {
//		return null;// ordersRepo.getView();
//	}

}
