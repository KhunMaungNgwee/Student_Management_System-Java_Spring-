
/**
 * 
 */
package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Semester;
import com.internship.sms.repository.SemesterRepository;
import com.internship.sms.service.SemesterService;

/**
 * Thu Soe San
 */
@Service
public class SemesterServiceImpl implements SemesterService {

	@Autowired
	SemesterRepository semesterRepository;

	@Override
	public List<Semester> getAllSemester() {
		// TODO Auto-generated method stub
		return semesterRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Semester create(Semester semester) {
		// TODO Auto-generated method stub
		return semesterRepository.save(semester);
	}

	@Override
	public Semester update(Semester semester) {
		// TODO Auto-generated method stub
		return semesterRepository.save(semester);
	}

	@Override
	public boolean delete(Semester semester) {
		// TODO Auto-generated method stub
		try {
			semesterRepository.delete(semester);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public Semester getSemesterById(Long id) {
		// TODO Auto-generated method stub
		Optional<Semester> optional = semesterRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

}
