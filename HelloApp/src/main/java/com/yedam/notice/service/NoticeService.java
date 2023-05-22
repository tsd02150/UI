package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.domain.NoticeVO;

public interface NoticeService {
	// CRUD 
	public NoticeVO getNotice(int noticeId);
	public List<NoticeVO> noticeList(int page);
	public boolean addNotice(NoticeVO vo);
	public boolean modifyNotice(NoticeVO vo);
	public boolean removeNotice(int noticeId);
	public int totalCount();
	public boolean modifyNoticeFile(NoticeVO vo);
	
	//json.
	public List<NoticeVO> noticeListJson();
}
