<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>register</title>
    <link
      href="https://hangeul.pstatic.net/hangeul_static/css/NanumAJumMaJaYu.css"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="./assets/css/sub.css" />
  </head>
  <body>
    <div class="container">
      <header class="header-nav">
        <div>
          <a href="${pageContext.request.contextPath}/main"><h2>BODDARY</h2></a>
        </div>
      </header>
      <main id="loginMain">
        <div class="login-page">
          <div class="form" >
            <form name="register-form" class="register-form" method="POST" action="${root}/member?action=modifyMember">
              <input id="name" name="name" type="text" value="${memberInfo.name}" />
              <input id="email" name="email" type="text" value="${memberInfo.email}" />
              <input id="age" name="age" type="number" value="${memberInfo.age}" />
              <button>수정하기</button>
              <button id="deleteMemberBtn" type="button">탈퇴하기</button>
            </form>
          </div>
        </div>
      </main>
      <footer>
        <ul class="footer-list">
          <li><a href="#">개인정보 처리방침</a></li>
          <li><a href="#">이용 약관</a></li>
          <li><a href="#">오시는 길</a></li>
          <li>&copy; SSAFY</li>
        </ul>
      </footer>
    </div>
    <script type="text/javascript" src="./assets/js/login.js"></script>
    <script>
     	let deleteMemberBtn=document.querySelector("#deleteMemberBtn");
    	deleteMemberBtn.addEventListener("click",function(){
    		let really = confirm("정말 탈퇴하시겠습니까?");
			if(really){
				fetch("http://localhost:8080/%EA%B4%80%ED%86%B5back/member?action=deleteMember", {
					  method: "POST",
					}).then((response) => response.json())
					.then((result) => 
					{
						alert("회원탈퇴가 완료되었습니다.");
						location.href="http://localhost:8080/%EA%B4%80%ED%86%B5back/index.jsp";
					});
			}
    	}) 
    </script>
  </body>
</html>
