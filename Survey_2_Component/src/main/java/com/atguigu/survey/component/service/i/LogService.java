package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.manager.Log;
import com.github.pagehelper.PageInfo;

public interface LogService {

	void insertLog(Log log);

	void AutoCreateLogTable(String tableName);

	PageInfo<Log> showList(Integer pageNum, Integer pageSize);

}
