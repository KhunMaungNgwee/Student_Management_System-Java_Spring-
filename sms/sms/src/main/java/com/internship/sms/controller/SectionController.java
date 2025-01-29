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
import com.internship.sms.entity.Student;
import com.internship.sms.service.SectionService;
import com.internship.sms.service.StudentService;

@RestController
@RequestMapping("/section/")
@CrossOrigin(origins = "*")
public class SectionController {

	@Autowired
	SectionService sectionService;
	@Autowired
	StudentService studentService;

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<Section> getAllSection() {
		Response<Section> response = new Response<Section>();
		try {
			List<Section> result = sectionService.getAllSection();
			response.setData(result);
			response.setMessage("All Section Lists");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<Section> getSectionById(@RequestParam Long id) {
		Response<Section> response = new Response<Section>();
		try {
			Section result = sectionService.getSectionById(id);
			response.setData(result);
			response.setMessage("Section with id");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("internal server error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public Response<Section> save(@RequestBody Section section) {
		Response<Section> response = new Response<Section>();
		List<Student> students = new ArrayList<Student>();
		try {
			if (!section.getStudents().isEmpty()) {
				students = studentService.saveStudents(section.getStudents());

				section.setStudents(students);
				Section sec = sectionService.create(section);
				response.setData(sec);
			}
			Section result = sectionService.create(section);
			response.setData(result);
			response.setMessage("save success");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Selected student is already added to the section");
			return response;
		}
		return response;
	}

	// for simple edit and for adding student to section (this may be editing)
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<Section> update(@RequestParam Section section) {
		Response<Section> response = new Response<Section>();
		List<Student> students = new ArrayList<Student>();

		try {
			Section existingData = sectionService.getSectionById(section.getId());
			if (existingData != null) {
				Section oldData = existingData;
				oldData.setModifyDate(new Date());
				response.setData(sectionService.create(oldData));
				response.setMessage("Update succes");
			} else {
				response.setMessage("No Existing Data");
			}

			// if student is added to section
			if (!section.getStudents().isEmpty()) {
				students = studentService.saveStudents(section.getStudents());

				section.setStudents(students);
				Section sec = sectionService.create(section);
				response.setData(sec);
			}
		} catch (Exception e) {
			// TODO: handle
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Response<Section> delete(@RequestParam Long id) {
		Response<Section> response = new Response<Section>();
		try {
			Section existingData = sectionService.getSectionById(id);
			if (existingData != null) {
				Section oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(sectionService.create(oldData));
				response.setMessage("delete sucess");
			} else {
				response.setMessage("No existing data");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("internal server error occur");
			return response;
		}
		return response;
	}

	// method to get section for respective student

	@RequestMapping(value = "getSection", method = RequestMethod.POST)
	public Response<Section> getSection(@RequestBody FilterDTO student) {
		Response<Section> response = new Response<>();
		try {
			Section sec = sectionService.getSection(student);
			response.setData(sec);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("internal server error occur");

		}
		return response;
	}

	@RequestMapping(value = "getSectionList", method = RequestMethod.POST)
	public Response<Section> getSectionList(@RequestBody FilterDTO section) {
		Response<Section> response = new Response<>();
		try {
			List<Section> result = sectionService.getSectionList(section);
			response.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("internal server error occur");
			return response;
		}
		return response;
	}
	//get student list by section to do next implementation
	@RequestMapping(value = "getStudentList", method = RequestMethod.POST)
	public Response<Student> getStudentListBySection(@RequestBody FilterDTO filter) {
		Response<Student> response = new Response<>();
		try {
			List<Student> result = sectionService.getStudentListBySection(filter);
			response.setData(result);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("internal server error occur");
			return response;
		}
		return response;
	}

}
