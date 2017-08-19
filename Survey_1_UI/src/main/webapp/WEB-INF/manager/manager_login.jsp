<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<%@include file="/jspPage/base.jsp"%>
</head>
<body>
<div class="container">
		<%@ include file="/jspPage/navigator_manager.jsp"%>
		<div class="row">
			<!-- form: -->
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>登 录</h2>
				</div>

				<form id="defaultForm" method="post" class="form-horizontal"
					action="manager/admin/login">
					
					<c:if test="${!empty requestScope.exception }">
					<div class="form-group">
						<center>${requestScope.exception.message }</center>
					</div>
					</c:if>
					
					<div class="form-group">
						
						<label class="col-lg-3 control-label">用户名</label>
						<div class="col-lg-5">

							<input type="text" class="form-control" name="adminName" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="adminPwd" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-5 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" style="width: 100%">登录</button>
						</div>
					</div>
				</form>
			</div>
			</section>
		</div>
	</div>
</body>
</html>