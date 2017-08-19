package com.atguigu.survey.component.handler.guest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.EngageService;
import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.utils.GlobalSettings;
import com.github.pagehelper.PageInfo;

@Controller
public class EngageHandler {

	@Autowired
	private EngageService  engageService;
	@Autowired
	private SurveyService surveyService;
	
	@RequestMapping("guest/engage/showAllAvailable/{pageNum}")
	public String showAllAvailablesurvey(@PathVariable("pageNum") Integer pageNum,Model model){
		PageInfo<Survey> page=engageService.showAllAvailablesurvey(pageNum);
		model.addAttribute(GlobalSettings.PAGE, page);
		return "guest/engage_allAvailable";
	}
	
	@RequestMapping("guest/engage/entry/{surveyId}")
	public String entryEngage(@PathVariable("surveyId")Integer surveyId,Model model,HttpSession httpSession){
		/*①跨页面不变的
		[1]Survey对象的基本信息
		[2]最后一个包裹的索引
		[3]allBagMap对象中的值会变，但是始终是同一个对象

		②每个页面不同的
		[1]当前包裹的索引
		[2]当前包裹对象本身：currentBag
		[3]当前包裹的id*/
		Survey survey=engageService.getSurveDeeply(surveyId);//改成engageService调用用于二级缓存
		//Survey survey = surveyService.getSurveDeeply(surveyId);
		httpSession.setAttribute(GlobalSettings.CURRENT_ENTRY_SURVEY, survey);
		List<Bag> bags = survey.getBags();
		httpSession.setAttribute(GlobalSettings.ENTRY_BAG_LIST, bags);
		Map<Integer, Map<String, String[]>> allBagMap = new HashMap<Integer,Map<String,String[]>>();
		httpSession.setAttribute(GlobalSettings.ALL_BAGMAP, allBagMap);
		Bag bag = bags.get(0);
		model.addAttribute(GlobalSettings.CURRENT_BAG, bag);//将第一个包裹放入请求域
		Integer lastIndex=bags.size()-1;
		httpSession.setAttribute(GlobalSettings.LAST_INDEX, lastIndex);//将最后的索引保存
		int currentIndex = 0;
		model.addAttribute(GlobalSettings.CURRENT_INDEX, currentIndex);//将起始索引放入请求域
		return "guest/engage_engage";
	}
	
	@RequestMapping("guest/engage/engage")
	public String engage(HttpServletRequest request,HttpSession httpSession,
			@RequestParam("currentIndex") Integer currentIndex,@RequestParam("bagId")Integer bagId,Model model){
		Map<String,String[]> parameterMap = request.getParameterMap();//获取请求参数
	    HashMap<String, String[]> map = new HashMap<>(parameterMap);
		Map<Integer,Map<String,String[]>> allBagMap = (Map<Integer,Map<String,String[]>>)httpSession.getAttribute(GlobalSettings.ALL_BAGMAP);
		allBagMap.put(bagId, map);//保存请求参数，以便能够通过bagId从session域中取相应的请求参数
		List<Bag> bags = (List<Bag>)httpSession.getAttribute(GlobalSettings.ENTRY_BAG_LIST);
		
		if(map.containsKey("submit_prev")||map.containsKey("submit_next")){
			if(map.containsKey("submit_prev")){
				currentIndex=currentIndex-1;//得到上一个包裹的索引，页面转到上一页
			}
			
			if(map.containsKey("submit_next")){
				currentIndex=currentIndex+1;//得到下一个包裹的索引，页面转到下一页
			}
			Bag bag = bags.get(currentIndex);
			model.addAttribute(GlobalSettings.CURRENT_INDEX, currentIndex);
			model.addAttribute(GlobalSettings.CURRENT_BAG, bag);
			
			return "guest/engage_engage";
		}
		
		if(map.containsKey("submit_quit")||map.containsKey("submit_done")){
			
			if(map.containsKey("submit_done")){
				Survey survey = (Survey)httpSession.getAttribute(GlobalSettings.CURRENT_ENTRY_SURVEY);
				Integer surveyId = survey.getSurveyId();
				engageService.saveAnswer(allBagMap,surveyId);
			}
			httpSession.removeAttribute(GlobalSettings.ENTRY_BAG_LIST);
			httpSession.removeAttribute(GlobalSettings.ALL_BAGMAP);
			httpSession.removeAttribute(GlobalSettings.CURRENT_ENTRY_SURVEY);
			httpSession.removeAttribute(GlobalSettings.LAST_INDEX);
			
			return "redirect:/index.jsp";
		}
		return "";
	}
}
