package com.yedam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("---");
		int eid = Integer.parseInt(req.getParameter("uname"));
		String email = req.getParameter("psw");
		
		Employee emp = new Employee();
		emp.setEmployeeId(eid);
		emp.setEmail(email);
		
		EmpDAO dao = new EmpDAO();
		emp = dao.loginCheck(emp);
		System.out.println(emp);
		try {
			if(emp == null) {
				resp.sendRedirect("loginForm.do");
			}else {
				resp.sendRedirect("main.do");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
