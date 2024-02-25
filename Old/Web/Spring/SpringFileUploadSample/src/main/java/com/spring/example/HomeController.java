package com.spring.example;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.example.dao.FileDao;
import com.spring.example.dto.FileDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private FileDao fileDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/upload1", method = RequestMethod.GET)
	public String upload1(Locale locale, Model model) {

		return "upload1";
	}

	@RequestMapping(value = "/upload2", method = RequestMethod.GET)
	public String upload2(Locale locale, Model model) {

		return "upload2";
	}

	

	@RequestMapping(value = "requestupload1")
	public String requestupload1(MultipartHttpServletRequest mtfRequest) {
		FileDto fileDto = new FileDto();
		String src = mtfRequest.getParameter("src");
		System.out.println("src value : " + src);
		MultipartFile mf = mtfRequest.getFile("file");

		String path = "C:\\image\\";

		String originFileName = mf.getOriginalFilename(); // 원본 파일 명
		long fileSize = mf.getSize(); // 파일 사이즈

		System.out.println("originFileName : " + originFileName);
		System.out.println("fileSize : " + fileSize);

		String safeFile = path + System.currentTimeMillis() + originFileName;
		fileDto.setOriginfilename(originFileName);
		fileDto.setSafefilename(safeFile);
		
		boolean test = fileDao.fileinsert(fileDto);

		try {
			mf.transferTo(new File(safeFile));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/";
	}
	
	
	@RequestMapping(value = "requestupload2")
	public String requestupload2(MultipartHttpServletRequest mtfRequest, HttpServletRequest req) {
		
//				mtfRequest.setCharacterEncoding("UTF-8");
				FileDto fileDto = new FileDto();
				List<MultipartFile> fileList = mtfRequest.getFiles("file");
				String src = mtfRequest.getParameter("src");
				System.out.println("src value : " + src);
//				mtfRequest.getser
//				String path = "C:\\image\\";
//				String path = req.getServletContext().getRealPath("/productimg");
				String path = req.getSession().getServletContext().getRealPath("/image/");
				System.out.println(path);
				String originadd ="";
				String safefileadd = "";
				int num = 0;
				for (MultipartFile mf : fileList) {
					
					String originFileName = mf.getOriginalFilename(); // 원본 파일 명
					long fileSize = mf.getSize(); // 파일 사이즈

					System.out.println("originFileName : " + originFileName);
					System.out.println("fileSize : " + fileSize);
					
					String fpost ="";
					if(originFileName.indexOf('.') >=0	) { //확장자명이 있음
						fpost = originFileName.substring(originFileName.indexOf('.'));	// fpost = .txt가 들어가있다
					}

					String safeFile =  System.currentTimeMillis()+fpost;
					originadd += originFileName;
					safefileadd += safeFile+"-";

					try {
						mf.transferTo(new File(path + num + safeFile));
						
						

					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					num++;
				}
				String safefile = safefileadd.substring(0, safefileadd.lastIndexOf("-"));
				fileDto.setOriginfilename(originadd);
				fileDto.setSafefilename(safefile);
			
		


	//	boolean test = fileDao.fileinsert(fileDto);

		return "redirect:/";
	}
	
	@RequestMapping(value = "callfile")
	public String callfile(Model model) {
	
		FileDto callfile = fileDao.callfile(23);
		
		String[] list = callfile.getSafefilename().split("-");
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
		
		callfile.getSafefilename();
		model.addAttribute("callfile", list);
		return "callfile";
	}

}
