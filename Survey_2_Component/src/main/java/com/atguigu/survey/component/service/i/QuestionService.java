package com.atguigu.survey.component.service.i;

import com.atguigu.survey.entities.guest.Question;

public interface QuestionService {

	void saveQuestion(Question question);

	void removeQuestion(Integer questionId);

	Question getQuestionById(Integer questionId);

	void updateQuestion(Question question);

}
