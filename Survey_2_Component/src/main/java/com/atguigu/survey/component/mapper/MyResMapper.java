package com.atguigu.survey.component.mapper;

import com.atguigu.survey.entities.manager.MyRes;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface MyResMapper {
    int deleteByPrimaryKey(Integer resId);

    int insert(MyRes record);

    MyRes selectByPrimaryKey(Integer resId);

    List<MyRes> selectAll();

    int updateByPrimaryKey(MyRes record);

	void batchSaveRes(@Param("list")List<MyRes> list);
}