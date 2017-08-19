package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.guest.Survey;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SurveyMapper {
    int deleteByPrimaryKey(Integer surveyId);

    int insert(Survey record);

    Survey selectByPrimaryKey(Integer surveyId);

    List<Survey> selectAll();

    int updateByPrimaryKey(Survey record);

	List<Survey> selectAllMySurvey(@Param("userId") Integer userId,@Param("surveyComplete") boolean surveyComplete);

	Survey getSurveDeeply(Integer surveyId);

	void completeSurvey(Integer surveyId);

	List<Survey> selectAllAvailablesurvey();
}