let isSelectedOption = false;
function divClick(sidoCode, sidoName) {
	document.querySelector(".textBox").value = sidoName;
    sessionStorage.setItem("area-code", sidoCode);
    isSelectedOption=true;
}

document.getElementById("sub-btn").addEventListener("click", () => {
  console.log(isSelectedOption);
  if (!isSelectedOption) {
    alert("지역을 선택해주세요");
    return;
  } else {
    // sessionStorage.setItem('area-code',document.querySelector('.textBox').value);
    window.open("search.html");
  }
});

const options = {
  root: document.querySelector(".container"), // viewport
  rootMargin: "0px 100% 0px 100%",
  threshold: .5,  // 50%가 viewport에 들어와 있어야 callback 실행
}

const observer = new IntersectionObserver(entries => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      entry.target.classList.add("active");
    } else {
      entry.target.classList.remove("active");
    }
  });
}, options);

const titleList = document.querySelectorAll('h1');
const zonList = document.querySelectorAll('.Zone');
const cardList = document.querySelectorAll('.Zone');

// 반복문을 돌려 모든 DOM에 적용
titleList.forEach(el => observer.observe(el));
zonList.forEach(el => observer.observe(el))



let page = 0;
let containerList = [document.querySelector(".doorContainer"),
    document.querySelector(".hotContainer"),
    document.querySelector(".randomContainer")
];
let isScrolling = false;

function scrollMove(e) {
  if (isScrolling) return;
  console.log('isScrolling', isScrolling);
  isScrolling = true;
  const direction = e.deltaY > 0 ? 1 : -1;
  page += direction;
  if (page < 0) {
      page = 0;
  }
  if (page > 2) {
      page = 2;
  }
  seamless.polyfill();
  seamless.scrollIntoView(containerList[page],{
    behavior: "smooth"
});
  isScrolling = false;
}


function debounce(fn, delay) {
    let timer
    return function() {
        clearTimeout(timer);
        timer = setTimeout(() => { // *
            fn.apply(this, arguments);
        }, delay);
    }
}

addEventListener("mousewheel", debounce(scrollMove,150));
const topOption=0.6;
const rowOption=0.1;
let offsetPerPixel=(topOption-rowOption)/containerList[1].clientHeight;//픽셀당 투명도 단위
const container=document.querySelector(".container");
container.addEventListener('scroll', function(){
  let hotContainerTop=containerList[1].getBoundingClientRect().top;
  const nColor=topOption-Math.abs(hotContainerTop)*offsetPerPixel;
  containerList[1].style.background=`linear-gradient(rgba(0, 0, 0, ${nColor}), rgba(0, 0, 0, ${nColor})), url(./assets/img/bg/bg1.jpg)`;
  containerList[1].style.backgroundAttachment="fixed";
});

const videos = document.querySelectorAll('video');
console.log(videos);
videos.forEach(video => {
  video.addEventListener('mouseenter', () => {
    video.play();
  });
  
  video.addEventListener('mouseleave', () => {
    video.pause();
    video.currentTime = 0;
  });
});

let items = document.querySelectorAll('.slider .item');
let next = document.getElementById('next');
let prev = document.getElementById('prev');

let active = 0;
function loadShow() {
  let stt = 0;
  items[active].style.transform = `none`;
  items[active].style.zIndex = 1;
  items[active].style.filter = 'none';
  items[active].style.opacity = 1;
  items[active].style.scale = (1.2,1.2);
  for (var i = active + 1; i<items.length; i++){
    stt++;
    items[i].style.transform=`translateX(${140*stt}px) scale(${1-0.3*stt}) perspective(16px) rotateY(-1deg)`
    items[i].style.zIndex = -stt;
    items[i].style.filter = `blur(${stt+1}px)`;
    items[i].style.opacity = 1 - (stt * 0.4);
    items[i].style.scale = (1,1);
  }
  stt = 0;
  for (var i = active -1; i>=0; i--){
    stt++;
    items[i].style.transform=`translateX(${-140*stt}px) scale(${1-0.3*stt}) perspective(16px) rotateY(1deg)`
    items[i].style.zIndex = -stt;
    items[i].style.filter = `blur(${Math.abs(stt)+1}px)`;
    items[i].style.opacity = 1 - (Math.abs(stt) * 0.4);
    items[i].style.scale = (1,1);
  }

}
loadShow();

next.onclick = function() {
  active = active + 1 < items.length ? active + 1 : active;
  loadShow();
};
prev.onclick = function() {
  active = active -1 >=0 ? active -1 : active;
  loadShow();
};
let dropdown=document.querySelector(".dropdown");
dropdown.addEventListener("click",function(){
  dropdown.classList.toggle(`active`);
})
