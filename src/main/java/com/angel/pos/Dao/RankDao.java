package com.angel.pos.Dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angel.pos.Domain.Rank;

@Repository
public class RankDao {
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	public List<Rank> rankList(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try {
			return sqlSession.selectList("com.angel.pos.Dao.RankDao.rankList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return null;
	}
	
	public Rank select(String city){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try{
			return sqlSession.selectOne("com.angel.pos.Dao.RankDao.select",city);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		return null;
	}
	public void insert(String city) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Rank rank = new Rank();
		
		rank.setCity(city);
		rank.setHit(rank.getHit() + 1);
		
		try {
			sqlSession.insert("com.angel.pos.Dao.RankDao.insert", rank);
			sqlSession.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
	public void update(int no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("com.angel.pos.Dao.RankDao.update", no);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}
/*
	public String login(String user_email) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.selectOne("",
					user_email);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public Members getLoginInfo(String user_email, String user_password) {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("user_email", user_email);
			params.put("user_password", user_password);

			return sqlSession.selectOne("",params);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			sqlSession.close();
		}
	}

	public void delete(int user_no) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.delete("", user_no);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

	*/

}
