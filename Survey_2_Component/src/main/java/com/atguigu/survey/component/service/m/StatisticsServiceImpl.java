package com.atguigu.survey.component.service.m;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.survey.component.mapper.AnswerMapper;
import com.atguigu.survey.component.mapper.QuestionMapper;
import com.atguigu.survey.component.mapper.SurveyMapper;
import com.atguigu.survey.component.service.i.StatisticsService;
import com.atguigu.survey.entities.guest.Answer;
import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.guest.Survey;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private SurveyMapper surveyMapper;
	@Autowired
	private AnswerMapper answerMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Override
	public PageInfo<Survey> showAllAvailablesurvey(Integer pageNum) {
		PageHelper.startPage(pageNum, 5);
		List<Survey> list = surveyMapper.selectAllAvailablesurvey();
		PageInfo<Survey> pageInfo = new PageInfo<>(list, 5);
		return pageInfo;
	}
	
	public List<Object> showHighCharts(Integer questionId) {
		
		Question question = questionMapper.selectByPrimaryKey(questionId);
		int questionEngageCount= answerMapper.getQuestionEngageCount(questionId);
		List<String> optionList = question.getOptionList();
		
		String questionName = question.getQuestionName();
		questionName=questionName+""+questionEngageCount+"次参与";
		
		List<Object> arrayList = new ArrayList<>();
		for(int i=0;i<optionList.size();i++){
			String option = optionList.get(i);
			String index="%,"+i+",%";
			Integer optionEngagedCount=answerMapper.getoptionEngagedCount(questionId,index);
			Object[] obj=new Object[2];
			obj[0]=option;
			obj[1]=optionEngagedCount;
			arrayList.add(obj);
		}
		return arrayList;
	}
	
	@Override
	public JFreeChart showChart(Integer questionId) {
		Question question = questionMapper.selectByPrimaryKey(questionId);
		int questionEngageCount= answerMapper.getQuestionEngageCount(questionId);
		List<String> optionList = question.getOptionList();
		DefaultPieDataset dataset = new DefaultPieDataset();
		String questionName = question.getQuestionName();
		questionName=questionName+""+questionEngageCount+"次参与";
		for(int i=0;i<optionList.size();i++){
			String option = optionList.get(i);
			String index="%,"+i+",%";
			int optionEngagedCount=answerMapper.getoptionEngagedCount(questionId,index);
			
			dataset.setValue(option, optionEngagedCount);
		}
		
		JFreeChart pieChart3D = ChartFactory.createPieChart3D(questionName, dataset);
		pieChart3D.getTitle().setFont(new Font("华文彩云", Font.BOLD, 50));
		pieChart3D.getLegend().setItemFont(new Font("华文新魏", Font.ITALIC, 30));
		PiePlot plot = (PiePlot) pieChart3D.getPlot();
		plot.setLabelFont(new Font("宋体", Font.PLAIN, 20));
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}，{1}/{3}，{2}"));
		
		//设置前景色半透明
		plot.setForegroundAlpha(0.6f);
		return pieChart3D;
	}
	@Override
	public HSSFWorkbook exportExcel(Integer surveyId) {
		
		Survey survey = surveyMapper.getSurveDeeply(surveyId);
		String surveyName = survey.getSurveyName();
		int surveyEngageCount=answerMapper.getSurveyEngageCount(surveyId);
		List<Answer> answers = answerMapper.selectAnswerBysurveyId(surveyId);
		Map<String, Map<Integer, String>> bigMap = getBigMap(answers);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		
		List<Bag> bags = survey.getBags();
		List<Question> questionList = new ArrayList<>();
		HSSFSheet sheet = hssfWorkbook.createSheet(surveyName+""+surveyEngageCount+"次调查");
		HSSFRow row0 = sheet.createRow(0);
		for(int i=0;i<bags.size();i++){
			Bag bag = bags.get(i);
			List<Question> questions = bag.getQuestions();
			questionList.addAll(questions);//把所有包裹的所有问题都放到一个集合里
		}
		for(int i=0;i<questionList.size();i++){
			Question question = questionList.get(i);
			String questionName = question.getQuestionName();
			HSSFCell cell = row0.createCell(i);
			cell.setCellValue(questionName);
		}
		if(surveyEngageCount==0){
			return hssfWorkbook;
		}
		Collection<Map<Integer, String>> smallMaps = bigMap.values();
		List<Map<Integer, String>> smallMapList = new ArrayList<>(smallMaps);//小map集合
		
		for(int i=0;i<smallMapList.size();i++){
			int rowIndex=i+1;
			HSSFRow row = sheet.createRow(rowIndex);
			Map<Integer, String> map = smallMapList.get(i);//第i+1个小Map
			
			for(int j=0;j<questionList.size();j++){
				Question question = questionList.get(j);
				Integer questionId = question.getQuestionId();
				String questionAnsewr = map.get(questionId);//更据questionId取出第i+1行的第j+1列答案的内容
				
				
				HSSFCell cell = row.createCell(j);
				/*--------------*/
				Integer questionType = question.getQuestionType();
				if(questionAnsewr!=null&&(questionType==1||questionType==2)){//判断是否是单选或多选以便将answer_Content由下标转换成选项的内容
					List<String> optionList = question.getOptionList();
					String[] splitQuestionAnsewr = questionAnsewr.split(",");
					ArrayList<Integer> optionIndexlist = new ArrayList<>();
					for(String answer:splitQuestionAnsewr){
						int optionIndex = Integer.parseInt(answer);
						optionIndexlist.add(optionIndex);
					}
					StringBuffer stringBuffer = new StringBuffer();
					for(int optionIndex:optionIndexlist){
						String option = optionList.get(optionIndex);
						stringBuffer.append(",").append(option);
					}
					String questionAnsewrOptionStr = stringBuffer.substring(1).toString();
				 
					cell.setCellValue(questionAnsewrOptionStr);
				}else{
				/*-------------*/
					cell.setCellValue(questionAnsewr);
				}
			}
		}
		return hssfWorkbook;
	}
	
	public Map<String,Map<Integer,String>> getBigMap(List<Answer> answers){
		
		HashMap<String, Map<Integer,String>> bigMap = new HashMap<>();
		for(int i=0;i<answers.size();i++){
			Answer answer = answers.get(i);
			String answerContent = answer.getAnswerContent();
			String uuid = answer.getAnswerUuid();
			Integer questionId = answer.getQuestionId();
			Map<Integer, String> smallmap = bigMap.get(uuid);
			if(smallmap==null){
				smallmap = new HashMap<>();
				bigMap.put(uuid, smallmap);
			}
			smallmap.put(questionId, answerContent);
		}
		return bigMap;
	}
	@Override
	public List<Answer> showTextResult(Integer questionId) {
		List<Answer> answers = answerMapper.selectAnswersByQuestionId(questionId);
		return answers;
	}
	
	

}
