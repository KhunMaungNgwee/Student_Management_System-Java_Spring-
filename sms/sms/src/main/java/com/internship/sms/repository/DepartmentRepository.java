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
import com.internship.sms.entity.Department;

/**
 * Thu Soe San
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query("select u from Department u where u.activeStatus = :activeStatus")
	List<Department> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u from Department u where u.name = :deptName")
	Department checkByName(@Param("deptName") String name);

}
