package com.atguigu.survey.component.handler.manager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.survey.component.service.i.ResService;
import com.atguigu.survey.entities.manager.Res;

@Controller
public class ResHandler {

	@Autowired
	private ResService resService;
	
	@RequestMapping("manager/res/showList")
	public String showList(Model model){
		
		List<Res> resList=resService.showList();
		model.addAttribute("resList", resList);
		return "manager/res_list";
	}
	
	@RequestMapping("manager/res/importRes")
	public String importRes(@RequestParam("importFile")MultipartFile importFile) throws Exception{
		InputStream inputStream = importFile.getInputStream();
		Workbook workbook=null;
		
		try {
			workbook =new XSSFWorkbook(inputStream);
		} catch (OfficeXmlFileException e) {
			System.out.println("OfficeXmlFileException=====");
			workbook = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = workbook.getSheet("Sheet1");
	
		//HSSFSheet hSSFSheet = (HSSFSheet) workbook.getSheet("Sheet1");
		List<Res> list=new ArrayList<Res>();
		for(Row row:sheet){
			int rowNum = row.getRowNum();
			if(rowNum==0){
				continue;
			}
			//resId, String servletPath, Integer resCode, Integer resPos, Boolean publicStatus
			int resId =(int) row.getCell(0).getNumericCellValue();
			String servletPath = row.getCell(1).getStringCellValue();
			int resCode = (int)row.getCell(2).getNumericCellValue();
			int resPos =(int) row.getCell(3).getNumericCellValue();
			int publicStatus =(int) row.getCell(4).getNumericCellValue();
			boolean flag=false;
			if(publicStatus==1){
				flag=true;
			}
			Res res = new Res(resId, servletPath,resCode , resPos,flag);
			list.add(res);
		}
		workbook.close();
		resService.batchSaveRes(list);
		
		/*Iterator<Sheet> sheetIterator = hssfWorkbook.sheetIterator();
		 * if(sheetIterator.hasNext()){
			Sheet sheet = sheetIterator.next();
			Iterator<Row> rowIterator = sheet.rowIterator();
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			for(int i=firstRowNum;i<lastRowNum+1;i++){
				Row row = sheet.getRow(i);
				short firstCellNum = row.getFirstCellNum();
				short lastCellNum = row.getLastCellNum();
				MyRes myRes = new MyRes();
				
				for(int j=firstCellNum;j<lastCellNum+1;j++){
					Cell cell = row.getCell(j);
					String stringCellValue = cell.getStringCellValue();
				
				}
			}
			
		}*/
		
		return "redirect:/manager/res/showList";
	}
	
	
	@RequestMapping("manager/res/batchDelete")
	public String batchDelete(@RequestParam(value="resIdList",required=false)List<Integer> resIds){
		
		if(resIds!=null&&resIds.size()>0){
			resService.batchDelete(resIds);
		}
		return "redirect:/manager/res/showList";
	}
	
	@RequestMapping("manager/res/toggleStatus")
	@ResponseBody
	public Map toggleStatus(@RequestParam("resId")Integer resId){
		
		Integer result=null;
		try {
			 result=resService.changeStatus(resId);
		} catch (Exception e) {
			
			e.printStackTrace();
			result=0;
		}
		Map<String,Object> map=new HashMap<>();
		map.put("result", result);
		return map;
	}
}
