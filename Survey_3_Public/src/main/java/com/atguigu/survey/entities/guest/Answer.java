package com.atguigu.survey.entities.guest;

public class Answer {
    private Integer answerId;

    private String answerContent;

    private String answerUuid;

    private Integer surveyId;

    private Integer questionId;

    public Answer() {
		super();
	}

	public Answer(Integer answerId, String answerContent, String answerUuid, Integer surveyId, Integer questionId) {
		super();
		this.answerId = answerId;
		this.answerContent = answerContent;
		this.answerUuid = answerUuid;
		this.surveyId = surveyId;
		this.questionId = questionId;
	}

	public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent == null ? null : answerContent.trim();
    }

    public String getAnswerUuid() {
        return answerUuid;
    }

    public void setAnswerUuid(String answerUuid) {
        this.answerUuid = answerUuid == null ? null : answerUuid.trim();
    }

    public Integer getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Integer surveyId) {
        this.surveyId = surveyId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answerContent=" + answerContent + ", answerUuid=" + answerUuid
				+ ", surveyId=" + surveyId + ", questionId=" + questionId + "]";
	}
    
}