package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.BbsDao;
import dto.BbsDto;
import dto.MemberDto;

@WebServlet("/conBbs")
public class ControllerBbs extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		BbsDao dao = BbsDao.getInstance();
		//System.out.println(work);
		List<BbsDto> list = new ArrayList<BbsDto>();
		
		if(work.equals("movblist")) {
			resp.sendRedirect("bbslist.jsp");
		}else if(work.equals("blist")) {
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			String spageNumber = req.getParameter("pageNumber");
			int pageNumber;
			if(spageNumber.equals("")) {	//로그인 후 바로 값이 없기때문에 지정해줌
				pageNumber = 0;
			}else {
				pageNumber = Integer.parseInt(spageNumber);
			}
			
			System.out.println("blist"+choice+" "+search+" "+pageNumber);
			
		
			
			list = dao.getSearchBbs(choice, search,pageNumber);
			
				resp.setContentType("application/json"); 
				resp.setCharacterEncoding("UTF-8"); 
				String gson = new Gson().toJson(list);	
				resp.getWriter().write(gson);
			
			
		}else if(work.equals("bsearch")) {	//검색누르면 그값을 다시 jsp로 보내줌 (ajax 처리)
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			System.out.println("bsearch"+choice + " "+ search);
			req.setAttribute("choice", choice);
			req.setAttribute("search", search);
			forward("bbslist.jsp", req, resp);
		}else if(work.equals("write")) {
			resp.sendRedirect("bbswrite.jsp");
		}else if(work.equals("back")) {
			resp.sendRedirect("bbslist.jsp");
		}else if(work.equals("addcontent")) {
			String id = req.getParameter("id");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			BbsDto dto = new BbsDto(id, title, content);
			boolean isS = dao.writeBbs(dto);
			isS(isS, req, resp);
		}else if(work.equals("detail")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			BbsDto dto = dao.getBbs(seq);
			req.setAttribute("BbsDto", dto);
			forward("bbsdetail.jsp", req, resp);
		}else if(work.equals("deletecon")) {
			int seq = Integer.parseInt(req.getParameter("seq"));
			boolean isS = dao.bbsDelete(seq);
			isS(isS, req, resp);
		}else if(work.equals("answer")) {
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String id = req.getParameter("id");
			int seq = Integer.parseInt(req.getParameter("seq"));
			BbsDto dto = new BbsDto(id, title, content);
			boolean isS = dao.answer(seq, dto);
			req.setAttribute("isS", isS);
			forward("answer.jsp", req, resp);
			
		}else if(work.equals("page")) {	//페이지 총 개수를 구해주는 함수 및 그것을 ajax로 출력
			String choice = req.getParameter("choice");
			String search = req.getParameter("search");
			int len = dao.getAllBbs(choice, search);
			int bbsPage = len/5;
			if(len%5>0) {
				bbsPage = bbsPage +1;
			}
			resp.setContentType("application/json"); 
			resp.setCharacterEncoding("UTF-8"); 
			String gson = new Gson().toJson(bbsPage);	
			resp.getWriter().write(gson);
			
		}
	}

	public void forward(String linkname, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher = req.getRequestDispatcher(linkname);
		try {
			dispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void isS(boolean isS,HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(isS) {
			String check = "success";
			String gson = new Gson().toJson(check);
			resp.getWriter().write(gson);
		}else {
			String check = "fail";
			String gson = new Gson().toJson(check);
			resp.getWriter().write(gson);
		}
	}
}
