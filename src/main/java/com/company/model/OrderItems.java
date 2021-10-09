package com.company.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
		//name = "order_product", schema = "db_first_test", catalog = ""
)
public class OrderItems
		implements Serializable
{
//	@Embedded
//	@EmbeddedId

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
	@Id
	private Product productByProductId;

	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
	@Id
	private Order order1ByOrderId;

	@Basic
	@Column(name = "quantity")
	private int quantity;


	//@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OrderItems that = (OrderItems) o;

		//Objects.equals(slug, post.slug);

		return quantity == that.quantity
				&& productByProductId.id == that.productByProductId.id
				&& order1ByOrderId.id == that.order1ByOrderId.id;
	}

	//@Override
	public int hashCode() {
		return Objects.hash(quantity);
	}
}
