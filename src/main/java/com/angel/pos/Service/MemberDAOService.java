package com.angel.pos.Service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angel.pos.Dao.MemberDAO;
import com.angel.pos.Vo.Members;
import com.angel.pos.Mapper.MemberMapper;

@Repository
public class MemberDAOService implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<Members> getMembers() {
		// TODO Auto-generated method stub
		ArrayList<Members> result = new ArrayList<Members>();
		System.out.println("리스트만듬");
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
		System.out.println("멤버 메퍼 실행");
		result = memberMapper.getMembers();
		
		return result;
	}

	@Override
	public void insertMember(Members member) {
		// TODO Auto-generated method stub
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        memberMapper.insertMember(member);
	}

	@Override
	public void updateMember(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMember(String name) {
		// TODO Auto-generated method stub
		MemberMapper memberMapper = sqlSession.getMapper(MemberMapper.class);
        memberMapper.deleteMember(name);
	}

}
