package bit.com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.CalendarDao;
import bit.com.spring.dto.CalendarDto;
import bit.com.spring.service.CalendarService;

@Service
public class CalendarServiceimpl implements CalendarService {

	@Autowired
	CalendarDao dao;

	@Override
	public List<CalendarDto> getCalendarlist(CalendarDto cal) {
		return dao.getCalendarlist(cal);
	}

	@Override
	public boolean addcalendar(CalendarDto cal) {
		boolean isS = dao.addcalendar(cal);
		return isS;
	}

	@Override
	public CalendarDto getcaldetail(int seq) {
		CalendarDto caldto = dao.getcaldetail(seq);
		return caldto;
	}

	@Override
	public List<CalendarDto> getcallist(CalendarDto cal) {
		return dao.getcallist(cal);
	}
	
	
}
