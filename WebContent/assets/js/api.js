//const apiKey = "sO3MQCUWa9INBB6AYWIl%2FKCaQ0R6SzsOKAPd28hj5GnxCX1tWVlBLSG6zvN9Ep1hBiQzzRkbV%2FOfS3KBUYxOGw%3D%3D";
console.log(URL);
const defualtPinImage="../assets/defualtPinImage.png";
var markers = [];

document.getElementById("searchBtn").addEventListener("click", () => {
    console.log(isCategorySelected);
    if (!isCategorySelected) {
        alert("카테고리를 선택해주세요.");
        return;
    }
    
    var areaCode = sessionStorage.getItem('area-code');
    var categoryID = sessionStorage.getItem('categoryId');
    var URL = `http://localhost:8080/%EA%B4%80%ED%86%B5back/attraction?action=search&sidoCode=${areaCode}&contentTypeId=${categoryID}`;
    setMarkers(null);

//    let areaCode = sessionStorage.getItem('area-code');
//    let categoryID = sessionStorage.getItem('categoryId');
    
//    let URL = `${pageContext.request.contextPath}/attraction/search?`
    let imageSrc = `../assets/img/pin/${categoryID}.png`;
    
    fetch(URL).then((res) => res.json()).then((data) => {        
        data["data"].forEach(async(place) => {
        	console.log(place);
            let x = place.lat;
            let y = place.lng;

            let title = place.title;

            let contentID = place.contentid;
            let tel=  (place.tel==="")? "제공되지 않습니다.":place.tel;

            let pinImage=(place.firstimage === "") ? defualtPinImage : place.firstimage;

            var imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
            imageOption = {offset: new kakao.maps.Point(27, 69)};
            
        
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
            
            var markerPosition  = new kakao.maps.LatLng(x, y); 
            console.log(markerPosition);
            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                position: markerPosition,
                image: markerImage,
                clickable: true,
            });
    
            marker.setMap(map);
            markers.push(marker);
            let addr="";
            if(typeof place.addr1==='undefined' || place.addr1===""){
                addr="제공되지 않는 정보입니다."
            }else{
                addr=place.addr1;
            }
            var iwContent = `<div class="infoContainer" style="width:350px;box-sizing: border-box;display:flex;flex-wrap:wrap;align-items: center;border:1px solid;
            justify-content: space-evenly;">
                <div class class="textContaier">
                    <div class="title" style="width:250px;box-sizing: border-box;text-align: center;font-weight: bold;font-size:20px" >${title}</div>
                    <div class="addr" style="width:250px;box-sizing: border-box;">주소: ${addr}</div>
                    <div class="tel" style="width:250px;box-sizing: border-box;">전화번호: ${tel}</div>
                </div>
                <img src="${pinImage}" style="height:70px; width:70px;box-sizing: border-box;">
            </div>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
            iwPosition = new kakao.maps.LatLng(y, x);
    
            // 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
                position : iwPosition, 
                content : iwContent,
                removable : true
            });
            
            // kakao.maps.event.addListener(marker, 'mouseover', function() {
            //     // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
            //       infowindow.open(map, marker);
            //   });
              
            //   // 마커에 마우스아웃 이벤트를 등록합니다
            //   kakao.maps.event.addListener(marker, 'mouseout', function() {
            //       // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
            //       infowindow.close();
            //   });

            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커 위에 인포윈도우를 표시합니다
                infowindow.open(map, marker);  
            });
        });
    });
});

function setMarkers(map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map); 
    }            
}