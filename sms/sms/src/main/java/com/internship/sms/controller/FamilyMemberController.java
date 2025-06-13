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
import com.internship.sms.entity.FamilyMember;
import com.internship.sms.service.FamilyMemberService;

/**
 * 
 */
@RestController
@RequestMapping("/familyMember/")
@CrossOrigin(origins = "*")
public class FamilyMemberController {
	
	@Autowired
	FamilyMemberService familyMemberService;
	
	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<FamilyMember> getById(@RequestParam Long id) {
		Response<FamilyMember> response = new Response<>();
		try {
			FamilyMember result = familyMemberService.getFamilyMemberById(id);
			response.setMessage("All Family Member lists");
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
	public Response<FamilyMember> getAll() {
		Response<FamilyMember> response = new Response<>();
		try {
			List<FamilyMember> result = familyMemberService.getAll();
			response.setMessage("All Family Member lists");
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
	public Response<FamilyMember> create(@RequestBody FamilyMember member) {
		Response<FamilyMember> response = new Response<>();
		try {
			response.setData(familyMemberService.create(member));
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
	public Response<FamilyMember> update(@RequestBody FamilyMember member) {
		Response<FamilyMember> response = new Response<>();
		try {
			FamilyMember existingData = familyMemberService.getFamilyMemberById(member.getId());
			if (existingData != null) {
				FamilyMember oldData = existingData;
				oldData = member;
				oldData.setModifyDate(new Date());
				response.setData(familyMemberService.create(oldData));
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
	public Response<FamilyMember> delete(@RequestParam Long id) {
		Response<FamilyMember> response = new Response<>();
		try {
			FamilyMember existingData = familyMemberService.getFamilyMemberById(id);
			if (existingData != null) {
				FamilyMember oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(familyMemberService.create(oldData));
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
