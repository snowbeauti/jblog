package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;


@Service
public class CategoryService {

	
	@Autowired
	private BlogDao bdao;
	
	@Autowired
	private CategoryDao cdao;
	
	// 리스트
	public List<CategoryVo> cList() {

		return cdao.cList();
	}
	
	public BlogVo blogselectOne(String id) {
		 return bdao.selectOne(id);
	}
	
	public CategoryVo  addResultVo(CategoryVo cvo) {
		
		//글저장
		cdao.insertSelectkey(cvo);
		
		//글 1개 조회
		return cdao.selectOne(cvo.getCateNo());
		
	}
	//삭제
	public int remove(CategoryVo cvo) {
		return cdao.delete(cvo.getCateNo());
	}

}
