package bit.com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import bit.com.spring.dto.BbsDto;
import bit.com.spring.dto.BbsParam;
import bit.com.spring.service.BbsService;

@Controller
public class BitBbsController {

	@Autowired
	BbsService service;
	
	@RequestMapping(value = "bbslist2.do", method = RequestMethod.GET)
	public String bbslist2(Model model) {
		model.addAttribute("doc_title", "글 목록");
		return "bbslist2.tiles";
	}
	
	@ResponseBody
	@RequestMapping(value = "bbslistData.do", method = RequestMethod.GET)
	public List<BbsDto> bbslistData(BbsParam param){
		//paging 처리
		
		int sn = param.getPageNumber(); //현재 페이지
		int start = sn*param.getRecordCountPerPage() +1;
		int end = (sn+1)*param.getRecordCountPerPage();
		
		param.setStart(start);
		param.setEnd(end);
		
		List<BbsDto> bbslist = service.allBbsList(param);
		return bbslist;
	}
	
	//총 글자수를 가져오기 위한 컨트롤
	@ResponseBody
	@RequestMapping(value = "bbslistCount.do", method = RequestMethod.GET)
	public int bbslistCount(BbsParam param) {
		int count = service.getBbsCount(param);
		return count;
	}
}
