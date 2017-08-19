<%@page import="com.atguigu.survey.utils.DataProcessUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参与调查</title>

</head>
<%@include file="/jspPage/base.jsp"%>
<body>
<div class="container">
	<div class="row text-center">
		<%@ include file="/jspPage/navigator_guest.jsp"%>
		<h4>[参与调查]</h4>
		<table class="table table-bordered table-hover text-center">
			<tr>
				<td colspan="2">调查信息</td>
			</tr>
			<tr>
				<td>Logo</td>
				<td>名称</td>
			</tr>
			<tr>
				<td><img src="${sessionScope.currentEntrySurvey.logoPath}" /></td>
				<td>${sessionScope.currentEntrySurvey.surveyName}</td>
			</tr>
		</table>

		<br /> <br />

		<form action="guest/engage/engage" method="post">

			<input type="hidden" name="currentIndex" value="${requestScope.currentIndex }" />
			 <input type="hidden" name="bagId" value="${requestScope.currentBag.bagId }" />

			<table class="table table-bordered table-hover text-center">

				<tr>
					<td colspan="2">当前包裹：${requestScope.currentBag.bagName }</td>
				</tr>
				<c:forEach items="${requestScope.currentBag.questions }" var="question">
					<tr>
						
						<td>${question.questionName}</td>
						<td>
							<c:if test="${question.questionType==1 }">
								<c:forEach items="${question.optionList}" var="option"  varStatus="MyvarStatus">
									 <input id="${question.questionId}${MyvarStatus.index }" type="radio" name="q${question.questionId}"  value="${MyvarStatus.index }" <%=DataProcessUtils.checkedReDisplay(pageContext) %>/>
									<label for="${question.questionId}${MyvarStatus.index }">${option}</label>
								</c:forEach>
							</c:if>
							<c:if test="${question.questionType==2}">
								<c:forEach items="${question.optionList}" var="option"  varStatus="MyvarStatus">
									 <input id="${question.questionId}${MyvarStatus.index }" type="checkbox" name="q${question.questionId}"  value="${MyvarStatus.index }" <%=DataProcessUtils.checkedReDisplay(pageContext) %> />
									<label for="${question.questionId}${MyvarStatus.index }">${option}</label> 
								</c:forEach>
							</c:if>
							<c:if test="${question.questionType==3}">
								${question.questionName} <input type="text" name="q${question.questionId}"   <%=DataProcessUtils.checkedReDisplay(pageContext) %>/>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					
					<td colspan="2">
						<c:if test="${requestScope.currentIndex>0}">
							<input type="submit" name="submit_prev" value="返回上一个包裹" class="btn btn-default" />
						</c:if>
						<c:if test="${requestScope.currentIndex<sessionScope.lastIndex}">
							<input type="submit" name="submit_next" value="进入下一个包裹" class="btn btn-default"  />
						</c:if>
							<input type="submit" name="submit_quit" value="放弃" class="btn btn-default" />
						<c:if test="${requestScope.currentIndex==sessionScope.lastIndex }">
							<input type="submit" name="submit_done" value="完成" class="btn btn-default" />
						</c:if>
					</td>
					
				</tr>

			</table>
		</form>

	</div>
</div>


</body>
</html>