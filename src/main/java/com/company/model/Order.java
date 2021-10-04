package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
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

	@NonNull
	private ProdStatus status;


//	public Order(ProdStatus status) {
//		this.status = status;
//	}

	public Order(@NonNull ProdStatus status//, int userId4
		) {
		this.status = status;
		//this.userId4 = userId4;
	}

	@Column(name = "user_qqQ")
	int userQqq;


	@OneToMany(mappedBy = "order_id"
			//fetch = FetchType.LAZY
	)
//	@JoinColumn(name = "order_id2", referencedColumnName = "id")
//	@JoinColumn(name = "order_id2", referencedColumnName = "userId")

//	@JoinColumns({
//			@JoinColumn(name = "orderId2", referencedColumnName = "id"),
//			@JoinColumn(name = "userId2", referencedColumnName = "userId") })

//	@JoinTable(name = "order_items"
//			,joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}
////			,inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")}
//	)
	private List<OrderItems> products1;


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
}


