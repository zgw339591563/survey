<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改问题</title>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/jspPage/base.jsp"%>
<script type="text/javascript">
	$(function(){
		
		var questionType=$(":radio:checked").val();
		
		if(questionType==3){
			$("#optionsTr").hide();
		}
		
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
<% 

   Map<Integer,String> map=new LinkedHashMap<Integer,String>();
   map.put(1, "单选题");
   map.put(2, "多选题");
   map.put(3, "简答题");
   pageContext.setAttribute("radioMap", map);
%>



<div class="container ">
<%@ include file="/jspPage/navigator_guest.jsp"%>
		<h4>[修改问题]</h4>
		
		<form:form action="guest/question/updateQuestion" modelAttribute="question" method="post">
		<form:hidden path="questionId"/>
		<input type="hidden" name="surveyId" value="${requestScope.surveyId }"/>
		
		<table class="table table-bordered table-hover text-center">
				<tr>
					<td>问题名称</td>
					<td>
						<form:input path="questionName" cssClass="form-control"/>
					</td>
				</tr>
				<tr>
					<td>选择题型</td>
					<td >
						
						<form:radiobuttons path="questionType" items="${radioMap}" />
					</td>
				</tr>
				<tr id="optionsTr">
					<td>选项</td>
					<td>
						<!-- 不使用单行文本框是因为单行文本框中输入数据的过程中“回车”会提交表单 -->
						<form:textarea path="questionOptions" cssClass="form-control" rows="10"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-default" style="width: 30%">保存</button>
					</td>
				</tr>
			</table>
	</form:form>
		</div>
	
	
	
	
</body>
</html>