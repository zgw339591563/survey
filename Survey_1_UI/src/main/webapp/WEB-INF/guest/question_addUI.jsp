<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加问题</title>
<%@include file="/jspPage/base.jsp"%>
<script type="text/javascript">
	$(function(){
	  $("#optionsTr").hide();
		
		$(":radio").click(function(){
			var type=this.value;
			if(type==2||type==1){
				$("#optionsTr").show();
			}
			if(type==3){
				$("#optionsTr").hide();
			}
		})
		
		$("button[type=submit]").click(function(){
			var questionName=$("input[name=questionName]").val();
			if(questionName==""){
				alert("问题名称不能为空");
				return false;
			}
			var radioValue=$(":checked").val();
			if(radioValue==2||radioValue==1){
				var textareaVal=$("textarea").val();
				if(textareaVal==""){
					alert("选项不能为空");
					return false;
				}
			}
		})
		
	})

</script>
</head>
<body>
<div class="jumbotron">
<div class="container">
<%@ include file="/jspPage/navigator_guest.jsp"%>
		<h4>[创建问题]</h4>
		
		
		<form action="guest/question/saveQuestion" method="post" id="defaultForm" class="form-horizontal" >
		
			<input type="hidden" name="bagId" value="${requestScope.bagId }"/>
			<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
			<fieldset>
			<table class="table table-bordered table-hover text-center">
				
				<tr>
					<td>问题名称</td>
					<td>
					<input type="text" name="questionName" class="form-control"/>
					</td>
				</tr>
				
				<tr>
					<td>选择题型</td>
					<td>
						<input id="type01" type="radio" name="questionType" value="1"/>
						<label for="type01">单选题</label>
						
						<input id="type02" type="radio" name="questionType" value="2"/>
						<label for="type02">多选题</label>
						
						<input id="type03" type="radio" name="questionType" value="3" checked="checked"/>
						<label for="type03">简答题</label>
						
					</td>
				</tr>
				<tr id="optionsTr">
					<td>选项</td>
					<td>
						<!-- 不使用单行文本框是因为单行文本框中输入数据的过程中“回车”会提交表单 -->
						<textarea name="questionOptions" class="form-control" rows="10"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-default" style="width: 30%">保存</button>
					</td>
				</tr>
			</table>
			</fieldset>
		</form>
		</div>
	</div>

</body>
</html>