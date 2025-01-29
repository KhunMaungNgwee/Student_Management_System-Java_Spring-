package com.internship.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

	@Query("select u  from Notice u where u.activeStatus=:activeStatus")
	List<Notice> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u  from Notice u where u.activeStatus=:activeStatus and u.notice_status=:notice_status")
	List<Notice> getAllByNoticeStatus(@Param("activeStatus") ActiveStatus activeStatus,
			@Param("notice_status") boolean notice_status);

}
