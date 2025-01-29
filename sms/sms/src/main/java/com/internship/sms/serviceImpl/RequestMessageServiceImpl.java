/**
 * 
 */
package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.RequestMessage;
import com.internship.sms.repository.RequestMessageRepository;
import com.internship.sms.service.RequestMessageService;

/**
 * 
 */
@Service
public class RequestMessageServiceImpl implements RequestMessageService {

	@Autowired
	RequestMessageRepository reqRepo;

	@Override
	public RequestMessage create(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return reqRepo.save(requestMessage);
	}

	@Override
	public RequestMessage update(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		return reqRepo.save(requestMessage);
	}

	@Override
	public boolean delete(RequestMessage requestMessage) {
		// TODO Auto-generated method stub
		try {
			reqRepo.delete(requestMessage);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public List<RequestMessage> getAll() {
		// TODO Auto-generated method stub
		return reqRepo.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public RequestMessage getMessageById(Long id) {
		Optional<RequestMessage> optional = reqRepo.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override
	public List<RequestMessage> getAllByRequestStatus() {
		// TODO Auto-generated method stub
		return reqRepo.getAllByRequestStatus(ActiveStatus.ACTIVE, true);
	}

	@Override
	public List<RequestMessage> getSelfMessage(String email) {
		// TODO Auto-generated method stub
		return reqRepo.getSelfMessage(ActiveStatus.ACTIVE, email);
	}

}
