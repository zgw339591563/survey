<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建包裹</title>
<%@include file="/jspPage/base.jsp"%>
</head>

<body>
	<div class="container">

		<div class="row">
			<%@ include file="/jspPage/navigator_guest.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>创 建 包 裹</h2>
				</div>

				<form id="defaultForm" method="post" action="guest/bag/saveBag"
					class="form-horizontal">
					<input type="hidden" name="surveyId"
						value="${requestScope.surveyId}">
					<fieldset>
						<div class="form-group">
							<label class="col-lg-3 control-label">包 裹 名 称</label>
							<div class="col-lg-5">
								<input type="text" class="form-control" name="bagName" />
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
        	bagName: {
                message: 'The bagName is not valid',
                validators: {
                    notEmpty: {
                        message: '包裹名不能为空'
                    }
                }
            }
        }
    });
});
</script>
</body>
</html>