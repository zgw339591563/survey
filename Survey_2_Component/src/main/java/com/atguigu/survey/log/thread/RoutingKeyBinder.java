package com.atguigu.survey.log.thread;

public class RoutingKeyBinder {

	private static ThreadLocal<String> threadLocal=new ThreadLocal<>();
	
	public static void setRoutingKey(String routingKey){
		threadLocal.set(routingKey);
	}
	
	public static String getRoutingKey(){
		String routingKey = threadLocal.get();
		return routingKey;
	}
	
	public static void removeRoutingKey(){
		threadLocal.remove();
	}
}
