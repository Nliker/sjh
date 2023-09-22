const apiKey = "sO3MQCUWa9INBB6AYWIl%2FKCaQ0R6SzsOKAPd28hj5GnxCX1tWVlBLSG6zvN9Ep1hBiQzzRkbV%2FOfS3KBUYxOGw%3D%3D";

const defualtPinImage="../assets/defualtPinImage.png";
var markers = [];

document.getElementById("searchBtn").addEventListener("click", () => {
    console.log(isCategorySelected);
    if (!isCategorySelected) {
        alert("카테고리를 선택해주세요.");
        return;
    }
    setMarkers(null);

    let areaCode = sessionStorage.getItem('area-code');
    let categoryID = sessionStorage.getItem('categoryId');
    
    let URL = `http://apis.data.go.kr/B551011/KorService1/areaBasedList1?numOfRows=${200}&pageNo=1&MobileOS=ETC&MobileApp=AppTest&ServiceKey=${apiKey}&_type=json&listYN=Y&arrange=A&contentTypeId=${categoryID}&areaCode=${areaCode}&sigunguCode=&cat1=&cat2=&cat3=`
    let imageSrc = `../assets/pin/${categoryID}.png`;
    
    fetch(URL).then((res) => res.json()).then((data) => {
        
        data.response.body.items.item.forEach(async(place) => {
            let x = place.mapx;
            let y = place.mapy;
            let title = place.title;

            let contentID = place.contentid;
            let tel;

            let placeUrl = `http://apis.data.go.kr/B551011/KorService1/detailIntro1?ServiceKey=${apiKey}&_type=json&contentTypeId=${categoryID}&contentId=${contentID}&MobileOS=ETC&MobileApp=AppTest`

            await fetch(placeUrl).then((back) => back.json()).then((data) => {
                data.response.body.items.item.forEach((content) => {
                    
                    console.log(content);
                    if (typeof content.infocenter==='undefined' || content.infocenter===""){
                        tel="제공되지 않는 정보입니다.";
                    }else{
                        let numberTokens=content.infocenter.replace(/[^0-9-]/g,"");
                        if (numberTokens.length!=1){
                            tel=numberTokens
                        }else{
                            tel = numberTokens[numberTokens.length-1];
                        }
                    }
                })
            });
            let pinImage=(place.firstimage === "") ? defualtPinImage : place.firstimage;

            // var imageSrc = '../assets/pin/touristspot-ping.png', // 마커이미지의 주소입니다    
            var imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
            imageOption = {offset: new kakao.maps.Point(27, 69)};
            
            // let placeUrl = `http://apis.data.go.kr/B551011/KorService1/detailIntro1?ServiceKey=${apiKey}&contentTypeId=${categoryID}&contentId=${contentID}&MobileOS=ETC&MobileApp=AppTest`
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
            var markerPosition  = new kakao.maps.LatLng(y, x); 
    
            // 마커를 생성합니다
            var marker = new kakao.maps.Marker({
                position: markerPosition,
                image: markerImage,
                clickable: true,
            });
    
            marker.setMap(map);
            markers.push(marker);
            console.log(pinImage);
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