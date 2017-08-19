package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

	int checkUserNameExisit(String userName);

	User checkUserAndPassword(@Param("userName") String userName,@Param("userPwd") String userPwd);

	List<Role> getRoleListDeeplyByUserId(Integer userId);

	void updateUserCodeArr(@Param("userId")Integer userId,@Param("codeArr") String codeArr);

	void insertUserAndRoleRelationship(@Param("userId")Integer userId,@Param("roleId") Integer roleId);
}