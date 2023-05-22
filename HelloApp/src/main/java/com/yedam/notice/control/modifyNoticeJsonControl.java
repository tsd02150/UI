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
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class modifyNoticeJsonControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NoticeService service = new NoticeServiceImpl();
		int nid = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String subject = req.getParameter("subject");
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(nid);
		vo.setNoticeTitle(title);
		vo.setNoticeSubject(subject);
		System.out.println(nid);
		
		Gson gson = new GsonBuilder().create();
		Map<String, Object> map = new HashMap<>();
		if (service.modifyNotice(vo)) {
			vo=service.getNotice(vo.getNoticeId());
			map.put("retCode", "Success");
			map.put("retVal", vo);
		}else {
			map.put("retCode", "Fail");
			map.put("retVal", "err");
		}
		return gson.toJson(map)+".json";
	}

}
