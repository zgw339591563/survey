package com.atguigu.survey.component.service.i;

import java.util.List;


import com.atguigu.survey.entities.manager.Res;

public interface ResService {

	List<Res> showList();

	

	void batchSaveRes(List<Res> list);



	void batchDelete(List<Integer> resIds);



	Integer changeStatus(Integer resId);



	Res  findResByServletPath(String servletPath);



	Integer getMaxResCode(Integer leadingResPos);



	Integer getMaxResPos();



	void saveRes(Res resource);

}
