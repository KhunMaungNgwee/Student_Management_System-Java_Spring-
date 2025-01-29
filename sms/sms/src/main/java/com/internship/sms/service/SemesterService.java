package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.Semester;


public interface SemesterService {
	
	public List<Semester> getAllSemester();
	
	public Semester getSemesterById(Long id);
	
	public Semester create(Semester semester);
	
	public Semester update(Semester semester);
	
	public boolean  delete(Semester semester);

}
