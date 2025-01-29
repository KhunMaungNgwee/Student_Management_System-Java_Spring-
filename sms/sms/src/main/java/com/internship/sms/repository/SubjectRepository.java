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
import com.internship.sms.entity.Semester;
import com.internship.sms.entity.Subject;

/**
 * 
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
	@Query("select u from Subject u where u.activeStatus = :activeStatus")
	List<Subject> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("SELECT s FROM Subject s WHERE s.subjectBatch = :SubjectBatch AND s.major = :major AND s.subjectSem = :SubjectSem")
	List<Subject> getSubByBatch(@Param("SubjectBatch") AcademicBatch batch, @Param("major") String major,@Param("SubjectSem") Semester semester);

}
