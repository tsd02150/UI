package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	
	// key & value 저장할 수 있는 컬렉션 Map
	Map<String,Control> map;
	
	public FrontController() {
		System.out.println("FrontController() call.");
		map = new HashMap<>();
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() call.");
		map.put("/main.do", new MainControl());
		map.put("/first.do", new FirstControl());
		map.put("/second.do", new SecondControl());
		// 사원정보 상세 페이지(getMember.jsp)
		map.put("/getMember.do", new GetMemberControl());
		map.put("/modifyMember.do", new ModifyMemberControl());
		map.put("/addMemberForm.do", new AddMemberFormControl());
		map.put("/addMember.do", new AddMemberControl());
		map.put("/delMemberForm.do", new delMemberFormControl());
		map.put("/delMember.do", new delMemberControl());
		map.put("/loginForm.do", new LoginFormControl());
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		System.out.println("service() call.");
		//http://localhost:8081/HelloWeb/first.do
		// uri : url에서 호스트와 포트 번호 뺀거
		String uri = req.getRequestURI(); // /HelloWeb/first.do
		// context : 프로젝트 이름
		String context = req.getContextPath(); // /HelloWeb
		String page = uri.substring(context.length());

		System.out.println(map.get(page));
		
		Control control = map.get(page);
		control.exec(req, resp);
		
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() call.");
	}
}
