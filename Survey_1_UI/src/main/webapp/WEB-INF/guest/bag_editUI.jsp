<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新包裹</title>
<%@include file="/jspPage/base.jsp"%>

</head>

<body>
    <div class="container">
    	
        <div class="row">
        <%@ include file="/jspPage/navigator_guest.jsp"%>
            <div class="col-lg-8 col-lg-offset-2">
                <div class="page-header">
                    <h2>更 新 包 裹</h2>
                </div>

                <form id="defaultForm" method="post" action="guest/bag/updateBag" class="form-horizontal" >
                	<input type="hidden" name="bagId" value="${requestScope.bag.bagId}">
                	<input type="hidden" name="surveyId" value="${requestScope.bag.surveyId}">
                	<c:if test="${!empty requestScope.exception }">
					<div class="form-group">
						<center>${requestScope.exception.message }</center>
					</div>
					</c:if>
                    <div class="form-group">
                        <label class="col-lg-4 control-label">包 裹 名 称</label>
                        <div class="col-lg-7">
                            <input type="text" class="form-control" name="bagName" value="${requestScope.bag.bagName}"/>
                            <span class="help-block">请输入包 裹 名 称</span>
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
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	/* bagName: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '包裹名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 10,
                        message: '包裹名必须在2到10位长度之间且不能有空格'
                    }
                }
            } */
        }
    })
    
});
</script>
</body>
</html>