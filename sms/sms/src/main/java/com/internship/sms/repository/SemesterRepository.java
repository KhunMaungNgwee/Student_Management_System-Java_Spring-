package com.internship.sms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Semester;

@Repository

public interface SemesterRepository extends JpaRepository<Semester, Long> {
	@Query("select u from Semester u where u.activeStatus = :activeStatus")
	  List<Semester> getAllByActiveStatus(@Param("activeStatus")ActiveStatus activeStatus);
}
