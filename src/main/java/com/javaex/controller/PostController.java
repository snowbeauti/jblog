package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.javaex.service.PostService;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class PostController {

	@Autowired
	private PostService pservice;

	
	// 글작성 폼
	@RequestMapping(value = "/{id}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminwriteform(@PathVariable("id") String id, 
								 HttpSession session, 
								 Model model) {

		model.addAttribute("bvo", pservice.blogselectOne(id));
		model.addAttribute("authUser", ((UserVo) session.getAttribute("authUser")));

		return "/blog/admin/blog-admin-write";
	}
	
	//글작성
	@RequestMapping(value = "/{id}/admin/post", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminwrite(@PathVariable("id") String id,
						     @ModelAttribute PostVo pvo,
							 HttpSession session, 
							 Model model) {
		
		int count = pservice.post(pvo);
		System.out.println(count + "건 작성: " + pvo);
		
		model.addAttribute("bvo", pservice.blogselectOne(id));
		model.addAttribute("authUser", ((UserVo) session.getAttribute("authUser")));
		
		

		return "/blog/admin/blog-admin-write";
	}
}
