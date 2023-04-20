package com.yedam.notice.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class AddNoticeControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 멀티파트 요청 : 요청정보, 저장경로, 최대파일사이즈, 인코딩, 리네임정책인스턴스
		String saveDir = req.getServletContext().getRealPath("images");
		int maxSize = 5*1024*1024;
		String encoding = "UTF-8";
		DefaultFileRenamePolicy rn = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(req, saveDir,maxSize,encoding,rn);
		
		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeTitle(multi.getParameter("title"));
		vo.setNoticeSubject(multi.getParameter("subject"));
		vo.setNoticeWriter(multi.getParameter("writer"));
		vo.setAttachFile(multi.getFilesystemName("attach"));
		
		if(service.addNotice(vo)) {
			return "noticeList.do";			
		}else {
			return "main.do";
		}
		
	}

}
