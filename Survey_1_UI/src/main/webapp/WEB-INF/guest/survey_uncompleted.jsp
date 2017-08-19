<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<title>我 未 完 成 调 查</title>
<!-- <link rel="stylesheet" href="bootstrap/theme.css"/> -->
<%@include file="/jspPage/base.jsp"%>
<SCRIPT type="text/javascript">
$(function(){
	$(".btn-danger").click(function(){
		var c=confirm("是否真的要删除该调查");
		if(c){
			return true;
		}
		return false;
	})
	
	
})

</SCRIPT>

</head>
<body>
	<div class="container ">
		<%@ include file="/jspPage/navigator_guest.jsp"%>
		
			<div class="row  ">
				<div class="col-md-12 text-center">
					<table class="table table-bordered  text-center">
						<c:if test="${empty requestScope.page.list }">
							<tr><td>您尚未创建调查</td></tr>
							<tr><td> <a  href="guest/survey/toAddUI"  >创建调查</a></td></tr>
							<td> <a href="#"  >返回</a></td>
						</c:if>
						<c:if test="${!empty requestScope.page.list }">
							<thead>
								<tr>
									<th>ID</th>
									<th>Logo</th>
									<th>SurveyName</th>
									<th colspan="4">Operation</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.page.list }" var="survey">
									<tr>
										<td><a>${survey.surveyId}</a></td>
										<td><IMG alt="无照片" src="${survey.logoPath}" /></td>
										<td><a>${survey.surveyName}</a></td>
										<td><a href="guest/survey/toDesignUI/${survey.surveyId}" class="btn btn-xs btn-default">设 计</a></td>
										<td><a href="guest/survey/RemoveSurvey/${survey.surveyId}/${requestScope.page.pageNum}" class="btn btn-xs btn-warning">删 除</a></td>
										<td><a href="guest/survey/toEditUI/${survey.surveyId}/${requestScope.page.pageNum}" class="btn btn-xs btn-info">更 新</a></td>
										<td><a href="guest/survey/deeplyRemoveSurvey/${survey.surveyId}/${requestScope.page.pageNum}" class="btn btn-xs btn-danger" >深 度 删 除</a></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
		
		<c:if test="${!empty requestScope.page.list }">
			<!-- 分页 -->
			<c:set value="manager/log/showList" scope="page"
				var="targetUrl" />
			<%@ include file="/jspPage/pages.jsp"%>
		</c:if>
	</div>
	<!-- /container -->
</body>
</html>