<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员列表</title>
<%@ include file="/jspPage/base.jsp" %>
</head>
<body>

	<div class="container">

		<div class="row text-center">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header text-center">
					<h2>管理员列表</h2>
				</div>
				<form action="manager/admin/batchDelete"
					class="form-horizontal text-center" method="post"
					 id="defaultForm2">
					<table class="table table-bordered table-hover text-center">
					<tr>
						<td>AdminName</td>
						<td>分配</td>
						<td>删除</td>
					</tr>
					<c:forEach items="${requestScope.adminList}" var="admin" >
					<tr>
						<td>
							${admin.adminName}
						</td>
						<td>
						<c:if test="${admin.adminName!='SuperAdmin' }">
							<a class="btn btn-default" href="manager/admin/toDispatcherUI/${admin.adminId}">分配角色</a>
						</c:if>
						</td>
						<td>
							<c:if test="${admin.adminName!='SuperAdmin' }">
							<input  id="s${admin.adminId}" name="adminIdList" value="${admin.adminId}"  type="checkbox" >
							<label for="s${admin.adminId}">^_^删我呀^_^</label>
							</c:if>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="2">
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

<script type="text/javascript">
	$(function(){
		
		
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
</body>
</html>