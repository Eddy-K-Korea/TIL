# 로그인 세션 확인 AOP 걸어주기
- 세션에 시간조건을 두어 일정 시간이 지나면 세션이 소멸되기 설정한다
- 세션이 소멸되면 로그인을 다시 하게끔 설정
### AOP 함수 설정
```java
package bit.com.spring.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import bit.com.spring.dto.MemberDto;


@Aspect	//어노테이션으로 aop를 사용하기위해 클래스에 설정
public class LogAop {

	
	
	@Around("within(bit.com.spring.controller.*) or within(bit.com.spring.dao.impl.*)") //아까 xml의 around이며 실행할 callback함수에 붙여준다
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		
		
		//session check
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest(); //현재 rquest에 들어가 있는 모든 컨텐츠를 가져온다
		
		if(request != null) { //request에 어떤 값이라도 있다면 아래 함수 실행
			HttpSession session = request.getSession();
			MemberDto login = (MemberDto)session.getAttribute("login");	//세션이 남아있는지 체크
			if(login == null) { // 만약 세션이 없다면 다시 로그인 페이지로 이동
				return "redirect:/sessionOut.do";
			}
		}
		
		try {
			
			System.out.println("loggerAop:"+signatureStr+" 메소드가 실행");
			Object obj = joinpoint.proceed(); //지정 클래스의 어떠한 메소드가 실행 시
			
			return obj;
		}finally {
			
		}
	}
}
```
### - Controller
```java
@RequestMapping(value = "sessionOut.do", method = RequestMethod.GET)
	public String sessionOut() {
		return "sessionOut.tiles";
	}
```
- 로그인 페이지로 이동하는 컨트롤러 추가

#### - 원래 각 Controller , dao , service 에 Member (longin)과 관련된 클래스가 있었으나 AOP 설정을 위해 따로 패키지를 만들어서 빼준다<br>
![image](https://user-images.githubusercontent.com/65350890/92718915-6c438200-f39d-11ea-834b-ae19aeba1b1d.png)

#### 만약 따로 빼주지않으면 현재 AOP가 모든 컨트롤의 메소드를 관여하고 있기 때문에 세션이 없을 경우 계속 AOP의 메소드를 실행하고를 반복하여 무한 반복에 빠지게 된다.
