package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class FirstControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("firstControl 실행");
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EmpDAO dao = new EmpDAO();
		List<Employee> list = dao.getEmpList();
		out.print("<table border='1'>");
		out.print("<thead><tr><th>사원번호</th><th>FirstName</th><th>LasttName</th><th>email</th><th>JobId</th><th>HireDate</th></tr></thead>");
		out.print("<tbody>");
		for (Employee emp:list) {
			System.out.println("eid : " + emp.getEmployeeId() + " ,fName : " + emp.getFirstName()); // 순번도가능 rs.getInt(1)
			out.print("<tr><td><a href='searchMember?eid="+emp.getEmployeeId()+"'>"+emp.getEmployeeId()
			+"</a></td><td>"+emp.getFirstName()
			+"</td><td>"+emp.getLastName()
			+"</td><td>"+emp.getEmail()
			+"</td><td>"+emp.getJobId()
			+"</td><td>"+emp.getHireDate()
			+"</td></tr>");
		}
		out.print("</tbody>");
		out.print("</table>");
	}

}
