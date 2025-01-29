/**
 * 
 */
package com.internship.sms.service;

import java.util.List;

import com.internship.sms.entity.FamilyMember;

/**
 * 
 */
public interface FamilyMemberService {
	public List<FamilyMember> getAll();
	
	public FamilyMember getFamilyMemberById(Long id);
	
	public FamilyMember create(FamilyMember member);
	
	public List<FamilyMember> saveFamilyList(List<FamilyMember> members);
	
	public FamilyMember update(FamilyMember member);
	
	public boolean delete(FamilyMember member);
	
	public boolean deleteAll(List<FamilyMember> members);

}
