package com.atguigu.survey.log.router;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


import com.atguigu.survey.log.thread.RoutingKeyBinder;

public class SurveyLogRouter extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		
		String routingKey = RoutingKeyBinder.getRoutingKey();
		RoutingKeyBinder.removeRoutingKey();//得到Key后马上移除绑定的线程Key
		return routingKey;
	}
	
}
