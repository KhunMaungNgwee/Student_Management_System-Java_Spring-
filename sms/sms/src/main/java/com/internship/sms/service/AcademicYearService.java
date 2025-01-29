/**
 * 
 */
package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.AcademicYear;

/**
 * Thu Soe San
 */
public interface AcademicYearService {
	
	public List<AcademicYear> getAllAcademicYear();
	
	public AcademicYear getAcademicYearById(Long id);
	
	public AcademicYear create(AcademicYear year);
	
	public AcademicYear update (AcademicYear year);
	
	public boolean delete(AcademicYear year);

	public AcademicYear checkStatus();

	
}
