<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="border-end bg-white" id="sidebar-wrapper">
	<div class="sidebar-heading border-bottom bg-light">Start Bootstrap</div>
	<div class="list-group list-group-flush">
		<c:choose>
			<c:when test="${id == null }">
				<a class="list-group-item list-group-item-action list-group-item-light p-3" href="loginForm.do">로그인</a> 
			</c:when>
			<c:otherwise>		
				<a class="list-group-item list-group-item-action list-group-item-light p-3" href="logout.do">로그아웃</a> 
			</c:otherwise>
		</c:choose>
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="noticeList.do">공지사항 목록</a> 
		<c:choose>
			<c:when test="${id != null }">
				<a class="list-group-item list-group-item-action list-group-item-light p-3" href="noticeAddForm.do?writer=${id} ">공지사항 등록</a> 			 
			</c:when>

		</c:choose>
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Events</a> 
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Profile</a> 
		<a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a>
	</div>
</div>