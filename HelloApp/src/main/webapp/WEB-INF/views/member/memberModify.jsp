<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>수정 페이지(modifyMember.jsp)</h3>

<form action="modifyMemberInfo.do" method="POST" >
	<table class="table">
		<tr>
			<th>Email</th>
			<td><input type="text" name="email" value="${memberInfo.email }"  readonly></td>
		<tr>
		<tr>
			<th>Password</th>
			<td><input type="text" name="password" value="${memberInfo.password }" ></td>
		<tr>
		<tr>
			<th>이름</th>
			<td><input type="text" name="name" value="${memberInfo.name }"  readonly></td>
		<tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="phone" value="${memberInfo.phone }"></td>
		<tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="address" value="${memberInfo.address }" ></td>
		<tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit">저장</button>
				<button type="button">돌아가기</button>
			</td>
		</tr>
	</table>
</form>

