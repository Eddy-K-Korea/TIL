package com.test.jwt.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.jwt.model.User;
import com.test.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

// @CrossOrigin 해당 어노테이션은 인증이 필요없는 경우에만 사용이 가능하다.
@RequiredArgsConstructor
@RestController
public class RestApiController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder BcryptPasswordEncoder;

	@GetMapping("home")
	public String home() {
		return"<h1>home</h1>";
	}
	
	@PostMapping("token")
	public String token() {
		return"<h1>token</h1>";
	}
	
	@PostMapping("join")
	public String join(@RequestBody User user) {
		user.setPassword(BcryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles("ROLE_USER");
		userRepository.save(user);
		return "회원가입 완료";
	}
	
	//user권한만 접근 가능
	@GetMapping("/api/v1/user")
	public String user() {
		return "user";
	}
	
	//admin, manager권한만 접근 가능
	@GetMapping("/api/v1/manager")
	public String manager() {
		return "manager";
	}
	
	//admin 권한만 접근 가능
	@GetMapping("/api/v1/admin")
	public String admin() {
		return "admin";
	}
}
