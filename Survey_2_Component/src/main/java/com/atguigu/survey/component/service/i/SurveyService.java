package com.atguigu.survey.component.service.i;


import com.atguigu.survey.entities.guest.Survey;
import com.github.pagehelper.PageInfo;

public interface SurveyService {

	void saveSurvey(Survey survey);

	PageInfo<Survey> getMyUncompletedSurvey(Integer pageNum,Integer userId);

	Survey getSurveyById(Integer surveyId);

	Survey getSurveDeeply(Integer surveyId);

	void deleteSurvey(Integer surveyId);

	void updateSurvey(Survey survey);

	void completeSurvey(Integer surveyId);

	void deeplyRemoveSurvey(Integer surveyId);

}
