/**
 * 
 */
package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.Department;

/**
 * Thu Soe San
 */
public interface DepartmentService {
	
	public List<Department> getAll();
	
	public Department getDepartmentById(Long id);
	
	public Department create(Department department);
	
	public Department update(Department department);
	
	public boolean delete(Department department);

	public Department checkByName(String name);

}
