package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustUserDao;
import dto.CustUserDto;

@WebServlet("/updateUser")
public class CustUserUpdateController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		String id = req.getParameter("id");
		CustUserDao dao = CustUserDao.getInstance();
		if(work.equals("updateview")) {
			CustUserDto dto = dao.getCustuser(id);
			req.setAttribute("userdetail", dto);
			forward("custuserupdate.jsp", req, resp);
		}
		else if(work.equals("saveuser")) {
			String address = req.getParameter("address");
			boolean update = dao.updateCustUser(id, address);
				if(update) { 
					resp.sendRedirect("list"); 
				}
		}else if(work.equals("deleteuser")) {
			dao.deleteCustUser(id);
			resp.sendRedirect("list");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	public void forward(String link, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher(link);
		dis.forward(req, resp);
	}
}
