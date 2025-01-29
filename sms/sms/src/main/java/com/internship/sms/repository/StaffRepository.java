package com.internship.sms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Staff;


@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	@Query("select u from Staff u where u.activeStatus = :activeStatus")
	List<Staff> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);
	
	@Query("select u from Staff u where u.activeStatus = :activeStatus and u.staffEmail = :email")
	Staff getStaffInfoByEmail(@Param("activeStatus") ActiveStatus activeStatus, @Param("email") String email);
}
