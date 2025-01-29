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
import com.internship.sms.entity.AcademicYear;
import com.internship.sms.service.AcademicYearService;

/**
 * 
 */

@RestController
@RequestMapping("/academic_years/")
@CrossOrigin(origins = "*")
public class AcademicYearController {
	@Autowired
	AcademicYearService academicSer;

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<AcademicYear> getById(@RequestParam Long id) {
		Response<AcademicYear> response = new Response<>();

		try {
			AcademicYear result = academicSer.getAcademicYearById(id);
			response.setMessage("All user list");
			response.setData(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;

		}

		return response;

	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<AcademicYear> getAll() {
		Response<AcademicYear> response = new Response<>();
		try {
			List<AcademicYear> result = academicSer.getAllAcademicYear();
			response.setMessage("All user list");
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;
		}
		return response;

	}

	@RequestMapping(value = "getCurrent", method = RequestMethod.GET)
	public Response<AcademicYear> getCurrentStatus() {
		Response<AcademicYear> response = new Response<>();
		try {
			AcademicYear result = academicSer.checkStatus();
			response.setData(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;
		}
		return response;

	}

	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Response<AcademicYear> create(@RequestBody AcademicYear year) {
		Response<AcademicYear> response = new Response<AcademicYear>();

		try {
			AcademicYear result = academicSer.checkStatus();
			if (result != null) {
				result.setCurrentStatus(false);
			}

			year.setCurrentStatus(true);
			response.setData(academicSer.create(year));
			response.setMessage("Success");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// throw new RuntimeException(e.toString());
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;
		}

		return response;

	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<AcademicYear> update(@RequestBody AcademicYear year) {
		Response<AcademicYear> response = new Response<>();
		try {
			AcademicYear existingData = academicSer.getAcademicYearById(year.getId());
			if (existingData != null) {
				AcademicYear oldData = existingData;
				oldData = year;
				oldData.setModifyDate(new Date());
				response.setData(academicSer.create(oldData));
				response.setMessage("Update Success");

			} else {
				response.setMessage("No existing data");
			}

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
	public Response<AcademicYear> delete(@RequestParam Long id) {
		Response<AcademicYear> response = new Response<>();
		try {
			AcademicYear existingData = academicSer.getAcademicYearById(id);
			if (existingData != null) {
				AcademicYear oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(academicSer.create(oldData));
				response.setMessage("Delete Success");
			} else {
				response.setMessage("No existing data");
			}

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
