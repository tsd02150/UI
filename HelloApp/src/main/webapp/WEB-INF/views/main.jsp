<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Main Page</p>
	<p>Expression Language</p>
	${3>2}
	${"Hello" }
	${10 - 5 }
	${myName }
	${myName }
	${myName != null ? '<h3>있음</h3>':'<h3>없음</h3>' }
</body>
</html>