<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<head>
<title>我 未 完 成 调 查</title>
<!-- <link rel="stylesheet" href="bootstrap/theme.css"/> -->
<%@include file="/jspPage/base.jsp"%>
<SCRIPT type="text/javascript">
$(function(){
	$(".btn-danger").click(function(){
		var c=confirm("是否真的要删除该包裹");
		if(c){
			return true;
		}
		return false;
	})
})
</SCRIPT>
</head>
<body>
	
<div class="container">	
	<%@ include file="/jspPage/navigator_guest.jsp"%>
	<div class=" text-center">
			<h4>[设计调查]</h4>
			
			<!-- 单独展示调查数据 -->
			<table class="table table-bordered table-hover text-center">
				<tr>
					<td>调查名称</td>
					<td>调查Logo</td>
					<td colspan="2">操作</td>
				</tr>
				<tr>
					<td>${requestScope.survey.surveyName }</td>
					<td>
						<img src="${requestScope.survey.logoPath }"/>
					</td>
					<td>
						<a href="guest/bag/toAddUI/${requestScope.survey.surveyId}" class="btn btn-default">创建包裹</a>
					</td>
					<td>
						<a href="guest/bag/toAdjustUI/${requestScope.survey.surveyId}" class="btn btn-default">调整包裹顺序</a>
					</td>
				</tr>
			</table>
			
			<!-- 展示bagSet和questionSet -->
			<table class="table table-bordered table-hover text-center">
				
				<c:if test="${empty requestScope.survey.bags }">
					
					<tr>
						<td>暂时没有包裹数据！</td>
					</tr>
					
				</c:if>
				
				<c:if test="${!empty requestScope.survey.bags }">
					
					<tr>
						<td colspan="2">包裹信息</td>
					</tr>
					<tr>
						<td>包裹名称</td>
						<td>基本操作</td>
					</tr>
					
					<c:forEach items="${requestScope.survey.bags }" var="bag">
						
						<tr>
							<td>${bag.bagName }</td>
							<td>
								<a href="guest/bag/removeBag/${bag.bagId }/${requestScope.survey.surveyId}" class="btn btn-warning">删除包裹</a>
								<a href="guest/bag/toEditUI/${bag.bagId }" class="btn btn-default">更新包裹</a>
								<a href="guest/bag/removeBagDeeply/${bag.bagId }/${requestScope.survey.surveyId}" class="btn btn-danger">深度删除</a>
								<a href="guest/bag/toMoveOrCopyPage/${bag.bagId }/${requestScope.survey.surveyId}/1" class="btn btn-default">移动/复制包裹</a>
								<a href="guest/question/toAddUI/${bag.bagId }/${requestScope.survey.surveyId}" class="btn btn-default">创建问题</a>
							</td>
						</tr>
						<tr>
							<td><!-- 留空，以实现缩进效果 --></td>
							<td>
								<table class="table table-bordered table-hover text-center">
									
									<c:if test="${empty bag.questions }">
										<tr>
											<td>暂时没有问题！</td>
										</tr>
									</c:if>
									<c:if test="${!empty bag.questions }">
										<tr>
											<td>问题详情</td>
											<td>问题操作</td>
										</tr>
										<c:forEach items="${bag.questions }" var="question">
											<tr>
												<td>
													<h5>${question.questionName}</h5>
												<c:if test="${question.questionType==1 }">
													<c:forEach items="${question.optionList}" var="option" varStatus="MyvarStatus">
														
															
															<input id="${question.questionId}${MyvarStatus.index }" type="radio" />
															<label for="${question.questionId}${MyvarStatus.index }">${option}</label>
													</c:forEach>
												</c:if>
												<c:if test="${question.questionType==2 }">
													<c:forEach items="${question.optionList}" var="option" varStatus="MyvarStatus">
														
															
															<input id="${question.questionId}${MyvarStatus.index }"  type="checkbox" />
															<label for="${question.questionId}${MyvarStatus.index }">${option}</label>
													</c:forEach>
												</c:if>
												<c:if test="${question.questionType==3 }">
															
															${question.questionName}<input  type="text" />
													
												</c:if>
												</td>
												<td>
													<a href="guest/question/removeQuestion/${question.questionId}/${requestScope.survey.surveyId}" class="btn btn-warning">删除问题</a>
													<a href="guest/question/toEditUI/${question.questionId}/${requestScope.survey.surveyId}" class="btn btn-default">更新问题</a>
												</td>
											</tr>
										</c:forEach>
									</c:if>
									
								</table>
							</td>
						</tr>
						
					</c:forEach>
					
				</c:if>
				
				<tr>
					<td colspan="2">
						<a href="guest/survey/complete/${requestScope.survey.surveyId}" class="btn btn-default">完成调查</a>
					</td>
				</tr>
				
			</table>
			
		</div>	
	</div>	
		
		
		
		
		
	
	${requestScope.survey.bags }	
		
	
		
		
		
		

		<!-- /container -->
</body>
</html>