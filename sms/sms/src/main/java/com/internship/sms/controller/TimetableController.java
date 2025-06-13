package com.internship.sms.controller;

import java.util.ArrayList;
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
import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Section;
import com.internship.sms.entity.Timetable;
import com.internship.sms.service.ScheduleService;
import com.internship.sms.service.TimetableService;

@RestController
@RequestMapping("/timetable/")
@CrossOrigin(origins = "*")
public class TimetableController {

	@Autowired
	TimetableService timetableService;

	@Autowired
	ScheduleService scheduleService;

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<Timetable> getAll() {
		Response<Timetable> response = new Response<Timetable>();
		try {
			List<Timetable> result = timetableService.getAll();
			response.setData(result);
			response.setMessage("All Tabletable Lists");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal Error Occur!");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<Timetable> getById(@RequestParam Long id) {
		Response<Timetable> response = new Response<Timetable>();
		try {
			Timetable result = timetableService.getById(id);
			response.setData(result);
			response.setMessage("Timetable with Id");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal Server Error Occur!");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Response<Timetable> create(@RequestBody List<Timetable> timetable) {
		Response<Timetable> response = new Response<Timetable>();
		try {

//			if (timetable.size()!=30) {
//				response.setStatus(false);
//				response.setMessage("Please , complete first to your timetable...");
//			}

			List<Timetable> result = timetableService.createList(timetable);
			response.setData(result);
			response.setMessage("Save succes");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occur!");
			return response;
		}
		return response;
	}

	// need to update timetable in admin_side to change lecture time
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<Timetable> update(@RequestBody List<Timetable> timetable) {
		Response<Timetable> response = new Response<Timetable>();
		try {
			for(int i=0;i<timetable.size();i++) {
			Timetable existingData = timetableService.getById(timetable.get(i).getId());
			if (existingData != null) {
				Timetable oldData = existingData;
				oldData.setModifyDate(new Date());
				response.setData(timetableService.create(timetable.get(i)));
				response.setMessage("Update succes");
			} else {
				response.setMessage("No existing data");
			}
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

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Response<Timetable> delete(@RequestBody Timetable timetable) {
		Response<Timetable> response = new Response<Timetable>();
		try {
			Timetable existingData = timetableService.getById(timetable.getId());
			if (existingData != null) {
				Timetable oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.ACTIVE);
				oldData.setModifyDate(new Date());
				response.setData(timetableService.create(timetable));
				response.setMessage("Delete Succes");
			} else {
				response.setMessage("No existing Data");
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

	// Retrieving timetable in Student view according to section
	@RequestMapping(value = "retrieveTimetable", method = RequestMethod.POST)
	public Response<Timetable> TimetableList(@RequestBody FilterDTO filter) {
		Response<Timetable> response = new Response<Timetable>();

		try {
			List<Timetable> result = timetableService.getListBySection(filter);
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;

	}

	// retrieving section according to subject taught by respective teacher
	@RequestMapping(value = "getSectionByTr", method = RequestMethod.POST)
	public Response<Section> SectionListByTr(@RequestBody FilterDTO filter) {
		Response<Section> response = new Response<Section>();
		List<Section> sections = new ArrayList<Section>();
		try {
			List<Timetable> result = timetableService.getSectionListBySubject(filter);

			for (int i = 0; i < result.size(); i++) {
				sections.add(result.get(i).getSection());
			}
			response.setData(sections);

//			for (int i = 0; i < result.size(); i++) {
//				for (int j = 0; j < sections.size(); j++) {
//					if (sections.size() == 0)
//						sections.add(result.get(i).getSection());
//
//					else if (sections.get(j) != result.get(i).getSection()) {
//						sections.add(result.get(i).getSection());
//					}
//				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return response;

	}

}
