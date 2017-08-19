package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.guest.Question;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer questionId);

    int insert(Question record);

    Question selectByPrimaryKey(Integer questionId);

    List<Question> selectAll();

    int updateByPrimaryKey(Question record);

	void removeQuestionsInBagId(Integer bagId);

	void batchInsert(@Param("questions")List<Question> questions,@Param("bagId") Integer bagId);
}