package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getMethod().equals("GET")) {
			NoticeVO vo = new NoticeVO();
			int nid = Integer.parseInt(req.getParameter("nid"));
			NoticeService service = new NoticeServiceImpl();
			req.setAttribute("noticeInfo", service.getNotice(nid));
			return "notice/noticeModify.tiles";
		}else if (req.getMethod().equals("POST")) {
			NoticeService service = new NoticeServiceImpl();
			int nid = Integer.parseInt(req.getParameter("nid"));
			String title = req.getParameter("title");
			String subject = req.getParameter("subject");
			NoticeVO vo = new NoticeVO();
			vo.setNoticeId(nid);
			vo.setNoticeTitle(title);
			vo.setNoticeSubject(subject);
			System.out.println(nid);
			if (service.modifyNotice(vo)) {
				return "noticeList.do";
			} else {
				return "notice/noticeModify.tiles";
			}	
		}
		return null;
	}

}
