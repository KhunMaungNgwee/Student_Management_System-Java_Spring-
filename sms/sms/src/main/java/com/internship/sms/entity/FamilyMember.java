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
@Table(name="FamilyMember")

public class FamilyMember extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2543362043005795781L;
	@Column(name="name")
	private String name;
	
	@Column(name="nrcNo")
	private String nrcNo;
	
	
	@Column(name="nation")
	private String nation;
	
	@Column(name="relation")
	private String relationStatus;
	
	@Column(name="religion")
	private String religion;
	
	@Column(name="occupation")
	private String occupation;
	
	@Column(name="Address")
	private String address;
	
	@Column(name="phoneNo")
	private String phoneNo;
	
	@Column(name="guardian")
	private boolean guardianStatus;

}
