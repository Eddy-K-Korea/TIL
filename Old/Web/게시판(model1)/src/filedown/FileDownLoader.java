package filedown;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownLoader extends HttpServlet {

	ServletConfig mConfig = null;
	final int BUFFER_SIZE = 8192; // 1키로바이트
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		mConfig = config; //file 업로드 path를 얻어오기위해
	}



	@Override// web xml로 오랜만에 연결 (WEB-INF 확인)
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("FileDownLoader doGet()");
		String filename = req.getParameter("filename");
		String sseq = req.getParameter("seq");
		
		//download 횟수를 증가(DB접근)
		
		
		BufferedOutputStream out = new BufferedOutputStream(resp.getOutputStream());
		
		String filePath = "";
		
		// tomcat(server)
		filePath = mConfig.getServletContext().getRealPath("/upload");
		//폴더(client)
		//filePath = "d:\\tmp";
		filePath = filePath +"\\"+filename;
		System.out.println("filePath:"+filePath);
		
		File f = new File(filePath);
		
		if(f.exists()&&f.canRead()) {
			// download window set (다운로드 윈도우 화면)
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary;");
			resp.setHeader("Content-Length", "" + f.length());
			resp.setHeader("Pragma", "no-cache;"); 
			resp.setHeader("Expires", "-1;");
			
			//파일 생성,기입
			BufferedInputStream fileInput = new BufferedInputStream(new FileInputStream(f));
			byte buffer[] = new byte[BUFFER_SIZE];
			
			int read = 0;
			
			while((read = fileInput.read(buffer)) != -1) {
				out.write(buffer, 0 , read); //실제 다운로드
			}
			fileInput.close();
			out.flush();
		}
	}

}
