package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Role;

public interface RoleService {

	void save(Role role);

	List<Role> showList();

	void batchDelete(List<Integer> roleIds);

	List<Auth> getAuthListByRoleId(Integer roleId);

	void updateAuth(Integer roleId, List<Integer> authIdList);

	String updateRoleName(Role role);

}
