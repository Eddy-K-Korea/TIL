package bit.com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bit.com.spring.dto.PdsDto;
import bit.com.spring.dto.PdsParam;
import bit.com.spring.service.PdsService;
import bit.com.spring.util.PdsUtil;

@Controller
public class PdsController {

	@Autowired
	PdsService service;
	
	@RequestMapping(value = "pdslist.do", method = {RequestMethod.GET,RequestMethod.POST })
	public String pdslist(Model model,PdsParam param) {
		model.addAttribute("doc_title", "자료실 목록");
		
		List<PdsDto> list = service.getPdsList(param);
		model.addAttribute("pdslist", list);
		model.addAttribute("choice", param.getChoice());
		model.addAttribute("searchWord", param.getSearchWord());
		return "pdslist.tiles";
	}
	
	@RequestMapping(value = "pdswrite.do",method = {RequestMethod.GET,RequestMethod.POST })
	public String pdswrite(Model model) {
		model.addAttribute("doc_title", "자료 올리기");
		
		return "pdswrite.tiles";
	}
	
	@RequestMapping(value = "pdsupload.do",method = {RequestMethod.GET,RequestMethod.POST })
	public String pdsupload(PdsDto pdsdto,
			@RequestParam(value="fileload",required=false)MultipartFile fileload,HttpServletRequest req) { //@RequestParam 뒤에 는 파일 정보를 byte형식으로 받아주는 식
		
		//filename 취득
		String filename = fileload.getOriginalFilename(); //원본 파일 명 myfile.text 에서 myfile
		pdsdto.setOldfilename(filename);
		
		//upload 경로 설정
		// 서버 톰캣에 올리는 경로 (서버가 껐다가 킬때 자주할 경우 데이터가 날라 갈 수 있다)
		String fupload = req.getServletContext().getRealPath("/upload");
		
		//폴더로 경로(데이터가 날아가지 않는다)
	//	String fupload = "d:\\tmp"; 클라이언트 로컬에 저장
		
		System.out.println("fupload: "+fupload);
		
		//file 명을 취득
		String f = pdsdto.getOldfilename();
		String newfilename = PdsUtil.getNewFileName(f);	//return을 받게 되면 시스템 타임으로 파일명으로 받아온다
		
		pdsdto.setFilename(newfilename); //바뀐 파일명을 dto에 set 시켜준다
		
		//import java.io.File;
		File file = new File(fupload+"/"+newfilename);
		
		//파일을 만든다.(실제 업로드가 되는 부분) import org.apache.commons.io.FileUtils;
		try {
			FileUtils.writeByteArrayToFile(file, fileload.getBytes()); //fileload는 multipart에서 받아온것
			
			//db 에 저장
			service.uploadPds(pdsdto);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return "redirect:/pdslist.do";
	}
	
	//fileDownload.do
	@RequestMapping(value = "fileDownload.do",method = {RequestMethod.GET,RequestMethod.POST })
	public String fileDownload(String filename, int seq, HttpServletRequest req, Model model) {
		
		//경로
		//server
		String fupload = req.getServletContext().getRealPath("/upload");
		
		//폴더
		// String fupload = "c:\\tmp";		만약 로컬로 하게된다면
		
		File downloadFile = new File(fupload + "/" + filename);
		
		model.addAttribute("downloadFile", downloadFile);
		model.addAttribute("seq", seq);
		
		return "downloadView";	//servlet context 에서 id가 downloadView으로 생성하였던 인스턴스를 찾아간다.
	}
	
	@RequestMapping(value = "pdsdetail.do",method = {RequestMethod.GET,RequestMethod.POST })
	public String pdsDetail(int seq, Model model) {
		System.out.println(seq);
		//model.addAttribute("doc_title", "자료 상세보기");
		PdsDto pdsdto = service.getPdsdetail(seq);
		System.out.println(pdsdto.toString());
		model.addAttribute("pds", pdsdto);
		return "pdsdetail.tiles";
		
	}
	
	@RequestMapping(value = "pdsupdate.do",method = {RequestMethod.GET,RequestMethod.POST })
	public String pdsupdate(int seq, Model model) {
		model.addAttribute("doc_title", "자료 수정");
		
		//dto 취득
		PdsDto pdsdto = service.getPdsdetail(seq);
		model.addAttribute("pds", pdsdto);
		
		return "pdsupdate.tiles";
	}
	
	@RequestMapping(value = "pdsupdateAf.do",method = {RequestMethod.GET,RequestMethod.POST })
	public String pdsupdateAf(PdsDto pdsdto, String namefile, //기존 파일명
									HttpServletRequest req, @RequestParam(value = "fileload", required = false)MultipartFile fileload) {
		pdsdto.setOldfilename(fileload.getOriginalFilename());
		
		//파일 경로 설정
		String fupload = req.getServletContext().getRealPath("/upload");
		
		if(pdsdto.getOldfilename() != null && !pdsdto.getOldfilename().equals("")) {//파일을 수정한다는 의미로 받아들여짐
			String f = pdsdto.getOldfilename();
			String newfilename = PdsUtil.getNewFileName(f);
			
			pdsdto.setFilename(newfilename);
			
			File file = new File(fupload + "/" + newfilename);
			
			//  실제 업로드
			
			try {
				FileUtils.writeByteArrayToFile(file, fileload.getBytes());
				
				//db갱신
				service.updatePds(pdsdto);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		else {//수정할 파일 없음
			// 기존의 파일명으로 설정
			pdsdto.setFilename(namefile);
			
			//DB
			service.updatePds(pdsdto);
			
		}
		return "redirect:/pdslist.do";
	}
}









