package com.yedam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.persistence.EmpDAO;

@WebServlet("/delMemberServlet")
public class DelMemberServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int eid = Integer.parseInt(req.getParameter("eid"));
		EmpDAO dao = new EmpDAO();
		if(dao.deleteEmp(eid)) {
			resp.sendRedirect("empList");			
		}else {
			resp.sendRedirect("employee/delForm.html");
		}
	}
	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// jdbc 연결 접속 조회
//				try {
//					Class.forName("oracle.jdbc.driver.OracleDriver");
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//				int eid = Integer.parseInt(req.getParameter("eid"));
//				try {
//					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
//					String sql = "delete from employees where employee_id=?";
//					PreparedStatement psmt = conn.prepareStatement(sql);
//					psmt.setInt(1, eid);
//
//					int r = psmt.executeUpdate();
//					System.out.println("처리된 건수 : "+r);
//					conn.close();
//					psmt.close();
//					
//					resp.sendRedirect("empList"); // form -> addMemberServlet -> empList
//				}catch (SQLException e) {
//					e.printStackTrace();
//				}
//
//				System.out.println("삭제된 employee_id : " + eid);
//	}
}
