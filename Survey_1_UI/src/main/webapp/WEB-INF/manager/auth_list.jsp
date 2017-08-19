<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限列表</title>
<%@ include file="/jspPage/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$(".updateAuthName").change(function(){
			var authName=this.value;
			var thisobj=this;
			var authId=$(this).attr("id");
			alert(authId);
			authName=$.trim(authName);
			if(authName==""){
                this.value=this.defaultValue;
                alert("权限名不能为空");
                return;
         	}
			var yes=confirm("是否要修改该权限名称");
			if(!yes){
				
				this.value=this.defaultValue;
				return ;
			}else{
				$.post("manager/auth/updateAuthName",{"authName":authName,"authId":authId,"ajaxRequest":"ajaxRequest"},
					function(data){
					alert(data);
					if(data=="ok"){
						alert("修改成功");
						thisobj.value=authName;
						thisobj.defaultValue=thisobj.value;
					}else if(data=="pleaselogin"){
						alert("请登录后再进行操作");
						location.href="${pageContext.request.contextPath}/manager/admin/to_loginUI"
					}else{
						alert("修改失败");
						thisobj.value=thisobj.defaultValue;
					}
				},"json"
			  )
			}
		});
		
		$("#submbut").click(function(){
			var checkedNum=$("input:checked").length;
			if(checkedNum==0){
				alert("请选择要删除的权限");
				return false;
			}else{
				var c=confirm("是否要删除该权限");
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
	})

</script>

</head>
<body>
	<div class="container">

		<div class="row">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="text-center">权限列表</div>
			
			<div class="text-center">
				<form action="manager/auth/batchDelete"
					class="form-horizontal text-center" method="post"
					 id="defaultForm2">
					<table class="table table-bordered table-hover text-center">
						
						<tr>
							<td>ID</td>
							<td>权限名称</td>
							<td>分配资源</td>
							<td>删除</td>
						</tr>
						<c:if test="${!empty requestScope.authList }">
							<c:forEach items="${requestScope.authList }" var="auth">
								<tr>
									<td>${auth.authId}</td>
									<td><input id="${auth.authId}" class="updateAuthName" type="text" name="authName" value="${auth.authName}"></td>
									<td>
										<a class="btn btn-default" href="manager/auth/toDispatcherUI/${auth.authId}">分配资源</a>
									</td>
									<td>
											<input  id="s${auth.authId}" name="authList"  value="${auth.authId}"  type="checkbox" >
											<label for="s${auth.authId}">^_^删我呀^_^</label>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty requestScope.authList }">
							<tr>
								<td colspan="4">没有资源列表</td>
							</tr>
						</c:if>
						<tr>
						<td colspan="3">
							<button id="submbut" type="submit" class="btn btn-primary" >批量删除</button>
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