package com.internship.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
	@Query("select u from Schedule u where u.activeStatus = :activeStaus")
	List<Schedule> getAllByActiveStatus(@Param("activeStaus") ActiveStatus activeStatus);
}
