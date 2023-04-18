package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/initServlet")
public class InitServlet extends HttpServlet{
	// 서블릿 : 서버측에서 실행되는 웹 페이지(자바 코드)
	//		  톰캣 컨테이너가 실행시켜줌 
	//		  <-> 클라이언트의 요청. url : localhost:8081/HelloWeb/initServelet
	// .java 확장자 -> .class 파일로 컴파일이 된 후 .class 파일이 실행
	public InitServlet() {
		System.out.println("InitServlet() Call.");
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() Call.");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() Call.");
		// 화면에 출력되는 컨텐츠의 타입 지정
		resp.setContentType("text/html;charset=UTF-8"); 
		// 사용자의 화면에 출력. -> 출력스트림 생성
		PrintWriter out = resp.getWriter(); // 출력스트림 생성.
		out.print("initServlet 페이지 입니다.");
		out.print("<h3>InitPage</h3>");
		out.print("<a href='index.html'>index페이지로 이동</a>");
	}
	
	
}
