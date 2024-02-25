package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.MountinDao;
import dto.MountinDto;

@WebServlet("/mtlist")
public class MountinListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		MountinDao dao = MountinDao.getInstance();
		List<MountinDto> list = new ArrayList<MountinDto>();
		if(work.equals("list")) {
			list = dao.getMountList();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String gson = new Gson().toJson(list);	//Gson을 통해 json형식으로 ajax와 연동
			
			resp.getWriter().write(gson);
		}else if(work.equals("seloption1")) {
			String loc = req.getParameter("search");
			list = dao.getsearchList(loc);
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String gson = new Gson().toJson(list);
			resp.getWriter().write(gson);
		}else if(work.equals("seloption2")) {
			String mtname =req.getParameter("search");
			list = dao.getsearchList2(mtname);
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String gson = new Gson().toJson(list);
			resp.getWriter().write(gson);
		}else if(work.equals("seloption3")) {
			String mana =req.getParameter("search");
			list = dao.getsearchList3(mana);
			
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			String gson = new Gson().toJson(list);
			resp.getWriter().write(gson);
		}
		
	}

}
