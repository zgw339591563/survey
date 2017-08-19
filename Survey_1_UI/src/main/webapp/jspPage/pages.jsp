<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 分页 -->
		<SCRIPT type="text/javascript">
			$(function(){
				$("#jumperBlock").change( function() {
					  var jumpPage=this.value;
					  jumpPage=$.trim(jumpPage);
					  if(isNaN(jumpPage)||jumpPage==""){
						  this.value="";
						  return;
					  }
					  window.location.href="${pageContext.request.contextPath}/${targetUrl}/"+jumpPage;
				});
			})
			
		
		</SCRIPT>
		<nav aria-label="...">
			<ul class="pager ">
				
				<c:if test="${requestScope.page.hasPreviousPage}">
					<li><a href="${targetUrl}/1">首页</a></li>
					<li><a href="${targetUrl}/${requestScope.page.prePage}"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<c:forEach items="${requestScope.page.navigatepageNums }" var="navigatepage">
					<li class="active"><a href="${targetUrl}/${navigatepage}">${navigatepage}</a>
					</li>
				</c:forEach>
				<c:if test="${requestScope.page.hasNextPage}">
					<li><a href="${targetUrl}/${requestScope.page.nextPage}"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
					<li><a href="${targetUrl}/${requestScope.page.pages}">末页</a></li>
				</c:if>
				<li><a>共 ${requestScope.page.pages} 页</a></li>
				<li><a>${requestScope.page.total} 条 记 录</a></li>
				<li>
					<input type="text" id="jumperBlock" class="form-control"
					style="width: 100px; display: inline; margin-left: 10px;" />
				</li>
			</ul>
		</nav>