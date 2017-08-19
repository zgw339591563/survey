package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.guest.Bag;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BagMapper {
    int deleteByPrimaryKey(Integer bagId);

    int insert(Bag record);

    Bag selectByPrimaryKey(Integer bagId);

    List<Bag> selectAll();

    int updateByPrimaryKey(Bag record);

	int selectMaxbagOrder();

	List<Bag> selectBySurveyId(Integer surveyId);

	void updateBags(@Param("bags")List<Bag> bags);

	Bag selectBagDeeply(Integer bagId);

}