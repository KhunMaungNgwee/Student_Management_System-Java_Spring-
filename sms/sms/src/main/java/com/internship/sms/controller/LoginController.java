/**
 * 
 */
package com.internship.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.internship.sms.common.Response;
import com.internship.sms.entity.Staff;
import com.internship.sms.entity.Student;
import com.internship.sms.entity.User;
import com.internship.sms.service.StaffService;
import com.internship.sms.service.StudentService;
import com.internship.sms.service.UserService;

/**
 * 
 */
@RestController
@RequestMapping("/login/")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	StudentService studentService;
	
	@Autowired
	StaffService staffService;

	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	public Response<User> checkUser(@RequestBody User usr) {
		Response<User> response = new Response<User>();
		try {
			User checkUser = userService.checkUser(usr.getUserName(), usr.getPassword());
			if (checkUser != null) {
				User user = new User();
				user.setUserName(checkUser.getUserName());
				user.setEmail(checkUser.getEmail());
				user.setRole(checkUser.getRole());
				switch (user.getRole()) {
				case "STUDENT":
					Student student = studentService.getStudentInfoByEmail(user.getEmail());
					if (student != null)
						user.setUserProfile(student.getStu_pp());
					break; 
				case "TEACHER":
					Staff staff = staffService.getStaffInfoByEmail(user.getEmail());
					if(staff != null) {
						user.setUserProfile(staff.getStaffProfilePicture());
					}
					break;
				case "ADMIN":
					Staff admin = staffService.getStaffInfoByEmail(user.getEmail());
					if(admin != null) {
						user.setUserProfile(admin.getStaffProfilePicture());
					}
					break;
				}
				response.setData(user);
			} else {
				response.setStatus(false);
				response.setMessage("Invalid user account");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "checkPassword", method = RequestMethod.POST)
	public Response<User> checkpass(@RequestBody User usr) {
		Response<User> response = new Response<User>();
		try {
			User checkUser = userService.checkUser(usr.getEmail(), usr.getPassword());
			response.setData(checkUser);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(false);
			response.setMessage("Internal server error");
			return response;
		}
		return response;
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public Response<User> changePass(@RequestBody User usr) {
	Response <User> response=new Response<User>();
	
	User result=userService.findByEmail(usr.getEmail());
	if(result!=null) {
	result.setPassword(usr.getPassword());
	 userService.createUser(result);
	response.setData(result);
	
	}
	
	return response;
	
	}
	
}
