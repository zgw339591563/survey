package com.atguigu.survey.component.service.m;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.AdminMapper;
import com.atguigu.survey.component.mapper.AuthMapper;
import com.atguigu.survey.component.mapper.ResMapper;
import com.atguigu.survey.component.mapper.RoleMapper;
import com.atguigu.survey.component.mapper.UserMapper;
import com.atguigu.survey.component.service.i.AuthService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataProcessUtils;
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthMapper authMapper;

	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ResMapper resMapper;
	@Override
	public void saveAuth(Auth auth) {
		authMapper.insert(auth);
		
	}

	@Override
	public List<Auth> getlist() {
		List<Auth> list = authMapper.selectAll();
		return list;
	}

	@Override
	public void batchDelete(List<Integer> authIds) {
		List<User> BigUserList = new ArrayList<>();
		List<Admin> BigAdminList = new ArrayList<>();
		
		for(Integer authId:authIds){
			List<Role> roleListByAuthId = authMapper.getRoleListByAuthId(authId);
			if(roleListByAuthId!=null&&roleListByAuthId.size()>0){
				for(Role role: roleListByAuthId){
					if(role==null){
						continue;
					}
					Integer roleId = role.getRoleId();
					List<Admin> adminList = roleMapper.getAdminListByRoleId(roleId);
					List<User> userList = roleMapper.getUserListByRoleId(roleId);
					BigAdminList.addAll(adminList);
					BigUserList.addAll(userList);
					
				}
			}
			
		}
		authMapper.removeAllAuthResofAuthIdList(authIds);
		authMapper.removeAllAuthRoleofAuthIdList(authIds);
		authMapper.batchDelete( authIds);
		
		//删除后更新用户和管理员的权限码数组
		if(BigAdminList!=null&&BigAdminList.size()>0){
			for(Admin admin: BigAdminList){
				if(admin==null){
					continue;
				}
				Integer adminId = admin.getAdminId();
				List<Role> adminRolelist = adminMapper.getRoleListDeeplyByAdminId(adminId);
				Integer maxResPos = resMapper.getMaxResPos();
				String codeArr = DataProcessUtils.calculateCodeArr(adminRolelist, maxResPos);
				
				adminMapper.updateAdminCodeArr(adminId, codeArr);
				
			}
		}
		
		if(BigUserList!=null&&BigUserList.size()>0){
			for(User user:BigUserList){
				if(user==null){
					continue;
				}
				Integer userId = user.getUserId();
				List<Role> userRoleList=userMapper.getRoleListDeeplyByUserId(userId);
				Integer maxResPos = resMapper.getMaxResPos();
				String codeArr = DataProcessUtils.calculateCodeArr(userRoleList, maxResPos);
				userMapper.updateUserCodeArr(userId, codeArr);
			}
		}
		
		
	}

	@Override
	public List<Res> getResListByAuthId(Integer authId) {
		
		List<Res> list=authMapper.getResListByAuthId(authId);
		return list;
	}

	@Override
	public void updateRes(Integer authId, List<Integer> resIdList) {
		
		if(resIdList==null){
			authMapper.removeAllResByAuthId(authId);
		}else{
			authMapper.removeAllResByAuthId(authId);
			authMapper.insertRes(authId,resIdList);
		}
		
		List<Role> authRoleList=authMapper.getRoleListByAuthId(authId);
		if(authRoleList!=null&&authRoleList.size()>0){
			for(Role role: authRoleList){
				if(role==null){
					continue;
				}
				Integer roleId = role.getRoleId();
				List<Admin> adminList = roleMapper.getAdminListByRoleId(roleId);
				List<User> userList = roleMapper.getUserListByRoleId(roleId);
				if(adminList!=null&&adminList.size()>0){
					for(Admin admin: adminList){
						if(admin==null){
							continue;
						}
						Integer adminId = admin.getAdminId();
						List<Role> adminRolelist = adminMapper.getRoleListDeeplyByAdminId(adminId);
						Integer maxResPos = resMapper.getMaxResPos();
						String codeArr = DataProcessUtils.calculateCodeArr(adminRolelist, maxResPos);
						
						adminMapper.updateAdminCodeArr(adminId, codeArr);
						
					}
				}
				
				if(userList!=null&&userList.size()>0){
					for(User user:userList){
						if(user==null){
							continue;
						}
						Integer userId = user.getUserId();
						List<Role> userRoleList=userMapper.getRoleListDeeplyByUserId(userId);
						Integer maxResPos = resMapper.getMaxResPos();
						String codeArr = DataProcessUtils.calculateCodeArr(userRoleList, maxResPos);
						userMapper.updateUserCodeArr(userId, codeArr);
					}
				}
			}
		}
		
		
	}

	@Override
	public String updateAuthName(Auth auth) {
		Integer row=authMapper.updateByPrimaryKey(auth);
		
		return row==0?"fail":"ok";
	}
}
