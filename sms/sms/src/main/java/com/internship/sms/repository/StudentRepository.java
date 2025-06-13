package com.internship.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("select u from Student u where u.activeStatus = :activeStatus")
	List<Student> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u from Student u where u.activeStatus = :activeStatus and u.stu_email = :email")
	Student getStudentInfoByEmail(@Param("activeStatus") ActiveStatus activeStatus, @Param("email") String email);

}
