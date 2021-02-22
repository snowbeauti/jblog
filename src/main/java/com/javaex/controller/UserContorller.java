package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserContorller {
	
	@Autowired
	private UserService uservice;
	
	//회원가입폼
	@RequestMapping(value = "/joinForm")
	public String foinform() {
		System.out.println("[u.cnt] : joinform()");
		
		return "/user/joinForm";
	}
	
	//회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo uvo) {
		System.out.println("[u.cnt] : join()");
		System.out.println("uvo: " + uvo.toString());
		
		uservice.join(uvo);
		
		return "/user/joinSuccess";
	}
	
	//로그인폼
	@RequestMapping(value = "/loginForm")
	public String loginform() {
		System.out.println("[u.cnt] : loginform()");
		
		return "/user/loginForm";
	}
	
	//로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo uvo, HttpSession session) {
		System.out.println("[u.cnt] : login()");
		
		UserVo authUser = uservice.login(uvo);
		
		
		if (authUser == null) {
			System.out.println("로그인실패");

			return "redirect:/user/loginForm?result=fail";
		} else {
			System.out.println("로그인성공");
			session.setAttribute("authUser", authUser);
			System.out.println("id: " + authUser.getId());

			return "redirect:/";
		}
		
	}
	
	//로그아웃
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		System.out.println("[u.cnt] : logout()");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원가입 아이디체크
	@ResponseBody
	@RequestMapping(value = "/idcheck", method= {RequestMethod.GET, RequestMethod.POST})
	public String idcheck(@RequestParam("id") String id) {
		System.out.println("[u.cnt] : idcheck(), id: " + id);
		String result = uservice.idcheck(id);
		
		return result;
	}
	

}
