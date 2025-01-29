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
@Table(name="position")
public class Position extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7770552593282392898L;

	@Column(name="position_name")
	private String name;
	
	
}
