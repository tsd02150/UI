package com.yedam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {

		int eid = Integer.parseInt(req.getParameter("uname"));
		String email = req.getParameter("psw");
		
		Employee emp =new Employee(); 
		emp.setEmployeeId(eid);
		emp.setEmail(email);
		System.out.println("1:"+emp);
		EmpDAO dao = new EmpDAO();
		emp = dao.loginCheck(emp);
		System.out.println("2:"+emp);
		
		try {
			if(emp == null) {
				resp.sendRedirect("loginForm.do");
			}else {
				req.setAttribute("reqInfo", emp.getFirstName());
				
				HttpSession session = req.getSession();
				session.setAttribute("sesInfo", emp.getLastName());
				
				resp.sendRedirect("main.do");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
