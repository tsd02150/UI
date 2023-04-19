package com.yedam.domain;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.notice.mapper.NoticeMapper;
import com.yedam.notice.domain.NoticeVO;

public class SampleExe2 {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = com.yedam.common.DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession(/*true 하면 자동 커밋*/true)) {
//			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
//			
//			
//			Employee emp = mapper.getEmp(100);
//			System.out.println(emp);
//			// session.commit();으로 커밋 가능
			
			NoticeMapper mapper = session.getMapper(NoticeMapper.class);
			for(NoticeVO vo : mapper.noticeList()) {
				System.out.println(vo);
			}
		}
	}
}
