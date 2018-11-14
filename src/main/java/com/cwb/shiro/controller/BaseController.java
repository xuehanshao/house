package com.cwb.shiro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

//import net.sf.json.JSONSerializer;
//import net.sf.json.JsonConfig;

import com.alibaba.fastjson.JSON;

public class BaseController {

	public void writeJson(Object object, HttpServletResponse response){
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void outString(String json, HttpServletResponse response) {
		PrintWriter writer = null;
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		try {
			writer = response.getWriter();
			writer.write(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				writer.close();
				writer = null;
			}
		}
	}
	
//	protected void writeJson2(Object obj, HttpServletResponse response){
//		 JsonConfig jsonConfig = new JsonConfig();
////        jsonConfig.setExcludes(new String[]{"memberRank","hibernateLazyInitializer"});      
//        String jsonStr = JSONSerializer.toJSON(obj, jsonConfig).toString();
//        System.out.println(jsonStr);
//        ajaxOut(jsonStr, response);
//	}
//	
//	protected void ajaxOut(String jsonStr, HttpServletResponse response){
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		try {
//			response.getWriter().write(jsonStr);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
