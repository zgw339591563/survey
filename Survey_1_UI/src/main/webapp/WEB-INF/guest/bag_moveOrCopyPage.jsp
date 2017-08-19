<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择目标调查</title>
<%@ include file="/jspPage/base.jsp" %>
</head>
<body>

<div class="container">	
<%@ include file="/jspPage/navigator_guest.jsp"%>
<div class=" text-center">
	<form >
	<td>选择调查目标</td>
		<table  class="table table-bordered table-hover text-center">
			<tr>
				<td>ID</td>
				<td>调查名称</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${requestScope.page.list }" var="survey">
				<tr>
					<td>${survey.surveyId}</td>
					<td>${survey.surveyName}</td>
					<c:if test="${survey.surveyId==requestScope.surveyId }"><!-- requestScope.surveyId原来的surveyId -->
						<td>当前调查</td>
					</c:if>
					<c:if test="${survey.surveyId!=requestScope.surveyId }">
						<td><a href="guest/bag/moveToThisSurvey/${requestScope.bagId}/${survey.surveyId}">移动到当前调查</a> | <a href="guest/bag/copyToThisSurvey/${requestScope.bagId}/${survey.surveyId}">复制到当前调查</a></td>
					</c:if>
				</tr>
			</c:forEach>
			<c:if test="${!empty requestScope.page.list }">
				<!-- 分页 -->
				<td colspan="3">
				<c:set value="guest/bag/toMoveOrCopyPage/${requestScope.bagId}/${requestScope.surveyId}" scope="page"
					var="targetUrl" />
				<%@ include file="/jspPage/pages.jsp"%>
				</td>
			</c:if>
		</table>
	</form>
  </div>
 </div>
 ${requestScope.page}
</body>
</html>