package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(String id, String userName) {
		System.out.println("[b.dao]: insert()");
		
		BlogVo bvo = new BlogVo (id, userName);
		sqlSession.insert("blog.basicInsert", bvo);
	}
	
	public BlogVo selectOne(String id) {
		System.out.println("[b.dao]: selectOne()");
		return sqlSession.selectOne("blog.selectOne", id);
		
	}
	
	public void basicUpdate(BlogVo bvo){
		System.out.println("[b.dao]: basicUpdate(): " + bvo);
		sqlSession.update("blog.basicUpdate", bvo);

	}
	
	

}
