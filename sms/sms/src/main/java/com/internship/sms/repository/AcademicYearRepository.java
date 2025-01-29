/**
 * 
 */
package com.internship.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.AcademicYear;

/**
 * Thu Soe San
 */
@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {
	@Query("select u from AcademicYear u where u.activeStatus = :activeStatus")
	List<AcademicYear> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u from AcademicYear u where u.activeStatus = :activeStatus AND u.currentStatus=:currentStatus")
	AcademicYear CheckCurrentStatus(@Param("activeStatus") ActiveStatus activeStatus,
			@Param("currentStatus") boolean current);
}
