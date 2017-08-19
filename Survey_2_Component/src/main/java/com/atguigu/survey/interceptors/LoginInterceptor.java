package com.atguigu.survey.interceptors;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.e.AdminOperationForbiddenException;
import com.atguigu.survey.e.UserOperationForbiddenException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.utils.GlobalSettings;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("到preHandle里来了");
		// 静态资源全部放行
		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}
		// 公共资源全部放行
		Set<String> res = new HashSet<>();

		res.add("/guest/user/toLoginUI");
		res.add("/guest/user/toRegistUI");
		res.add("/guest/user/login");
		res.add("/guest/user/regist");
		res.add("/guest/user/logout");
		res.add("/manager/admin/toMainUI");
		res.add("/manager/admin/to_loginUI");
		res.add("/manager/admin/login");
		res.add("/manager/admin/logout");
		String servletPath = request.getServletPath();
		if (res.contains(servletPath)) {
			return true;
		}

		if (servletPath.startsWith("/guest")) {
			User user = (User) request.getSession().getAttribute(GlobalSettings.LOGIN_USER);
			if (user == null) {
				throw new UserOperationForbiddenException(GlobalSettings.User_Operation_Forbidden);
			}
			return true;
		}
		
		if (servletPath.startsWith("/manager")) {
			Admin admin = (Admin) request.getSession().getAttribute(GlobalSettings.LOGIN_Admin);
			if (admin == null) {
				throw new AdminOperationForbiddenException(GlobalSettings.AdminOperationForbidden);
			}
			return true;
		}
		return true;
	}
}
