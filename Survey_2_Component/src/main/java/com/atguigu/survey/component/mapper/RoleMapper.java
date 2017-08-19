package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Role;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    Role selectByPrimaryKey(Integer roleId);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

	void batchDelete(@Param("roleIds")List<Integer> roleIds);

	List<Auth> getAuthListByRoleId(Integer roleId);

	void removeAllRoleAuthByRoleId(Integer roleId);

	void insertAuth(@Param("roleId")Integer roleId,@Param("authIdList") List<Integer> authIdList);

	List<Admin> getAdminListByRoleId(Integer roleId);

	List<User> getUserListByRoleId(Integer roleId);

	Role gerRoleByRoleName(String roleName);

	void removeAllRoleAuthofRoleIdList(@Param("roleIds")List<Integer> roleIds);

	void removeAllAdminRoleofRoleIdList(@Param("roleIds")List<Integer> roleIds);
}