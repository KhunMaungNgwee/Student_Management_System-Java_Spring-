package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.Notice;

public interface NoticeService {

	public List<Notice> getAll();
	
	public Notice getById(Long id);
	
	public Notice create(Notice notice);
	
	public boolean delete(Notice notice);
	
	public Notice update(Notice notice);
	
	public List<Notice> getAllByNoticeStatus();
} 