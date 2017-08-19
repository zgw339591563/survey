<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>调查大纲</title>
<%@include file="/jspPage/base.jsp"%>
<script type="text/javascript">

$(function(){
	$(".showmychart").click(function(){
		var myId=this.value;
		alert(myId);
		$.ajax({
			type:"post",
			url:"manager/statistics/showChart2/"+myId,
		 success: function(data){
			 $('.qq').highcharts({
				 chart: {
			            plotBackgroundColor: null,
			            plotBorderWidth: null,
			            plotShadow: true,
			            type: 'pie',
			            options3d: {
			                enabled: true,
			                alpha: 45,
			                beta: 0
			            }
			        },
			        title: {
			            text: '问题选项统计'
			        },
			         tooltip: {
			            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
			        },
			        plotOptions: {
			            pie: {
			                innerSize: 80,
			                depth: 30,
			                allowPointSelect: true,
			                cursor: 'pointer',
			                dataLabels: {
			                    enabled: true,
			                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
			                    style: {
			                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
			                    }
			                }
			            }
			        },
			        series: [{
			            type: 'pie',
			            name: '选项占比',
			            data:data
			        }]
			    });
		 }
		})
	})
})
	
</script>
</head>
<body>
	<div class="container" >
		<%@ include file="/jspPage/navigator_manager.jsp"%>
		<div class="col-lg-8 col-lg-offset-2" >
			<div class="page-header text-center">
				<h2>调查大纲</h2>
			</div>
			
			<table class="table table-bordered table-hover text-center">
				
				<tr>
					<td colspan="3">包裹信息</td>
				</tr>
				<c:forEach items="${requestScope.survey.bags }"  var="bag">
					<tr>
						<th>${bag.bagName}</th>
						<th>问题统计结果</th>
					</tr>
					
						<c:forEach items="${bag.questions}" var="question">
							<tr>
								<td>${question.questionName}</td>
								<td>
								<c:if test="${question.questionType==1||question.questionType==2 }">
									
									<a target="blank"  href="manager/statistics/showChart/${question.questionId}" >Jfreechart查看统计结果</a>
									<button name="btn"   value="${question.questionId}"  class="showmychart btn btn-primary">Highchart查看统计结果</button>
									
								</c:if>
								<c:if test="${question.questionType==3 }">
									<a target="blank"  href="manager/statistics/showTextResult/${question.questionId}">查看回答内容</a>
								</c:if>
								</td>
							</tr>
						</c:forEach>
					
				</c:forEach>
				<div class="qq"></div>
			</table>
			
		</div>
	</div>

</body>
</html>