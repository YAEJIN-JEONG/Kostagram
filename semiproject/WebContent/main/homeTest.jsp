<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	let xhr;
	function startAjax(no){		
		xhr=new XMLHttpRequest();//Ajax 통신을 위한 자바스크립트 객체 
		//alert(xhr);
		alert(no);
		//XMLHttpRequest의 속성에 callback 함수를 바인딩
		//readystate가 변화될 때 callback 함수가 실행 
		//서버가 응답할 때 callback 함수를 실행하기 위한 코드이다 
		xhr.onreadystatechange=callback; 
		//서버로 요청 
		xhr.open("GET","LikesCountServlet");
		xhr.send(null); //post 방식일때 form data 명시 
	}
	function callback(){
		console.log(xhr.readyState)
		// readyState 가 4 : 서버의 응답 정보를 받은 상태 
		// status 가 200 : 정상 수행 
		if(xhr.readyState==4&&xhr.status==200){
			alert(xhr.responseText); // : 서버의 응답데이터를 저장하는 변수 
			document.getElementById("likeCount").innerHTML = xhr.responseText;
		}
	}
</script>


<c:forEach items="${requestScope.list}" var="pvo" varStatus="status">
번호 : ${pvo.no} <br>
사진 : <img src="images/contentImage/${pvo.postImage}" alt="My Image"
		width="100" height="200"> ${pvo.postImage} <br>
내용 : ${pvo.content}<br>
작성자 : ${pvo.mvo.userName}<br>
작성일 : ${pvo.regdate} <br>

<input type="button" value="좋아요~" onclick="startAjax(${pvo.no})"><br>
<input type="hidden" id="postNum${pvo.no}" name="${pvo.no}" value="${pvo.no}">
로그인유저의 <br> 좋아요 유무(0 or 1) : <span id="likeBoolean"></span><br>
좋아요 개수 : <span id="likeCount"></span><br>

	<hr>
	<hr>
</c:forEach>