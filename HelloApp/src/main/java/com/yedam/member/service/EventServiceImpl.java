package com.yedam.member.service;

import java.util.List;import org.apache.catalina.mapper.Mapper;
import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.member.domain.EventVO;
import com.yedam.member.mapper.EventMapper;

public class EventServiceImpl implements EventService{
	SqlSession session = DataSource.getInstance().openSession(true);
	EventMapper mapper = session.getMapper(EventMapper.class);
	
	@Override
	public List<EventVO> events() {
		return mapper.eventList();
	}

	@Override
	public boolean addEvent(EventVO event) {
		// TODO Auto-generated method stub
		return mapper.insertEvent(event)==1;
	}

	@Override
	public boolean removeEvent(EventVO event) {
		// TODO Auto-generated method stub
		return mapper.deleteEvent(event)==1;
	}
	
}
