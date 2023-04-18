package com.yedam;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.persistence.EmpDAO;

public class delMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		int eid = Integer.parseInt(req.getParameter("emp_id"));
		EmpDAO dao = new EmpDAO();
		try {
			if(dao.deleteEmp(eid)) {
				resp.sendRedirect("/HelloWeb");			
			}else {
				resp.sendRedirect("delMemberForm.do");
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
