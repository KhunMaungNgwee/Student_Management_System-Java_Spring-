/**
 * 
 */
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
 * 
 * 
 * 
 */

@Getter
@Setter
@Entity
@Table(name="academic")
public class AcademicYear extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -106977120966857415L;
	@Column(name="academic_year_name",length=10)
	private String name;
	
	@Column(name="currentStatus")
	private boolean currentStatus;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	
	
	
	

}
