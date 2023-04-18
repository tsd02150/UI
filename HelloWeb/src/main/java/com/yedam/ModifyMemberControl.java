package com.yedam;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class ModifyMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// View에서 요청한 method를 구분 GET/POST
		if (req.getMethod().equals("GET")) {
			// emp_id 파라미터
			// MVC 패턴 컨트롤러에서 DB의 처리 View 화면으로 정보를 전송
			int empId = Integer.parseInt(req.getParameter("emp_id"));
			EmpDAO dao = new EmpDAO();
			Employee emp = dao.getEmp(empId);
			req.setAttribute("empInfo", emp);

			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/modifyMember.jsp");
			try {
				rd.forward(req, resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(req.getMethod().equals("POST")) {		
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
			try {
				if(dao.updateMember(emp)) {
					resp.sendRedirect("/HelloWeb");
				}else {
					resp.sendRedirect("modifyMember.do?emp_id="+eid);
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
