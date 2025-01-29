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
import com.internship.sms.entity.FamilyMember;

/**
 * 
 */
@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
	@Query("select u from FamilyMember u where u.activeStatus = :activeStatus")
	List<FamilyMember> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);
}
