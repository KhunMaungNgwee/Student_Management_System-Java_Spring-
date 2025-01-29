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
import com.internship.sms.entity.AcademicBatch;
import com.internship.sms.service.AcademicBatchService;

/**
 * 
 */
@RestController
@RequestMapping("/academicBatch/")
@CrossOrigin(origins = "*")
public class AcademicBatchController {

	@Autowired
	AcademicBatchService academicBatchService;

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<AcademicBatch> getById(@RequestParam Long id) {
		Response<AcademicBatch> response = new Response<AcademicBatch>();
		try {
			AcademicBatch result = academicBatchService.getAcademicBatchById(id);
			response.setMessage("All Academic Batch lists");
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
	public Response<AcademicBatch> getAll() {
		Response<AcademicBatch> response = new Response<AcademicBatch>();
		try {
			List<AcademicBatch> result = academicBatchService.getAll();
			response.setMessage("All Academic Batch lists");
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
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public Response<AcademicBatch> create(@RequestBody AcademicBatch academicBatch) {
		Response<AcademicBatch> response = new Response<AcademicBatch>();
		try {
			/* Check to avoid same department */
			AcademicBatch checkAcademicBatch = academicBatchService.checkByName(academicBatch.getName());
			if(checkAcademicBatch != null) {
				response.setStatus(false);
				response.setMessage("Existing AcademicBatch Name.");
			}else {
				response.setData(academicBatchService.create(academicBatch));
				response.setMessage("Success");
			}
			/* End of Check to avoid same department */
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error occur");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<AcademicBatch> update(@RequestBody AcademicBatch academicBatch) {
		Response<AcademicBatch> response = new Response<AcademicBatch>();
		try {
			AcademicBatch existingData = academicBatchService.getAcademicBatchById(academicBatch.getId());
			if (existingData != null) {
				AcademicBatch oldData = existingData;
				oldData = academicBatch;
				oldData.setModifyDate(new Date());
				response.setData(academicBatchService.create(oldData));
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
	public Response<AcademicBatch> delete(@RequestParam Long id) {
		Response<AcademicBatch> response = new Response<AcademicBatch>();
		try {
			AcademicBatch existingData = academicBatchService.getAcademicBatchById(id);
			if (existingData != null) {
				AcademicBatch oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(academicBatchService.create(oldData));
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
