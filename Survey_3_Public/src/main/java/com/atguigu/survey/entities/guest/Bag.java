package com.atguigu.survey.entities.guest;

import java.io.Serializable;
import java.util.List;


public class Bag implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Integer bagId;

    private String bagName;

    private Integer bagOrder;

    private Integer surveyId;

    private List<Question> questions;
    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

    public String getBagName() {
        return bagName;
    }

    public void setBagName(String bagName) {
        this.bagName = bagName == null ? null : bagName.trim();
    }

    public Integer getBagOrder() {
        return bagOrder;
    }

    public void setBagOrder(Integer bagOrder) {
        this.bagOrder = bagOrder;
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Bag [bagId=" + bagId + ", bagName=" + bagName + ", bagOrder=" + bagOrder + ", surveyId=" + surveyId
				+ ", questions=" + questions + "]";
	}
	
}