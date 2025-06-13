/**
 * 
 */
package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.AcademicBatch;
import com.internship.sms.repository.AcademicBatchRepository;
import com.internship.sms.service.AcademicBatchService;

/**
 * 
 */
@Service
public class AcademicBatchServiceImpl implements AcademicBatchService {

	@Autowired
	AcademicBatchRepository academicBatchRepository;

	@Override
	public List<AcademicBatch> getAll() {
		// TODO Auto-generated method stub
		return academicBatchRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public AcademicBatch getAcademicBatchById(Long id) {
		// TODO Auto-generated method stub
		Optional<AcademicBatch> optional = academicBatchRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override
	public AcademicBatch create(AcademicBatch academicBatch) {
		// TODO Auto-generated method stub
		return academicBatchRepository.save(academicBatch);
	}

	@Override
	public AcademicBatch update(AcademicBatch academicBatch) {
		// TODO Auto-generated method stub
		return academicBatchRepository.save(academicBatch);
	}

	@Override
	public boolean delete(AcademicBatch academicBatch) {
		try {
			academicBatchRepository.delete(academicBatch);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public AcademicBatch checkByName(String name) {
		// TODO Auto-generated method stub
		 AcademicBatch result = academicBatchRepository.checkByName(name);
		return result;
	}

}
