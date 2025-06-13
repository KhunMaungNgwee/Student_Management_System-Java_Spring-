package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Schedule;
import com.internship.sms.repository.ScheduleRepository;
import com.internship.sms.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;

	@Override
	public List<Schedule> getAll() {
		// TODO Auto-generated method stub
		return scheduleRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Schedule getById(Long id) {
		// TODO Auto-generated method stub
		Optional<Schedule> optional = scheduleRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public Schedule create(Schedule schedule) {
		// TODO Auto-generated method stub
		return scheduleRepository.save(schedule);
	}

	@Override
	public Schedule update(Schedule schedule) {
		// TODO Auto-generated method stub
		return scheduleRepository.save(schedule);
	}

	@Override
	public boolean delete(Schedule schedule) {
		// TODO Auto-generated method stub
		try {
			scheduleRepository.delete(schedule);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

}
