package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao udao;
	
	@Autowired
	private BlogDao bdao; 
	
	//회원가입
	public void join(UserVo uvo) {
		System.out.println("[u.service]: join()");	
		udao.insert(uvo);
		bdao.insert(uvo.getId(), uvo.getUserName());	
	}
	
	//로그인
	public UserVo login(UserVo uvo) {
		System.out.println("[u.service]: login()");
		
		return udao.selectlogin(uvo);
	}
	
	//아이디체크
	public String idcheck(String id) {
		System.out.println("[u.service]: idcheck()");
		
		UserVo uvo = udao.selectOne(id);
		String result = "";
		
		if(id == "") {
			result = "empty";
		}else if(uvo == null) {
			result = "can";
		} else {
			result = "cant";
		}
		
		return result;
	}
}
