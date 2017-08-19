package com.atguigu.survey.component.mapper;


import com.atguigu.survey.entities.manager.Res;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ResMapper {
    int deleteByPrimaryKey(Integer resId);

    int insert(Res record);

    Res selectByPrimaryKey(Integer resId);

    List<Res> selectAll();

    int updateByPrimaryKey(Res record);

	void batchSaveRes(@Param("list")List<Res> list);

	void batchDelete(@Param("resIds")List<Integer> resIds);

	Integer changeStatus(Integer resId);

	Res findResByServletPath(String servletPath);

	Integer getMaxResCode(Integer leadingResPos);

	Integer getMaxResPos();
}