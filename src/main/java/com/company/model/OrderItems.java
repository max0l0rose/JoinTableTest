package com.company.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Table(
		//name = "order_product", schema = "db_first_test", catalog = ""
)
@IdClass(OrderProductPK.class)
public class OrderItems
		//implements Serializable
{
//	@Embedded
//	@EmbeddedId
//	OrderProductPK orderProductPK;

	public OrderItems(Product product1, Order order1, int quantity) {
		this.product = product1;
		this.order = order1;
		this.quantity = quantity;
	}

	@Column(name = "product_id", insertable = false, updatable = false)
	@Id
	private long productId;

	@Column(name = "order_id", insertable = false, updatable = false)
	@Id
	private long orderId;

	@ManyToOne // generates FIELDNAME_id fkey
	//@JoinColumn(name = "product1_id", referencedColumnName = "id", nullable = false)
	//@Id
	//@MapsId("product1")
	@PrimaryKeyJoinColumn(name = "productId", referencedColumnName = "id")
	private Product product;

	@ManyToOne
	//@JoinColumn(name = "order1_id", referencedColumnName = "id", nullable = false)
	@PrimaryKeyJoinColumn(name = "orderId", referencedColumnName = "id")
	//@Id
	//@MapsId("order1")
	private Order order;

	@Basic
	//@Column(name = "quantity")
	private int quantity;


//	//@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		OrderItems that = (OrderItems) o;
//
//		//Objects.equals(slug, post.slug);
//
//		return quantity == that.quantity
//				&& product.id == that.product.id
//				&& order.id == that.order.id;
//	}
//
//	//@Override
//	public int hashCode() {
//		return Objects.hash(quantity);
//	}
}
