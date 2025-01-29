/**
 * 
 */
package com.internship.sms.service;

import com.internship.sms.entity.User;

/**
 * 
 */
public interface UserService {

	public User createUser(User user);

	public User findByEmail(String email);

	public User checkUser(String userName, String password);

}
