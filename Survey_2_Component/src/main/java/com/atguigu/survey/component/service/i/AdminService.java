package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;

public interface AdminService {

	Admin login(Admin admin);

	Admin findAdminByName(String adminName);

	void saveAdmin(Admin admin);

	List<Admin> getAdminList();

	List<Role> getRoleListDeeplyByAdminId(Integer adminId);

	void updateRoles(Integer adminId, List<Integer> roleIdList);

	void batchDelete(List<Integer> adminIdList);

}
