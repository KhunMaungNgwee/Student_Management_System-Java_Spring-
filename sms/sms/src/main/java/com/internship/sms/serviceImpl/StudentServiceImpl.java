package com.internship.sms.serviceImpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.internship.sms.common.ActiveStatus;
import com.internship.sms.dto.FilterDTO;
import com.internship.sms.entity.Student;
import com.internship.sms.repository.StudentRepository;
import com.internship.sms.service.StudentService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentrepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Student getStudentById(Long id) {
		// TODO Auto-generated method stub
		Optional<Student> optional = studentrepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override
	public Student create(Student student) {
		// TODO Auto-generated method stub
		return studentrepository.save(student);
	}

	@Override
	public Student update(Student student) {
		// TODO Auto-generated method stub
		return studentrepository.save(student);
	}

	@Override
	public boolean delete(Student student) {
		// TODO Auto-generated method stub

		try {
			studentrepository.delete(student);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Student> getAll() {
		// TODO Auto-generated method stub
		return studentrepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public Student getStudentInfoByEmail(String email) {
		// TODO Auto-generated method stub
		return studentrepository.getStudentInfoByEmail(ActiveStatus.ACTIVE, email);
	}

	// checking student 's batch ID and major to retrieve student list
	@Override
	public List<Student> getListbyBatch(FilterDTO filter) {
		// TODO Auto-generated method stub

		try {

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Student> query = builder.createQuery(Student.class);
			Root<Student> root = query.from(Student.class);

			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (filter.getBatchId() != null) {
				predicate = builder.and(predicate,
						builder.equal(root.get("studentBatch").get("id"), filter.getBatchId()));

			}
			if (filter.getMajor() != null && !filter.getMajor().isEmpty()) {
				predicate = builder.and(predicate, builder.equal(root.get("stu_major"), filter.getMajor()));
			}
			query.where(predicate);
			TypedQuery<Student> typeQuery = entityManager.createQuery(query);
			List<Student> students = typeQuery.getResultList();

			return students;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

//to save student list in database when student are added in section
	@Override
	public List<Student> saveStudents(List<Student> students) {
		// TODO Auto-generated method stub
		return studentrepository.saveAll(students);
	}

	@Override
	public List<Student> getStudentListBySection(FilterDTO filter) {
		// TODO Auto-generated method stub
		try {

			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Student> query = builder.createQuery(Student.class);
			Root<Student> root = query.from(Student.class);

			query.select(root);
			Predicate predicate = builder.equal(root.get("activeStatus"), ActiveStatus.ACTIVE);
			if (filter.getSection() != null) {
				predicate = builder.and(predicate, builder.equal(root.get("").get("id"), filter.getBatchId()));
			}
			query.where(predicate);
			TypedQuery<Student> typeQuery = entityManager.createQuery(query);
			List<Student> students = typeQuery.getResultList();

			return students;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
