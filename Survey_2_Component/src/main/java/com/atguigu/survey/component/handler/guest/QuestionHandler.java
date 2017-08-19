package com.atguigu.survey.component.handler.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.QuestionService;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.utils.DataProcessUtils;

@Controller
public class QuestionHandler {

	@Autowired
	private QuestionService questionService;
	@RequestMapping("guest/question/toAddUI/{bagId}/{surveyId}")
	public String toAddUI(@PathVariable("bagId")Integer bagId,@PathVariable("surveyId")Integer surveyId){
		return "guest/question_addUI";
	}
	
	
	@RequestMapping("guest/question/saveQuestion")
	public String saveQuestion(Question question,@RequestParam("surveyId")Integer surveyId){
		
		DataProcessUtils.processOptionTojson(question);
		System.out.println("================options"+question.getQuestionOptions());
		questionService.saveQuestion(question);
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
	@RequestMapping("guest/question/removeQuestion/{questionId}/{surveyId}")
	public String removeQuestion(@PathVariable Integer questionId,@PathVariable("surveyId")Integer surveyId){
		questionService.removeQuestion(questionId);
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
	
	@RequestMapping("guest/question/toEditUI/{questionId}/{surveyId}")
	public String toEditUI(@PathVariable Integer questionId,@PathVariable("surveyId")Integer surveyId,Model model){
		Question question = questionService.getQuestionById(questionId);
		DataProcessUtils.processOptionFromJson(question);
		model.addAttribute("question", question);
		return "guest/question_EditUI";
	}
	//guest/question/updateQuestion
	@RequestMapping("guest/question/updateQuestion")
	public String updateQuestion(Question question,@RequestParam("surveyId")Integer surveyId,Model model){
		DataProcessUtils.processOptionTojson(question);
		questionService.updateQuestion(question);
		
		
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
}
