/**
 * 
 */
package com.internship.sms.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.internship.sms.common.ActiveStatus;
import com.internship.sms.entity.FamilyMember;
import com.internship.sms.repository.FamilyMemberRepository;
import com.internship.sms.service.FamilyMemberService;

import jakarta.transaction.Transactional;

/**
 * 
 */
@Service
@Transactional
public class FamilyMemberServiceIMpl implements FamilyMemberService {

	@Autowired
	FamilyMemberRepository familyMemberRepository;

	@Override
	public List<FamilyMember> getAll() {
		// TODO Auto-generated method stub
		return familyMemberRepository.getAllByActiveStatus(ActiveStatus.ACTIVE);
	}

	@Override
	public FamilyMember getFamilyMemberById(Long id) {
		Optional<FamilyMember> optional = familyMemberRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override
	public FamilyMember create(FamilyMember member) {
		// TODO Auto-generated method stub
		return familyMemberRepository.save(member);
	}

	@Override
	public FamilyMember update(FamilyMember member) {
		// TODO Auto-generated method stub
		return familyMemberRepository.save(member);
	}

	@Override
	public boolean delete(FamilyMember member) {
		try {
			familyMemberRepository.delete(member);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	@Override
	public List<FamilyMember> saveFamilyList(List<FamilyMember> members) {
		// TODO Auto-generated method stub
		return familyMemberRepository.saveAll(members);
	}

	@Override
	public boolean deleteAll(List<FamilyMember> members) {
		// TODO Auto-generated method stub
		try {
			familyMemberRepository.deleteAll(members);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

}
