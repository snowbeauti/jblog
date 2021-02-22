package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private BlogDao bdao;
	
	@Autowired
	private PostDao pdao;
	
	public BlogVo blogselectOne(String id) {
		System.out.println("[p.service]: blogselectOne()");
		 return bdao.selectOne(id);
	}
	
	public int post(PostVo pvo) {
		System.out.println("[p.service]: write()");
		return pdao.insert(pvo);
	}

}
