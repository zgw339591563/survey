<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>给管理员分配角色</title>
<%@ include file="/jspPage/base.jsp" %>
</head>
<body>

	<div class="container">

		<div class="row text-center">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header text-center">
					<h2>给管理员分配角色</h2>
				</div>
				<form action="manager/admin/doDispatcher" method="post">
			
					<input type="hidden" name="adminId" value="${requestScope.adminId}"/>
				
					<table class="table table-bordered table-hover text-center">
					
					<c:forEach items="${requestScope.allRoleList}" var="role" >
						<tr>
							<td>
								${role.roleName}
							</td>
							<td>
								<input id="checkbox${role.roleId}"  type="checkbox" name="roleIdList"  value="${role.roleId}" 
									<c:forEach items="${requestScope.roleListDeeply}" var="deeplyRole">
											<c:if test="${deeplyRole.roleName==role.roleName}">
												checked="checked"
											</c:if>
									</c:forEach>
								/>
									<label for="checkbox${role.roleId}">${role.roleName}</label>
									
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
	${requestScope.roleListDeeply}<br>

</body>
</html>