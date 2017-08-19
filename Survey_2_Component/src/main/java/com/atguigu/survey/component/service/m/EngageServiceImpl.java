package com.atguigu.survey.component.service.m;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.AnswerMapper;
import com.atguigu.survey.component.mapper.SurveyMapper;
import com.atguigu.survey.component.service.i.EngageService;
import com.atguigu.survey.entities.guest.Answer;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.utils.DataProcessUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class EngageServiceImpl implements EngageService{

	@Autowired
	private SurveyMapper surveyMapper;
	
	@Autowired
	private AnswerMapper answerMapper;
	@Override
	public PageInfo<Survey> showAllAvailablesurvey(Integer pageNum) {

		PageHelper.startPage(pageNum, 5);
		List<Survey> list = surveyMapper.selectAllAvailablesurvey();
		PageInfo<Survey> pageInfo = new PageInfo<>(list, 5);
		return pageInfo;
	}
	@Override
	public void saveAnswer(Map<Integer, Map<String, String[]>> allBagMap, Integer surveyId) {
		String uuid = UUID.randomUUID().toString();
		Collection<Map<String, String[]>> values = allBagMap.values();
		List<Answer> answers = new ArrayList<>();
		for(Map<String, String[]> map:values){//大循环的一次遍历是遍历一个包裹的数据
			Set<String> keySet = map.keySet();
			
			for(String paramName:keySet){//遍历一个包裹中的所有问题
				if(!paramName.startsWith("q")){
					continue;
				}
				String[] paramVals = map.get(paramName);
				Integer questionId = DataProcessUtils.parseQuestionId(paramName);
				String answerContent = DataProcessUtils.joinAnswerContent(paramVals);
				Answer answer = new Answer(null, answerContent, uuid, surveyId, questionId);
				
				answers.add(answer);
			}
			
		}
		answerMapper.batchInsert(answers);
		
	}
	@Override
	public Survey getSurveDeeply(Integer surveyId) {
		Survey survey=surveyMapper.getSurveDeeply(surveyId);
		
		return survey;
	}

	
	
}
