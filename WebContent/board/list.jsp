<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="./assets/css/style.css">
</head>
<body>
<div class="container">
	<c:set var="pg" value="${result.pg}" />
	<c:set var="list" value="${result.list}" />
	<div class="content">
		<ol class="breadcrumb">
		  <li><a href="#1">Home</a></li>
		  <li class="active">Board</li>
		</ol>
		<hr>		
		<div class="row">
		    <div class="col-md-10"></div>
		    <div class="col-md-2" style="text-align: right">
				전체 <c:out value="${pg.count}"/>개
		    </div>
		</div>
		<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>등록일</th>
				</tr>
			</thead>
			<c:forEach var="board" items="${list}">
				<tr>
					
					<td>${board.no}</td>
					
					<td><a href="board?action=mvBoard&detail=${board.no}">${board.title}</a></td>
					
					<td>${board.userId}</td>
					<td>${board.regDate}</td>
					
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan='4'>입력된 게시물이 없습니다.</td>
				</tr>
			</c:if>
		</table>
		</div>
		
		<c:if test="${pg.count != 0}">
			<nav>
			  <ul class="pagination">
			  	<c:if test="${pg.prev}">
				    <li>
				      <a href="board?action=mvPage&pageNo=${pg.beginPage - 1}">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
			    </c:if>

				<c:forEach var="i" begin="${pg.beginPage}" end="${pg.endPage}">
				    <c:choose>
				    	<c:when test="${i eq pg.pageNo}">
						    <li class="active"><a href="#${i}">${i}</a></li>
				    	</c:when>
				    	<c:otherwise>
						    <li><a href="board?action=mvPage&pageNo=${i}">${i}</a></li>
				    	</c:otherwise>
				    </c:choose>
				</c:forEach>
				
				<c:if test="${pg.next}">
				    <li>
				      <a href="board?action=mvPage&pageNo=${pg.endPage + 1}">
				        <span aria-hidden="true">&raquo;</span>
				      </a>
				    </li>	
			    </c:if>	    
			  </ul>
			</nav>
		</c:if>
	</div>		
</div>
<script>
	let liList = document.querySelectorAll("ul.nav.navbar-nav > li");
	for (let liEl of liList) {
		liEl.classList.remove("active");
	}
	document.querySelector("ul.nav.navbar-nav > li:nth-child(3)").classList.add("active");	
</script>
</body>
</html>

