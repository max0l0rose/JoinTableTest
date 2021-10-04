package com.company.model;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.Instant;

//@Entity // ERROR
//@Inheritance(strategy = InheritanceType.JOINED)
@Data
@MappedSuperclass
public abstract class BaseEntity //implements StringsArray
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "sequenceGen")

//	@GeneratedValue(generator = "idSequence")
//	@SequenceGenerator(//schema = "MYORASCHEMA",
//			name = "idSequence",
//			sequenceName = "MY_ORACLE_SEQ_NAME",
//			allocationSize = 1)

	//@Column(name="ID")
	long id;

	@Column(columnDefinition = "integer default 0")
	int version;

	//@Temporal(TemporalType.TIMESTAMP) // ERROR
	@Column(name = "created_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP",
			updatable = false, insertable = false)
	//@CreationTimestamp // hibernate
	//@Transient
	Instant created;

	//@Generated(GenerationTime.INSERT)
	//@Column(name = "column_name", insertable = false)
	@Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
			updatable = false, insertable = false)
	//@Temporal(TemporalType.TIMESTAMP) // ERROR
	//@UpdateTimestamp // hibernate
	Instant modified; //LocalDateTime


	public String[] toStringsArray() {
		return new String[] {String.valueOf(id), String.valueOf(created), String.valueOf(modified)};
	}

//	@Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
//	OffsetDateTime created_offsetDT;
}