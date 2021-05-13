<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<meta charset="UTF-8">
<title>현재 로그인한 유저정보</title>
<script type="text/javascript">
	function updatePost() {
			document.updateForm.submit();
	}
	function deletePost() {
		if (confirm("게시글을 삭제하시겠습니까?")) {
			//location.href='DeletePostController.do?no='+no;
			document.forms["deleteForm"].submit();
			//document.deleteForm.submit();
		}
	}
</script>

<!-- 내 프로필 출력 시작 -->
	<table >
	<tr>

	</tr>	
		<tr>
			<td>프로필사진</td>
			<%-- default 이미지 설정해주기 --%>
			<td><img src="images/profileImage/${requestScope.vo.profileImage}" alt="My Image" width="200" height="200"></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${requestScope.vo.userEmail}</td>
		</tr>
		<tr>
			<td>자기소개</td>
			<td>${requestScope.vo.profileContent}</td>
		</tr>
	</table>
	<c:if test="${requestScope.vo.userEmail==sessionScope.mvo.userEmail}">
			<tr>
			<td colspan="5" class="btnArea">
				<form name="updateForm"
					action="${pageContext.request.contextPath}/UpdateProfileFormController.do" method="post">
					<input type="hidden" name="userEmail" value="${requestScope.vo.userEmail}">
				</form>
				<button type="button" class="btn" onclick="updatePost()">수정</button>
			</td>
		</tr>
		</c:if>
		<br><br>
		<!-- 내 프로필 출력 끝 -->
		
		<!-- 개인 피드 게시물 출력 시작 -->
<section class="ftco-section-3">
<div class="photography">
   <div class="col-md-4 ftco-animate">
      <div class="main-feed"
         style="padding: 0 1rem 2rem 2rem; margin-right: -1rem; margin-left: -1rem;">
         <div class="row">

            <c:forEach items="${requestScope.list}" var="pvo" varStatus="status">
               <%-- 글번호 : ${pvo.no}  --%>
               
               <div class="col-md-4 ftco-animate"
                  style="box-shadow: 0 2px 4px 0 rgb(216 216 216/ 20%), 0 16px 68px 0 rgb(216 216 216/ 40%)">
                  <a href="images/contentImage/${pvo.postImage}"
                     class="photography-entry img image-popup d-flex justify-content-start align-items-end"
                     style="background-image: url(images/contentImage/${pvo.postImage});">
                     <div class="overlay"></div>
                  </a>
                  <%-- 원본
                     <a href="images/contentImage/${pvo.postImage}"
                        class="photography-entry img image-popup d-flex justify-content-start align-items-end"
                        style="background-image: url(images/contentImage/${pvo.postImage});">
                        <div class="overlay">
                        </div>
                     </a> --%>
                  <div class="text ml-4 mb-4"
                     style="display: inline-flex; align-items: center; margin-top: 0 !important; margin-bottom: 0 !important;">

                     <a
                        href="ProfileDetailController.do?userEmail=${pvo.mvo.userEmail}">
                        <img class="image--cover" alt="이미지안뜸"
                        src="images/profileImage/${pvo.mvo.profileImage}"
                        style="width: 4rem; height: 4rem;"> ${pvo.mvo.userName}<br>
                     </a> <span style="margin-left: 8rem;">작성일 : ${pvo.regdate}</span>
                  </div>
               </div>
               
         <!-- <hr> -->
         <div class="post-content">${pvo.content}</div>
         </div>
         
         </c:forEach>
      </div>
      
   </div>
   
   </div>
   
</section>

 <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/jquery.timepicker.min.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>