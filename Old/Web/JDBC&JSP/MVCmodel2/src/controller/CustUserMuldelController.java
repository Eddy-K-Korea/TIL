package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustUserDao;

@WebServlet("/muldel")
public class CustUserMuldelController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String delck[] = req.getParameterValues("delck");
		CustUserDao dao = CustUserDao.getInstance();
		boolean isS= true;
		if(delck != null) {
			isS = dao.deleteCustUsers(delck);
		}
		if(isS == true){
			resp.sendRedirect("list");
		}
	}

}
