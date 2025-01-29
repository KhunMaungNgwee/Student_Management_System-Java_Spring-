package com.internship.sms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.common.Response;
import com.internship.sms.common.Util;
import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.FamilyMember;
import com.internship.sms.entity.Student;
import com.internship.sms.entity.User;
import com.internship.sms.service.FamilyMemberService;
import com.internship.sms.service.StudentService;
import com.internship.sms.service.UserService;

@RestController
@RequestMapping("/student/")
@CrossOrigin(origins = "*")
public class StudentController {

	@Value("${student.file.path.realPath}")
	private String studentRealPath;

	@Value("${student.file.path.relativePath}")
	private String studentRelativePath;

	@Value("${student.file.path.defaultPath}")
	private String defaultStudentPhoto;

	@Autowired
	StudentService studentservice;

	@Autowired
	UserService userService;

	@Autowired
	FamilyMemberService familyMemberService;

	@RequestMapping(value = "getById", method = RequestMethod.GET)
	public Response<Student> getById(@RequestParam Long id) {
		Response<Student> response = new Response<Student>();
		try {
			Student result = studentservice.getStudentById(id);
			response.setMessage("All Student Lists");
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

	@RequestMapping(value = "getStudentInfoByEmail", method = RequestMethod.GET)
	public Response<Student> getStudentInfoByEmail(@RequestParam String email) {
		Response<Student> response = new Response<Student>();
		try {
			Student student = studentservice.getStudentInfoByEmail(email);
			response.setData(student);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "getAll", method = RequestMethod.GET)
	public Response<Student> getAll() {
		Response<Student> response = new Response<Student>();
		try {
			List<Student> result = studentservice.getAll();
			response.setMessage("All Student Lists");
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
	public Response<Student> create(@RequestBody Student student) {

		Response<Student> response = new Response<Student>();
		List<FamilyMember> members = new ArrayList<FamilyMember>();
		try {
			/*
			 * if(student.getStu_pp()== null) student.setStu_pp(defaultStaffPhoto);
			 */

			if (!student.getFamilyMembers().isEmpty()) {
				members = familyMemberService.saveFamilyList(student.getFamilyMembers());
			}
			student.setFamilyMembers(members);
			Student result = studentservice.create(student);
			if (!result.getStu_email().isEmpty()) {
				User user = new User();
				user.setUserName((result.getStu_gender().equals("Male") ? "Mg " : "Ma ") + result.getStu_name());
				user.setEmail(result.getStu_email());
				user.setPassword("cuStudent");
				user.setRole("STUDENT");

				userService.createUser(user);

			}

			response.setData(result);
			response.setMessage("Success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			familyMemberService.deleteAll(members);
			response.setStatus(false);
			response.setMessage("Error Occurs");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<Student> update(@RequestBody Student student) {
		Response<Student> response = new Response<Student>();

		try {
			Student existingData = studentservice.getStudentById(student.getId());
			if (existingData != null) {
				student.setModifyDate(new Date());
				if (!student.getStu_email().isEmpty()) {
					User user = userService.findByEmail(existingData.getStu_email());
					if (user != null) {
						if (user.getEmail().equals(student.getStu_email())) {
							user.setUserName(
									(student.getStu_gender().equals("Male") ? "Mg " : "Ma ") + student.getStu_name());

						} else {
							user.setUserName(
									(student.getStu_gender().equals("Male") ? "Mg " : "Ma ") + student.getStu_name());
							user.setEmail(student.getStu_email());
						}
					} else {
						user = new User();
						user.setUserName(
								(student.getStu_gender().equals("Male") ? "Mg " : "Ma ") + student.getStu_name());
						user.setEmail(student.getStu_email());
						user.setPassword("cuStudent");
						user.setRole("STUDENT");
					}
					userService.createUser(user);
				}

				List<FamilyMember> members = new ArrayList<FamilyMember>();
				if (!student.getFamilyMembers().isEmpty()) {
					members = familyMemberService.saveFamilyList(student.getFamilyMembers());
				}
				student.setFamilyMembers(members);
				studentservice.create(student);
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

	@RequestMapping(value = "uploadStudentFile", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public Response<String> saveStudentFile(@RequestPart("uploadFile") MultipartFile uploadFile) throws IOException {
		Response<String> response = new Response<String>();

		if (uploadFile == null || uploadFile.isEmpty()) {
			response.setStatus(false);
			response.setData(defaultStudentPhoto);
			response.setMessage("Invalid file upload");
		} else {
			String filePath = Util.uploadFile(uploadFile, studentRealPath, studentRelativePath);
			response.setData(filePath);
		}
		return response;
	}

	@RequestMapping(value = "delete", method = RequestMethod.DELETE)
	public Response<Student> delete(@RequestParam Long id) {
		Response<Student> response = new Response<Student>();

		try {

			Student existingData = studentservice.getStudentById(id);
			if (existingData != null) {
				Student oldData = existingData;
				oldData.setActiveStatus(ActiveStatus.DELETE);
				oldData.setModifyDate(new Date());
				response.setData(studentservice.create(oldData));
				response.setMessage("Delete Success");
				User user = userService.findByEmail(existingData.getStu_email());
				user.setActiveStatus(ActiveStatus.DELETE);
				user.setModifyDate(new Date());
				userService.createUser(user);

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

	// Retrieving student list for adding to section By checking academic Batch and
	// major

	@RequestMapping(value = "getStudentByBatch", method = RequestMethod.POST)
	public Response<Student> getStudentByBatch(@RequestBody FilterDTO filter) {
		Response<Student> response = new Response<Student>();
		try {
			List<Student> students = studentservice.getListbyBatch(filter);
			response.setData(students);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return response;
	}
}
