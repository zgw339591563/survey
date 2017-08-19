<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
<%@ include file="/jspPage/base.jsp" %>
<script type="text/javascript">
$(function(){
	$(".updateRoleName").change(function(){
		var roleName=this.value;
		var thisobj=this;
		var roleId=$(this).attr("id");
		alert(roleId);
		roleName=$.trim(roleName);
		if(roleName==""){
            this.value=this.defaultValue;
            alert("角名不能为空");
            return;
     	}
		var yes=confirm("是否要修改该角色名称");
		if(!yes){
			
			this.value=this.defaultValue;
			return ;
		}else{
			$.post("manager/role/updateRoleName",{"roleName":roleName,"roleId":roleId,"ajaxRequest":"ajaxRequest"},
				function(data){
				alert(data)
				if(data=="ok"){
					alert("修改成功");
					thisobj.value=roleName;
					thisobj.defaultValue=thisobj.value;
				}else if(data=="pleaselogin"){
					alert("请登录后再进行操作");
					location.href="${pageContext.request.contextPath}/manager/admin/to_loginUI"
				}else{
					alert("修改失败");
					thisobj.value=thisobj.defaultValue;
				}
			},"json")
		}
	});
	
	$("#submbut").click(function(){
		var checkedNum=$("input:checked").length;
		if(checkedNum==0){
			alert("请选择要删除的角色");
			return false;
		}else{
			var c=confirm("是否要删除该角色");
			if(c==false){
				return false;
			}
		}
		
	})
	
	/* 全选*/
		$("#btn2").click(function(){
			
			$(":checkbox").each(function(){
				   this.checked = true;
			});
		})
		/* 全不选  */
		$("#btn3").click(function(){
			
			$(":checkbox").each(function(){
				   this.checked = false;
			});
		})
	
	/* $('#defaultForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
        	roles: {
                validators: {
                    notEmpty: {
                        message: '请选择要删除的角色'
                    }
                    
                }
            }
        }
    });  */
	
	
	
	
})

</script>
</head>
<body>

	<div class="container">

		<div class="row text-center">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header text-center">
					<h2>角色列表</h2>
				</div>
				<form action="manager/role/batchDelete"  class="form-horizontal" method="post" id="defaultForm">
					<table class="table table-bordered table-hover text-center">
					<tr>
						<td>ID</td>
						<td>角色名称</td>
						<td>分配</td>
						<td>删除</td>
					</tr>
					<c:forEach items="${requestScope.roles }" var="role" >
					<tr>
						<td>${role.roleId}</td>
						<td><input id="${role.roleId}" class="updateRoleName" type="text" name="roleName" value="${role.roleName }"></td>
						
						<td>
							<a class="btn btn-default" href="manager/role/toDispatcherUI/${role.roleId}">分配权限</a>
						</td>
						<td>
							<input id="My${role.roleId}"  type="checkbox"  name="roles" value="${role.roleId}" class="btn btn-default">
							<label for="My${role.roleId}">删除我^_^</label>
						</td>
					</tr>
					</c:forEach>
					
					<tr>
						<td colspan="3">
							<button id="submbut" type="submit" class="btn btn-default" style="width: 30%">批量删除</button>
						</td>
						<td>	
						<button id="btn2" type="button" class="btn btn-primary" >全选</button>
						<button id="btn3" type="button" class="btn btn-primary" >全不选</button>
						</td>
					</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>