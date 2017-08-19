package com.atguigu.survey.component.handler.guest;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.e.EmptyBagException;
import com.atguigu.survey.e.EmptySurveyNameException;
import com.atguigu.survey.e.FileTooLargeException;
import com.atguigu.survey.e.RemoveSurveyFailedException;
import com.atguigu.survey.e.SurveyNotBagException;
import com.atguigu.survey.e.UploadFileInvalidException;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.log.thread.RoutingKeyBinder;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public class SurveyHandler {

	@Autowired
	private SurveyService surveyService;
	
	@RequestMapping("guest/survey/saveSurvey")
	public String saveSurvey(@RequestParam("logoFile")MultipartFile logoFile,Survey survey,HttpSession httpSession) throws Exception{
		String surveyName = survey.getSurveyName();
		if(surveyName==null||"".equals(surveyName)){
			throw new EmptySurveyNameException(GlobalSettings.EmptySurveyNameException);
		}
		if(!logoFile.isEmpty()){
			InputStream inputStream = logoFile.getInputStream();
			long size = logoFile.getSize();
			if(size>500*1024){
				throw new FileTooLargeException(GlobalSettings.FileTooLargeException);
			}
			String contentType = logoFile.getContentType();
			if(!contentType.startsWith("image")){
				throw new UploadFileInvalidException(GlobalSettings.UploadFileInvalid);
			}
			String realPath = httpSession.getServletContext().getRealPath("/surveyLogos");//获得虚拟文件路径的真实路径
			String logo_path = DataProcessUtils.resizeImages(inputStream, realPath);
			survey.setLogoPath(logo_path);
		}
		
		User user =(User) httpSession.getAttribute(GlobalSettings.LOGIN_USER);
		Integer userId = user.getUserId();
		survey.setUserId(userId);
		surveyService.saveSurvey(survey);
		return "redirect:/guest/survey/showMyUncompleted/1";
	}
	
	//@PathVariable接收了的数据会将该数据放在request域中
	@RequestMapping("guest/survey/showMyUncompleted/{pageNum}")
	public String survey_uncompleted(@PathVariable("pageNum")Integer pageNum,HttpSession httpSession,Model model) throws Exception{
		User user = (User)httpSession.getAttribute(GlobalSettings.LOGIN_USER);
		PageInfo<Survey> page=surveyService.getMyUncompletedSurvey(pageNum, user.getUserId());
		model.addAttribute(GlobalSettings.PAGE, page);
		return "guest/survey_uncompleted";
	}
	
	
	@RequestMapping("guest/survey/toDesignUI/{surveyId}")
	public String toSurveyDesignUI(@PathVariable("surveyId") Integer surveyId,Model model){
		Survey survey=surveyService.getSurveDeeply(surveyId);
		model.addAttribute("survey", survey);
		return "guest/survey_design";
	}
	
	@RequestMapping("guest/survey/RemoveSurvey/{surveyId}/{pageNum}")
	public String removeSurvey(@PathVariable("surveyId") Integer surveyId,@PathVariable("pageNum")Integer pageNum){
		
		try {
			surveyService.deleteSurvey(surveyId);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if(cause!=null&&cause instanceof MySQLIntegrityConstraintViolationException ){
				throw new RemoveSurveyFailedException(GlobalSettings.RemoveSurveyFailedException);
			}
			throw e;
		}
		return "redirect:/guest/survey/showMyUncompleted/"+pageNum;
	}
	
	@RequestMapping("guest/survey/deeplyRemoveSurvey/{surveyId}/{pageNum}")
	public String deeplyRemoveSurvey(@PathVariable("surveyId") Integer surveyId,@PathVariable("pageNum")Integer pageNum){
		
		
			surveyService.deeplyRemoveSurvey(surveyId);
		
		return "redirect:/guest/survey/showMyUncompleted/"+pageNum;
	}
	
	
	
	@RequestMapping("guest/survey/toEditUI/{surveyId}/{pageNum}")
	public String toEditUI(@PathVariable("surveyId") Integer surveyId,@PathVariable("pageNum")Integer pageNum,Model model){
		
		Survey survey = surveyService.getSurveyById(surveyId);
		model.addAttribute("survey", survey);
		return "guest/survey_editUI";
	}
	
	//修改调查
	@RequestMapping("guest/survey/updateSurvey")
	public String updateSurvey( Survey survey,@RequestParam("pageNum")Integer pageNum,HttpServletRequest request,@RequestParam("logoFile")MultipartFile logoFile) throws Exception{
		
		
		boolean empty = logoFile.isEmpty();
		if(!empty){
			
			long size = logoFile.getSize();
			if(size>200*1024){
				request.setAttribute("survey", survey);
				request.setAttribute("pageNum", pageNum);
				throw new FileTooLargeException(GlobalSettings.FileTooLargeException);
			}
			String contentType = logoFile.getContentType();
			if(!contentType.startsWith("image")){
				request.setAttribute("survey", survey);
				request.setAttribute("pageNum", pageNum);
				throw new UploadFileInvalidException(GlobalSettings.UploadFileInvalid);
			}
			InputStream inputStream = logoFile.getInputStream();
			String realPath = request.getSession().getServletContext().getRealPath("/surveyLogos");
			
			String resizeImages = DataProcessUtils.resizeImages(inputStream, realPath);
			survey.setLogoPath(resizeImages);
		}
		
		 surveyService.updateSurvey(survey);
		return "redirect:/guest/survey/showMyUncompleted/"+pageNum;
	}
	
	//完成调查
	@RequestMapping("guest/survey/complete/{surveyId}")
	public String completeSurvey(@PathVariable("surveyId") Integer surveyId,Model model){
		
		Survey survey = surveyService.getSurveDeeply(surveyId);
		
		List<Bag> bags = survey.getBags();
		if(bags==null||bags.size()==0){
			throw new SurveyNotBagException(GlobalSettings.SurveyNotBagException);
		}
		for(Bag bag:bags){
			List<Question> questions = bag.getQuestions();
			if(questions==null||questions.size()==0){
				throw new EmptyBagException(GlobalSettings.Empty_Bag_Exception);
			}
		}
		surveyService.completeSurvey(surveyId);
		return "redirect:/guest/survey/showMyUncompleted/1";
	}
}
