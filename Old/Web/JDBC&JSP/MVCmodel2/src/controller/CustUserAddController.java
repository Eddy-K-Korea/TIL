package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustUserDao;
import dto.CustUserDto;

@WebServlet("/adduser")
public class CustUserAddController extends HttpServlet { 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		if(work.equals("addview")) {
			resp.sendRedirect("custuseradd.jsp");
		}
		else if(work.equals("defwork")) {
			
		}
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 한글 깨짐 고쳐줌
		String work = req.getParameter("work");
		if(work.equals("dataSave")) {
			
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String address = req.getParameter("address");
			
			
			//DB insert
			CustUserDao dao = CustUserDao.getInstance();
			dao.addCustUser(new CustUserDto(id,name,address));
			
			//list로 이동
			resp.sendRedirect("list");
		}
	
	}

	
}
