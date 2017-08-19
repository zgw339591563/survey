<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>问答题答案列表</title>
<%@include file="/jspPage/base.jsp"%>
</head>
<body>
	<div class="container">
		<%@ include file="/jspPage/navigator_manager.jsp"%>
		<div class="col-lg-8 col-lg-offset-2">
			<div class="page-header text-center">
				<h2>答案列表</h2>
			</div>
			<table class="table table-bordered table-hover text-center">
				
				<c:if test="${!empty requestScope.answers }">
				<c:forEach items="${requestScope.answers }" var="answer">
					<tr>
						<td>${answer.answerContent}</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty requestScope.answers }">
					<tr>
						<td>
							没有回答内容
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>

</body>
</html>