<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>调查改变世界</title>

<!-- 声明base标签统一管理路径 -->
<!-- http://localhost:8080/SurveyExample/guest/user/toLoginUI -->
<!-- pageContext.request.serverName：主机地址 -->
<!-- pageContext.request.serverPort：端口号 -->
<!-- 主机地址和端口号之间要有“:” -->
<!-- pageContext.request.contextPath：Web应用的虚拟路径 -->
<!-- Web应用的虚拟路径本身就是以“/”开头的，所以不要在前面添加多余的“/” -->
<!-- Web应用的虚拟路径的后面必须加一个“/” -->
<!-- 原因是：只有不以“/”开头的相对路径才参照base标签，而且base标签的值和相对路径值拼接起来后必须是一个完整有效的地址 -->
<%@include file="/jspPage/base.jsp" %>

<!-- 一定要在bootstrap前面引入jQuery -->
<!-- 引入bootstrap自身的JS文件和样式文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap-theme.min.css"/>
</head>
<body>

		
	<div class="container theme-showcase" role="main">
	<%@ include file="/jspPage/navigator_guest.jsp" %>
		
		<div class="bs-docs-header" id="content" tabindex="-1">
	      <div class="container">
	        <h1><center>欢迎光临在线调查系统</center></h1>
	        
	      </div>
    	</div>
		
      <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
          <li data-target="#carousel-example-generic" data-slide-to="1"></li>
          <li data-target="#carousel-example-generic" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
          <div class="item active">
            <img data-src="holder.js/1140x500/auto/#777:#555/text:First slide" src="surveyLogos/1.jpg" alt="First slide">
          </div>
          <div class="item">
            <img data-src="holder.js/1140x500/auto/#666:#444/text:Second slide" src="surveyLogos/2.jpg" alt="Second slide">
          </div>
          <div class="item">
            <img data-src="holder.js/1140x500/auto/#555:#333/text:Third slide" src="surveyLogos/3.jpg" alt="Third slide">
          </div>
        </div>
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
          <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
          <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
          <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
          <span class="sr-only">Next</span>
        </a>
      </div>
 </div>
 
</body>
</html>