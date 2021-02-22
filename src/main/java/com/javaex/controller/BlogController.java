package com.javaex.controller;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService bservice;

	// 블로그 메인페이지
	@RequestMapping(value = "/{id}")
	public String jblog(@PathVariable("id") String id, 
			            HttpSession session, 
			            Model model) {


		model.addAttribute("pMap", bservice.selectOne(id));
		model.addAttribute("bvo", bservice.blogselectOne(id));
		model.addAttribute("authUser", ((UserVo) session.getAttribute("authUser")));

		
		return "/blog/blog-main";
	}

	// 블로그 기본설정 폼
	@RequestMapping(value = "/{id}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminBasicform(@PathVariable("id") String id, 
								 HttpSession session, 
								 Model model) {

		BlogVo bvo = bservice.blogselectOne(id);
		model.addAttribute("bvo", bvo);
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		model.addAttribute("authUser", authUser);
		
		System.out.println("cnt basic [bvo]: "+ bvo);
		return "/blog/admin/blog-admin-basic";
	}

	// 블로그 기본설정
	@RequestMapping(value = "/{id}/admin/basic/upload", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminBasic(@PathVariable("id") String id, 
							 @RequestParam("file") MultipartFile file,
			                 @ModelAttribute BlogVo bvo,
			                 Model model) {
		
		model.addAttribute("id", id);


		String saveName = bservice.restore(file, bvo);
		
		model.addAttribute("file", saveName);
		
		return "redirect:/{id}/admin/basic";
	}




}
