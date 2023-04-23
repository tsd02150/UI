package com.yedam.member.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.member.domain.MemberVO;
import com.yedam.member.service.MemberService;
import com.yedam.member.service.MemberServiceImpl;

public class ModifyMemberControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberService service = new MemberServiceImpl();
		if (req.getMethod().equals("GET")) {
			MemberVO vo = service.getMember((String)req.getSession().getAttribute("id"));
			req.setAttribute("memberInfo", vo);
			return "member/memberModify.tiles";
		}else if(req.getMethod().equals("POST")) {			
			MemberVO vo = new MemberVO();
			vo.setEmail(req.getParameter("email"));
			vo.setPassword(req.getParameter("password"));
			vo.setPhone(req.getParameter("phone"));
			vo.setAddress(req.getParameter("address"));
			
			if(service.modifyMember(vo)) {
				service.gmailSend();
				return "noticeList.do";
			}else {
				return "modifyMemberInfo.do";
			}
		}
		return null;
	}

}
