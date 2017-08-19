package com.atguigu.survey.component.service.i;

import java.util.Map;

import com.atguigu.survey.entities.guest.Survey;
import com.github.pagehelper.PageInfo;

public interface EngageService {

	PageInfo<Survey> showAllAvailablesurvey(Integer pageNum);

	void saveAnswer(Map<Integer, Map<String, String[]>> allBagMap, Integer surveyId);

	Survey getSurveDeeply(Integer surveyId);

}
