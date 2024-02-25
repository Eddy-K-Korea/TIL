package bit.com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.spring.dto.CalendarPlugDto;
import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.CalendarPlugService;

@Controller
public class CalendarPlugController {

	@Autowired
	CalendarPlugService service;
	
	@RequestMapping(value = "calendarpluglist.do", method = RequestMethod.GET)
	public String calendarPlugList(Model model, HttpSession session) {
		model.addAttribute("doc_title", "일정Plug");
		
		MemberDto dto =  (MemberDto)session.getAttribute("login");
		
		CalendarPlugDto cal = new CalendarPlugDto();
		cal.setId(dto.getId());
		
		List<CalendarPlugDto>list = service.getCalendarPlugList(cal);
		model.addAttribute("callist", list);
		return "calendarpluglist.tiles";
	}

}
