/**
 * 
 */
package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.AcademicBatch;



/**
 * 
 */
public interface AcademicBatchService {
public List<AcademicBatch> getAll();
	
	public AcademicBatch getAcademicBatchById(Long id);

	public AcademicBatch create(AcademicBatch academicBatch);
	
	public AcademicBatch update(AcademicBatch academicBatch);
	
	public boolean delete(AcademicBatch academicBatch);
	
	public AcademicBatch checkByName(String name);
}
