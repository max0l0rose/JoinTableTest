package com.company.model;

import lombok.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.util.*;

@Entity
@EntityListeners(AuditTrailListener.class)
//@Data
@Getter
@Setter
//@EqualsAndHashCode(callSuper = true)
//@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "sequenceGen", sequenceName = "seqOrders", allocationSize = 1)
//@TableGenerator(name="DepTableGen",
//		table = "sequences",
//		pkColumnName="SEQ_NAME", // Specify the name of the column of the primary key
//		valueColumnName="SEQ_NUMBER", // Specify the name of the column that stores the last value generated
//		pkColumnValue="LICENSE_ID" // Specify the primary key column value that would be considered as a primary key generator
////		,allocationSize=1
//)
@Table(name = "orders")
public class Order extends BaseEntity
		//implements Serializable
{
	public final static String[][] headers = {{"Id", "user_id", "Status", "Created", "Modified" },};

	//@GenericGenerator(name = "native", strategy = "native")
	//@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGen2")
//	@Column(
//			//name = "userid",
//			//columnDefinition = "bigint default 1 auto_increment"
//			updatable = false, insertable = false
//	)
//	@OneToOne(
//	//		cascade = CascadeType.ALL
//	)
//	//@JoinColumn(name = "id")
//	private GeneralSequenceNumber userId = new GeneralSequenceNumber();

	//@NonNull
	private ProdStatus status = ProdStatus.IN_STOCK;


//	public Order(ProdStatus status) {
//		this.status = status;
//	}

	public Order(@NonNull ProdStatus status//, int userId4
		) {
		this.status = status;
		//this.userId4 = userId4;
	}

//	@PrePersist
//	public void ini(
//			//EntityManager entityManager // error
//	) {
////		EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("default");
////		EntityManager em = emFactory.createEntityManager();
//
//		userId = 5;
//	}
//
//	@PreUpdate
//	public void ini2() {
//		userId = 6;
//	}



	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	//@GenericGenerator(name = "native", strategy = "native")

//	@Generated(GenerationTime.INSERT)
//	@Column(name = "uid", columnDefinition = "integer default 0",
//			updatable = false, nullable = false, insertable = false)

//	//@OneToOne
//	//@MapsId("uId")
//	////@JoinColumn(name = "id", referencedColumnName = "user_id", nullable = false)
//	@Generated(GenerationTime.ALWAYS)
//	@Column(insertable = false, updatable = false)
	//@Query("SELECT next_val FROM seq_orders")
	long userId;// = new GeneralSequenceNumber();



	@OneToMany(mappedBy = "order"
			, cascade = CascadeType.ALL, orphanRemoval = true
			//fetch = FetchType.LAZY
	)
//	@JoinColumn(
//			name = "order_id"
//			, referencedColumnName = "id"
// 	)
//	@JoinColumn(name = "order_id2", referencedColumnName = "userId")

//	@JoinColumns({
//			@JoinColumn(name = "orderId2", referencedColumnName = "id"),
//			@JoinColumn(name = "userId2", referencedColumnName = "userId") })

//	@JoinTable(name = "order_items"
//			,joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}
////			,inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
//	)
	private Set<OrderItems> products = new HashSet<>();


	public void addProduct(Product product, int quantity) {
		products.add(new OrderItems(product, this, quantity));
	}


//	@ManyToMany(//mappedBy = "orders"
//			//fetch = FetchType.LAZY
//	)
//	@JoinTable(name = "order_items",
//			joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
//			inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
//	)
//	private List<Product> products;



//	public String[] toStringsArray() {
//		return new String[] {String.valueOf(id), String.valueOf(user_id), String.valueOf(status),
//				String.valueOf(created), String.valueOf(modified)};
//	}


//	@Override
//	public String toString() {
//		return "Order{"
//				       + id +
//				       ", userId=" + userId +
//				       ", status=" + status +
//				       ", created=" + created +
//				       ", modified=" + modified +
//				       '}';
//	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		Order order1 = (Order) o;
//		return id == order1.id; //&& Objects.equals(status, order1.status);
//				//&& status == order1.status;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(id);//, status);
//	}


}




//@RepositoryEventHandler
//@Component
//public class PersonEventHandler {
//
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@HandleBeforeSave
//	public void handlePersonSave(Person newPerson) {
//		entityManager.detach(newPerson);
//		Person currentPerson = personRepository.findOne(newPerson.getId());
//		if (!newPerson.getName().equals(currentPerson.getName)) {
//			//react on name change
//		}
//	}
//}

class AuditTrailListener {
	private static Log log = LogFactory.getLog(AuditTrailListener.class);

//	public AuditTrailListener(EntityManager em) {
//		this.em = em;
//	}
//
//	//@Autowired
//	EntityManager em;

	@PrePersist
	@PreUpdate
	@PreRemove
	private void beforeAnyUpdate(Order order) // 1 param!
	{
		if (order.getId() == 0) {
			log.info("[AUDIT] About to add a user");
		} else {
			log.info("[AUDIT] About to update/delete user: " + order.getId());
		}
	}

	@PostPersist
	@PostUpdate
	@PostRemove
	private void afterAnyUpdate(Order order) {
		log.info("[AUDIT] add/update/delete complete for user: " + order.getId());
	}

	@PostLoad
	private void afterLoad(Order order) {
		log.info("[AUDIT] user loaded from database: " + order.getId());
	}
}
