/**
 * 
 */
package com.internship.sms.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7272082479158227575L;

	@Column(name = "user_name", length = 100)
	private String userName;

	@Column(name = "password", length = 30)
	private String password;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "role", length = 8)
	private String role;
	
	@Transient
	private String userProfile;

}
