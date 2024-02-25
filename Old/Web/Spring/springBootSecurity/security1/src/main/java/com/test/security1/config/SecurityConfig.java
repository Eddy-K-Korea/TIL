package com.test.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.test.security1.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링필터체인에 틍록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true , prePostEnabled = true) //secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	//해당 메서드의 리턴되는 오브젝트를 Ioc에 등록
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
		    .antMatchers("/user/**").authenticated()
		    .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
		    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		    .anyRequest().permitAll()
		    .and()
		    .formLogin()
		    .loginPage("/loginForm")
		    .loginProcessingUrl("/login") // /login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행한다.
		    .defaultSuccessUrl("/")
		    .and()
		    .oauth2Login()				//oauth2로 로그인 시 기존의 로그인 페이지로 이동한다라는 의미
		    .loginPage("/loginForm")   //구글 로그인이 완료된 뒤의 후처리가 필요함 . Tip. 코드 X (엑세스토근 + 사용자프로필정보 O)
		    					       //1. 코드 받기(인증완료) 2. 엑세스토근(권한) 3. 사용자프로필 정보를 가져옴 4-1. 정보를 토대로 회원가입을 자동진행 
		    						  //4-2. (이메일, 전화번호, 이름, 아이디) 이외의 주소 , 등급 등 추가정보가 필요시 추가 회원가입 페이지 이동
		    .userInfoEndpoint()
		    .userService(principalOauth2UserService);
	}
}
