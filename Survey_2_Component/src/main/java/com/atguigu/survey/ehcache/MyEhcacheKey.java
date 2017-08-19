package com.atguigu.survey.ehcache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;

public class MyEhcacheKey implements KeyGenerator{

	@Override
	public Object generate(Object target, Method method, Object... params) {
		
		StringBuffer keyBuffer =new StringBuffer();
		String targetName = target.getClass().getName();
		keyBuffer.append(".").append(targetName);
		String methodName = method.getName();
		keyBuffer.append(".").append(methodName);
		for(int i=0;params!=null&&i<params.length;i++){
			keyBuffer.append(",").append(params[i]);
		}
		String key = keyBuffer.substring(1).toString();
		return key;
	}

}
