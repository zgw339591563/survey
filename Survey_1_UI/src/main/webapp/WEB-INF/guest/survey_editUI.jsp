<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新调查</title>
<%@include file="/jspPage/base.jsp"%>
</head>

<body>
    <div class="container">
    	
        <div class="row">
        <%@ include file="/jspPage/navigator_guest.jsp"%>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>更 新 调 查</h2>
                </div>

                <form id="defaultForm" method="post" action="guest/survey/updateSurvey" class="form-horizontal" enctype="multipart/form-data">
                	<input type="hidden" name="logoPath" value="${requestScope.survey.logoPath}">
                	<input type="hidden" name="pageNum" value="${requestScope.pageNum}">
                	<input type="hidden" name="surveyId" value="${requestScope.survey.surveyId}">
                	<c:if test="${!empty requestScope.exception }">
					<div class="form-group">
						<center>${requestScope.exception.message }</center>
					</div>
					</c:if>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">调 查 名 称</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control" name="surveyName" value="${requestScope.survey.surveyName}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-4 control-label">logo 图 片</label>
                        <div class="col-lg-7">
                         <img alt="请选择图片" src="${requestScope.survey.logoPath}">  <input type="file"  name="logoFile" />
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-lg-9 col-lg-offset-4">
                            <button type="submit" class="btn btn-primary" name="filevalidate" value="Validate">保 存</button>
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
        	surveyName: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '调查名不能为空'
                    }
                }
            },
        	logoFile: {
                 validators: {
                    file: {
                       extension: 'jpg', 
                         type: 'image/jpeg', 
                        maxSize: 500*1024*1024,
                        message: '图片不能大于5M,且只能上传jpg文件格式的图片!'
                    }
                } 
            }
        }
    })
    
});
</script>
</body>
</html>