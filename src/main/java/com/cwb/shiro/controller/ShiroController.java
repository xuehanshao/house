package com.cwb.shiro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.Md5CredentialsMatcher;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cwb.shiro.model.User;
import com.cwb.shiro.service.ShiService;

@Controller
public class ShiroController {
	
	@Autowired
	ShiService shiService;
	
	
	private static final Logger logger = Logger.getLogger(ShiroController.class);
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        logger.info("======用户进入了ShiroController的/login.html");
        return "login";
    }

    @RequestMapping(value = "/logout")
    public String doLogout(HttpServletRequest request, Model model) {
        logger.info("======用户"+request.getSession().getAttribute("user")+"退出了系统");
        SecurityUtils.getSubject().logout();
        
        return "redirect:login.do";
    }

    @RequestMapping(value="/doLogin",method=RequestMethod.POST)
    public String doLogin(User user,HttpServletRequest request, Model model){
        logger.info("======用户进入了ShiroController的/doLogin.html");
        String msg ;
        
        
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(true);
        //token.setHost("asdadsadasd");
        Subject subject = SecurityUtils.getSubject();
        try {
        	//  使用用户令牌登录
            subject.login(token);
            
             //  使用验证方法 验证是否登录
            if (subject.isAuthenticated()) {
                request.getSession().setAttribute("user",user);
                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                // 获取保存的URL
                if (savedRequest == null || savedRequest.getRequestUrl() == null) {
                    return "views/admin/home";
                } else {
                    //String url = savedRequest.getRequestUrl().substring(12, savedRequest.getRequestUrl().length());
                    return "forward:" + savedRequest.getRequestUrl();
                }
            } else {
                return "/login";
            }
        } catch (IncorrectCredentialsException e) {
            msg = "登录密码错误";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExcessiveAttemptsException e) {
            msg = "登录失败次数过多";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (LockedAccountException e) {
            msg = "帐号已被锁定. The account for username " + token.getPrincipal() + " was locked.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (DisabledAccountException e) {
            msg = "帐号已被禁用. The account for username " + token.getPrincipal() + " was disabled.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (ExpiredCredentialsException e) {
            msg = "帐号已过期. the account for username " + token.getPrincipal() + "  was expired.";
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnknownAccountException e) {
            msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
            model.addAttribute("message", msg);
            System.out.println(msg);
        } catch (UnauthorizedException e) {
            msg = "您没有得到相应的授权！" + e.getMessage();
            model.addAttribute("message", msg);
            System.out.println(msg);
        }
        return "/login";
    }
	
}
