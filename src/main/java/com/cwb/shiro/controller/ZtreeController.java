package com.cwb.shiro.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cwb.shiro.service.ShiService;

@Controller
@RequestMapping("ztree")
public class ZtreeController extends BaseController{

	@Resource
	private ShiService shiService;
	
	
	@RequestMapping("queryZtree")
	public void queryZtree(HttpServletResponse response){
		
		List list = shiService.queryZtree();
		super.writeJson(list, response);
	}
}
