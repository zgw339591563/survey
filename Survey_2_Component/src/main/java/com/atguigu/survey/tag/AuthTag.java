package com.atguigu.survey.tag;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;

public class AuthTag extends SimpleTagSupport {

	private String servletPath;
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) getJspContext();
		ServletContext servletContext = pageContext.getServletContext();
		WebApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		ResService resService = ioc.getBean(ResService.class);
		Res res = resService.findResByServletPath(servletPath);
		
		HttpSession session = pageContext.getSession();
		Admin admin = (Admin)session.getAttribute(GlobalSettings.LOGIN_Admin);
		if(admin!=null){
			String adminName = admin.getAdminName();
			if(adminName.equals("SuperAdmin")){
				getJspBody().invoke(null);
				return;
			}
		}
		/*User user=(User)session.getAttribute(GlobalSettings.LOGIN_USER);
		
		if(user!=null){
			String codeArr = user.getCodeArr();
			Boolean checkAuthority = DataProcessUtils.checkAuthority(res, codeArr);
			if(checkAuthority==true){
				getJspBody().invoke(null);
			}
		}*/
		
		if(admin!=null){
				String codeArr = admin.getCodeArr();
				Boolean checkAuthority = DataProcessUtils.checkAuthority(res, codeArr);
				if(checkAuthority==true){
					getJspBody().invoke(null);
				}
		}
	}
	
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
}
