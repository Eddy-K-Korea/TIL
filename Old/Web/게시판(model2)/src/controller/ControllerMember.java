package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.MemberDao;
import dto.MemberDto;

@WebServlet("/memberLogin")
public class ControllerMember extends HttpServlet {
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String work = req.getParameter("work");
		String id = req.getParameter("id");
		MemberDao dao = MemberDao.getInstance();
		String gson;
		if(work.equals("signup")) {
			resp.sendRedirect("signup.jsp");
		}else if(work.equals("checkId")) {
			boolean findId = dao.getId(id);
			if(!findId){
				String check = "YES";
				gson = new Gson().toJson(check);
				resp.getWriter().write(gson);
			}
			else{
				String check = "NO";
				gson = new Gson().toJson(check);
				resp.getWriter().write(gson);
			}
			
		}else if(work.equals("am")) {
			req.setCharacterEncoding("utf-8");
			String pwd = req.getParameter("pwd");
			String name = req.getParameter("name");
			String birth = req.getParameter("yy") +"/"+ req.getParameter("mm") +"/"+ req.getParameter("dd");
			String sex =req.getParameter("sex");
			String email = req.getParameter("email");
			int phone = Integer.parseInt(req.getParameter("phoneNo"));
			
			//System.out.println(id+" "+pwd+" "+name+" "+birth+" "+sex+" "+email+" "+phone);
			MemberDto mem = new MemberDto(id, pwd, name, birth, sex, email, phone, 3);
			
			boolean addSuccess = dao.addMember(mem); 
				if(addSuccess) {
					String check = "add";
					gson = new Gson().toJson(check);
					resp.getWriter().write(gson);
				}else{
					String check = "nadd";
					gson = new Gson().toJson(check);
					resp.getWriter().write(gson);
				}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		//System.out.println(id+" "+pwd);
		
		MemberDao dao = MemberDao.getInstance();
		MemberDto mem = dao.login(id, pwd);
		
		if(mem != null && !mem.getId().equals("")) {
			String check = "success";
			String gson = new Gson().toJson(check);
			resp.getWriter().write(gson);
		}else {
			String check = "fail";
			String gson = new Gson().toJson(check);
			resp.getWriter().write(gson);
			
		}
		HttpSession session = req.getSession();
		session.setAttribute("login", mem);
		session.setMaxInactiveInterval(30 * 60 * 60);
		forward("bbslist.jsp", req, resp);
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
	
}
