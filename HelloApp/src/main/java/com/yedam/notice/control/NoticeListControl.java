package com.yedam.notice.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class NoticeListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pageStr = req.getParameter("page");

		pageStr= pageStr==null ? "1":pageStr;
		int page= Integer.parseInt(pageStr);

		NoticeService service = new NoticeServiceImpl();
		List<NoticeVO> list = service.noticeList(page);
		
		int total = service.totalCount();
		PageDTO dto = new PageDTO(page, total);
		req.setAttribute("list", list);
		req.setAttribute("pageInfo", dto);
		
//		return "WEB-INF/views/notice/noticeList.jsp";
		return "notice/noticeList.tiles";
	}

}
