package com.internship.sms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.AcademicBatch;
import com.internship.sms.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
	@Query("select u from  Section u where u.activeStatus= :activeStatus")
	List<Section> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u from  Section u where u.activeStatus= :activeStatus AND u.academicBatch= :batch AND u.major=:major")
	List<Section> getSectionList(@Param("batch") AcademicBatch academicBatch, @Param("major") String major);
}
