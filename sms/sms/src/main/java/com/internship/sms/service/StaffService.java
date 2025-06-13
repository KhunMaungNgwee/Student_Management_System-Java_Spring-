package com.internship.sms.service;

import java.util.List;


import com.internship.sms.entity.Staff;


public interface StaffService {
	
	public List<Staff> getAllStaff();
	
	public Staff getStaffById(Long id);
	
	public Staff create(Staff staff);
	
	public Staff update(Staff staff);
	
	public Staff getStaffInfoByEmail(String email);
	
	public  boolean delete(Staff staff);
}
