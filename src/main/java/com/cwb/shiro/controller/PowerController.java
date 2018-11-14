package com.cwb.shiro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cwb.shiro.model.Power;
import com.cwb.shiro.model.Relo;
import com.cwb.shiro.model.State;
import com.cwb.shiro.model.User;
import com.cwb.shiro.service.ShiService;

@Controller
@RequestMapping("power")
public class PowerController extends BaseController{
	
	@Resource
	private ShiService shiService;
	
	// 只有角色为admin的才能访问
    @RequiresRoles(value={"admin"},logical=Logical.AND)
	@RequestMapping("queryTree")
	public void queryTree(HttpServletResponse response){
		List<Power> list = shiService.findTree(0);
		List<Power> parentTree = parentTree(list);
		super.writeJson(parentTree, response);
		
	}

	
	public  List<Power> parentTree(List<Power> list){
		List<Power>  childList=new ArrayList<Power>();
		
		for (int i = 0; i < list.size(); i++) {
			Power menus = list.get(i);  
			
			List<Power> findTree = shiService.findTree(menus.getId());
			
			if(findTree.size()>0){   
				
				List<Power> getchirdMenu = getchirdMenu(findTree);
				
				menus.setNodes(getchirdMenu);
				childList.add(menus);
			}else{
				childList.add(menus);
			}
		}
		
		return  childList;
	}
	
	
	public  List<Power> getchirdMenu(List<Power> list){
		List<Power>  childList=new ArrayList<Power>();
		for (int i = 0; i < list.size(); i++) {
			Power menus = list.get(i);  
			List<Power> findTree = shiService.findTree(menus.getId());
			
			if(findTree.size()>0){
				List<Power> getchirdMenu = getchirdMenu(findTree);
				
				menus.setNodes(getchirdMenu);
				childList.add(menus);
			}else{
				
				childList.add(menus);
			}
		}
		
		return   childList;
	};
	
	// 只用具有一组权限才能访问
    @RequiresPermissions(value={"一组","二组"}, logical= Logical.AND)
	@RequestMapping("queryUsers")
	public void queryUsers(HttpServletResponse response,HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		List<User> list = shiService.queryUserById(user.getUsername(),user.getPassword());
		request.getSession().setAttribute("userid", list.get(0).getUserid());
		super.writeJson(list, response);
	}
	
	
	
	@RequestMapping("queryRelos")
	public void queryRelos(Integer id,HttpServletResponse response,HttpServletRequest request){
		//查询所有角色
		List<Relo> list1 = shiService.queryRelos();
		//根据id查询该用户所拥有的角色
		List<Relo> list = shiService.queryReloById(id);
		
		for (int i = 0; i < list1.size(); i++) {
			for (int j = 0; j < list.size(); j++) {  
				if (list1.get(i).getRid()==list.get(j).getRid()) {
					list1.get(i).setState(true);
					continue;
				}
			}
		}
	
		super.writeJson(list1, response);
	}
   
	

	@RequestMapping("addRelo")
	@ResponseBody
	public void addRelo(Integer id,String rids){
		shiService.deleRelo(id);
		String[] str = rids.split(",");
		//for (int i = 0; i < str.length; i++) {
		shiService.addRelo(id,str);
		//}
		
	}
	
	
	@RequestMapping("getRelo")
	public void getRelo(HttpServletResponse response,HttpServletRequest request){
		Integer id = (Integer) request.getSession().getAttribute("userid");
		List<Relo> list1 = shiService.queryRelo(id);
		super.writeJson(list1, response);
	}
	
	@RequestMapping(value="queryPower", produces = "application/json; charset=utf-8")  
	@ResponseBody
	public String queryPower(Integer id,HttpServletResponse response){
		List<Power> list = shiService.queryPower(0);
		List<Power> list1 = shiService.queryPowerById(id);
		List<Power> parentTree = parentPower(list,list1);
	
		return JSON.toJSONStringWithDateFormat(parentTree, "yyyy-MM-dd", SerializerFeature.DisableCircularReferenceDetect);
		//super.writeJson(parentTree, response);
         		
	}
	
	public  List<Power> parentPower(List<Power> list, List<Power> list1){
		List<Power> childList = new ArrayList<Power>();
		State state = new State();
		state.setChecked(true);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list1.size(); j++) {
				if (list.get(i).getId()==list1.get(j).getId()) {
					list.get(i).setState(state);
					continue;
				}
			}
			Power menus = list.get(i);  
			
			List<Power> findTree = shiService.queryPower(menus.getId());
			
			if(findTree.size()>0){  
				
				List<Power> getchirdMenu = getchirdMenus(findTree,list1);
				menus.setNodes(getchirdMenu);
				childList.add(menus);
			}else{
				childList.add(menus);
			}
		}
		
		return  childList;
	}
	
	public  List<Power> getchirdMenus(List<Power> list, List<Power> list1){
		List<Power>  childList=new ArrayList<Power>();
		State state = new State();
		state.setChecked(true);
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list1.size(); j++) {
				if (list.get(i).getId()==list1.get(j).getId()) {
					list.get(i).setState(state);
					continue;
				}
			}
			Power menus = list.get(i);  
			List<Power> findTree = shiService.queryPower(menus.getId());
			
			if(findTree.size()>0){
				List<Power> getchirdMenu = getchirdMenus(findTree, list1);
				 for (int j = 0; j < getchirdMenu.size(); j++) {
						for (int j2 = 0; j2 < list1.size(); j2++) {
							if (getchirdMenu.get(j).getId()==list1.get(j2).getId()) {
								getchirdMenu.get(j).setState(state);
								continue;
							}
						}
					}
				menus.setNodes(getchirdMenu);
				childList.add(menus);
			}else{
				
				childList.add(menus);
			}
		}
		
		return   childList;
	};
	
	

	@RequestMapping("addPower")
	@ResponseBody
	public void addPower(Integer id,String pids){
		shiService.deletePowerById(id);
		String[] str = pids.split(",");
		shiService.addPowerById(id,str);
		
	}
	

}
