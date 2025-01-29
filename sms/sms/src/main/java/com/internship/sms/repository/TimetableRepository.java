package com.internship.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Timetable;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {

	@Query("select u from Timetable u where u.activeStatus = :activeStatus")
	List<Timetable> getAllTimeTableByActiveStatus(@Param("activeStatus") ActiveStatus active);
}
