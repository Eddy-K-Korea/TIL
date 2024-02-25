package bit.com.spring.dao;

import java.util.List;

import bit.com.spring.dto.CalendarPlugDto;

public interface CalendarPlugDao {

	List<CalendarPlugDto> getCalendarPlugList(CalendarPlugDto dto);
}
