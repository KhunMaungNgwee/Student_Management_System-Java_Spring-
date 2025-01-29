package com.internship.sms.entity;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "section")
public class Section extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Column(name = "name")
	private String name;

	@Column(name = "major")
	private String major;

	@Column(name = "No_of_student")
	private Integer noOfStudent;

	@ManyToOne
	@JoinColumn(name = "academicBatch_id")
	private AcademicBatch academicBatch;

	@OneToMany
	@JoinTable(name = "add_Student_toSection", joinColumns = {
			@JoinColumn(name = "Section_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "student_id", referencedColumnName = "id", nullable = true) })
	private List<Student> students;

}
