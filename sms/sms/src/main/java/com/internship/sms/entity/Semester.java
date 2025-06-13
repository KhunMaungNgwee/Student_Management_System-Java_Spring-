package com.internship.sms.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Thu Soe San
 */

@Getter
@Setter
@Entity
@Table(name="semester")

public class Semester extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -373313639936416956L;
	
	@Column(name="name")
	private String semesterName;
	
	@Column(name="start_date")
	private Date semStartDate;
	
	@Column (name="end_date")
	private Date semEndDate;

}
