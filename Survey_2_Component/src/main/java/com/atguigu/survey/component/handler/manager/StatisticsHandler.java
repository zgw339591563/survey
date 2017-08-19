package com.atguigu.survey.component.handler.manager;

import java.util.List;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.survey.component.service.i.StatisticsService;
import com.atguigu.survey.component.service.i.SurveyService;
import com.atguigu.survey.entities.guest.Answer;

import com.atguigu.survey.entities.guest.Survey;
import com.atguigu.survey.utils.GlobalSettings;
import com.github.pagehelper.PageInfo;

@Controller
public class StatisticsHandler {

	@Autowired
	private StatisticsService statisticsService;
	@Autowired
	private SurveyService surveyService;

	@RequestMapping("manager/statistics/showAllAvailable/{pageNum}")
	public String showAllAvailable(@PathVariable("pageNum") Integer pageNum, Model model) {

		int defualtpageNum = 1;
		if (pageNum == null) {
			pageNum = defualtpageNum;
		}
		PageInfo<Survey> pageInfo = statisticsService.showAllAvailablesurvey(pageNum);
		model.addAttribute(GlobalSettings.PAGE, pageInfo);
		return "manager/statistics_list";
	}

	@RequestMapping("manager/statistics/showSummary/{surveyId}")
	public String showSummary(@PathVariable("surveyId") Integer surveyId, Model model) {
		Survey survey = surveyService.getSurveDeeply(surveyId);
		model.addAttribute("survey", survey);
		return "manager/statistics_summary";
	}

	@RequestMapping("manager/statistics/showChart/{questionId}")
	public void showChart(@PathVariable("questionId") Integer questionId, HttpServletResponse response)
			throws Exception {

		JFreeChart chart = statisticsService.showChart(questionId);
		ServletOutputStream outputStream = response.getOutputStream();
		ChartUtilities.writeChartAsJPEG(outputStream, chart, 1300, 700);
	}
	@RequestMapping("manager/statistics/showChart2/{questionId}")
	@ResponseBody
	public List<Object> showHighCharts(@PathVariable("questionId") Integer questionId)
			throws Exception {
		List<Object> list = statisticsService.showHighCharts(questionId);
		return list;
	}
	
	
	
	@RequestMapping("manager/statistics/showTextResult/{questionId}")
	public String showTextResult(@PathVariable("questionId") Integer questionId, Model model)
			throws Exception {
		List<Answer> answers=statisticsService.showTextResult(questionId);
		model.addAttribute("answers", answers);
		return "manager/statistics_textResult";
	}
	
	
	@RequestMapping("manager/statistics/exportExcel/{surveyId}")
	public void exportExcel(@PathVariable("surveyId") Integer surveyId, HttpServletResponse response) throws Exception {

		HSSFWorkbook workbook = statisticsService.exportExcel(surveyId);
		// 2.将Excel文件以下载形式返回给浏览器
		// i.设置响应数据的内容类型
		response.setContentType("application/vnd.ms-excel");

		// ii.生成文件名
		String filename = System.nanoTime() + ".xls";

		// iii.在响应消息头中设置文件名
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
	}
}
