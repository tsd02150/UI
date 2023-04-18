package com.yedam.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.common.DAO;
import com.yedam.domain.Employee;

public class EmpDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;

	public void close() {
		try {
			if (conn != null) {
				conn.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 사원 목록
	public List<Employee> getEmpList() {
		List<Employee> list = new ArrayList<>();
		try {
			String sql = "select * from employees order by 1 desc";
			conn = DAO.getConnenct();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
				list.add(emp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	// 사원등록
	public boolean insertEmployee(Employee emp) {
		try {
			String sql = "insert into employees(employee_id,first_name,last_name,email,job_id,hire_date) values(employees_seq.nextval,?,?,?,?,?)";
			conn = DAO.getConnenct();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, emp.getFirstName());
			psmt.setString(2, emp.getLastName());
			psmt.setString(3, emp.getEmail());
			psmt.setString(4, emp.getJobId());
			psmt.setString(5, emp.getHireDate());

			int r = psmt.executeUpdate();
			System.out.println("처리된 건수 : " + r);

			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 사원삭제
	public boolean deleteEmp(int eid) {
		try {
			String sql = "delete from employees where employee_id=?";
			conn = DAO.getConnenct();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, eid);

			int result = psmt.executeUpdate();
			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	public Employee getEmp(int eid) {
		Employee emp = null;
		try {
			String sql = "select * from employees where employee_id=?";
			conn = DAO.getConnenct();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, eid);
			rs = psmt.executeQuery();
			if (rs.next()) {
				emp = new Employee();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setJobId(rs.getString("job_id"));
				emp.setHireDate(rs.getString("hire_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return emp;
	}
	
	public boolean updateMember(Employee emp) {
		int result=0;
		try {
			String sql = "update employees set first_name=?,last_name=?,email=?,job_id=?,hire_date=? where employee_id=?";
			conn = DAO.getConnenct();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,emp.getFirstName());
			psmt.setString(2,emp.getLastName());
			psmt.setString(3,emp.getEmail());
			psmt.setString(4,emp.getJobId());
			psmt.setString(5,emp.getHireDate());
			psmt.setInt(6, emp.getEmployeeId());
			result = psmt.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
