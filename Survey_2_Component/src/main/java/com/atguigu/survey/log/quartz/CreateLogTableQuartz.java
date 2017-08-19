package com.atguigu.survey.log.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.log.thread.RoutingKeyBinder;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;

public class CreateLogTableQuartz extends QuartzJobBean {

	
	private LogService logService;
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		String createLogTableName = DataProcessUtils.createLogTableName(4);
		RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
		logService.AutoCreateLogTable(createLogTableName);
		createLogTableName = DataProcessUtils.createLogTableName(5);
		RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
		logService.AutoCreateLogTable(createLogTableName);
		createLogTableName = DataProcessUtils.createLogTableName(6);
		RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
		logService.AutoCreateLogTable(createLogTableName);
	}
	
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

}
