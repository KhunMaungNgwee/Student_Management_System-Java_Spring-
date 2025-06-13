package com.internship.sms.service;

import java.util.List;
import com.internship.sms.entity.Schedule;

public interface ScheduleService {

	public List<Schedule> getAll();

	public Schedule getById(Long id);

	public Schedule create(Schedule schedule);

	public Schedule update(Schedule schedule);

	public boolean delete(Schedule schedule);
}
