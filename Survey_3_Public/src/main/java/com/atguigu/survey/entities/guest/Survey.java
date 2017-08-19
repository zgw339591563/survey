package com.atguigu.survey.entities.guest;

import java.util.List;


public class Survey {
    private Integer surveyId;

    private String surveyName;

    private Boolean surveyComplete=false;

    private String logoPath="surveyLogos/logo.gif";

    private Integer userId;
    private List<Bag> bags;

    public Survey(String surveyName, Boolean surveyComplete, String logoPath, Integer userId) {
		super();
		this.surveyName = surveyName;
		this.surveyComplete = surveyComplete;
		this.logoPath = logoPath;
		this.userId = userId;
	}

	public Survey() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public String getSurveyName() {
        return surveyName;
    }

    public void setSurveyName(String surveyName) {
        this.surveyName = surveyName == null ? null : surveyName.trim();
    }

    public Boolean getSurveyComplete() {
        return surveyComplete;
    }

    public void setSurveyComplete(Boolean surveyComplete) {
        this.surveyComplete = surveyComplete;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath == null ? null : logoPath.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public List<Bag> getBags() {
		return bags;
	}

	public void setBags(List<Bag> bags) {
		this.bags = bags;
	}

	@Override
	public String toString() {
		return "Survey [surveyId=" + surveyId + ", surveyName=" + surveyName + ", surveyComplete=" + surveyComplete
				+ ", logoPath=" + logoPath + ", userId=" + userId + ", bags=" + bags + "]";
	}
	
}