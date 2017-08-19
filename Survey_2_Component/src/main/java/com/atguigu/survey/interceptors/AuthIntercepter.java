package com.atguigu.survey.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.e.AdminOperationForbiddenException;
import com.atguigu.survey.e.AuthForbiddenException;
import com.atguigu.survey.e.OperationForbiddenException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;

public class AuthIntercepter extends HandlerInterceptorAdapter {


	@Autowired
	private ResService resService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler instanceof DefaultServletHttpRequestHandler) {
			return true;
		}

		String servletPath = request.getServletPath();
		String[] split = servletPath.split("/");
		servletPath = "/" + split[1] + "/" + split[2] + "/" + split[3];
		Res res = resService.findResByServletPath(servletPath);
		if (res != null && res.getPublicStatus() == true) {
			return true;
		}

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(GlobalSettings.LOGIN_USER);
		Admin admin = (Admin) session.getAttribute(GlobalSettings.LOGIN_Admin);
		if (user == null && admin == null) {
			if(servletPath.startsWith("/guest")){
				throw new OperationForbiddenException(GlobalSettings.OperationForbiddenException);
			}
			if(servletPath.startsWith("/manager")){
				throw new AdminOperationForbiddenException(GlobalSettings.ADMIN_OPERATION_FORBIDDEN_EXCEPTION);
			}
			
		}
		
		if(admin != null){
			String adminName = admin.getAdminName();
			if(adminName.equals("SuperAdmin")){
				return true;
			}
		}
		if(servletPath.startsWith("/guest")){
			if (user != null) {
	
				String userCodeArr = user.getCodeArr();
				Boolean checkAuthority = DataProcessUtils.checkAuthority(res, userCodeArr);
				if (checkAuthority == false) {
					throw new AuthForbiddenException(GlobalSettings.AuthForbiddenException);
				}
			}
		}
		if(servletPath.startsWith("/manager")){
			if (admin != null) {
				
					String adminCodeArr = admin.getCodeArr();
					Boolean checkAuthority = DataProcessUtils.checkAuthority(res, adminCodeArr);
					if (checkAuthority == false) {
						throw new AuthForbiddenException(GlobalSettings.AuthForbiddenException);
					}
			}
		}
		return true;
	}
}
