package com.atguigu.survey.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.manager.Res;

public class ResCatchInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private ResService resService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(handler instanceof DefaultServletHttpRequestHandler){
			return true;
		}
		String servletPath = request.getServletPath();
		String[] split = servletPath.split("/");
		servletPath="/"+split[1]+"/"+split[2]+"/"+split[3];
		Res res=resService.findResByServletPath(servletPath);
		if(res!=null){
			return true;
		}
		
		int finalResCode=1;
		int finaldefResPos=0;
		int maxResCode=1<<30;
		
		
		Integer leadingResPos=resService.getMaxResPos();//得到最大权限位
		
		Integer leadingResCode=(leadingResPos==null)?null:resService.getMaxResCode(leadingResPos);//得到最大权限位中的最大权限码
		
		
		if(leadingResPos!=null){
			if(leadingResCode<maxResCode){
				finalResCode=leadingResCode<<1;
				finaldefResPos=leadingResPos;
			}else{
				finaldefResPos=leadingResPos+1;
			}
		}
		Res resource = new Res(null, servletPath, finalResCode, finaldefResPos, false);
		resService.saveRes(resource);
		return true;
	}
}
