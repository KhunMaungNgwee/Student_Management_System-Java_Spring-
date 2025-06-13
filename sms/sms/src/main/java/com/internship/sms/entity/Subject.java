/**
 * 
 */
package com.internship.sms.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name="subject")

public class Subject extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7914523810039664448L;

	@Column(name="subject_name")
	private String name;
	
	@Column(name="module_no")
	private String moduleNo;
	
	@Column(name="major")
	private String major;
	
	@ManyToOne
	@JoinColumn(name = "batch_id")
	private AcademicBatch subjectBatch;
	
	@ManyToOne
	@JoinColumn(name = "sem_id")
	private Semester subjectSem;
	
	@ManyToMany
	@JoinTable(name = "subject_staff", joinColumns = {
			@JoinColumn(name = "subject_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "teacher_id", referencedColumnName = "id") })
	private List<Staff> subjectStaff;
}
