package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.LogMapper;
import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.utils.DataProcessUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogMapper logMapper;
	@Override
	public void insertLog(Log log) {
		String logTableName = DataProcessUtils.createLogTableName(0);//当月的表名
		logMapper.insert(log,logTableName);
	}
	@Override
	public void AutoCreateLogTable(String tableName) {
		logMapper.CreateLogTableByName(tableName);
		
	}
	@Override
	public PageInfo<Log> showList(Integer pageNum, Integer pageSize) {
		
		List<String> logTableNames=logMapper.selectAllLogtableNamesInSurvey_log("survey_log");
		PageHelper.startPage(pageNum, pageSize);
		List<Log> logList=logMapper.getAllLogInSurvey_log(logTableNames);
		PageInfo<Log> pageInfo = new PageInfo<>(logList, 5);
		return pageInfo;
	}

}
