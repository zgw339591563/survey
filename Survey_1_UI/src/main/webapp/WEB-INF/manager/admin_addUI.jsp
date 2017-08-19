<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建管理员</title>
<%@include file="/jspPage/base.jsp"%>
</head>
<body>
	<div class="container ">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
		<div class="row">
			<!-- form: -->
			
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>管 理 员注 册</h2>
				</div>

				<form id="defaultForm" method="post"  class="form-horizontal"
					action="manager/admin/saveAdmin">

					<fieldset>
					<div class="form-group">
						<c:if test="${!empty requestScope.exception }">
							<td class="warning">${requestScope.exception.message }</td>
						</c:if>
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
						<label class="col-lg-3 control-label">确认密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="adminPwdConfirm" />
						</div>
					</div>
					</fieldset>
					
					<div class="form-group">
						<div class="col-lg-5 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" style="width: 100%">提交</button>
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
        	adminName: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '管理员名不能为空'
                    }/*
                     remote: {
                        type: 'POST',
                        url: 'remote.php',
                        message: 'The username is not available'
                    }, */
                    
                }
            },
            adminPwd: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'adminName',
                        message: '密码不能和用户名一样'
                    }
                }
            },
            adminPwdConfirm: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'adminPwd',
                        message: '密码和确认密码不一致'
                    }
                }
            }
        }
    });

   
});
</script >
</body>
</html>