
package com.internship.sms.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Thu Soe San
 */
@Getter
@Setter
@Entity
@Table(name = "staff")

public class Staff extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3194557301464095956L;
	/**
	 * 
	 */

	@Column(name = "name", length = 50)
	private String staffName;

	@Column(name = "phoneNo", length = 50)
	private String staffPhoneNo;

	@Column(name = "address", length = 50)
	private String staffAddress;

	@Column(name = "email", length = 50)
	private String staffEmail;

	@Column(name = "gender", length = 20)
	private String staffGender;

	@Column(name = "nrc_no")
	private String staffNrcNo;

	@Column(name = "profile_picture")
	private String staffProfilePicture;

//	@ManyToMany
//	@JoinTable(name = "staff_position", joinColumns = {
//			@JoinColumn(name = "staff_id", referencedColumnName = "id") }, inverseJoinColumns = {
//					@JoinColumn(name = "position_id", referencedColumnName = "id") })
//	private List<Position> staffPosition;
	
	 @ManyToOne
	 @JoinColumn(name = "position_id") private Position staffPosition;
	 
	 @ManyToOne
	 @JoinColumn(name = "department_id") private Department staffDepartment;

}
