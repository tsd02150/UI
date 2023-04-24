package com.yedam.notice.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class ReplyListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//json 데이터 생성
		// [{"replyId":5,"noticeId":98 ...}...]
		String nid = req.getParameter("nid");
		ReplyService service = new ReplyServiceImpl();
		List<ReplyVO> list = service.getReplies(Integer.parseInt(nid));
		String json="[";
		for(int i=0;i<list.size();i++) {
			json +="{\"replyId\":"+list.get(i).getReplyId()+","
					+ "\"noticeId\":"+list.get(i).getNoticeId()+","
					+ "\"reply\":\""+list.get(i).getReply()+"\","
					+ "\"replyWriter\":\""+list.get(i).getReplyWriter()+"\","
					+ "\"replyDate\":\""+list.get(i).getReplyDate()+"\"}";
			if(i!=list.size()-1) {
				json+=",";
			}
		}
		json+="]";
		
		return json+".json";
	}

}
