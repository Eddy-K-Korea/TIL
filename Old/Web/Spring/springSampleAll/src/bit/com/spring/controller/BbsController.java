package bit.com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.BbsParam;
import bit.com.spring.service.BbsService;

@Controller
public class BbsController {

	@Autowired
	BbsService service;
	/*
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbslist(Model model) {
		model.addAttribute("doc_title", "글 목록");
		
		List<BbsDto> list = service.allBbsList();
		model.addAttribute("bbslist", list);
		return "bbslist.tiles";
	}
	*/
	
	@RequestMapping(value = "bbslist.do", method = RequestMethod.GET)
	public String bbslist(Model model, BbsParam param) {
		model.addAttribute("doc_title", "글 목록");
		
		
		//paging 처리
		int sn = param.getPageNumber(); 	//현재 페이지
		int start = sn * param.getRecordCountPerPage() +1; // 1 11 21 이렇게 디비에서 시작하여 접근
		int end = (sn +1) *param.getRecordCountPerPage(); // 10 20 30 
		
		param.setStart(start);	//바뀐 start와 end set
		param.setEnd(end);
		
		List<BbsDto> list = service.allBbsList(param);	//그 값들을 꺼내옴
		model.addAttribute("bbslist", list);
		
		// 글의 총수
		int totalRecordCount = service.getBbsCount(param);
		
		//값들을 넘겨줌
		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);		
		
		//검색 값들을 유지시켜주기 위해 다시 넘겨준다
		model.addAttribute("choice", param.getChoice());
		model.addAttribute("searchWord", param.getSearchWord());
		
		return "bbslist.tiles";
	}
	
	@RequestMapping(value = "bbswrite.do", method = RequestMethod.GET)
	public String bbswrite(Model model) {
		System.out.println("BbsController bbswrite()");
		model.addAttribute("doc_title", "글 쓰기");
		return "bbswrite.tiles";
	}
	
	@RequestMapping(value = "bbswriteAf.do", method = RequestMethod.GET)
	public String bbswriteAf(BbsDto dto) {
		System.out.println("BbsController bbswriteAf()");
		System.out.println(dto.getTitle());
		boolean isS = service.addbbs(dto);
		if(isS) {
			System.out.println("글 추가 성공");
			return "redirect:/bbslist.do";
		}
		else {
			System.out.println("글 추가 실패");
			return "redirect:/bbslist.do";
		}
	}
	
	@RequestMapping(value = "bbsdetail.do", method = RequestMethod.GET)
	public String bbsdetail(String seq, Model model) {
		
		BbsDto bbs = service.getbbs(seq);
		model.addAttribute("doc_title", "글 상세보기");
		model.addAttribute("bbs", bbs);
		return "bbsdetail.tiles";
	}
	@RequestMapping(value = "bbsreply.do", method = RequestMethod.GET)
	public String bbsreply(BbsDto bbs) {
		boolean isS = service.addreply(bbs);
		if(isS) {
			return "redirect:/bbslist.do";
		}
		else {
			return "redirect:/bbslist.do";
		}
	}
	@RequestMapping(value = "bbsupdate.do", method = RequestMethod.GET)
	public String bbsupdate(String seq, Model model) {
		BbsDto bbs = service.getbbs(seq);
		model.addAttribute("doc_title", "글 수정");
		model.addAttribute("bbs", bbs);
		return"bbsupdate.tiles";
	}
	
	@RequestMapping(value = "bbsdelete.do", method = RequestMethod.GET)
	public String bbsdelete(String seq) {
		boolean isS = service.removebbs(seq);
		if(isS) {
			return "redirect:/bbslist.do";
		}
		else {
			return "redirect:/bbslist.do";
		}
	}
	
	@RequestMapping(value = "bbsupdateAf.do", method = RequestMethod.GET)
	public String bbsupdateAf(BbsDto bbs) {
		boolean isS = service.updatebbs(bbs);
		if(isS) {
			return "redirect:/bbslist.do";
		}
		else {
			return "redirect:/bbslist.do";
		}
	}
}
