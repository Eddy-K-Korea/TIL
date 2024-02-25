package com.test.security1.config.oauth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.test.security1.config.auth.PrincipalDetails;
import com.test.security1.config.oauth.provider.FacebookUserInfo;
import com.test.security1.config.oauth.provider.GoogleUserInfo;
import com.test.security1.config.oauth.provider.NaverUserInfo;
import com.test.security1.config.oauth.provider.OAuth2UserInfo;
import com.test.security1.model.User;
import com.test.security1.repository.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService{

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	//구글로 부터 받은 userRequest 데이터에 대한 후처리되는 함수
	//함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : "+userRequest.getClientRegistration()); //registration로 어떤 OAuth로 로그인 했는지 구분 가능
		System.out.println("getAccessToken : "+userRequest.getAccessToken().getTokenValue());
		
		
		OAuth2User oauth2User = super.loadUser(userRequest);
		//구글로그인버튼 클릭 -> 구글로그인 창 -> 로그인 완료 -> code를 리턴(OAuth-client라이브러리) -> Access Token요청
				//userRequest정보 -> loadUser함수 호출 ->구글로 부터 회원 프로필 받음
		System.out.println("getAttributes : "+oauth2User.getAttributes());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oauth2User.getAttributes());
		}else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuth2UserInfo = new FacebookUserInfo(oauth2User.getAttributes());
		}else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map)oauth2User.getAttributes().get("response")); //response안에 또 response키값을 가져와야한다
		}else {
			System.out.println("구글과 페이스북과 네이버 지원");
		}
		
		String provider = oAuth2UserInfo.getProvider();
		String providerId = oAuth2UserInfo.getProviderId();
		String email = oAuth2UserInfo.getEmail();
		String username = provider + "_"+providerId; // google_120310245324...
		String password = bCryptPasswordEncoder.encode("겟인데어");
		String role = "ROLE_USER";
		
		//회원인지 아닌지 구분
		User userEntity = userRepository.findByUsername(username);
		
		//회원이 아니라면 자동 회원가입
		if(userEntity == null) {
			userEntity = User.builder()
					.username(username)
					.password(password)
					.email(email)
					.role(role)
					.provider(provider)
					.providerId(providerId)
					.build();
			userRepository.save(userEntity);
		}
				
		//회원가입 강제로 진행
		return new PrincipalDetails(userEntity, oauth2User.getAttributes());
	}
}
