<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




<div class="row block-9"
	style="margin-top: 100px; margin-bottom: 100px; margin-right: 250px; margin-left: 250px;">

	<div class="col-md-6 d-flex"
		style="text-align: center; margin: 0 auto;">

		
		<form method="post"
			action="${pageContext.request.contextPath}/RegisterController.do"
			class="bg-light p-5 contact-form" style="width: 100%">
				<img alt="dd" src="images/contentImage/kosta-logo.png" width = "150" height="150">
			<div class="form-group">
				<input type="submit" value="회원가입?" class="btn btn-primary py-3 px-5">
			</div>
		</form>
	</div>
	<div class="col-md-6 d-flex"
		style="text-align: center; margin: 0 auto;">
		<form method="post"
			action="${pageContext.request.contextPath}/LoginController.do"
			class="bg-light p-5 contact-form" style="width: 100%">
			<div class="form-group" style="margin-bottom: 2.3rem">
				<input type="text" name="userEmail" class="form-control"
					placeholder="이메일">
			</div>
			<div class="form-group" style="margin-bottom: 2.3rem">
				<input type="password" name="userPassword" class="form-control"
					placeholder="패스워드">
			</div>
			<div class="form-group">
				<input type="submit" value="로그인" class="btn btn-primary py-3 px-5">
			</div>

		</form>

	</div>


</div>

