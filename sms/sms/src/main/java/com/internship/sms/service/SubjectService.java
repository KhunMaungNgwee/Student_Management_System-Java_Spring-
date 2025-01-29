/**
 * 
 */
package com.internship.sms.service;

import java.util.List;

import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Subject;

/**
 * 
 */
public interface SubjectService {

	public List<Subject> getAll();
	
	public Subject getSubjectById(Long id);
	
	public Subject create(Subject subject);
	
	public Subject update(Subject subject);
	
	public boolean delete(Subject subject);
	
	public List<Subject> getSubByBatch(FilterDTO dto);
	
	public List <Subject> getSubTeachedBy(FilterDTO filter);
}
	
