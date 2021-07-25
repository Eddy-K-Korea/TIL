package com.test.jwt.config.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.jwt.config.auth.PrincipalDetails;
import com.test.jwt.model.User;

import lombok.RequiredArgsConstructor;

//스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음.
// /login 요청해서 username, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private final AuthenticationManager authenticationManager;
	
	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter: 로그인 시도중");
		
		// 1. username, password 받아서
		try {
//			BufferedReader br = request.getReader();
//			
//			String input = null;
//			while((input = br.readLine()) != null) {
//				System.out.println(input);
//			}
			
			ObjectMapper om = new ObjectMapper(); //json 데이터를 파싱한다.
			
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);
			
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			
			//PrincipalDetailService의  loadUserByUsername() 함수가 실행 후 정상이면 authentication이 리턴됨
			//DB에 있는 username과 password가 일치한다.
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			//authentication 객체가 session영역에 저장이됨 => 로그인 되었다는 의미
			PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
			System.out.println("로그인 완료됨: "+principalDetails.getUser().getUsername());
			System.out.println("1=======================================");
			// manager에 토큰을 넘기면authentication 객체가 session영역에 저장을 해야하고 그 방법이 return 해주면됨
			//리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는거임
			//굳이 JWT토큰을 사용하면서 세션을 만들 이유가 없음. 단지 권한 처리때문에 session 넣어준다.
			
			
			return authentication;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("2=======================================");

		return null;
	}
	
	//attemptAuthentication실행 후 인증이 정상적으로 되었으면 successfulAuthentication함수가 실행된다.
	//JWT토큰을 만들어서 request 요청한 사용자에게 JWT토큰을 response해주면됨
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAuthentication실행됨 : 인증이 완료 되었다는 뜻");
		PrincipalDetails principalDetails = (PrincipalDetails)authResult.getPrincipal();
		
		//Hash암호 방식
		String jwtToken = JWT.create()
				.withSubject("cos토큰")//이름
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))//만료시간 (10분)
				.withClaim("id", principalDetails.getUser().getId())
				.withClaim("username",principalDetails.getUser().getUsername())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PRDFIX+jwtToken);
	}
}
