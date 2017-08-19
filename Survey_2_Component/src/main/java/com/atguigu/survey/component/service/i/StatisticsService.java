package com.atguigu.survey.component.service.i;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.JFreeChart;

import com.atguigu.survey.entities.guest.Answer;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;
import com.github.pagehelper.PageInfo;

public interface StatisticsService {

	
	PageInfo<Survey> showAllAvailablesurvey(Integer pageNum);

	JFreeChart showChart(Integer questionId);

	HSSFWorkbook exportExcel(Integer surveyId);

	List<Answer> showTextResult(Integer questionId);

	List<Object> showHighCharts(Integer questionId);

}
