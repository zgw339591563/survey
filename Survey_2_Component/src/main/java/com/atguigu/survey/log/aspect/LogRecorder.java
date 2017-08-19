package com.atguigu.survey.log.aspect;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;


import com.atguigu.survey.component.service.i.LogService;
import com.atguigu.survey.entities.guest.User;
import com.atguigu.survey.entities.manager.Admin;
import com.atguigu.survey.entities.manager.Log;
import com.atguigu.survey.log.thread.RequestBinder;
import com.atguigu.survey.log.thread.RoutingKeyBinder;
import com.atguigu.survey.utils.GlobalSettings;

public class LogRecorder {

	@Autowired
	private LogService logService;

	private String operator;
	private String operateTime;
	private String methodName;
	private String typeName;
	private String inputData;
	private String outputData;
	private String exceptionType;
	private String exceptionMessage;

	public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
		Object returnValue = null;

		try {
			Signature signature = joinPoint.getSignature();
			typeName = signature.getDeclaringTypeName();
			methodName = signature.getName();
			Object[] args = joinPoint.getArgs();

			if (args == null || args.length == 0) {
				inputData = "无效输入数据";
			} else {
				List<Object> argList = Arrays.asList(args);
				inputData = argList.toString();
			}
			returnValue = joinPoint.proceed(args);

		} catch (Throwable e) {

			Throwable cause = e.getCause();
			while(cause!=null){
				exceptionType = cause.getClass().getName();
				exceptionMessage = cause.getMessage();
				 cause = cause.getCause();
			}
			throw e;
		} finally {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd hh:mm:ss");
			Date date = new Date();
			operateTime = dateFormat.format(date);
			
			HttpServletRequest request = RequestBinder.getRequest();
			HttpSession session = request.getSession();
			User user =(User) session.getAttribute(GlobalSettings.LOGIN_USER);
			Admin admin = (Admin)session.getAttribute(GlobalSettings.LOGIN_Admin);
			String userName=(user==null)?"用户未登陆":user.getUserName();
			String adminName=(admin==null)?"管理员未登陆":admin.getAdminName();
			
			operator="/用户:"+userName+"/管理员:"+adminName;
			if(returnValue==null){
				outputData="无返回值";
			}else{
				outputData = returnValue.toString();
			}
			Log log = new Log(null, operator, operateTime, methodName, typeName, inputData, outputData, exceptionType,
					exceptionMessage);
			RoutingKeyBinder.setRoutingKey(GlobalSettings.SURVEY_LOG_DATASOURCE);
			logService.insertLog(log);
		}
		
		return returnValue;

	}
}
