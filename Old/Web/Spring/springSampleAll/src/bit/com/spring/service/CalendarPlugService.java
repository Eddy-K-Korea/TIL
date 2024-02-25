package bit.com.spring.service;

import java.util.List;

import bit.com.spring.dto.CalendarPlugDto;

public interface CalendarPlugService {

	List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto);
}
