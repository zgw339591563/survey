package com.atguigu.survey.component.service.i;

import java.util.List;

import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;

public interface AuthService {

	public void saveAuth(Auth auth);

	public List<Auth> getlist();

	public void batchDelete(List<Integer> authIds);

	public List<Res> getResListByAuthId(Integer authId);

	public void updateRes(Integer authId, List<Integer> resIdList);

	public String updateAuthName(Auth auth);
}
