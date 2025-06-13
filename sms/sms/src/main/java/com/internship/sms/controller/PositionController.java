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
import com.internship.sms.entity.Position;
import com.internship.sms.service.PositionService;

/**
 * 
 */
@RestController
@RequestMapping("/position/")
@CrossOrigin(origins = "*")
public class PositionController {
	@Autowired
	PositionService positionService;

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<Position> getById(@RequestParam Long id) {
		Response<Position> response = new Response<Position>();
		try {
			Position result = positionService.getPositionById(id);
			response.setMessage("All position lists");
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<Position> getAll() {
		Response<Position> response = new Response<Position>();
		try {
			List<Position> result = positionService.getAll();
			response.setMessage("All position lists");
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
	public Response<Position> create(@RequestBody Position position) {

		Response<Position> response = new Response<Position>();

		try {
			/* Check to avoid same position */
			Position checkpos = positionService.checkByName(position.getName());
			if (checkpos != null) {
				response.setStatus(false);
				response.setMessage("Existing Position Name");
			} else {
				response.setData(positionService.create(position));
				response.setMessage("Success");
			}
			/* End of Check to avoid same position */

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
	public Response<Position> update(@RequestBody Position position) {
		Response<Position> response = new Response<Position>();
		try {
			Position existingData = positionService.getPositionById(position.getId());
			if (existingData != null) {
				Position oldData = existingData;
				oldData = position;
				oldData.setModifyDate(new Date());
				response.setData(positionService.create(oldData));
				response.setMessage("Update Success");
			} else
				response.setMessage("No existing data");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Response<Position> delete(@RequestParam Long id) {
		Response<Position> response = new Response<Position>();
		try {
			Position existingData = positionService.getPositionById(id);
			if (existingData != null) {
				Position oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(positionService.create(oldData));
				response.setMessage("Delete Success");
			} else
				response.setMessage("No existing data");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

}
