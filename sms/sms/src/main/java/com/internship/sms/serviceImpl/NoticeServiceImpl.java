package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Notice;
import com.internship.sms.repository.NoticeRepository;
import com.internship.sms.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeRepository noticeRepository;

	@Override
	public List<Notice> getAll() {
		// TODO Auto-generated method stub
		return noticeRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Notice getById(Long id) {
		// TODO Auto-generated method stub
		Optional<Notice> optional = noticeRepository.findById(id);
		if (optional != null) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public Notice create(Notice notice) {
		// TODO Auto-generated method stub
		return noticeRepository.save(notice);
	}

	@Override
	public boolean delete(Notice notice) {
		// TODO Auto-generated method stub
		try {
			noticeRepository.delete(notice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public Notice update(Notice notice) {
		// TODO Auto-generated method stub
		return noticeRepository.save(notice);
	}

	@Override
	public List<Notice> getAllByNoticeStatus() {
		// TODO Auto-generated method stub
		return noticeRepository.getAllByNoticeStatus(ActiveStatus.ACTIVE, true);
	}

}
