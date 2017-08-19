package com.atguigu.survey.component.service.i;


import java.util.List;

import com.atguigu.survey.entities.guest.Bag;

public interface BagService {

	void saveBag(Bag bag);

	List<Bag> getSurveyBags(Integer surveyId);

	void updateBags(List<Bag> bags);

	void removeBag(Integer bagId);

	Bag getBag(Integer bagId);

	void updateBag(Bag bag);

	void batchUpdatebags(List<Integer> bagIds, List<Integer> bagOrders);

	void removeBagDeeply(Integer bagId);

	void moveToThisSurvey(Integer surveyId, Integer bagId);

	void copyToThisSurvey(Integer surveyId, Integer bagId);


}
