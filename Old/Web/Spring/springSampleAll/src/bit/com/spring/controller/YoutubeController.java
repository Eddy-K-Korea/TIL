package bit.com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.spring.dto.MemberDto;
import bit.com.spring.dto.Youtube;
import bit.com.spring.dto.YoutubeSave;
import bit.com.spring.service.YoutubeService;
import bit.com.spring.util.YoutubeParser;

@Controller
public class YoutubeController {

	@Autowired
	private YoutubeParser youtubeParser;
	@Autowired
	YoutubeService service;
	
	@RequestMapping(value = "youtube.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String youtube(String s_keyword, Model model) {
		model.addAttribute("doc_title", "유튜브");
		
		// 검색 기능이 있어야한다 ***
		//youtubeParser.getTitles("백종원");
		if(s_keyword != null && !s_keyword.equals("")) {
			
			List<Youtube> getTtitles = youtubeParser.getTitles(s_keyword);
			
			model.addAttribute("yulist", getTtitles);
			model.addAttribute("s_keyword", s_keyword);
		}
		
		
		return "youtube.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "youtubesave.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/String;charset=utf-8")
	public String youtubesave(YoutubeSave ys) {
		System.out.println(ys.toString());
		boolean isS = service.saveVideo(ys);
		String msg ="";
		if(isS) {
			msg = "저장 되었습니다.";
			return msg;
		}
		else {
			msg = "저장 실패";
			return msg;
		}
	}
	
	@RequestMapping(value = "myyoutube.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String myyoutube(Model model, HttpSession session) {
		String id = ((MemberDto)session.getAttribute("login")).getId();
		List<YoutubeSave> list = service.getMyVideos(id);
		model.addAttribute("doc_title", "유튜브");
		model.addAttribute("myVideos", list);
		return "myyoutube.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "removevideo.do", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/String;charset=utf-8")
	public String removevideo(int seq) {
		boolean isS = service.removeVideo(seq);
		String msg = "";
		if(isS) {
			msg="삭제 되었습니다";
			return msg;
		}
		else {
			msg="삭제 실패";
			return msg;
		}
	}
}
