package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.BagMapper;
import com.atguigu.survey.component.mapper.QuestionMapper;
import com.atguigu.survey.component.mapper.SurveyMapper;

import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Survey;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private BagMapper bagMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Override
	public void saveSurvey(Survey survey) {
		surveyMapper.insert(survey);
	}
	
	@Override
	public PageInfo<Survey> getMyUncompletedSurvey(Integer pageNum,Integer userId) {
		boolean surveyComplete=false;
		PageHelper.startPage(pageNum, 5);
		List<Survey> list=surveyMapper.selectAllMySurvey(userId,surveyComplete);
		PageInfo<Survey> pageInfo = new PageInfo<>(list, 5);
		return pageInfo;
	}

	@Override
	public Survey getSurveyById(Integer surveyId) {
		Survey survey = surveyMapper.selectByPrimaryKey(surveyId);
		return survey;
	}

	

	@Override
	public Survey getSurveDeeply(Integer surveyId) {
		
		Survey survey=surveyMapper.getSurveDeeply(surveyId);
		
		return survey;
	}

	@Override
	public void deleteSurvey(Integer surveyId) {
		surveyMapper.deleteByPrimaryKey(surveyId);
		
	}

	@Override
	public void updateSurvey(Survey survey) {
		surveyMapper.updateByPrimaryKey(survey);
		
	}

	@Override
	public void completeSurvey(Integer surveyId) {
		surveyMapper.completeSurvey(surveyId);
	}

	@Override
	public void deeplyRemoveSurvey(Integer surveyId) {
		List<Bag> bags = bagMapper.selectBySurveyId(surveyId);
		for(Bag bag:bags){
			Integer bagId = bag.getBagId();
			questionMapper.removeQuestionsInBagId(bagId);
			bagMapper.deleteByPrimaryKey(bagId);
		}
		surveyMapper.deleteByPrimaryKey(surveyId);
	}

}
