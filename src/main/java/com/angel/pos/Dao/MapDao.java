package com.angel.pos.Dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angel.pos.Domain.Members;

@Repository
public class MapDao {
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	/*public Members select(int no){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try{
			return sqlSession.selectOne("com.angel.pos.Dao.MembersDao.select",no);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		return null;
	}*/
	public void insert(Members members) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			sqlSession.insert("", members);
			sqlSession.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
}
