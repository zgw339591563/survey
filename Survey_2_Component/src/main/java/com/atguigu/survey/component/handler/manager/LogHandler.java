package com.atguigu.survey.component.handler.manager;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.log.thread.RoutingKeyBinder;
import com.atguigu.survey.utils.GlobalSettings;
import com.github.pagehelper.PageInfo;

@Controller
public class LogHandler {

	@Autowired
	private LogService logService;
	//manager/log/showList
	@RequestMapping("manager/log/showList/{pageNum}")
	public String showList(@PathVariable("pageNum")Integer pageNum,Map<String,PageInfo<Log>> map){
		
		Integer pageSize=5;
		RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
		PageInfo<Log> page=logService.showList(pageNum,pageSize);
		map.put(GlobalSettings.PAGE, page);
		return "manager/log_list";
	}
}
