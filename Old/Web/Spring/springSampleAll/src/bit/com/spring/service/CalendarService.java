package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.CalendarDto;

public interface CalendarService {

	List<CalendarDto> getCalendarlist(CalendarDto cal);
	boolean addcalendar(CalendarDto cal);
	CalendarDto getcaldetail(int seq);
	List<CalendarDto> getcallist(CalendarDto cal);
}
