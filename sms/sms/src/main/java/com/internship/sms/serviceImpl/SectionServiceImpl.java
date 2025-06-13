package com.internship.sms.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Section;
import com.internship.sms.entity.Student;
import com.internship.sms.repository.SectionRepository;
import com.internship.sms.service.SectionService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	SectionRepository sectionRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public List<Section> getAllSection() {
		// TODO Auto-generated method stub
		return sectionRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Section getSectionById(Long id) {
		// TODO Auto-generated method stub
		Optional<Section> optional = sectionRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override
	public Section create(Section section) {
		// TODO Auto-generated method stub
		return sectionRepository.save(section);
	}

	@Override
	public Section update(Section section) {
		// TODO Auto-generated method stub
		return sectionRepository.save(section);
	}

	@Override
	public boolean delete(Section section) {
		// TODO Auto-generated method stub
		try {
			sectionRepository.delete(section);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public List<Section> getSectionList(FilterDTO dto) {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Section> query = builder.createQuery(Section.class);
			Root<Section> root = query.from(Section.class);
			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (dto.getBatchId() != null) {
				predicate = builder.and(predicate,
						builder.equal(root.get("academicBatch").get("id"), dto.getBatchId()));
			}
			if (dto.getMajor() != null && !dto.getMajor().isEmpty()) {
				predicate = builder.and(predicate, builder.equal(root.get("major"), dto.getMajor()));
			}
			query.where(predicate);
			TypedQuery<Section> typeQuery = entityManager.createQuery(query);
			List<Section> sections = typeQuery.getResultList();
			return sections;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Section getSection(FilterDTO dto) {
		// TODO Auto-generated method stub

		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Section> query = builder.createQuery(Section.class);
			Root<Section> root = query.from(Section.class);
			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (dto.getStudentId() != null) {
				Join<Section, Student> student_section = root.join("students", JoinType.INNER);
				predicate = builder.and(predicate, builder.equal(student_section.get("id"), dto.getStudentId()));
			}
			query.where(predicate);
			TypedQuery<Section> typeQuery = entityManager.createQuery(query);
			Section section = typeQuery.getSingleResult();
			return section;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}

	// retrieving student list according to section
	@Override
	public List<Student> getStudentListBySection(FilterDTO filter) {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Section> query = builder.createQuery(Section.class);
			Root<Section> root = query.from(Section.class);
			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (filter.getSection() != null) {
				predicate = builder.and(predicate, builder.equal(root.get("id"), filter.getSection()));
			}

			query.where(predicate);
			TypedQuery<Section> typeQuery = entityManager.createQuery(query);
			Section section = typeQuery.getSingleResult();
			List<Student> students = section.getStudents();
			return students;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
