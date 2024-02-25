package com.test.security1.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.test.security1.model.User;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다.(Security ContextHolder)에 저장
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 한다.
// User 오브젝트 타입 => UserDetails 타입 객체

//Security Session => Authentication => UserDetails(PrincipalDetails) 타입으로 정해져 있다.

public class PrincipalDetails implements UserDetails, OAuth2User{ //일반 로그인시 authentication의 userDetails와 oauth2User로 로그인시 세션을  둘다 부모로 가지는 객체로 만들어줌

	private User user; 
	private Map<String, Object> attribute;
	
	//일반로그인
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	//OAuth 로그인
	public PrincipalDetails(User user, Map<String, Object>attribute) {
		this.user = user;
		this.attribute = attribute;
	}
	
	//해당 User의 권한을 리턴하는곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//타입을 맞춰줘야한다.
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	//계정이 완료 안되었기 때문에 true
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠기지 않았기 떄문에 true
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정의 패스워드가 오래 안되었는지
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정 활성화 여부
	@Override
	public boolean isEnabled() {
		//계정 만료 여부 확인하는 로직을 넣을 수도 있음
		return true;
	}

	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attribute;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
