package com.yedam.notice.mapper;

import java.util.List;

import com.yedam.domain.Employee;
import com.yedam.notice.domain.NoticeVO;

public interface NoticeMapper {
	// 공지사항 CRUD
	public NoticeVO searchNotice(int noticeId);
	public List<NoticeVO> noticeList();
	public int insertNotice(NoticeVO vo);
	public int updateNotice(NoticeVO vo);
	public int deleteNotice(int noticeId);
	//조회수 증가
	public int updateCount(int noticeId);
	//페이징리스트
	public List<NoticeVO> noticeWithPage(int page);
	public int getCount();
}
