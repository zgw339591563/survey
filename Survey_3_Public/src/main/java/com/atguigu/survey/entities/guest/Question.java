package com.atguigu.survey.entities.guest;

import java.io.Serializable;
import java.util.List;

import com.atguigu.survey.utils.DataProcessUtils;


public class Question  implements Serializable{
    
	private static final long serialVersionUID = 1L;

	private Integer questionId;

    private String questionName;

    private Integer questionType;

    private String questionOptions;

    private Integer bagId;

    
    private List<Answer> answers;
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName == null ? null : questionName.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(String questionOptions) {
        this.questionOptions = questionOptions == null ? null : questionOptions.trim();
    }

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionName=" + questionName + ", questionType="
				+ questionType + ", questionOptions=" + questionOptions + ", bagId=" + bagId + ", answers=" + answers
				+ "]";
	}
	
	 public List<String> getOptionList() {
	    	return DataProcessUtils.convertJSONToList(questionOptions);
	    }

	
}