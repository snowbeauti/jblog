package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	//회원가입
	public void insert(UserVo uvo) {
		System.out.println("[u.dao]: insert()");
		sqlSession.insert("user.insert", uvo);
	}
	
	//로그인
	public UserVo selectlogin(UserVo uvo) {
		System.out.println("[u.dao]: selectlogin()");
		return sqlSession.selectOne("user.selectlogin", uvo);
	}
	
	//아이디체크
	public UserVo selectOne(String id) {
		System.out.println("[u.dao]: selectOne()");
		return sqlSession.selectOne("user.selectOne", id);
	}
}
