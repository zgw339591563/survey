package com.atguigu.survey.component.handler.guest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.survey.component.service.i.UserService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.utils.GlobalSettings;

@Controller
public class UserHandler {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("guest/user/regist")
	public String regist(User user,HttpSession httpSession){
		System.out.println("user:"+user);
		userService.regist( user);
		 httpSession.setAttribute(GlobalSettings.LOGIN_USER, user);
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("guest/user/login")
	public String login(User user,HttpSession httpSession){
		
		
		User loginUser=userService.login(user);
		
		httpSession.setAttribute(GlobalSettings.LOGIN_USER, loginUser);
		
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("guest/user/logout")
	public String logout(HttpSession httpSession){
		
		//httpSession.removeAttribute(GlobalSettings.LOGIN_USER);
		httpSession.invalidate();
		return "redirect:/index.jsp";
	}
}
