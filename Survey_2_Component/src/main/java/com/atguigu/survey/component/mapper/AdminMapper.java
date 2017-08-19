package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);

    Admin selectLoginAdmin(Admin admin);

	Admin findAdminByName(String adminName);

	List<Role> getRoleListDeeplyByAdminId(Integer adminId);

	void removeAllRolesByAdminId(Integer adminId);

	void insertRoles(@Param("adminId")Integer adminId,@Param("roleIdList") List<Integer> roleIdList);

	void updateAdminCodeArr(@Param("adminId")Integer adminId,@Param("codeArr") String codeArr);

	void removeAllRolesByAdminIdList(@Param("adminIdList")List<Integer> adminIdList);

	void batchDelete(@Param("adminIdList")List<Integer> adminIdList);

	
}