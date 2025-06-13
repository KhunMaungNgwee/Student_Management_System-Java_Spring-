/**
 * 
 */
package com.internship.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.internship.sms.entity.User;

/**
 * 
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.activeStatus = 'ACTIVE' and u.email = :email")
	User getUserInfo(@Param("email") String email);
	
	@Query("select u from User u where u.activeStatus = 'ACTIVE' and (u.email = :userName OR u.userName = :userName) and u.password = :password")
	User checkUser(@Param("userName") String name,@Param("password") String password);

}
