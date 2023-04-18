package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/modifyMemberServlet")
public class ModifyMemberServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int eid= Integer.parseInt(req.getParameter("emp_id"));
		String fname=req.getParameter("first_name");
		String lname=req.getParameter("last_name");
		String email=req.getParameter("email");
		String jobId=req.getParameter("job_id");
		String hireDate=req.getParameter("hire_date");
		
		Employee emp = new Employee();
		emp.setEmployeeId(eid);
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		emp.setJobId(jobId);
		emp.setHireDate(hireDate);
		
		EmpDAO dao = new EmpDAO();
		if(dao.updateMember(emp)) {
			resp.sendRedirect("empList.jsp");
		}else {
			resp.sendRedirect("modifyMember.jsp?emp_id="+eid);
		}
	}
}
