package com.yedam.notice.service;

import java.util.List;

import com.yedam.notice.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> getReplies(int noticeId);
	public boolean addReply(ReplyVO vo);
	public boolean removeReply(int replyId);
}
