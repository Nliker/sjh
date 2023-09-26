<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib
prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumAJumMaJaYu.css" rel="stylesheet">
    <link rel="stylesheet" href="./css/sub.css" />
  </head>
  <body>
    <div class="container">
      <header class="header-nav">
        <div>
          <a href="index.html"><h2>BODDARY</h2></a>
        </div>
      </header>
      <main id="loginMain">
        <div class="login-page">
          <div class="form">
            <img src="./assets/boddary_black.png" alt="">
            <form class="login-form" method="POST" action="${root}/member?action=login">
              <input id="id" name="id" type="text" placeholder="ID" />
              <input id="password" name="password" type="password" placeholder="password" />
              <button class="login-btn" type="submit">로그인</button>
              <button onclick="location.href='${root}/member?action=mvJoin'" type="button">회원가입</button>
            </form>
          </div>
        </div>
      </main>
      <footer>
        <ul class="footer-list">
          <li><a href="#">ê°ì¸ì ë³´ ì²ë¦¬ë°©ì¹¨</a></li>
          <li><a href="#">ì´ì© ì½ê´</a></li>
          <li><a href="#">ì¤ìë ê¸¸</a></li>
          <li>&copy; SSAFY</li>
        </ul>
      </footer>
    </div>
    <script type="text/javascript" src="js/login.js"></script>
  </body>
</html>
