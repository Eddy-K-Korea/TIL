package bit.com.spring.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.spring.dao.CalendarDao;
import bit.com.spring.dto.CalendarDto;

@Repository
public class CalendarDaoimpl implements CalendarDao {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	private String ns = "Calendar.";

	@Override
	public List<CalendarDto> getCalendarlist(CalendarDto cal) {
		return sqlSession.selectList(ns+"getCalendar", cal);
	}

	@Override
	public boolean addcalendar(CalendarDto cal) {
		int result = sqlSession.insert(ns+"addCalendar", cal);
		return result>0?true:false;
	
	}

	@Override
	public CalendarDto getcaldetail(int seq) {
		CalendarDto caldto = sqlSession.selectOne(ns+"getcaldetail", seq);
		return caldto;
	}

	@Override
	public List<CalendarDto> getcallist(CalendarDto cal) {
		return sqlSession.selectList(ns+"getcallist", cal);
	}
	
}
