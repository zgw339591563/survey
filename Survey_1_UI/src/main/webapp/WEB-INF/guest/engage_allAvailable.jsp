<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>参与调查</title>
<%@include file="/jspPage/base.jsp"%>
</head>
<body>
	<div class="container ">
		<%@ include file="/jspPage/navigator_guest.jsp"%>
		
			<div class="row  ">
				<div class="col-md-12 text-center">
					<table class="table table-bordered  text-center">
						<thead>
							<tr>
								<th>Logo</th>
								<th>调查名称</th>
								<th>参与调查</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.page.list }" var="survey">
								<tr>
									<td><IMG alt="无照片" src="${survey.logoPath}" /></td>
									<td><a>${survey.surveyName}</a></td>
									<td><a href="guest/engage/entry/${survey.surveyId}" class="btn btn-xs btn-danger" >参与调查</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		

		<!-- 分页 -->
		<c:set value="guest/engage/showAllAvailable" scope="page"
			var="targetUrl" />
		<%@ include file="/jspPage/pages.jsp"%>
	</div>
</body>
</html>