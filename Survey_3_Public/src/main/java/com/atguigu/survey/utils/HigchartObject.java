package com.atguigu.survey.utils;

public class HigchartObject {

	private String option;
	private Integer optionEngagedCount;
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public Integer getOptionEngagedCount() {
		return optionEngagedCount;
	}
	public void setOptionEngagedCount(Integer optionEngagedCount) {
		this.optionEngagedCount = optionEngagedCount;
	}
	public HigchartObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HigchartObject(String option, Integer optionEngagedCount) {
		super();
		this.option = option;
		this.optionEngagedCount = optionEngagedCount;
	}
}
