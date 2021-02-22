package com.javaex.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Controller
public class Categorycontroller {
	
	@Autowired
	private CategoryService cservice;

	
	// 카테고리 관리 폼
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
	public List<CategoryVo>list(@PathVariable("id") String id, 
								HttpSession session, 
								Model model) {

		model.addAttribute("bvo", cservice.blogselectOne(id));
		model.addAttribute("authUser", ((UserVo) session.getAttribute("authUser")));		
		
		return cservice.cList();
	}

	//카테고리 추가 
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/add", method = { RequestMethod.GET, RequestMethod.POST })
	public CategoryVo add(@PathVariable("id") String id,
						  @RequestBody CategoryVo cvo) {
		
		return cservice.addResultVo(cvo);
	}
	
	// 카테고리 삭제
	@ResponseBody
	@RequestMapping(value = "/{id}/admin/category/remove", method = { RequestMethod.GET, RequestMethod.POST })
	public int remove(@ModelAttribute CategoryVo cvo) {


		return cservice.remove(cvo);
	}
}
