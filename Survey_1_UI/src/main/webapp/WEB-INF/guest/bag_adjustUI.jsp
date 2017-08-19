<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调整包裹顺序</title>
<%@include file="/jspPage/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#back").click(function(){
			window.history.back();
		})
		
		$(".bagOrder").change(function(){
			var bagorder=this.value;
			bagorder=$.trim(bagorder);
			if(bagorder==""||isNaN(bagorder)){
				this.value=this.defaultValue;
				return;
			}
			this.value=bagorder;//去空串后的值再赋给原来的文本框后再进行后续操作
			this.defaultValue=this.value;
		})
	})
	
</script>
</head>

<body>
	<div class="container">

		<div class="row">
			<%@ include file="/jspPage/navigator_guest.jsp"%>
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h2>调 整 包 裹 顺 序</h2>
				</div>

				<form action="guest/bag/doAdjust" method="post"
					class="form-horizontal">
					<input type="hidden" name="surveyId"
						value="${requestScope.surveyId}">

					<table class="table table-bordered text-center table-hover">
						<c:if test="${empty requestScope.bags}">
							<tr><td>暂时没有包裹数据</td></tr>
							<td> <a  href="#" id="back" class="btn btn-lg btn-default">返回</a></td>
						</c:if>
					
						<c:if test="${!empty requestScope.bags}">
							<tr>
								<td>ID</td>
								<td>包裹名称</td>
								<td>包裹顺序</td>
							</tr>
							<c:forEach items="${requestScope.bags}" var="bag">

								<tr>
									<td>${bag.bagId}<input class="form-control" type="hidden"
										name="bagId" value="${bag.bagId}">
									</td>
									<td>${bag.bagName}<input class="form-control"
										type="hidden" name="bagName" value="${bag.bagName}"></td>
									<td><input type="text" name="bagOrder"
										value="${bag.bagOrder}" class="bagOrder"></td>
								</tr>
							</c:forEach>
							<td colspan="3"><button type="submit"
									class="btn btn-default" style="width: 30%">Submit</button></td>
						</c:if>
					</table>
				</form>
			</div>
		</div>
	</div>


</body>
</html>