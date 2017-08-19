package com.atguigu.survey.log.thread;

import javax.servlet.http.HttpServletRequest;

public class RequestBinder {

	private static ThreadLocal<HttpServletRequest> threadLocal=new ThreadLocal<>();
	
	public static void setRequest(HttpServletRequest request){
		threadLocal.set(request);
	}
	
	public static HttpServletRequest getRequest(){
		HttpServletRequest servletRequest = threadLocal.get();
		
		return servletRequest;
	}
	
	public static void removeRequest(){
		threadLocal.remove();
	}
}
