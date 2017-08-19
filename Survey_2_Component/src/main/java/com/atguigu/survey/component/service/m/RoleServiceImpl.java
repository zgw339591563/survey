package com.atguigu.survey.component.service.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.AdminMapper;
import com.atguigu.survey.component.mapper.ResMapper;
import com.atguigu.survey.component.mapper.RoleMapper;
import com.atguigu.survey.component.mapper.UserMapper;
import com.atguigu.survey.component.service.i.AdminService;
import com.atguigu.survey.component.service.i.AuthService;
import com.atguigu.survey.component.service.i.RoleService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataProcessUtils;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ResMapper resMapper;
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public void save(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public List<Role> showList() {
		List<Role> roles = roleMapper.selectAll();
		return roles;
	}

	@Override
	public void batchDelete(List<Integer> roleIds) {
		List<User> BigUserList = new ArrayList<>();
		List<Admin> BigAdminList = new ArrayList<>();
		for (Integer roleId : roleIds) {
			List<Admin> adminList = roleMapper.getAdminListByRoleId(roleId);
			List<User> userList = roleMapper.getUserListByRoleId(roleId);
			BigAdminList.addAll(adminList);
			BigUserList.addAll(userList);
		}
		roleMapper.removeAllRoleAuthofRoleIdList(roleIds);
		roleMapper.removeAllAdminRoleofRoleIdList(roleIds);
		roleMapper.batchDelete(roleIds);

		// 删除后更新用户和管理员的权限码数组
		if (BigAdminList != null && BigAdminList.size() > 0) {
			for (Admin admin : BigAdminList) {
				if (admin == null) {
					continue;
				}
				Integer adminId = admin.getAdminId();
				List<Role> adminRolelist = adminMapper.getRoleListDeeplyByAdminId(adminId);
				Integer maxResPos = resMapper.getMaxResPos();
				String codeArr = DataProcessUtils.calculateCodeArr(adminRolelist, maxResPos);

				adminMapper.updateAdminCodeArr(adminId, codeArr);

			}
		}

		if (BigUserList != null && BigUserList.size() > 0) {
			for (User user : BigUserList) {
				if (user == null) {
					continue;
				}
				Integer userId = user.getUserId();
				List<Role> userRoleList = userMapper.getRoleListDeeplyByUserId(userId);
				Integer maxResPos = resMapper.getMaxResPos();
				String codeArr = DataProcessUtils.calculateCodeArr(userRoleList, maxResPos);
				userMapper.updateUserCodeArr(userId, codeArr);
			}
		}
	}

	@Override
	public List<Auth> getAuthListByRoleId(Integer roleId) {
		List<Auth> list = roleMapper.getAuthListByRoleId(roleId);
		return list;
	}

	@Override
	public void updateAuth(Integer roleId, List<Integer> authIdList) {

		if (authIdList == null) {
			roleMapper.removeAllRoleAuthByRoleId(roleId);
		} else {
			roleMapper.removeAllRoleAuthByRoleId(roleId);
			roleMapper.insertAuth(roleId, authIdList);
		}

		List<Admin> adminList = roleMapper.getAdminListByRoleId(roleId);
		List<User> userList = roleMapper.getUserListByRoleId(roleId);

		if (adminList != null && adminList.size() > 0) {
			for (Admin admin : adminList) {
				Integer adminId = admin.getAdminId();
				List<Role> adminRolelist = adminMapper.getRoleListDeeplyByAdminId(adminId);
				Integer maxResPos = resMapper.getMaxResPos();
				String codeArr = DataProcessUtils.calculateCodeArr(adminRolelist, maxResPos);

				adminMapper.updateAdminCodeArr(adminId, codeArr);

			}
		}

		if (userList != null && userList.size() > 0) {

			for (User user : userList) {
				Integer userId = user.getUserId();
				List<Role> userRoleList = userMapper.getRoleListDeeplyByUserId(userId);
				Integer maxResPos = resMapper.getMaxResPos();
				String codeArr = DataProcessUtils.calculateCodeArr(userRoleList, maxResPos);
				userMapper.updateUserCodeArr(userId, codeArr);
			}
		}

	}

	@Override
	public String updateRoleName(Role role) {
		int row = roleMapper.updateByPrimaryKey(role);

		return row == 0 ? "fail" : "ok";
	}
}
