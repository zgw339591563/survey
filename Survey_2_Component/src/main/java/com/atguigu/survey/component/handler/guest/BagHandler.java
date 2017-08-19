package com.atguigu.survey.component.handler.guest;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.BagService;
import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.e.BagNameEmptyException;
import com.atguigu.survey.e.BagOrderDuplicateException;
import com.atguigu.survey.e.EmptyBagException;
import com.atguigu.survey.e.RemoveBagFailedException;
import com.atguigu.survey.e.RemoveSurveyFailedException;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.utils.GlobalSettings;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@Controller
public class BagHandler {

	@Autowired
	private BagService bagService;
	@Autowired
	private SurveyService surveyService;

	@RequestMapping("guest/bag/toAddUI/{surveyId}")
	public String toAddUI(@PathVariable("surveyId") Integer surveyId) {

		/* @PathVariable 会把参数放到request域中 */
		return "guest/bag_addUI";
	}

	@RequestMapping("guest/bag/saveBag")
	public String saveBag(Bag bag, Model model) {

		String bagName = bag.getBagName();
		Integer surveyId = bag.getSurveyId();
		if (bagName.equals("") || bagName == null) {
			model.addAttribute("surveyId", surveyId);
			throw new EmptyBagException(GlobalSettings.EmptyBagException);
		}
		bagService.saveBag(bag);
		System.out.println(bag);

		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}

	@RequestMapping("guest/bag/toAdjustUI/{surveyId}")
	public String toAdjustUI(@PathVariable("surveyId") Integer surveyId, Model model) {
		List<Bag> bags = bagService.getSurveyBags(surveyId);
		model.addAttribute("bags", bags);
		return "guest/bag_adjustUI";
	}

	@RequestMapping("guest/bag/doAdjust")
	public String doAdjust(@RequestParam("bagId") List<Integer> bagIds, @RequestParam("surveyId") Integer surveyId,
			@RequestParam("bagOrder") List<Integer> bagOrders, HttpServletRequest request) {
		HashSet<Integer> bagOrderset = new HashSet<>(bagOrders);
		int size1 = bagOrderset.size();
		int size2 = bagOrders.size();
		if (size2 != size1) {
			List<Bag> bags = bagService.getSurveyBags(surveyId);
			request.setAttribute("bags", bags);
			request.setAttribute("surveyId", surveyId);
			throw new BagOrderDuplicateException(GlobalSettings.BagOrderDuplicateException);
		}
		System.out.println("bagIds:" + bagIds);
		System.out.println("bagOrders:" + bagOrders);
		System.out.println("surveyId:" + surveyId);
		bagService.batchUpdatebags(bagIds, bagOrders);

		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}

	@RequestMapping("guest/bag/removeBag/{bagId}/{surveyId}")
	public String removeBag(@PathVariable("bagId") Integer bagId, @PathVariable("surveyId") Integer surveyId) {
		try {
			bagService.removeBag(bagId);
		} catch (Exception e) {
			e.printStackTrace();
			Throwable cause = e.getCause();
			if (cause != null && cause instanceof MySQLIntegrityConstraintViolationException) {
				throw new RemoveBagFailedException(GlobalSettings.RemoveBagFailedException);
			}
			throw e;
		}
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}

	@RequestMapping("guest/bag/removeBagDeeply/{bagId}/{surveyId}")
	public String removeBagDeeply(@PathVariable("bagId") Integer bagId, @PathVariable("surveyId") Integer surveyId) {

		bagService.removeBagDeeply(bagId);
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}

	@RequestMapping("guest/bag/toEditUI/{bagId}")
	public String toEditUI(@PathVariable("bagId") Integer bagId, Model model) {

		Bag bag = bagService.getBag(bagId);
		model.addAttribute("bag", bag);
		return "guest/bag_editUI";
	}

	@RequestMapping("guest/bag/updateBag")
	public String updateBag(Bag bag, HttpServletRequest request) {
		Integer bagId = bag.getBagId();
		String bagName = bag.getBagName();
		if (bagName == null || "".equals(bagName)) {
			bag = bagService.getBag(bagId);
			request.setAttribute("bag", bag);
			throw new BagNameEmptyException(GlobalSettings.BagNameEmpty);
		}
		bagService.updateBag(bag);
		Integer surveyId = bag.getSurveyId();
		return "redirect:/guest/survey/toDesignUI/" + surveyId;
	}
	//去复制移动的页面，带上要复制的bagId和surveyId
	@RequestMapping("guest/bag/toMoveOrCopyPage/{bagId}/{surveyId}/{pageNum}")
	public String toMoveOrCopyPage(@PathVariable("bagId") Integer bagId, @PathVariable("surveyId") Integer surveyId,
			@PathVariable("pageNum") Integer pageNum, HttpSession session, Model model) {
		Integer pageNumStr = 1;

		try {
			pageNumStr = pageNum;
		} catch (NumberFormatException e) {
			/* 什么都不用做，如果类型转换失败则保持默认值 */}
		User user = (User) session.getAttribute(GlobalSettings.LOGIN_USER);
		PageInfo<Survey> pageInfo = surveyService.getMyUncompletedSurvey(pageNumStr, user.getUserId());
		model.addAttribute(GlobalSettings.PAGE, pageInfo);
		return "guest/bag_moveOrCopyPage";
	}
	
	@RequestMapping("guest/bag/moveToThisSurvey/{bagId}/{surveyId}")
	public String moveToThisSurvey(@PathVariable("bagId") Integer bagId, @PathVariable("surveyId") Integer surveyId){
		
		bagService.moveToThisSurvey(surveyId,bagId);
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
	@RequestMapping("guest/bag/copyToThisSurvey/{bagId}/{surveyId}")
	public String copyToThisSurvey(@PathVariable("bagId") Integer bagId, @PathVariable("surveyId") Integer surveyId){
		
		bagService.copyToThisSurvey(surveyId,bagId);
		return "redirect:/guest/survey/toDesignUI/"+surveyId;
	}
	
}
