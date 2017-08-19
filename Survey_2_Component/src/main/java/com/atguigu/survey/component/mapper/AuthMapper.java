package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.entities.manager.Role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AuthMapper {
    int deleteByPrimaryKey(Integer authId);

    int insert(Auth record);

    Auth selectByPrimaryKey(Integer authId);

    List<Auth> selectAll();

    int updateByPrimaryKey(Auth record);

	void batchDelete(@Param("authIds")List<Integer> authIds);

	List<Res> getResListByAuthId(Integer authId);

	void removeAllResByAuthId(Integer authId);

	void insertRes(@Param("authId")Integer authId,@Param("resIdList") List<Integer> resIdList);

	List<Role> getRoleListByAuthId(Integer authId);

	void removeAllAuthResofAuthIdList(@Param("authIds")List<Integer> authIds);

	void removeAllAuthRoleofAuthIdList(@Param("authIds")List<Integer> authIds);

}