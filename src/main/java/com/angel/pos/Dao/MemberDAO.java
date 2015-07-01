package com.angel.pos.Dao;

import java.util.ArrayList;

import com.angel.pos.Vo.Members;

public interface MemberDAO {
	ArrayList<Members> getMembers();
	void insertMember(Members member);
	void updateMember(String name);
	void deleteMember(String name);
}
