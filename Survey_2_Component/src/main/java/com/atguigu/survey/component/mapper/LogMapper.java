package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.manager.Log;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(@Param("log")Log log,@Param("logTableName")String logTableName);

    Log selectByPrimaryKey(Integer logId);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);

	void CreateLogTableByName(@Param("tableName")String tableName);

	List<String> selectAllLogtableNamesInSurvey_log(String tableName);

	List<Log> getAllLogInSurvey_log(@Param("logTableNames") List<String> logTableNames);
}