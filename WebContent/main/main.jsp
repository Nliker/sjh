<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="attraction.service.AttractionServiceImpl"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<c:set var="areaList" value="${applicationScope.areaList}" />

<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>BODDARY</title>
    <link rel="stylesheet" href="./assets/css/main.css" />
    <link href="https://hangeul.pstatic.net/hangeul_static/css/NanumMaGoCe.css" rel="stylesheet" />
    <script src="https://cdn.jsdelivr.net/npm/seamless-scroll-polyfill@latest"></script>
  </head>
  <body>
    <div class="container">
      <div class="doorContainer">
        <div class="main">
        	<c:if test="${not empty memberInfo}">
        		 <h2 style="font-family: 'NanumMaGoCe'; color:white; text-decoration:none; padding-top:10px;">
        		 	${memberInfo.name}님 환영합니다.
        		 </h2>
        	</c:if>
          <ul>
            <c:choose>
	          <c:when test="${not empty memberInfo}">
	          		<li><a href="${root}/member?action=logout">Logout</a></li>
            		<li>|</li>
            		<li><a href="${root}/member?action=mvMypage">MyPage</a></li>
            		<li>|</li>	
		            <li><a href="${root}/board?action=mvPage">Board</a></li>
	          </c:when>
	          <c:otherwise>
	          		<li><a href="${root}/member?action=mvLogin">Login</a></li>
            		<li>|</li>
            		<li><a href="${root}/member?action=mvJoin">SingUp</a></li>
	          </c:otherwise>
          	</c:choose>
            <li>|</li>
            <li><a href="#hotC">Recommend</a></li>
          </ul>
          <!-- <h1>보따리</h1> -->
          <img src="./assets/img/bg/logo2.gif" alt="" />
          <!-- <p id="hello">안녕하세요</p> -->
          <br />
          <div class="searchZone">
            <div class="dropdown">
              <input type="text" class="textBox" placeholder="검색" readonly />
              <div class="option">
              	<c:forEach var="sido" items="${areaList}">
              		<div class="dropDonwItem" onclick="divClick(${sido.sidoCode}, '${sido.sidoName}')">${sido.sidoName}</div>
              	</c:forEach>
              </div>
            </div>
            <button type="button" id="sub-btn">GO</button>
          </div>
        </div>
      </div>
      <div class="hotContainer" id="hotC">
        <h1>추천 여행지</h1>
        <div class="hotpl">
          <div class="picZone Zone">
            <div class="card">
              <img src="./assets/img/hotPic/gangnam.jpg" />
              <p>강남</p>
            </div>
            <div class="card">
              <img src="./assets/img/hotPic/busan.jpg" />
              <p>부산</p>
            </div>
            <div class="card">
              <img src="./assets/img/hotPic/jeonju.png" />
              <p>전주</p>
            </div>
            <div class="card">
              <img src="./assets/img/hotPic/daejeon.png" />
              <p>대전</p>
            </div>
            <div class="card">
              <img src="./assets/img/hotPic/greece.jpg" />
              <p>그리스</p>
            </div>
            <div class="card">
              <img src="./assets/img/hotPic/gangreung.jpg" />
              <p>강릉</p>
            </div>
            <div class="card">
              <img src="./assets/img/hotPic/yeosu.JPG" />
              <p>여수</p>
            </div>
            <div class="card">
              <img src="./assets/img/hotPic/ulsan.jpg" />
              <p>울산</p>
            </div>
          </div>
          <div class="shortsZone Zone">
            <div class="card">
              <video src="./assets/img/shorts/video (1080p).mp4" type="video/mp4"></video>
              <p>울산</p>
            </div>
            <div class="card">
              <video
                src="./assets/img/shorts/pexels_videos_1093662 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>부산</p>
            </div>
            <div class="card">
              <video
                src="./assets/img/shorts/pexels-anna-tarazevich-6550972 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>강릉</p>
            </div>
            <div class="card">
              <video src="./assets/img/shorts/video (1080p) (1).mp4" type="video/mp4"></video>
              <p>노을</p>
            </div>
            <div class="card">
              <video
                src="./assets/img/shorts/pexels-peggy-anke-5566467 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>태평양</p>
            </div>
            <div class="card">
              <video
                src="./assets/img/shorts/pexels-peggy-anke-5566609 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>하와이</p>
            </div>
            <div class="card">
              <video
                src="./assets/img/shorts/pexels-peggy-anke-5566567 (1080p).mp4"
                type="video/mp4"
              ></video>
              <p>발리</p>
            </div>
          </div>
        </div>
      </div>
      <div class="randomContainer" id="ranC">
        <!-- <h1>당신의 다음 여행지는</h1> -->
        <div class="slider">
          <div class="item">
            <h1>준비중</h1>
              준비중입니다.
          </div>
          <div class="item">
            <h1>준비중</h1>
              준비중입니다.
          </div>
          <div class="item">
            <h1>준비중</h1>
              준비중입니다.
          </div>
          <div class="item">
            <h1>준비중</h1>
              준비중입니다.
          </div>
          <button id="next">></button>
          <button id="prev"><</button>
        </div>
      </div>
    </div>
    <script src="./assets/js/main.js"></script>
    <script>
    document.getElementById("sub-btn").addEventListener("click", function() {
    	  console.log(isSelectedOption);
    	  if (!isSelectedOption) {
    	    alert("지역을 선택해주세요");
    	    return;
    	  } else {
    	    location.href = "${pageContext.request.contextPath}/main/search.jsp";
    	  }
    	});
    </script>
  </body>
</html>