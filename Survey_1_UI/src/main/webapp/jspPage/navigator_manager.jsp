<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="atguigu" uri="http://www.atguigu.com/survey/tag" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<nav class="navbar navbar-default  navbar-inverse" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.jsp">尚硅谷在线调查系统[后台]</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<c:if test="${empty sessionScope.loginadmin }">
							<li><a href="manager/admin/to_loginUI">登录</a></li>
						</c:if>
						<c:if test="${!empty sessionScope.loginadmin}">
							<li><a>欢迎您管理员: ${sessionScope.loginadmin.adminName}</a></li>
							<li><a href="manager/admin/logout">退出登录</a></li>
						</c:if>
						<atguigu:Auth servletPath="/manager/statistics/showAllAvailable">
						<li><a href="manager/statistics/showAllAvailable/1">统计调查</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/res/showList">
						<li><a href="manager/res/showList">资源列表</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/auth/toAddUI">
						<li><a href="manager/auth/toAddUI">创建权限</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/auth/showList">
						<li><a href="manager/auth/showList">权限列表</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/role/toAddUI">
						<li><a href="manager/role/toAddUI">创建角色</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/role/showList">
						<li><a href="manager/role/showList">角色列表</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/admin/toAddUI">
						<li><a href="manager/admin/toAddUI">创建账号</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/admin/showList">
						<li><a href="manager/admin/showList">账号列表</a></li>
						</atguigu:Auth>
						<atguigu:Auth servletPath="/manager/log/showList">
						<li><a href="manager/log/showList/1">日志列表</a></li>
						</atguigu:Auth>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="manager/admin/toMainUI">首页</a></li>
						<li><a href="index.jsp">回前台首页</a></li>
					</ul>
				  </div>
			</div>
		</nav>