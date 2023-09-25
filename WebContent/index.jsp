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
    <title>Bottary</title>
    <link rel="stylesheet" href="./css/main.css" />
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumMaGoCe.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/seamless-scroll-polyfill@latest"></script>
  </head>
  <body>
    <div class="container">
      <div class="doorContainer">
        <div class="main">	
          <ul>
          <c:choose>
	          <c:when test="${not empty memberInfo}">
	          		<li><a href="${root}/member?action=logout">Logout</a></li>
            		<li>|</li>
            		<li><a href="#">MyPage</a></li>
            		<li>|</li>	
	          </c:when>
	          <c:otherwise>
	          		<li><a href="${root}/member?action=mvLogin">Login</a></li>
            		<li>|</li>
            		<li><a href="${root}/member?action=mvJoin">SingUp</a></li>
            		<li>|</li>
	          </c:otherwise>
          </c:choose>
            <li><a href="#hotC">Recom</a></li>
            <li>|</li>
            <li><a href="#ranC">Check</a></li>
          </ul>
          <!-- <h1>ë³´ë°ë¦¬</h1> -->
          <img src="./assets/logo2.gif" alt="" />
          <!-- <p id="hello">ìëíì¸ì</p> -->
          <br />
          <!-- <input type="text" id="address" placeholder="ì£¼ìë¥¼ ìë ¥íì¸ì" />
          <button type="submit" onclick="search()">GO</button> -->
          <div class="searchZone">
            <div class="dropdown">
              <input type="text" class="textBox" placeholder="ê²ì" readonly />
              <div class="option"></div>
            </div>
            <button type="submit" id="sub-btn">GO</button>
          </div>
        </div>
      </div>
      <div class="hotContainer" id="hotC">
        <h1>ì¶ì² ì¬íì§</h1>
        <div class="hotpl">
          <div class="picZone Zone">
            <div class="card">
              <img src="./assets/gangnam.jpg" />
              <p>ê°ë¨</p>
            </div>
            <div class="card">
              <img src="./assets/busan.jpg" />
              <p>ë¶ì°</p>
            </div>
            <div class="card">
              <img src="./assets/jeonju.png" />
              <p>ì ì£¼</p>
            </div>
            <div class="card">
              <img src="./assets/daejeon.png" />
              <p>ëì </p>
            </div>
            <div class="card">
              <img src="./assets/daejeon.png" />
              <p>ëì </p>
            </div>
            <div class="card">
              <img src="./assets/daejeon.png" />
              <p>ëì </p>
            </div>
            <div class="card">
              <img src="./assets/daejeon.png" />
              <p>ëì </p>
            </div>
            <div class="card">
              <img src="./assets/daejeon.png" />
              <p>ëì </p>
            </div>
          </div>
          <div class="shortsZone Zone">
            <div class="card">
              <video src="./assets/shorts/video (1080p).mp4" type="video/mp4"></video>
              <p>ì¸ì°</p>
            </div>
            <div class="card">
              <video
                src="./assets/shorts/pexels_videos_1093662 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>ë¶ì°</p>
            </div>
            <div class="card">
              <video
                src="./assets/shorts/pexels-anna-tarazevich-6550972 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>ê°ë¦</p>
            </div>
            <div class="card">
              <video src="./assets/shorts/video (1080p) (1).mp4" type="video/mp4"></video>
              <p>ë¸ì</p>
            </div>
            <div class="card">
              <video
                src="./assets/shorts/pexels-peggy-anke-5566467 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>ííì</p>
            </div>
            <div class="card">
              <video
                src="./assets/shorts/pexels-peggy-anke-5566609 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>íìì´</p>
            </div>
            <div class="card">
              <video
                src="./assets/shorts/pexels-peggy-anke-5566567 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>ë°ë¦¬</p>
            </div>
          </div>
        </div>
      </div>
      <div class="randomContainer" id="ranC">
        <!-- <h1>ë¹ì ì ë¤ì ì¬íì§ë</h1> -->
        <div class="slider">
          <div class="item">
            <h1>ìì´í1</h1>
            asodjopasdjpoasdjpojasdop
          </div>
          <div class="item">
            <h1>ìì´í2</h1>
            asodjopasdjpoasdjpojasdop
          </div>
          <div class="item">
            <h1>ìì´í3</h1>
            asodjopasdjpoasdjpojasdop
          </div>
          <div class="item">
            <h1>ìì´í4</h1>
            asodjopasdjpoasdjpojasdop
          </div>
          <div class="item">
            <h1>ìì´í5</h1>
            asodjopasdjpoasdjpojasdop
          </div>
          <div class="item">
            <h1>ìì´í6</h1>
            asodjopasdjpoasdjpojasdop
          </div>
          <button id="next">></button>
          <button id="prev"><</button>
        </div>
      </div>
    </div>
    <script src="./js/main.js"></script>
    <!-- <script src="./js/search.js"></script> -->
  </body>
</html>
