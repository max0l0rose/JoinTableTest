package com.company.model;

import java.io.Serializable;
import java.util.Objects;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Embeddable
public class OrderProductPK implements Serializable {
	//@Column(name = "product_id", insertable = false, updatable = false)
	private long product;
	//@Column(name = "order_id", insertable = false, updatable = false)
	private long order;

	//@Id
	public long getProduct() {
		return product;
	}

	public void setProduct(int product1) {
		this.product = product1;
	}

	//@Id
	public long getOrder() {
		return order;
	}

	public void setOrder(int order1) {
		this.order = order1;
	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		OrderProductPK that = (OrderProductPK) o;
//		return product == that.product && order == that.order;
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(product, order);
//	}
}
