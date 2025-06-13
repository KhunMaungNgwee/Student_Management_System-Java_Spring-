/**
 * 
 */
package com.internship.sms.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.entity.User;
import com.internship.sms.repository.UserRepository;
import com.internship.sms.service.UserService;

/**
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.getUserInfo(email);
	}

	@Override
	public User checkUser(String userName, String password) {
		// TODO Auto-generated method stub
		return userRepository.checkUser(userName, password);
	}

}
