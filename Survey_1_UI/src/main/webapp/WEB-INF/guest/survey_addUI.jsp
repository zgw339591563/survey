<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建调查</title>
<%@include file="/jspPage/base.jsp"%>
<script type="text/javascript">
	/* $(function() {
		$("button[type=submit]").click(function() {
			var surveyName = $("input[name=surveyName]").val();
			if (surveyName == "") {
				alert("调查名称不能为空");
				return false;
			}

		})
	}) */
</script>
</head>

<body>
	<div class="container">
	
		<div class="row ">
			<%@ include file="/jspPage/navigator_guest.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>创 建 调 查</h2>
				</div>

				<form id="defaultForm" method="post"
					action="guest/survey/saveSurvey" class="form-horizontal"
					enctype="multipart/form-data">
					<c:if test="${!empty requestScope.exception }">
						<div class="form-group">
							<center>${requestScope.exception.message }</center>
						</div>
					</c:if>
					<div class="form-group">
						<label class="col-lg-4 control-label">调 查 名 称</label>
						<div class="col-lg-7">
							<input type="text" class="form-control" name="surveyName" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-4 control-label">logo 图 片</label>
						<div class="col-lg-7">
							 <input type="file" name="logoFile" class="file-loading"
								accept="image/*" id="input-image" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-9 col-lg-offset-4">
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

			$("#input-image").fileinput({
			    allowedFileExtensions: ["jpg", "png", "gif"],
			    maxImageWidth: 200,
			    maxImageHeight: 150,
			    resizePreference: 'height',
			    maxFileCount: 1,
			    resizeImage: true
			}).on('filepreupload', function() {
			    $('#kv-success-box').html('');
			}).on('fileuploaded', function(event, data) {
			    $('#kv-success-box').append(data.response.link);
			    $('#kv-success-modal').modal('show');
			});
		});
	</script>
</body>
</html>