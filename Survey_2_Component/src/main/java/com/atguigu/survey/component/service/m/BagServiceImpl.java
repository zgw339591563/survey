package com.atguigu.survey.component.service.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.BagMapper;
import com.atguigu.survey.component.mapper.QuestionMapper;
import com.atguigu.survey.component.service.i.BagService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.utils.DataProcessUtils;

@Service
public class BagServiceImpl implements BagService{

	@Autowired
	private BagMapper bagMapper;
	@Autowired
	private QuestionMapper questionMapper;
	
	
	public void saveBag(Bag bag) {
		
		bagMapper.insert(bag);
		Integer bagId = bag.getBagId();
		bag.setBagOrder(bagId);
		bagMapper.updateByPrimaryKey(bag);
	}
	/**
	 * 通过surveyId查找相应的bag
	 */
	public List<Bag> getSurveyBags(Integer surveyId) {
		List<Bag> bags=bagMapper.selectBySurveyId(surveyId);
		return bags;
	}
	@Override
	public void updateBags(List<Bag> bags) {
		bagMapper.updateBags(bags);
		
	}
	@Override
	public void removeBag(Integer bagId) {
		
		bagMapper.deleteByPrimaryKey(bagId);
	}
	@Override
	public Bag getBag(Integer bagId) {
		Bag bag = bagMapper.selectByPrimaryKey(bagId);
		return bag;
	}
	@Override
	public void updateBag(Bag bag) {
		bagMapper.updateByPrimaryKey(bag);
		
	}
	@Override
	public void batchUpdatebags(List<Integer> bagIds, List<Integer> bagOrders) {
		
		List<Bag> bags = new ArrayList<>();
		int size = bagIds.size();
		
		for(int i=0;i<size;i++){
			Bag bag = new Bag();
			bag.setBagId(bagIds.get(i));
			bag.setBagOrder(bagOrders.get(i));
			bags.add(bag);
		}
		
		bagMapper.updateBags(bags);
	}
	@Override
	public void removeBagDeeply(Integer bagId) {
		
		questionMapper.removeQuestionsInBagId(bagId);
		bagMapper.deleteByPrimaryKey(bagId);
	}
	@Override
	public void moveToThisSurvey(Integer surveyId, Integer bagId) {
		Bag bag = bagMapper.selectByPrimaryKey(bagId);
		bag.setSurveyId(surveyId);
		bagMapper.updateByPrimaryKey(bag);
	}
	
	public void copyToThisSurvey(Integer surveyId, Integer bagId) {
	
		Bag bag = bagMapper.selectBagDeeply(bagId);
		
		bag =(Bag) DataProcessUtils.deeplyCopy(bag);
		List<Question> questions = bag.getQuestions();
		
		bag.setSurveyId(surveyId);
		bag.setBagId(null);
		bagMapper.insert(bag);
		bagId = bag.getBagId();
		questionMapper.batchInsert(questions, bagId);
	}
	

}
