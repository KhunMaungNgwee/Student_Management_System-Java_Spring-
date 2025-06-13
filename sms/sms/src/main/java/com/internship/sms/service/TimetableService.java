package com.internship.sms.service;

import java.util.List;

import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Timetable;

public interface TimetableService {

	public List<Timetable> getAll();

	public Timetable getById(Long id);

	public List<Timetable> createList(List<Timetable> timetable);
	
	public Timetable create(Timetable timetable);

	public Timetable update(Timetable timetable);

	public boolean delete(Timetable timetable);

	public List<Timetable> getListBySection(FilterDTO filter);
	//retrieve section list By respective subjects
	public List <Timetable> getSectionListBySubject(FilterDTO filter);
	
}
