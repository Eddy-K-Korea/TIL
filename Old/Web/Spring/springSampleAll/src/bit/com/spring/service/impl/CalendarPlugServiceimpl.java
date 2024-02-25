package bit.com.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.spring.dao.CalendarPlugDao;
import bit.com.spring.dto.CalendarPlugDto;
import bit.com.spring.service.CalendarPlugService;

@Service
public class CalendarPlugServiceimpl implements CalendarPlugService {

	@Autowired
	CalendarPlugDao dao;
	
	@Override
	public List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto) {
		return dao.getCalendarPlugList(dto);
	}

}
