package com.atguigu.survey.component.service.m;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.QuestionMapper;
import com.atguigu.survey.component.service.i.QuestionService;
import com.atguigu.survey.entities.guest.Question;
@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Override
	public void saveQuestion(Question question) {
		questionMapper.insert(question);
	}

	
	public void removeQuestion(Integer questionId) {
		questionMapper.deleteByPrimaryKey(questionId);
		
	}


	@Override
	public Question getQuestionById(Integer questionId) {
		Question question = questionMapper.selectByPrimaryKey(questionId);
		return question;
		
	}


	
	public void updateQuestion(Question question) {
		questionMapper.updateByPrimaryKey(question);
		
	}

}
