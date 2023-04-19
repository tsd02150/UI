package com.yedam.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;

public class SampleExe {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory= DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
			// select 테스트
//			Employee emp = session.selectOne("com.yedam.common.NoticeMapper.getEmp", 100);
			// 전체 조회
			List<Employee> list = session.selectList("com.yedam.common.NoticeMapper.empList");
			System.out.println(list);

			// delete 테스트
//			session.delete("com.yedam.common.NoticeMapper.delEmp",206);
			
			// insert 테스트
//			Employee emp = new Employee();
//			emp.setEmployeeId(300);
//			emp.setFirstName("Kildong");
//			emp.setLastName("Hong");
//			emp.setEmail("hong@email");
//			emp.setJobId("IT_PROG");
//			session.insert("com.yedam.common.NoticeMapper.addEmp",emp);
			
			
		}
	}
}
