<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
<%@include file="/jspPage/base.jsp"%>
</head>
<body>
	<div class="container ">
		<%@ include file="/jspPage/navigator_guest.jsp"%>
		<div class="row">
			<!-- form: -->
			<section>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>注 册</h2>
				</div>

				<form id="defaultForm" method="post"  class="form-horizontal"
					action="guest/user/regist">

					<fieldset>
					<div class="form-group">
						<c:if test="${!empty requestScope.exception }">
							<td class="warning">${requestScope.exception.message }</td>
						</c:if>
						<label class="col-lg-3 control-label">用户名</label>
						<div class="col-lg-5">

							<input type="text" class="form-control" name="userName" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="userPwd" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-lg-3 control-label">确认密码</label>
						<div class="col-lg-5">
							<input type="password" class="form-control" name="userPwdConfirm" />
						</div>
					</div>
					</fieldset>
					<div class="form-group">
						<label class="col-lg-3 control-label"></label>
						<div class="col-lg-5">
							<div class="radio" style="text-align: center;">
								<label> <input type="radio" name="company" value="true"
									checked="checked" /> 企业用户
								</label> <label> <input type="radio" name="company"
									value="false" /> 个人用户
								</label>
							</div>
						</div>
					</div>

					<div class="form-group">
						<div class="col-lg-5 col-lg-offset-3">
							<button type="submit" class="btn btn-primary" style="width: 100%">提交</button>
						</div>
					</div>
				</form>
			</div>
			</section>
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function() {

    $('#defaultForm').bootstrapValidator({
      
        fields: {
            userName: {
                message: 'The username is not valid',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 2,
                        max: 16,
                        message: '用户名必须在2到16位之间'
                    }/*,
                     remote: {
                        type: 'POST',
                        url: 'remote.php',
                        message: 'The username is not available'
                    }, */
                    
                }
            },
            userPwd: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    different: {
                        field: 'userName',
                        message: '密码不能和用户名一样'
                    }
                }
            },
            userPwdConfirm: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'userPwd',
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