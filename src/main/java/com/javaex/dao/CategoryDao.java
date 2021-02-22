package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.javaex.vo.CategoryVo;


@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	//조회
	public List<CategoryVo> cList() {
		System.out.println("cList");
		
		return sqlSession.selectList("guest.selectList");
	}
	
	//저장
	public int insertSelectkey(CategoryVo cvo) {
	return sqlSession.insert("category.insertSelectkey", cvo);	
	}
	
	//카테고리 1개 가져오기
	public CategoryVo selectOne(int cateNo) {
		System.out.println("category.selectOne");
		return sqlSession.selectOne("category.selectOne", cateNo);
	}
	
	//카테고리 삭제
	public int delete(int cateNo) {
	return sqlSession.delete("category.delete", cateNo);	
	}

}
