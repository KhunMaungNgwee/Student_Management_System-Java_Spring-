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
import com.internship.sms.entity.Department;
import com.internship.sms.service.DepartmentService;

/**
 * Thu Soe San
 */
@RestController
@RequestMapping("/department/")
@CrossOrigin(origins = "*")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<Department> getById(@RequestParam Long id) {
		Response<Department> response = new Response<Department>();
		try {
			Department result = departmentService.getDepartmentById(id);
			response.setMessage("All Department lists");
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
	public Response<Department> getAll() {
		Response<Department> response = new Response<Department>();
		try {
			List<Department> result = departmentService.getAll();
			response.setMessage("All Department lists");
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
	public Response<Department> create(@RequestBody Department department) {
		Response<Department> response = new Response<Department>();
		try {
			/* Check to avoid same department */
			Department checkDept = departmentService.checkByName(department.getName());
			if(checkDept != null) {
				response.setStatus(false);
				response.setMessage("Existing Department Name.");
			}else {
				response.setData(departmentService.create(department));
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
	public Response<Department> update(@RequestBody Department department) {
		Response<Department> response = new Response<Department>();
		try {
			Department existingData = departmentService.getDepartmentById(department.getId());
			if (existingData != null) {
				Department oldData = existingData;
				oldData = department;
				oldData.setModifyDate(new Date());
				response.setData(departmentService.create(oldData));
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
	public Response<Department> delete(@RequestParam Long id) {
		Response<Department> response = new Response<Department>();
		try {
			Department existingData = departmentService.getDepartmentById(id);
			if (existingData != null) {
				Department oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(departmentService.create(oldData));
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
