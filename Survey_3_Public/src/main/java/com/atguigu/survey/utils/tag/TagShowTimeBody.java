package com.atguigu.survey.utils.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagShowTimeBody extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		
		//1.准备系统时间数据
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		
		//2.获取PageContext
		PageContext pageContext = (PageContext) getJspContext();
		
		pageContext.setAttribute("timeValue", time + "@@@");
		
		//3.获取代表标签体的对象
		JspFragment tagBody = getJspBody();
		
		//4.执行标签体
		/*StringWriter writer = new StringWriter();
		tagBody.invoke(writer);
		
		//5.获取标签体执行的结果——通常没用
		String result = writer.toString();
		System.out.println(result);*/
		
		//※最普遍的用法：getJspBody().invoke(null);表示执行标签体在页面显示结果
		getJspBody().invoke(null);
	}

}