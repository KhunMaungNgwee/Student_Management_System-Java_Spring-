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
import com.internship.sms.entity.Position;

/**
 * 
 */
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {

	@Query("select u from Position u where u.activeStatus = :activeStatus")
	List<Position> getAllByActiveStatus(@Param("activeStatus") ActiveStatus activeStatus);

	@Query("select u from Position u where u.name = :posName")
	Position checkByName(@Param("posName") String name);
}
