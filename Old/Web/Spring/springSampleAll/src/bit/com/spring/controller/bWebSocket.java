package bit.com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 AOP와 다른 Package에 사용 권장
 
 */


@Controller
public class bWebSocket {

	@RequestMapping(value = "chatting.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String chatting(Model model) {
		model.addAttribute("doc_title", "채팅");
		return "chatting.tiles";
	}
}
