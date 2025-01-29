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
import com.internship.sms.entity.Schedule;
import com.internship.sms.service.ScheduleService;

@RestController
@RequestMapping("/schedule/")
@CrossOrigin(origins = "*")
public class ScheduleController {
	@Autowired
	ScheduleService scheduleService;

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<Schedule> getAll() {
		Response<Schedule> response = new Response<Schedule>();
		try {
			List<Schedule> result = scheduleService.getAll();
			response.setData(result);
			response.setMessage("All Schedule List");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<Schedule> getById(@RequestParam Long id) {
		Response<Schedule> response = new Response<Schedule>();
		try {
			Schedule result = scheduleService.getById(id);
			response.setData(result);
			response.setMessage("Schedule list with id");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Response<Schedule> create(@RequestBody Schedule schedule) {
		Response<Schedule> response = new Response<Schedule>();
		try {
			Schedule result = scheduleService.create(schedule);
			response.setData(result);
			response.setMessage("Save record success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occour");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<Schedule> update(@RequestBody Schedule schedule) {
		Response<Schedule> response = new Response<Schedule>();
		try {
			Schedule existingData = scheduleService.getById(schedule.getId());
			if (existingData != null) {
				Schedule oldData = existingData;
				oldData.setModifyDate(new Date());
				response.setData(scheduleService.create(oldData));
				response.setMessage("Update succes");
			} else {
				response.setMessage("No Existing Data");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Response<Schedule> delete(@RequestBody Schedule schedule) {
		Response<Schedule> response = new Response<Schedule>();
		try {
			Schedule existingData = scheduleService.getById(schedule.getId());
			if (existingData != null) {
				Schedule oldData = existingData;
				oldData.setModifyDate(new Date());
				oldData.setActiveStatus(ActiveStatus.DELETE);
				response.setData(scheduleService.create(oldData));
				response.setMessage("Delete Succes");
			} else {
				response.setMessage("No existing data");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal Server Error Occur!");
			return response;
		}
		return response;
	}
}
