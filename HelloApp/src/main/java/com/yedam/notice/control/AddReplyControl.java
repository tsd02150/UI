package com.yedam.notice.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.notice.domain.ReplyVO;
import com.yedam.notice.service.ReplyService;
import com.yedam.notice.service.ReplyServiceImpl;

public class AddReplyControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 댓글 등록 컨트롤...ing...
		ReplyService service = new ReplyServiceImpl();
		
		String noticeId=req.getParameter("notice_id");
		String id = req.getParameter("id");
		String reply = req.getParameter("reply");
		ReplyVO vo = new ReplyVO();
		vo.setNoticeId(Integer.parseInt(noticeId));
		vo.setReplyWriter(id);
		vo.setReply(reply);
		
		String json="";
		
		Map<String,Object> map = new HashMap<>();
		
		if(service.addReply(vo)) {
			// {"retCode":"Success"}
			//json="{\"retCode\":\"Success\"}";
			// {"retCode":"Success","data":vo};

			map.put("retCode", "Success");
			map.put("data", vo);
			
		}else {
			// {"retCode":"Fail"}			
			//json="{\"retCode\":\"Fail\"}";
			map.put("retCode", "Fail");
		}
		
		Gson gson = new GsonBuilder().create();
		json = gson.toJson(map);
		
		return json+".json";
	}

}
