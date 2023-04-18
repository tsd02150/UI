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

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/addMemberServlet")
public class AddMemberServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fname = req.getParameter("fname"); // input name = "fname"
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String job = req.getParameter("job");
		String hdate = req.getParameter("hdate");
		
		Employee emp = new Employee();
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		EmpDAO dao = new EmpDAO();
		boolean result = dao.insertEmployee(emp);
		if(result) {
			resp.sendRedirect("empList");			
		}else {
			resp.sendRedirect("employee/addForm.html");
		}
	}
	
	// 생성자, init, service
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// jdbc 연결 접속 조회
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		String fname = req.getParameter("fname"); // input name = "fname"
//		String lname = req.getParameter("lname");
//		String email = req.getParameter("email");
//		String job = req.getParameter("job");
//		String hdate = req.getParameter("hdate");
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
//			// 쿼리 조회 -> 처리결과 : PreparedStatement
//			String sql = "insert into employees (employee_id,first_name, last_name,email,hire_date,job_id) values(employees_seq.nextval,?,?,?,?,?)";
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			psmt.setString(1, fname);
//			psmt.setString(2, lname);
//			psmt.setString(3, email);
//			psmt.setString(4, hdate);
//			psmt.setString(5, job);
//			int r = psmt.executeUpdate();
//			System.out.println("처리된 건수 : "+r);
//			conn.close();
//			psmt.close();
//			
//			resp.sendRedirect("empList"); // form -> addMemberServlet -> empList
//		}catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("fname : " + fname + ", lname : " + lname);
//	}
}
