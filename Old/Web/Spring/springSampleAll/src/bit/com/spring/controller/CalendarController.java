package bit.com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.spring.dto.CalendarDto;
import bit.com.spring.dto.CalendarParam;
import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.CalendarService;
import bit.com.spring.util.CalendarUtil;

@Controller
public class CalendarController {

	@Autowired
	CalendarService service;
	
	@RequestMapping(value = "calendarlist.do", method = RequestMethod.GET)
	public String calendarlist(Model model, HttpSession session, CalendarParam cal ) {
		model.addAttribute("doc_title", "일정");
		
		cal.calculate();//오늘 날짜 취득하기 위해
		//로그인한 사람의 정보가 필요함
		String id = ((MemberDto)session.getAttribute("login")).getId();
		
		String yyyymm = CalendarUtil.yyyymm(cal.getYear(), cal.getMonth());
		System.out.println(id);
		System.out.println(yyyymm);
		//dto set
		CalendarDto fcal = new CalendarDto();
		fcal.setId(id);
		fcal.setRdate(yyyymm);
		
		List<CalendarDto> list = service.getCalendarlist(fcal);
		model.addAttribute("flist", list);
		model.addAttribute("cal", cal);
		
		return "calendar.tiles";
	}
	@RequestMapping(value = "calwrite.do", method = RequestMethod.GET)
	public String calwriteday(String year,String month, String day, Model model) {
		model.addAttribute("doc_title", "일정쓰기");
		model.addAttribute("year",year);
		model.addAttribute("month",month);
		model.addAttribute("day",day);
		return "calwrite.tiles";
	}
	
	@RequestMapping(value = "calwriteAf.do", method = RequestMethod.POST)
	public String calwriteAf(String id, String title, String content, String year,String month, String day, String hour, String min) {
		CalendarUtil util = new CalendarUtil();
		String rdate = year+(util.two(month))+(util.two(day))+(util.two(hour))+(util.two(min));
		System.out.println(rdate);
		CalendarDto caldto = new CalendarDto(id, title, content, rdate);
		boolean isS = service.addcalendar(caldto);
		if(isS) {
			return "redirect:/calendarlist.do";
		}
		else {
			return "redirect:/calendarlist.do";
		}
		
	}
	
	@RequestMapping(value = "caldetail.do", method = RequestMethod.GET)
	public String caldetail(int seq, Model model) {
		System.out.println(seq);
		CalendarDto caldto = service.getcaldetail(seq);
		
		String year = caldto.getRdate().substring(0, 4);
		String month = caldto.getRdate().substring(4, 6);
		String day = caldto.getRdate().substring(6, 8);
		String hour = caldto.getRdate().substring(8, 10);
		String min = caldto.getRdate().substring(10, 12);
		
		model.addAttribute("caldto", caldto);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("hour", hour);
		model.addAttribute("min", min);
		return "caldetail.tiles";
	}
	
	@RequestMapping(value = "callist.do", method = RequestMethod.GET)
	public String callist(String year, String month, String day, HttpSession session, Model model) {
		CalendarUtil util = new CalendarUtil();
		String rdate = year+(util.two(month))+(util.two(day)); 
		String id = ((MemberDto)session.getAttribute("login")).getId();
		CalendarDto cal = new CalendarDto(id, rdate);
		List<CalendarDto>list = service.getcallist(cal);
		model.addAttribute("callist", list);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		return "callist.tiles";
		
	}
	
	@RequestMapping(value = "calendarMonth.do", method = RequestMethod.GET)
	public String calmonthlist(String year, String month, HttpSession session, Model model) {
		CalendarUtil util = new CalendarUtil();
		String rdate = year+(util.two(month));
		String id = ((MemberDto)session.getAttribute("login")).getId();
		CalendarDto cal = new CalendarDto(id, rdate);
		List<CalendarDto>list = service.getcallist(cal);
		model.addAttribute("callist", list);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "calmonthlist.tiles";
	}
	
}
