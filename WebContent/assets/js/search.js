let xyList = {
  1: [37.56658580121631, 126.9777104192369],
  2: [37.4550, 126.7031], 3: [36.35035288777408, 127.38459043689537], 4: [35.8714, 128.6014], 5: [35.1595, 126.8526], 6: [35.1796, 129.0756], 7: [35.5384, 129.3114],
  8: [36.4801, 127.2882], 31: [37.2636, 127.0286], 32: [37.8822, 127.7270], 33: [36.6357, 127.4914], 34: [36.446619, 127.119051], 35: [35.8714, 128.6014], 36: [35.2383, 128.6973],
  37: [35.8201, 127.1086], 38: [35.1547, 126.9156], 39: [33.4996, 126.5312]
};

// 지역별 좌표
// 서울 특별시 : 37.56658580121631, 126.9777104192369
// 대전 광역시 : 36.35035288777408, 127.38459043689537
// 울산 광역시 : 35.5384, 129.3114
// 광주 광역시 : 35.1595, 126.8526
// 대구 광역시 : 35.8714, 128.6014
// 부산 광역시 : 35.1796, 129.0756
// 인천 광역시 : 37.4550, 126.7031
// 세종특별자치시: 36.4801, 127.2882
// 경기도 (수원시): 37.2636, 127.0286
// 강원특별자치도 (춘천시): 37.8822, 127.7270
// 충청북도 (청주시): 36.6357, 127.4914
// 충청남도 (공주시): 36.446619, 127.119051
// 경상북도 (대구시): 35.8714, 128.6014
// 경상남도 (창원시): 35.2383, 128.6973
// 전라북도 (전주시): 35.8201, 127.1086
// 전라남도 (광주시): 35.1547, 126.9156
// 제주도 (제주시): 33.4996, 126.5312

let isCategorySelected=false;
var map;

function mapinit() {
    const idx = sessionStorage.getItem("area-code");
    
  // let idx = cityList.indexOf(areacode);
  // idx = idx < 0 ? 0 : idx;

  let x = xyList[idx][0];
  let y = xyList[idx][1];
  console.log(x, y);
    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(x, y), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
      map = new kakao.maps.Map(mapContainer, mapOption);
}

mapinit();


let category=document.querySelector(".category");
category.addEventListener("click",function(){
  category.classList.toggle(`active`);
})
let options=document.getElementsByClassName("categoryItem");

for(let option of options){
  option.addEventListener("click",function(){
    document.querySelector(".categoryBox").value=option.innerText;
    sessionStorage.setItem("categoryId",option["id"]);
    isCategorySelected=true;
  });
}
