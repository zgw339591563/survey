<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>给权限分配资源</title>
<%@ include file="/jspPage/base.jsp" %>
</head>
<body>

	<div class="container">

		<div class="row text-center">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header text-center">
					<h2>给权限分配资源</h2>
				</div>
				<form action="manager/auth/doDispatcher" method="post">
			
					<input type="hidden" name="authId" value="${requestScope.authId}"/>
				
					<table class="table table-bordered table-hover text-center">
						<tr>
							<td>resId</td>
							<td>servletPath</td>
						</tr>
					<c:forEach items="${requestScope.allResList}" var="res" >
						
						<tr>
							<td>
								${res.resId}
							</td>
							<td>
								<input id="checkbox${res.resId}"  type="checkbox" name="resIdList"  value="${res.resId}" 
									<c:forEach items="${requestScope.authResList}" var="authRes">
											<c:if test="${authRes.servletPath==res.servletPath}">
												checked="checked"
											</c:if>
									</c:forEach>
								/>
									<label for="checkbox${res.resId}">${res.servletPath}</label>
									
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="2">
							<button type="submit" class="btn btn-default" style="width: 30%">OK</button>
						</td>
					</tr>
					
					</table>
				</form>
			</div>
		</div>
	</div>
	${requestScope.authResList}

</body>
</html>