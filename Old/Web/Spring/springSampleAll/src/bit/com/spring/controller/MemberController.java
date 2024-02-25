package bit.com.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bit.com.spring.dto.MemberDto;
import bit.com.spring.service.MemberService;

@Controller
//@RestController //자동으로 responsbody와 맵핑을 합쳐준다
public class MemberController {
	
	@Autowired
	MemberService service;

	@RequestMapping(value = "login.do", method = RequestMethod.GET)
	public String login() {
		System.out.println("MemberController login()");
		return "login.tiles";
	}
	
	@RequestMapping(value = "regi.do", method = RequestMethod.GET)
	public String regi() {
		System.out.println("MemberController regi()");
		return "regi.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "getId.do", method = RequestMethod.POST)
	public String getId(String id) {
		System.out.println("MemberController getId()");
		
		int count = service.checkid(id);
		String msg= "";
		if(count>0) {
			msg="YES";
		}else {
			msg="NO";
		}
		return msg;
	}
	
	@RequestMapping(value = "regiAf.do", method = RequestMethod.POST)
	public String regi(MemberDto mem) {
		
		service.addMember(mem);
		
		return "redirect:/login.do";
	}
	@RequestMapping(value = "loginAf.do", method = RequestMethod.POST)
	public String loginAf(MemberDto dto, HttpServletRequest req) {
		System.out.println("MemberController loginAf()");
		
		MemberDto login = service.login(dto);
		
		if(login !=null && !login.getId().equals("")) {
			//session
			req.getSession().setAttribute("login", login);
			req.getSession().setMaxInactiveInterval(60 * 60 * 2); // 2시간
			return "redirect:/bbslist.do";
		}
		else {
			return "redirect:/login.do";
		}
	}
	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
	
		return "redirect:/login.do";
	}
}
