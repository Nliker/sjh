<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:if test="${board ne null}">
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
      crossorigin="anonymous"
    />
    <link href="${root}/assets/css/app.css" rel="stylesheet" />
    <title>SSAFY</title>
  </head>
  <body>
    <div class="container">
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <label>글보기</label>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row my-2">
            <h2 class="text-secondary px-5">${board.no}. ${board.title}</h2>
          </div>
          <div class="row">
            <div class="col-md-8">
              <div class="clearfix align-content-center">
                <img
                  class="avatar me-2 float-md-start bg-light p-2"
                  src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg"
                />
                <p>
                  <span class="fw-bold">${board.userId}</span> <br />
                  <span class="text-secondary fw-light"> ${board.regDate}</span>
                </p>
              </div>
            </div>
            <div class="divider mb-3"></div>
            <div class="text-secondary">
              ${board.content}
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="commentWriteContainer">
            	<input id="commentContent" type="text" style="width:500px;height:100px">
            	<button id="registCommentBtn">등록</button>	
            </div>
            <div class="divider mt-3 mb-3"></div>
            <div class="commentContainer">
            	<table id="commentList" style="text-align:center;">
            		<thead>
            			<tr>
            				<th>작성자</th>
            				<th style="width:300px">댓글</th>
            				<th>작성일자</th>
            			</tr>
            		</thead>
            	</table>
			</div>
			<div class="divider mt-3 mb-3"></div>
            <div class="d-flex justify-content-end">
              <button type="button" id="btn-list" class="btn btn-outline-primary mb-3">
                글목록
              </button>
              <button type="button" id="btn-mv-modify" class="btn btn-outline-success mb-3 ms-1">
                글수정
              </button>
              <button type="button" id="btn-delete" class="btn btn-outline-danger mb-3 ms-1">
                글삭제
              </button>
              
            </div>
          </div>
        </div>
      </div>
    </div>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
      crossorigin="anonymous"
    ></script>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/board?action=mvPage";
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
        alert("글수정하자!!!");
        location.href = "${root}";
      });
      document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글삭제하자!!!");
        location.href = "${root}";
      });
    </script>
  </body>
</html>
</c:if>
<c:if test="${board eq null}">
	<script>
	alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
	location.href = "${root}/board?action=mvPage";
	</script>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
	document.getElementById('registCommentBtn').addEventListener('click',function(){
		console.log("버튼 눌림");
		postComments();
	});
	
	function postComments(){
		let input=document.getElementById('commentContent')
		
		axios.post('${root}/board?action=registComments', {
		    boardNo: '${board.no}',
		    content: input.value
		  }).then(function (response) {
			  	input.value = null;
			    getComments(${board.no});
		  })
	}
	
	function getComments(boardNo) {
		axios.get('${root}/board?action=getComments', {
		    params: {
		    	boardNo
		    }
		})
		.then(function ({data}) {
	    	renderList(data.list);
	  	});
	}
	
	getComments(${board.no});
	

	function renderList(list) {
		let tbodyHtml = "";
		for (let i = 0; i < list.length; i++) {
			let comment = list[i];
			tbodyHtml +=		
				"<tr> <td>"+comment.userId+"</td> <td>" + comment.content+ 
				"</td> <td>"+comment.regDate+"</td> </tr>"
			;
		}
		if (list.length === 0) {
			tbodyHtml = `
				<tr>
					<td colspan='4'>입력된 댓글이 없습니다.</td>
				</tr>
			`;
		}
		document.getElementById("commentList").innerHTML += tbodyHtml;		
	}
</script>
