package com.cwb.shiro.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	// 登录成功的页面
    @RequestMapping(value = "/admin/home")
    public String adminHomePage(){
        return "views/admin/home";
    }

    // 只有角色为admin的才能访问
    @RequiresRoles("admin")
    @RequestMapping(value = "/admin/role")
    public String adminWithRole(){
        return "views/admin/withrole";
    }

    // 只用具有一组权限才能访问
    @RequiresPermissions(value={"一组"}, logical= Logical.AND)
    @RequestMapping(value = "/admin/auth")
    public String adminWithAuth(){
        return "views/admin/withauth";
    }
    
    @RequiresRoles("user")
    @RequestMapping(value="/user/role")
    public String userWithRole(Model model){
    	model.addAttribute("login", "你没有权限创建角色");
        return "views/admin/withrole";
    }
    
    @RequiresPermissions(value={"二组"}, logical= Logical.AND)
    @RequestMapping(value = "/user/auth")
    public String userWithAuth(){
        return "views/admin/withauth";
    }
    
}
