package com.yedam.member.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.member.domain.EventVO;
import com.yedam.member.service.EventService;
import com.yedam.member.service.EventServiceImpl;


public class EventListControl implements Control {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		EventService service = new EventServiceImpl();
		List<EventVO> list = service.events();
			
		String json="[";
		for(int i=0;i<list.size();i++) {
			json +="{\"title\":\""+list.get(i).getTitle()+"\","
					+ "\"start\":\""+list.get(i).getStartDate()+"\","
					+ "\"end\":\""+list.get(i).getEndDate()+"\"}";
			if(i!=list.size()-1) {
				json+=",";
			}
		}
		json+="]";
		
		
		return json+".json";
	}

}
