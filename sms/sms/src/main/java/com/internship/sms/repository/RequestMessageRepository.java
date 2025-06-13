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
import com.internship.sms.entity.RequestMessage;

/**
 * 
 */
@Repository
public interface RequestMessageRepository extends JpaRepository<RequestMessage, Long> {
	@Query("select u from RequestMessage u where u.activeStatus = :activeStatus")
	List<RequestMessage> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u from RequestMessage u where u.activeStatus = :activeStatus and u.request_status=:request_status")
	List<RequestMessage> getAllByRequestStatus(@Param("activeStatus") ActiveStatus activeStatus,
			@Param("request_status") boolean request_status);

	@Query("select u from RequestMessage u where u.activeStatus = :activeStatus and u.email=:email")
	List<RequestMessage> getSelfMessage(@Param("activeStatus") ActiveStatus activeStatus, @Param("email") String email);

}
