package bit.com.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import bit.com.spring.service.PdsService;

//web에서 등록을 해야하기 때문에  생성
// 가상 뷰 다운로드 창이 만들어지는곳
public class DownloadView extends AbstractView{

	@Autowired
	PdsService service;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("DownloadView renderMergedOutputModel");
		
		File file = (File)model.get("downloadFile");	//getAttribute랑 동일하다
		
		response.setContentType(this.getContentType()); 
		response.setContentLength((int)file.length());
		
		//만약 IE/chrome
		String userAgent = request.getHeader("user-Agent");	//브라우저 헤더부분에 MSIE가 -1 보다 크면 크롬이거나 다른 브라우져이다
		boolean ie = userAgent.indexOf("MSIE") > -1;
		
		String filename = null;
		if(ie) {
			filename = URLEncoder.encode(file.getName(), "utf-8"); 
		}
		else {
			filename = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
			
		}
		// 다운로드 창
		response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");	//파일명이 나오기 위해
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Content-Length", "" + file.length());
		response.setHeader("Pragma", "no-cache;"); 
		response.setHeader("Expires", "-1;");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fi = null;
		
		fi = new FileInputStream(file);
		FileCopyUtils.copy(fi, out);
		
		//download count 증가
		
		if(fi != null) {
			fi.close();
		}
	}

}
