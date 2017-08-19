package com.atguigu.survey.component.handler.manager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.survey.component.service.i.AdminService;
import com.atguigu.survey.component.service.i.RoleService;
import com.atguigu.survey.e.AdminNameExistException;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.GlobalSettings;

@Controller
public class AdminHandler {

	@Autowired
	private  AdminService adminService;
	@Autowired
	private RoleService roleService;
	@RequestMapping("manager/admin/toMainUI")
	public String toMainUI(){
		
		return "manager/manager_main";
	}
	
	@RequestMapping("manager/admin/to_loginUI")
	public String to_loginUI(){
		
		return "manager/manager_login";
	}
	
	@RequestMapping("manager/admin/login")
	public String login(Admin admin,HttpSession httpSession){
		Admin loginadmin=adminService.login(admin);
		httpSession.setAttribute(GlobalSettings.LOGIN_Admin, loginadmin);
		return "redirect:/manager/admin/toMainUI";
	}
	
	@RequestMapping("manager/admin/logout")
	public String logout(HttpSession httpSession){
		
		//httpSession.removeAttribute(GlobalSettings.LOGIN_Admin);
		httpSession.invalidate();
		return "redirect:/manager/admin/toMainUI";
	}
	
	
	
	@RequestMapping("manager/admin/toAddUI")
	public String toAddUI(){
		
		
		return "manager/admin_addUI";
	}
	
	
	@RequestMapping("manager/admin/saveAdmin")
	public String saveAdmin(Admin admin){
		
		String adminName = admin.getAdminName();
		Admin admin2=adminService.findAdminByName(adminName);
		if(admin2!=null){
			throw new AdminNameExistException(GlobalSettings.ADMINNAME_EXIST_EXCEPTION);
		}
		adminService.saveAdmin(admin);
		return "redirect:/manager/admin/toMainUI";
	}
	
	
	@RequestMapping("manager/admin/showList")
	public String showList(Model model){
		
		List<Admin> list=adminService.getAdminList();
		model.addAttribute("adminList", list);
		return "manager/admin_list";
	}
	
	@RequestMapping("manager/admin/toDispatcherUI/{adminId}")
	public String toDispatcherUI(@PathVariable("adminId")Integer adminId,Map<String,List<Role>> map){
		
		List<Role> roleListDeeply=adminService.getRoleListDeeplyByAdminId(adminId);
		List<Role> allRoleList = roleService.showList();
		map.put("roleListDeeply", roleListDeeply);
		map.put("allRoleList", allRoleList);
		return "manager/dispatcher_admin_role";
	}
	
	
	@RequestMapping("manager/admin/doDispatcher")
	public String doDispatcher(@RequestParam("adminId")Integer adminId,
			@RequestParam(value="roleIdList",required=false)List<Integer> roleIdList){
		
		adminService.updateRoles(adminId,roleIdList);
		return "redirect:/manager/admin/showList";
	}
	
	@RequestMapping("manager/admin/batchDelete")
	public String batchDelete(@RequestParam("adminIdList")List<Integer> adminIdList){
		
		if(adminIdList!=null&&adminIdList.size()>0){
			adminService.batchDelete(adminIdList);
		}
		return "redirect:/manager/admin/showList";
	}
}
