package com.yedam;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		/*try {
			// 페이지 재지정
			// resp.sendRedirect() : 내부에 있는 페이지 외부에 있는 페이지 다됨 , 파라미터 값으로 넘길수 없음
			req.getRequestDispatcher("WEB-INF/views/empList.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		try {
			EmpDAO dao = new EmpDAO();
			List<Employee> list = dao.getEmpList();

			req.setAttribute("listInfo", list);
			
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/empList.jsp");
			rd.forward(req, resp);
			
		} catch (Exception e) {
			// TODO: handle exception
		} 

	}

}
