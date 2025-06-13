package com.internship.sms.service;

import java.util.List;

import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Section;
import com.internship.sms.entity.Student;

public interface SectionService {

	public List<Section> getAllSection();

	public Section getSectionById(Long id);

	public Section create(Section section);

	public Section update(Section section);

	public boolean delete(Section section);
	
	public Section getSection(FilterDTO student_section);

	public List<Section> getSectionList(FilterDTO dto);
	public List<Student> getStudentListBySection(FilterDTO filter);

}
