<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>search</title>
    <link rel="stylesheet" href="../assets/css/search.css" />
  </head>
  <body>
    <div class="containerS">
      <header class="header-nav">
        <a href="./main.jsp"><img src="../assets/img/bg/logo2.gif" alt="" /></a>
      </header>
      <main id="searchM">
        <div id="input">
          <div class="form">
            <div class="category">
              <input type="text" class="categoryBox" placeholder="카테고리" readonly />
              <div class="categoryOption">
                <div id="12" class="categoryItem">관광지</div>
                <div id="14" class="categoryItem">문화시설</div>
                <div id="15" class="categoryItem">축제공연행사</div>
                <div id="25" class="categoryItem">여행코스</div>
                <div id="28" class="categoryItem">레포츠</div>
                <div id="32" class="categoryItem">숙박</div>
                <div id="38" class="categoryItem">쇼핑</div>
                <div id="39" class="categoryItem">음식점</div>
              </div>
            </div>
            <input type="text" id="addResult" placeholder="키워드를 입력하세요" />
            <button type="submit" id="searchBtn">검색</button>
          </div>
          <div id="btnForm">
            <button type="button" id="">화장실</button>
            <button type="button" id="CS2">편의점</button>
            <button type="button" id="PM9">약국</button>
            <button type="button">세탁방</button>
          </div>
        </div>
        <div class="output" id="map"></div>
      </main>
    </div>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0884821c5c56b40942ed805dab33a174"
    ></script>
    <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0884821c5c56b40942ed805dab33a174&libraries=services"
    ></script>
    <script>
      <!-- const valueFromIndex = localStorage.getItem("valueFromIndex");
      if (valueFromIndex !== null) {
        document.getElementById("addResult").value = valueFromIndex;
      }
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.796881, 128.917414), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption); -->
    </script>
    <script type="text/javascript" src="../assets/js/search.js"></script>
    <script src="js/api.js"></script>
  </body>
</html>