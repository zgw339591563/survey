package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.ResMapper;
import com.atguigu.survey.component.mapper.RoleMapper;
import com.atguigu.survey.component.mapper.UserMapper;
import com.atguigu.survey.component.service.i.UserService;
import com.atguigu.survey.e.LoginUserNameAndpassWordEmptyException;
import com.atguigu.survey.e.UserNameAlreadyExistException;
import com.atguigu.survey.e.UserNameAndpassWordEmptyException;
import com.atguigu.survey.e.UserOrPasswordException;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Role;
import com.atguigu.survey.utils.DataProcessUtils;
import com.atguigu.survey.utils.GlobalSettings;
import com.atguigu.survey.utils.MD5Utils;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper  roleMapper;
	@Autowired
	private ResMapper resMapper;
	@Override
	public void regist(User user) {
		if(user==null){
			throw new UserNameAndpassWordEmptyException(GlobalSettings.USERNAME_AND__PASSWORD_EMPTYEXCEPTION);
		}
		String userName = user.getUserName();
		String userPwd = user.getUserPwd();
		if(userName==null&&userPwd==null){
			throw new UserNameAndpassWordEmptyException(GlobalSettings.USERNAME_AND__PASSWORD_EMPTYEXCEPTION);
		}
		int nameExisit= userMapper.checkUserNameExisit(userName);
		if(nameExisit>0){
			throw new UserNameAlreadyExistException(GlobalSettings.UserName_Already_Exist);
		}
		userPwd = DataProcessUtils.MD5(userPwd);
		user.setUserPwd(userPwd);
		Boolean company = user.getCompany();
		String roleName=null;
		if(company==true){
			roleName="企业用户";
		}else{
			roleName="个人用户";
		}
		Role role=roleMapper.gerRoleByRoleName(roleName);
		Integer roleId = role.getRoleId();
		userMapper.insert(user);
		Integer userId = user.getUserId();
		userMapper.insertUserAndRoleRelationship(userId,roleId);
		List<Role> roleListDeeplyByUserId = userMapper.getRoleListDeeplyByUserId(userId);
		Integer maxResPos = resMapper.getMaxResPos();
		String codeArr = DataProcessUtils.calculateCodeArr(roleListDeeplyByUserId, maxResPos);
		userMapper.updateUserCodeArr(userId, codeArr);
	}
	@Override
	public User login(User user) {
		System.out.println(user);
		if(user==null||user.getUserName()==null||user.getUserPwd()==null){
			throw new LoginUserNameAndpassWordEmptyException(GlobalSettings.USERNAME_AND__PASSWORD_EMPTYEXCEPTION);
		}
		 String userName = user.getUserName();
		 String userPwd = user.getUserPwd();
		
		 userPwd = DataProcessUtils.MD5(userPwd);
		 user=userMapper.checkUserAndPassword(userName,userPwd);
		 if(user==null){
			 throw new UserOrPasswordException(GlobalSettings.UserOrPassword_Error);
		 }
		 
		return user;
	}

}
