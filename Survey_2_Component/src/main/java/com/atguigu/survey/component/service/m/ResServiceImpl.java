package com.atguigu.survey.component.service.m;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.MyResMapper;
import com.atguigu.survey.component.mapper.ResMapper;
import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.manager.MyRes;
import com.atguigu.survey.entities.manager.Res;

@Service
public class ResServiceImpl implements ResService{

	@Autowired
	private ResMapper resMapper;

	
	@Override
	public List<Res> showList() {
		List<Res> list = resMapper.selectAll();
		return list;
	}

	@Override
	public void batchSaveRes(List<Res> list) {
		
		resMapper.batchSaveRes(list);
	}

	@Override
	public void batchDelete(List<Integer> resIds) {
		
		resMapper.batchDelete(resIds);
	}

	@Override
	public Integer changeStatus(Integer resId) {
		Integer result=resMapper.changeStatus(resId);
		return result;
	}

	@Override
	public Res findResByServletPath(String servletPath) {
		Res res=resMapper.findResByServletPath(servletPath);
		return res;
	}

	@Override
	public Integer getMaxResCode(Integer leadingResPos) {
		Integer maxResCode=resMapper.getMaxResCode(leadingResPos);
		return maxResCode;
	}

	@Override
	public Integer getMaxResPos() {
		Integer maxResPos=resMapper.getMaxResPos();
		return maxResPos;
	}

	@Override
	public void saveRes(Res resource) {
		resMapper.insert(resource);
	}
	
}
