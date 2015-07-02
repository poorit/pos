package com.angel.pos.Dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.angel.pos.Domain.Members;

@Repository
public class MembersDao {
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	public Members select(int no){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try{
			return sqlSession.selectOne("com.angel.pos.Dao.MembersDao.select",no);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			sqlSession.close();
		}
		return null;
	}
	/*public void insert(Members members) {
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

	public void updatePassword(Members members) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			sqlSession.update("", members);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}*/

}
