/**
 * 
 */
package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Staff;
import com.internship.sms.entity.Subject;
import com.internship.sms.repository.SubjectRepository;
import com.internship.sms.service.SubjectService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * 
 */
@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Subject> getAll() {
		// TODO Auto-generated method stub
		return subjectRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Subject getSubjectById(Long id) {
		// TODO Auto-generated method stub
		Optional<Subject> optional = subjectRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else
			return null;
	}

	@Override
	public Subject create(Subject subject) {
		// TODO Auto-generated method stub
		return subjectRepository.save(subject);
	}

	@Override
	public Subject update(Subject subject) {
		// TODO Auto-generated method stub
		return subjectRepository.save(subject);
	}

	@Override
	public boolean delete(Subject subject) {
		// TODO Auto-generated method stub
		try {
			subjectRepository.delete(subject);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	//retrieving subject list according to student batch and major and semester optional
	@Override
	public List<Subject> getSubByBatch(FilterDTO dto) {
		// TODO Auto-generated method stub return
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
			Root<Subject> root = query.from(Subject.class);
			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (dto.getBatchId() != null) {
				predicate = builder.and(predicate, builder.equal(root.get("subjectBatch").get("id"), dto.getBatchId()));
			}
			if (dto.getMajor() != null && !dto.getMajor().isEmpty()) {
				predicate = builder.and(predicate, builder.or(
					    builder.equal(root.get("major"), dto.getMajor()),
					    builder.equal(root.get("major"), "CST")));
//				predicate=builder.and(predicate,builder.equal(root.get("major"),"CST"));
			}
			
			if (dto.getSemesterId() != null) {
				predicate = builder.and(predicate,
						builder.equal(root.get("subjectSem").get("id"), dto.getSemesterId()));
			}

			query.where(predicate);
			TypedQuery<Subject> typedQuery = entityManager.createQuery(query);

			List<Subject> subjects = typedQuery.getResultList();

			return subjects;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return null;
	}

	// retrieving Subject List taught by respective teacher
	@Override
	public List<Subject> getSubTeachedBy(FilterDTO filter) {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
			Root<Subject> root = query.from(Subject.class);
			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (filter.getTeacherId() != null) {
				Join<Subject, Staff> subject_staff = root.join("subjectStaff", JoinType.INNER);
				predicate = builder.and(predicate, builder.equal(subject_staff.get("id"), filter.getTeacherId()));

			}
//			
			query.where(predicate);
			TypedQuery<Subject> typedQuery = entityManager.createQuery(query);

			List<Subject> subjects = typedQuery.getResultList();
			return subjects;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
