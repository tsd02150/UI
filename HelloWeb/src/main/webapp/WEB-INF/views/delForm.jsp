<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
<form action="delMember.do">
eid:<input name="emp_id">
<input type="submit" value="삭제">
<jsp:include page="footer.jsp"></jsp:include>