package com.yedam.notice.control;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.yedam.common.Control;
import com.yedam.notice.domain.NoticeVO;
import com.yedam.notice.service.NoticeService;
import com.yedam.notice.service.NoticeServiceImpl;

public class ModifyNoticeFile implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String saveDir = req.getServletContext().getRealPath("images");
		int maxSize = 5 * 1024 * 1024;
		String encoding = "UTF-8";
		DefaultFileRenamePolicy rn = new DefaultFileRenamePolicy();
		MultipartRequest multi = new MultipartRequest(req, saveDir, maxSize, encoding, rn);
		
		int nid=Integer.parseInt(multi.getParameter("nid"));
		String attach="";
		
		Enumeration<?> enu = multi.getFileNames();
		while(enu.hasMoreElements()) {
			String file = (String)enu.nextElement();
			System.out.println("file: "+file);
			attach=multi.getFilesystemName(file);
		}

		NoticeService service = new NoticeServiceImpl();
		NoticeVO vo = new NoticeVO();
		vo.setNoticeId(nid);
		vo.setAttachFile(attach);
		System.out.println(vo);
		
		String json="";
		Gson gson = new GsonBuilder().create();
		
		if (service.modifyNoticeFile(vo)) {
			json=gson.toJson(vo);
		}
		return json+".json";
	}

}
