/**
 * 
 */
package com.internship.sms.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@Entity
@Table(name="academicBatch")
public class AcademicBatch extends AbstractEntity implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2741439850511615677L;
	
	@Column(name = "name")
	private String name;

}
