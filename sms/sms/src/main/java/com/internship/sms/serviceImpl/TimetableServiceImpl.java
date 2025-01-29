package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Timetable;
import com.internship.sms.repository.TimetableRepository;
import com.internship.sms.service.TimetableService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class TimetableServiceImpl implements TimetableService {

	@Autowired
	TimetableRepository timetableRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public List<Timetable> getAll() {
		// TODO Auto-generated method stub
		return timetableRepository.getAllTimeTableByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Timetable getById(Long id) {
		// TODO Auto-generated method stub
		Optional<Timetable> optional = timetableRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Timetable> createList(List<Timetable> timetable) {
		// TODO Auto-generated method stub
		return timetableRepository.saveAll(timetable);
	}

	@Override
	public Timetable create(Timetable timetable) {
		// TODO Auto-generated method stub
		return timetableRepository.save(timetable);
	}

	@Override
	public Timetable update(Timetable timetable) {
		// TODO Auto-generated method stub
		return timetableRepository.save(timetable);
	}

	@Override
	public boolean delete(Timetable timetable) {
		// TODO Auto-generated method stub
		try {
			timetableRepository.delete(timetable);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

//Retrieving timetable records from student side
	@Override
	public List<Timetable> getListBySection(FilterDTO filter) {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Timetable> query = builder.createQuery(Timetable.class);
			Root<Timetable> root = query.from(Timetable.class);
			query.select(root);

			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (filter.getSection() != null) {
				predicate = builder.and(predicate, builder.equal(root.get("section").get("id"), filter.getSection()));

			}
			if(filter.getSemesterId()!=null) {
				predicate = builder.and(predicate, builder.equal(root.get("semesterId"), filter.getSemesterId()));

			}
			query.where(predicate);
			TypedQuery<Timetable> typeQuery = entityManager.createQuery(query);
			List<Timetable> timetable = typeQuery.getResultList();
			return timetable;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Timetable> getSectionListBySubject(FilterDTO filter) {
		// TODO Auto-generated method stub
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Timetable> query = builder.createQuery(Timetable.class);
			Root<Timetable> root = query.from(Timetable.class);
			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);

			if(filter.getSubjectId()!=null) {
				predicate = builder.and(predicate, builder.equal(root.get("subject").get("id"), filter.getSubjectId()));

			}
			if(filter.getTeacherId()!=null) {
				predicate = builder.and(predicate, builder.equal(root.get("subject").get("subjectStaff").get("id"), filter.getTeacherId()));
			}
			query.where(predicate).distinct(true);
			TypedQuery<Timetable> typeQuery = entityManager.createQuery(query);
			List<Timetable> timetable = typeQuery.getResultList();	
			return timetable;
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
