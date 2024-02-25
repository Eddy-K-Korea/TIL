package com.test.security1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.security1.config.auth.PrincipalDetails;
import com.test.security1.model.User;
import com.test.security1.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@ResponseBody
	@GetMapping("/test/login")
	public String testLogin(org.springframework.security.core.Authentication authentication) {
		System.out.println("/test/login==================");
		System.out.println("authentication : "+ authentication.getPrincipal());
		return "세션 정보 확인하기";
	}
	
	@GetMapping({"","/"})
	public String index() {
		return "index";
	}
	@GetMapping("/user")
	@ResponseBody
	
	public String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("principalDetails:"+principalDetails.getAttributes());
		return "user";
	}
	@GetMapping("/admin")
	@ResponseBody
	public String admin() {
		return "admin";
	}
	@GetMapping("/manager")
	@ResponseBody
	public String manager() {
		return "manager";
	}
	//securityConfig 파일로 인해 낚아채지지 않음
	@GetMapping("/loginForm") // 일반적으로 할 경우 스프링 시큐리티 페이지로 낚아챔
	public String login() {
		return "loginForm";
	}
	@PostMapping("/join")
	public String join(User user) {
		user.setRole("ROLE_USER");
		String rawPwd = user.getPassword();
		String encPwd = bCryptPasswordEncoder.encode(rawPwd);
		user.setPassword(encPwd);
		userRepository.save(user); // 회원가입 됨. 단 시큐리티 로그인 할 수 없음 , 패스워드 암호화가 필요함
		return "redirect:/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinFrom() {
		return "joinForm";
	}
	
	@Secured("ROLE_ADMIN") //admin만 들어가게  된다. 하나의 권한만 걸때 사용
	@GetMapping("/info")
	@ResponseBody
	public String info() {
		return "개인정보";
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") //다중 권한을 걸고 싶을때 사용
	@GetMapping("/data")
	@ResponseBody
	public String data() {
		return "데이터정보";
	}
}
