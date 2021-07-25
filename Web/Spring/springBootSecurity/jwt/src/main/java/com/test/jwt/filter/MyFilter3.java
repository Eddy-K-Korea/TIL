package com.test.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		//토큰 : cos 이걸 만들어줘야함. id, pw정상적으로 들어와서 로그인이 완료되면 토근을 만들어주고 그걸 응답해준다.
		// 요청 할 대 마다 header에 Authorization에 value 값으로 토큰을 가지고 옴
		// 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증하면됨.( RSA, HS256)
		if(req.getMethod().equals("POST")) {
			String headerAuth = req.getHeader("Authorization");
			//System.out.println(headerAuth);
			
			if(headerAuth.equals("cos")) { //한글은 안된다
				chain.doFilter(req, res);
			}else {
				PrintWriter out = res.getWriter();
				out.println("인증안됨");
			}
		}
	}

}
