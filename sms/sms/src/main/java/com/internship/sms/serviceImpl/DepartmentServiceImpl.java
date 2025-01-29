/**
 * 
 */
package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Department;
import com.internship.sms.repository.DepartmentRepository;
import com.internship.sms.service.DepartmentService;

/**
 * Thu Soe San
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Department getDepartmentById(Long id) {
		Optional<Department> optional = departmentRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override
	public Department create(Department department) {
		// TODO Auto-generated method stub
		return departmentRepository.save(department);
	}

	@Override
	public Department update(Department department) {
		// TODO Auto-generated method stub
		return departmentRepository.save(department);
	}

	@Override
	public boolean delete(Department department) {
		try {
			departmentRepository.delete(department);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public Department checkByName(String name) {
		// TODO Auto-generated method stub
		Department result = departmentRepository.checkByName(name);
		return result;
	}

}
