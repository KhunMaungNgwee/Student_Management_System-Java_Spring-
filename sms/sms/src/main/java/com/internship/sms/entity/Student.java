/**
 * 
 */
package com.internship.sms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

/**
 * Thu Soe San
 */
@Getter
@Setter
@Entity
@Table(name = "Student")
public class Student extends AbstractEntity implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5871816156506912417L;

	@Column(name = "student_name")
	private String stu_name;

	@Column(name = "Roll_No")
	private Integer stuRoll_no;

	@Column(name = "student_email")
	private String stu_email;

	@Column(name = "student_major")
	private String stu_major;

	@Column(name = "student_phone")
	private String phone_no;

	@Column(name = "student_currentAdd")
	private String stu_currAddress;

	@Column(name = "student_HomeAdd")
	private String stu_homeAdd;

	@Column(name = "student_gender")
	private String stu_gender;

	@Column(name = "student_DOB")
	private Date stu_dob;

	@Column(name = "student_Nrc")
	private String stu_nrc;

	@Column(name = "student_profile")
	private String stu_pp;

	@Column(name = "student_national")
	private String stu_national;

	@Column(name = "student_Religion")
	private String stu_religion;

	@Column(name = "student_relationStatus")
	private String stu_relationshipStat;

	@Column(name = "student_hostel")
	private String stu_hostel;

	@Column(name = "student_ferry")
	private String stu_ferry;
	
	@Transient
	private boolean select;

	@ManyToOne
	@JoinColumn(name = "academic_id")
	private AcademicYear stuAcademicYear;

	@OneToMany
	@JoinTable(name = "student_family_member", joinColumns = {
			@JoinColumn(name = "student_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "family_member_id", referencedColumnName = "id", nullable = true) })
	private List<FamilyMember> familyMembers;
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private AcademicBatch studentBatch;

}
