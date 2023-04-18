<%@page import="com.yedam.domain.Employee"%>
<%@page import="com.yedam.persistence.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>
	<%
	Employee emp = (Employee) request.getAttribute("empInfo");
	/* 	EmpDAO dao= new EmpDAO();
		Employee emp = dao.getEmp(eid);
	 */
	%>
	<table class="table">
		<tr>
			<th>사원번호</th>
			<td><%=emp.getEmployeeId()%></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=emp.getFirstName() + "," + emp.getLastName()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=emp.getEmail()%></td>
		</tr>
		<tr>
			<th>직종</th>
			<td><%=emp.getJobId()%></td>
		</tr>
		<tr>
			<th>입사일</th>
			<td><%=emp.getHireDate()%></td>
		</tr>
	</table>

	
	<a href="/HelloWeb">목록으로</a>
<jsp:include page="footer.jsp"></jsp:include>