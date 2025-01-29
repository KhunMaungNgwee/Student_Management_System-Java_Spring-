/**
 * 
 */
package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.RequestMessage;

/**
 * 
 */
public interface RequestMessageService {

	public List<RequestMessage> getAll();

	public RequestMessage getMessageById(Long id);

	public RequestMessage create(RequestMessage requestMessage);

	public RequestMessage update(RequestMessage requestMessage);

	public boolean delete(RequestMessage requestMessage);

	public List<RequestMessage> getAllByRequestStatus();

	public List<RequestMessage> getSelfMessage(String email);
}
