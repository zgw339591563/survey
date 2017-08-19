<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源列表</title>
<%@ include file="/jspPage/base.jsp"%>
</head>
<body>
	<div class="container">

		<div class="row">
			<%@ include file="/jspPage/navigator_manager.jsp"%>
			<div class="text-center">资源列表</div>
			
			<form action="manager/res/importRes"
					class="form-horizontal text-center" method="post"
					enctype="multipart/form-data" id="defaultForm">
			
				<table class="table table-bordered table-hover text-center">
					<tr>
							<td>
								<div class="col-lg-9 col-lg-offset-4 text-center">
									<input type="file" name="importFile">
								</div>
							</td>
							<td colspan="3">
								<div class="col-lg-9 col-lg-offset-4 text-center">
									<button class="btn btn-primary" name="btn">一键导入资源</button>
								</div>
							</td>
						</tr>
				</table>
			</form>

			<div class="text-center">
				<form action="manager/res/batchDelete"
					class="form-horizontal text-center" method="post"
					 id="defaultForm2">
					<table class="table table-bordered table-hover text-center">
						
						<tr>
							<td>ID</td>
							<td>ServletPath</td>
							<td>资源状态</td>
							<td>批量删除</td>
						</tr>
						<c:if test="${!empty requestScope.resList }">
							<c:forEach items="${requestScope.resList }" var="res">
								<tr>
									<td>${res.resId}</td>
									<td>${res.servletPath}</td>
									<td>
									<c:if test="${!res.publicStatus}">
										<input id="${res.resId}" type="button"  value="受保护资源" class="btn btn-default">
									</c:if>
									<c:if test="${res.publicStatus}">
										<input id="${res.resId}" type="button"  value="公共资源" class="btn btn-primary">
									</c:if>
									</td>
									<td>
											<input  id="s${res.resId}" name="resIdList" value="${res.resId}"  type="checkbox" >
											<label for="s${res.resId}">^_^删我呀^_^</label>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty requestScope.resList }">
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
<script type="text/javascript">
 		$(document).ready(function() {
			 $('#defaultForm').bootstrapValidator({

				fields : {
					importFile : {
						validators : {
							file : {
								extension : 'xls',
								type : 'application/vnd.ms-excel',
								message : '只能导入xls型的excel文件'
							}, 
						notEmpty: {
                            message: '文件不能为空'
                        }
					}
				}
			}
		});   
			$("input[type=button]").click(function(){
				var resId=this.id;
				
				var thisRes=this;
				var resParam={"resId":resId,"time":new Date(),"ajaxRequest":"ajaxRequest"};
				$.ajax({
					type:"post",
					data:resParam,
					url:"${pageContext.request.contextPath}/manager/res/toggleStatus",
					dataType:"json",
					success:function(data){
						
						if(data=="pleaselogin"){
							alert("请登录后再进行操作");
							location.href="${pageContext.request.contextPath}/manager/admin/to_loginUI";
						}
						var dataResult=data.result;
						if(dataResult==0){
							alert("替换失败");
						}
						if(dataResult==1){
							
							var oldValue=thisRes.value;
							
							if(oldValue=="受保护资源"){
								thisRes.value="公共资源";
							}
							
							if(oldValue=="公共资源"){
								thisRes.value="受保护资源";
							}
							$(thisRes).toggleClass("btn btn-default btn btn-primary");
						}
					}
				})
			})
			 
			
			
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
			
			
	}); 
</script>
</body>
</html>