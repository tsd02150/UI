package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		try {
			req.getRequestDispatcher("WEB-INF/views/loginForm.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace(); 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
