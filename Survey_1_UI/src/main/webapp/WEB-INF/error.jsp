<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
<%@include file="/jspPage/base.jsp"%>
<script type="text/javascript">
	$(function(){
		$("#back").click(function(){
			window.history.back();
		})
	})
	
</script>
</head>
<body>
                                                         <center><div><h1>${requestScope.exception.message}</h1></div></center>
                                                        <center><div><a href="#" id="back">返回</a></div></center>
</body>
</html> --%>

<!DOCTYPE html>
<html lang="en">
  <head>

<%@include file="/jspPage/base.jsp"%>
    <title>错 误 页 面</title>
    <link href="bootstrap/cover.css" rel="stylesheet">
<script type="text/javascript">
	$(function(){
		$("#back").click(function(){
			window.history.back();
		})
	})
	
</script>
   
  </head>

  <body>

    <div class="site-wrapper">

      <div class="site-wrapper-inner">

        <div class="cover-container">

          <div class="inner cover">
            <h1 class="cover-heading">sorry!</h1>
            <p class="lead">${requestScope.exception.message}</p>
            <p class="lead">
              <a  href="#" id="back" class="btn btn-lg btn-default">Back</a>
            </p>
          </div>

          <div class="mastfoot">
            <div class="inner">
              <p>itguiguSurveyOnline  <a href="#"> home</a>, by <a href="#">@zgw</a>.</p>
            </div>
          </div>

        </div>

      </div>

    </div>

   
  </body>
</html>