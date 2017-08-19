package com.atguigu.survey.log.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.log.thread.RoutingKeyBinder;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;



public class CreateTableListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private LogService logService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		ApplicationContext ioc = event.getApplicationContext();
		ApplicationContext parent = ioc.getParent();
		if(parent==null){
			String tableName = DataProcessUtils.createLogTableName(1);
			RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
			logService.AutoCreateLogTable(tableName);
			tableName = DataProcessUtils.createLogTableName(2);
			RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
			logService.AutoCreateLogTable(tableName);
			tableName = DataProcessUtils.createLogTableName(3);
			RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
			logService.AutoCreateLogTable(tableName);
		}
	}

}
