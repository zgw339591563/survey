package com.atguigu.survey.component.handler.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.survey.component.service.i.AuthService;
import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;

@Controller
public class AuthHandler {

	@Autowired
	private AuthService authService;
	@Autowired
	private ResService resService;
	
	@RequestMapping("manager/auth/toAddUI")
	public String toAddUI(){
		
		return "manager/auth_addUI";
	}
	@RequestMapping("manager/auth/saveAuth")
	public String saveAuth(Auth auth){
		if(auth!=null&&auth.getAuthName()!=null){
			authService.saveAuth(auth);
		}
		
		return "redirect:/manager/auth/showList";
	}
	
	@RequestMapping("manager/auth/showList")
	public String  showList(Model model){
		List<Auth> authList=authService.getlist();
		model.addAttribute("authList", authList);
		return "manager/auth_list";
	}
	
	@RequestMapping("manager/auth/batchDelete")
	public String  batchDelete(@RequestParam(value="authList",required=false)List<Integer> authIds){
		if(authIds!=null&&authIds.size()>0){
			authService.batchDelete(authIds);
		}
		return "redirect:/manager/auth/showList";
	}
	
	
	@RequestMapping("manager/auth/toDispatcherUI/{authId}")
	public String  toDispatcherUI(@PathVariable("authId")Integer authId,Model model){
		
		List<Res> allResList = resService.showList();
		List<Res> authResList=authService.getResListByAuthId(authId);
		model.addAttribute("allResList", allResList);
		model.addAttribute("authResList", authResList);
		return "manager/dispatcher_auth_res";
	}
	
	
	@RequestMapping("manager/auth/doDispatcher")
	public String  doDispatcherUI(@RequestParam("authId")Integer authId,@RequestParam(value="resIdList",required=false)List<Integer> resIdList){
		
		authService.updateRes(authId,resIdList);
		return "redirect:/manager/auth/showList";
	}
	
	@RequestMapping("manager/auth/updateAuthName")
	@ResponseBody
	public String updateAuthName(Auth auth){
		
		String result=authService.updateAuthName(auth);
		return result;
	}
}
