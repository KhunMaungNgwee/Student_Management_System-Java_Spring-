/**
 * 
 */
package com.internship.sms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.common.Response;
import com.internship.sms.entity.RequestMessage;
import com.internship.sms.service.RequestMessageService;

/**
 * 
 */
@RestController
@RequestMapping("/requestMessage/")
@CrossOrigin(origins = "*")
public class RequestMessageController {

	@Autowired
	RequestMessageService reqSer;

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<RequestMessage> getAll() {
		Response<RequestMessage> response = new Response<RequestMessage>();
		try {
			List<RequestMessage> result = reqSer.getAll();
			response.setMessage("All RequestMessage Lists");
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getAllByRequestStatus", method = RequestMethod.GET)
	public Response<RequestMessage> getAllByRequestStatus() {
		Response<RequestMessage> response = new Response<RequestMessage>();
		try {
			List<RequestMessage> result = reqSer.getAllByRequestStatus();
			response.setMessage("All unread messages");
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getSelfMessage", method = RequestMethod.GET)
	public Response<RequestMessage> getSelfMessage(@RequestParam String email) {
		Response<RequestMessage> response = new Response<RequestMessage>();
		try {
			List<RequestMessage> result = reqSer.getSelfMessage(email);
			response.setMessage("All your messages lists");
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<RequestMessage> getById(@RequestParam Long id) {
		Response<RequestMessage> response = new Response<RequestMessage>();
		try {
			RequestMessage result = reqSer.getMessageById(id);
			response.setMessage("All Subject Lists");
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Response<RequestMessage> create(@RequestBody RequestMessage requestMessage) {
		Response<RequestMessage> response = new Response<RequestMessage>();
		try {
			requestMessage.setRequest_status(true);
			response.setData(reqSer.create(requestMessage));
			response.setMessage("Success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<RequestMessage> update(@RequestBody RequestMessage requestMessage) {
		Response<RequestMessage> response = new Response<RequestMessage>();

		try {
			RequestMessage existingData = reqSer.getMessageById(requestMessage.getId());
			if (existingData != null) {
				RequestMessage oldData = requestMessage;
				oldData.setRequest_status(false);
				response.setData(reqSer.create(oldData));
				response.setMessage("Update Success");
			} else
				response.setMessage("No existing data");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Response<RequestMessage> delete(@RequestParam Long id) {
		Response<RequestMessage> response = new Response<RequestMessage>();

		try {
			RequestMessage existingData = reqSer.getMessageById(id);
			if (existingData != null) {
				RequestMessage oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(reqSer.create(oldData));
				response.setMessage("Delete Success");
			} else
				response.setMessage("No existing data");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error occur");
			return response;
		}
		return response;
	}

}
