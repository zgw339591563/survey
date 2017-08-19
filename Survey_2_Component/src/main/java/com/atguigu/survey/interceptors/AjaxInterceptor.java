package com.atguigu.survey.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.e.AdminOperationForbiddenException;
import com.atguigu.survey.e.AuthForbiddenException;
import com.atguigu.survey.e.OperationForbiddenException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;
import com.google.gson.Gson;

public class AjaxInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(handler instanceof DefaultServletHttpRequestHandler){
			
			return true;
		}
		
		Map parameterMap = request.getParameterMap();
		Object object = parameterMap.get("ajaxRequest");
		if(object!=null){
			HttpSession session = request.getSession();
			String servletPath = request.getServletPath();
			String[] split = servletPath.split("/");
			servletPath = "/" + split[1] + "/" + split[2] + "/" + split[3];
			User user = (User) session.getAttribute(GlobalSettings.LOGIN_USER);
			Admin admin = (Admin) session.getAttribute(GlobalSettings.LOGIN_Admin);
			if (user == null && admin == null) {
				if(servletPath.startsWith("/guest")){
					 response.getWriter().write("pleaselogin");
					  return false;
				}
				if(servletPath.startsWith("/manager")){
					  Gson gson = new Gson();
					  String pleaselogin = gson.toJson("pleaselogin");
					  System.out.println(pleaselogin);
					  response.getWriter().write(pleaselogin);
					  return false;
				}
				
			}
			
		}
		return true;
	}
}
