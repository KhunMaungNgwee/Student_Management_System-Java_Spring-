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
 * Thu Soe San
 */
@Getter
@Setter
@Entity
@Table(name="department")
public class Department  extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 91961684957304773L;
	
	@Column(name = "name")
	private String name;
	
	
}
