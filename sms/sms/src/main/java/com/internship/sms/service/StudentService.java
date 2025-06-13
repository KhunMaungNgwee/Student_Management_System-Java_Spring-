package com.internship.sms.service;

import java.util.List;

import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Student;

public interface StudentService {

	public Student getStudentById(Long id);

	public Student create(Student student);

	public Student update(Student student);

	public boolean delete(Student student);

	public Student getStudentInfoByEmail(String email);

	public List<Student> getAll();
	public List<Student> getListbyBatch(FilterDTO filter);
    //student list in service
	public List<Student> saveStudents(List<Student> students);
	public List<Student>getStudentListBySection(FilterDTO filter);
	
	

}
