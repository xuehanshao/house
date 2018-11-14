package com.cwb.shiro.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class CustomExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
        CustomException customException = null;
        //如果抛出的是系统自定义异常及其子类则直接转换
        if(e instanceof  CustomException){
            System.out.println("########################");
            System.out.println("正在转换异常信息！！！！！！！！！！");
            System.out.println("########################");
            customException = (CustomException) e;
        }else{
            //如果抛出的不是系统自定义异常及其子类则重新构造一个未知错误异常。
           e.printStackTrace();
            customException = new CustomException("发生未知错误，请与系统管理员联系！！！");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("errorMessage",customException.getMessage());
        modelAndView.setViewName("/admin.jsp");

        return modelAndView;
    }

}
