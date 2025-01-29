package com.internship.sms.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "timetable")
public class Timetable extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "schedule_id")
	private Long scheduleTime;

	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Subject subject;
//	@ManyToOne
//	@JoinColumn(name = "teacher_id")
//	private Staff teacher;

	@ManyToOne
	@JoinColumn(name = "year_id")
	private AcademicYear academicYear;

	@ManyToOne
	@JoinColumn(name = "section_id")
	private Section section;

	@Column
	private Long semesterId;
//	@OneToMany
//	@JoinTable(name = "timetable_section", joinColumns = {
//			@JoinColumn(name = "timetable_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
//					@JoinColumn(name = "section_id", referencedColumnName = "id", nullable = true) })
//	private List<Section> sections;

}
