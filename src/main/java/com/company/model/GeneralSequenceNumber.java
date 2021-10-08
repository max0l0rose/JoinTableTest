package com.company.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GeneralSequenceNumber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(
//			//name = "userid",
//			columnDefinition = "bigint default 2"
//			,updatable = false, insertable = false
//	)
	private Long uId = 1L;

	private String name1 = "name1";
}
