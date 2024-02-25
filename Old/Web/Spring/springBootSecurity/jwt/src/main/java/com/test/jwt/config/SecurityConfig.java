package com.test.jwt.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.test.jwt.config.jwt.JwtAuthenticationFilter;
import com.test.jwt.config.jwt.JwtAuthorizationFilter;
import com.test.jwt.filter.MyFilter3;
import com.test.jwt.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CorsFilter corsFilter;
	private final UserRepository userRepository;
	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
		//insert into USER (ID,PASSWORD,ROLES,USERNAME) values(1,'$2a$10$KSnPsHJlRfZCdXB9ujXoduPpTG5VVr5/Z.bLzOda9IV8G5m8zz85q','ROEL_USER','test')
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.addFilter(new MyFilter()); // 이것만 걸면 에러가 난다 (시큐리티와 호환 안된다.)
		//아래 필터는 스프링 시큐리티체인 필터이다.
		//http.addFilterBefore(new MyFilter3(), BasicAuthenticationFilter.class); //필터의 단계에서 BasicAuthenticationFilter 이거 전에 건다.
		http.csrf().disable();
		
		//h2  설정
		http.headers().frameOptions().disable();
		
		
		// 세션을 사용하지 않겠다는 의미
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.addFilter(corsFilter) //@CrossOring 은 인증이 필요없고 , 시큐리티 필터에 설정을 해야 인증이 있는것에 사용가능
		.formLogin().disable() //form 태그 로그인 안쓴다
		.httpBasic().disable() // http basic은 헤더에 authorization에 id, pw에 담아서 간다 단 ,bearer방식은 토큰으로 데이터를 전소하고 유효시간이있음
		.addFilter(new JwtAuthenticationFilter(authenticationManager())) // AuthenticationManager을 파라미터로 던져줘야한다.
		.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository)) //
		.authorizeRequests()
		.antMatchers("/api/v1/user/**")
		.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/v1/manager/**")
		.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
		.antMatchers("/api/v1/admin/**")
		.access("hasRole('ROLE_ADMIN')")
		.anyRequest().permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/h2-console/**","/favicon.ico");
	}
}
