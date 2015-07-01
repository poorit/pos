package com.angel.pos.Mapper;

import java.util.ArrayList;

import com.angel.pos.Vo.Members;

public interface MemberMapper {
	ArrayList<Members> getMembers();
	void insertMember(Members member);
	void updateMember(String name);
	void deleteMember(String name);
}
