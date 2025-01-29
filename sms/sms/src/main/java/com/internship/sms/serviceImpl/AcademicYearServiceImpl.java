/**
 * 
 */
package com.internship.sms.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.AcademicYear;
import com.internship.sms.repository.AcademicYearRepository;
import com.internship.sms.service.AcademicYearService;

/**
 * Thu Soe San
 */
@Service
public class AcademicYearServiceImpl implements AcademicYearService {

	@Autowired
	AcademicYearRepository academicYearRepository;

	@Override
	public List<AcademicYear> getAllAcademicYear() {
		// TODO Auto-generated method stub
		return academicYearRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public AcademicYear create(AcademicYear year) {
		// TODO Auto-generated method stub

		return academicYearRepository.save(year);
	}

	@Override
	public AcademicYear update(AcademicYear year) {
		// TODO Auto-generated method stub
		return academicYearRepository.save(year);
	}

	@Override
	public boolean delete(AcademicYear year) {
		// TODO Auto-generated method stub
		try {
			academicYearRepository.delete(year);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	@Override
	public AcademicYear getAcademicYearById(Long id) {
		// TODO Auto-generated method stub
		Optional<AcademicYear> optional = academicYearRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override 
	public AcademicYear checkStatus() { 
		// TODO Auto-generated method 
	  
	  return academicYearRepository.CheckCurrentStatus(ActiveStatus.ACTIVE,true);
	  
	  
	  }

}
