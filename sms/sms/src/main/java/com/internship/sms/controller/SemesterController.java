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
import com.internship.sms.entity.Semester;
import com.internship.sms.service.SemesterService;

@RestController
@RequestMapping("/semester/")
@CrossOrigin(origins = "*")
public class SemesterController {
	@Autowired
	SemesterService semesterService;
	
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public Response<Semester> getAll(){
		Response<Semester> response = new Response<Semester>();
		try {
			List<Semester> result=semesterService.getAllSemester();
			response.setData(result);
			response.setMessage("All semester list");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;
		}return response;
	}
	
	@RequestMapping(value="getById", method = RequestMethod.GET)
	public Response<Semester> getById(@RequestParam Long id){
		Response<Semester> response = new Response<>();
		try {
			Semester result=semesterService.getSemesterById(id);
			response.setData(result);
			response.setMessage("Semester List");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setMessage("Internal server error");
			response.setStatus(false);
			return response;
		}return response;
	}
	
	@RequestMapping(value="save",method = RequestMethod.POST)
	public Response<Semester> create(@RequestBody Semester semester){
		Response<Semester> response = new Response<Semester>();
		try {
			Semester result=semesterService.create(semester);
			response.setData(result);
			response.setMessage("Create Success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;
			
		}return response;
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Response<Semester> update(@RequestBody Semester semester){
		Response<Semester> response=new Response<>();
		try {
			Semester existingData=semesterService.getSemesterById(semester.getId());
			if (existingData!=null) {
				Semester oldData=new Semester();
				oldData=semester;
				oldData.setModifyDate(new Date());
				response.setData(semesterService.create(oldData));
				response.setMessage("Update success");
			} else {
				response.setMessage("No existing data");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server occur");
			return response;
		}return response;
	}
	@RequestMapping(value = "delete",method = RequestMethod.DELETE)
	public Response<Semester> delete(@RequestParam Long id){
		Response<Semester> response=new Response<>();
		try {
			Semester existingData=semesterService.getSemesterById(id);
			if (existingData!=null) {
				Semester oldData=existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(semesterService.create(oldData));
				response.setMessage("Delete success");
			} else {
				response.setMessage("No existing data");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server occur");
			return response;
			// TODO: handle exception
		}return response;
		
	}
	 
}
