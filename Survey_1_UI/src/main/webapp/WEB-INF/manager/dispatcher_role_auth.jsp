<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>给角色分配权限</title>
<%@ include file="/jspPage/base.jsp" %>
</head>
<body>

	<div class="container">

		<div class="row text-center">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header text-center">
					<h2>给角色分配权限</h2>
				</div>
				<form action="manager/role/doDispatcher" method="post">
			
					<input type="hidden" name="roleId" value="${requestScope.roleId}"/>
				
					<table class="table table-bordered table-hover text-center">
					
					<c:forEach items="${requestScope.allAuthList}" var="auth" >
						<tr>
							<td>
								${auth.authName}
							</td>
							<td>
								<input id="checkbox${auth.authId}"  type="checkbox" name="authIdList"  value="${auth.authId}" 
									<c:forEach items="${requestScope.roleAuthList}" var="roleAuth">
											<c:if test="${roleAuth.authName==auth.authName}">
												checked="checked"
											</c:if>
									</c:forEach>
								/>
									<label for="checkbox${auth.authId}">${auth.authName}</label>
									
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
	${requestScope.roleAuthList}

</body>
</html>