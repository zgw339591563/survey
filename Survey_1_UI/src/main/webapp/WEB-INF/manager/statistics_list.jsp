<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计调查</title>
<%@include file="/jspPage/base.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="/jspPage/navigator_manager.jsp"%>
		<div class="col-lg-8 col-lg-offset-2">
			<div class="page-header text-center">
				<h2>统计调查</h2>
			</div>
			<table class="table table-bordered table-hover text-center">
				<tr>
					<td>ID</td>
					<td>调查名称</td>
					<td colspan="2">操作</td>
				</tr>
				<c:forEach items="${requestScope.page.list }" var="survey">
					<tr>
						<td>${survey.surveyId }</td>
						<td>${survey.surveyName }</td>
						<td><a href="manager/statistics/showSummary/${survey.surveyId}">查询调查大纲</a></td>
						<td><a href="manager/statistics/exportExcel/${survey.surveyId}">导出excel</a></td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="4"><c:set
							value="manager/statistics/showAllAvailable" scope="page"
							var="targetUrl" /> <%@ include file="/jspPage/pages.jsp"%>
					</td>
				</tr>
			</table>
		</div>
		<!-- 分页 -->

	</div>

</body>
</html>