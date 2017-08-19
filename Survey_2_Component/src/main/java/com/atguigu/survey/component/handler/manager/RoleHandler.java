package com.atguigu.survey.component.handler.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.survey.component.service.i.AuthService;
import com.atguigu.survey.component.service.i.RoleService;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Role;

@Controller
public class RoleHandler {

	@Autowired
	private RoleService  roleService;
	@Autowired AuthService authService;
	@RequestMapping("manager/role/toAddUI")
	public String toAddUI(){
		
		return "manager/role_addUI";
	}
	@RequestMapping("manager/role/saveRole")
	public String saveRole(Role role){
		roleService.save(role);
		return "redirect:/manager/role/showList";
	}
	
	@RequestMapping("manager/role/showList")
	public String showList(Role role,Model model){
		List<Role> roles=roleService.showList();
		model.addAttribute("roles", roles);
		return "manager/role_list";
	}
	
	@RequestMapping("manager/role/batchDelete")
	public String batchDelete(@RequestParam(value="roles",required=false)List<Integer> roleIds){
		if(roleIds!=null&&roleIds.size()>0){
			roleService.batchDelete(roleIds);
		}
		
		return "redirect:/manager/role/showList";
	}

	@RequestMapping("manager/role/toDispatcherUI/{roleId}")
	public String toDispatcherUI(@PathVariable("roleId")Integer roleId,Map<String,List<Auth>> map){
		List<Auth> roleAuthList=roleService.getAuthListByRoleId(roleId);
		List<Auth> allAuthList = authService.getlist();
		map.put("roleAuthList", roleAuthList);
		map.put("allAuthList", allAuthList);
		return "manager/dispatcher_role_auth";
	}
	
	@RequestMapping("manager/role/doDispatcher")
	public String doDispatcher(@RequestParam("roleId")Integer roleId,@RequestParam(value="authIdList",required=false)List<Integer> authIdList){
		
		roleService.updateAuth(roleId,authIdList);
		return "redirect:/manager/role/showList";
	}
	
	@RequestMapping("manager/role/updateRoleName")
	@ResponseBody
	public String updateRoleName(Role role){
		
		String result=roleService.updateRoleName( role);
		return result;
	}
	
}
