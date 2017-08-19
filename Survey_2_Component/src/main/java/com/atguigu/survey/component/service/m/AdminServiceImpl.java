package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.AdminMapper;
import com.atguigu.survey.component.mapper.ResMapper;
import com.atguigu.survey.component.service.i.AdminService;
import com.atguigu.survey.e.AdminLoginFailedException;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private ResMapper resMapper;
	@Override
	public Admin login(Admin admin) {
		String adminPwd = admin.getAdminPwd();
		String md5 = DataProcessUtils.MD5(adminPwd);
		admin.setAdminPwd(md5);
		Admin loginAdmin=adminMapper.selectLoginAdmin(admin);
		if(loginAdmin==null){
			throw new AdminLoginFailedException(GlobalSettings.Admin_LoginFailed_Exception);
		}
		return loginAdmin;
	}
	@Override
	public Admin findAdminByName(String adminName) {
		Admin admin=adminMapper.findAdminByName( adminName);
		return admin;
	}
	@Override
	public void saveAdmin(Admin admin) {
		adminMapper.insert(admin);
		
	}
	@Override
	public List<Admin> getAdminList() {
		List<Admin> list = adminMapper.selectAll();
		return list;
	}
	@Override
	public List<Role> getRoleListDeeplyByAdminId(Integer adminId) {
		List<Role> list=adminMapper.getRoleListDeeplyByAdminId(adminId);
		return list;
	}
	@Override
	public void updateRoles(Integer adminId, List<Integer> roleIdList) {
		
		if(roleIdList==null){
			adminMapper.removeAllRolesByAdminId(adminId);
		}else{
			adminMapper.removeAllRolesByAdminId(adminId);
			adminMapper.insertRoles(adminId,roleIdList);
		}
		
		List<Role> roleListDeeplyByAdminId = adminMapper.getRoleListDeeplyByAdminId(adminId);
		Integer maxResPos = resMapper.getMaxResPos();
		String codeArr = DataProcessUtils.calculateCodeArr(roleListDeeplyByAdminId, maxResPos);
		adminMapper.updateAdminCodeArr(adminId,codeArr);
		
	}
	@Override
	public void batchDelete(List<Integer> adminIdList) {
		adminMapper.removeAllRolesByAdminIdList(adminIdList);
		adminMapper.batchDelete(adminIdList);
		
	}

	
	
}
