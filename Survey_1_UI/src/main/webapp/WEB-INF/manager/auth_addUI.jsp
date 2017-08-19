<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建权限</title>
<%@ include file="/jspPage/base.jsp" %>
</head>
<body>

	<div class="container">

		<div class="row">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>创 建 权 限</h2>
				</div>

				<form id="defaultForm" method="post" action="manager/auth/saveAuth"
					class="form-horizontal">
					
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">权 限 名 称</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="authName" />
								<c:if test="${!empty requestScope.exception }">
								${requestScope.exception.message }
							</c:if>
							</div>
						</div>
					</fieldset>


					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" name="filevalidate"
								value="Validate">保 存</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

<script type="text/javascript">
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
        
        fields: {
        	authName: {
                message: 'The authName is not valid',
                validators: {
                    notEmpty: {
                        message: '权限名不能为空'
                    }
                }
            }
        }
    });
});
</script>

</body>
</html>