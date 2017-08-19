package com.atguigu.survey.utils.tag;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.lf5.util.DateFormatManager;


public class TagShowTime extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
	
		DateFormatManager dateFormat = new DateFormatManager("yyyy-MM-dd hh-mm-ss");
		Date date = new Date();
		String format = dateFormat.format(date);
		PageContext pageContext=(PageContext) getJspContext();
		JspWriter jspWriter = pageContext.getOut();
		jspWriter.write(format);
		
	}
}
