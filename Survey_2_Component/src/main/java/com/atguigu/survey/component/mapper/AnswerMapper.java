package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.guest.Answer;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer answerId);

    int insert(Answer record);

    Answer selectByPrimaryKey(Integer answerId);

    List<Answer> selectAll();

    int updateByPrimaryKey(Answer record);

	void batchInsert(@Param("answers")List<Answer> answers);

	int getQuestionEngageCount(Integer questionId);

	int getoptionEngagedCount(@Param("questionId")Integer questionId,@Param("index") String index);

	int getSurveyEngageCount(Integer surveyId);

	List<Answer> selectAnswersByQuestionId(Integer questionId);

	List<Answer> selectAnswerBysurveyId(Integer surveyId);
}