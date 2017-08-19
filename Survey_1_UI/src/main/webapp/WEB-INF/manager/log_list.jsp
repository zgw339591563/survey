<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志列表</title>
<%@include file="/jspPage/base.jsp"%>
</head>
<body>


<div class="container">

		<div class="row">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="text-center">日志列表</div>
			
			<div class="text-center">
				
					<table class="table table-bordered table-hover text-center">
						
						<tr>
							<td>ID</td>
							<td>详情</td>
						</tr>
						<c:if test="${!empty requestScope.page }">
							<c:forEach items="${requestScope.page.list }" var="log">
								<tr>
									<td>${log.logId}</td>
									
									<td>
										${log.logId}<br>
										${log.operator}<br>
										${log.operateTime}<br>
										${log.methodName}<br>
										${log.typeName}<br>
										${log.inputData}<br>
										${log.outputData}<br>
										${log.exceptionType}<br>
										${log.exceptionMessage}<br>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty requestScope.page }">
							<tr>
								<td colspan="4">日志</td>
							</tr>
						</c:if>
					</table>
				
			</div>
		</div>
	</div>
<!-- 分页 -->
			<c:set value="manager/log/showList" scope="page"
				var="targetUrl" />
			<%@ include file="/jspPage/pages.jsp"%>
</body>
</html>