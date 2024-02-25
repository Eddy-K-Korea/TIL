package com.test.security1.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.security1.model.User;
import com.test.security1.repository.UserRepository;

// 시큐리티 설정에서 loginProcessingUrl("/login"); 설정
// /login 요청이 오면 자동으로 UserDetailsService타입으로 Ioc되어 있는 loadUserByUsername 함수가 실행 (규칙)

//뷰 단에 form의 name을 username으로 적어야 매핑이 된다. 만약 다르게 하려면 securityConfigure에서 파라미터 변경 설정을 해야함.
@Service
public class PrincipalDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// 시큐리티 session(내부 Authentication(내부 UserDetails))
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity = userRepository.findByUsername(username);
		if(username != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

	
}
