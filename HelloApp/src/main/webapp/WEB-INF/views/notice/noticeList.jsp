<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="my" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../main.jsp"></jsp:include>

<my:if test="${list != null }">
<p>${list }</p>
</my:if>

<hr>

<my:forEach begin="0" step="1" end="${list.size()-1 }" var="i"> 
<p>${list.get(i) }</p>
</my:forEach>

<hr>

<my:forEach var="notice" items="${list }">
<p>${notice }</p>
</my:forEach>

</body>
</html>