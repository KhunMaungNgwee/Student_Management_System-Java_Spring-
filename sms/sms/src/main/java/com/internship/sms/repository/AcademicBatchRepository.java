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
import com.internship.sms.entity.AcademicBatch;

/**
 * 
 */
@Repository
public interface AcademicBatchRepository extends JpaRepository<AcademicBatch, Long> {

	@Query("select u from AcademicBatch u where u.activeStatus = :activeStatus")
	List<AcademicBatch> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u from AcademicBatch u where u.name = :academicBatchName")
	AcademicBatch checkByName(@Param("academicBatchName") String name);
}
