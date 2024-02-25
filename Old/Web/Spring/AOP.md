# AOP 
- 특정처리를 주시하고 있다가 주시하는 함수가 호출되면 체킹또는 다른 처리(메소드)실행
- 선택적인 사용이며 사용하면 편리하다
- 실무에서 알아 들을 수 잇어야한다
- 하기전, 하기 후 , 공통 처리 기능

### -공통 메소드 
1. logger 찍어주기
2. session 체크 
3. 디비연결전 테스트

** filter는 진행처리로 걸러주는거고 aop는 멈춤 처리를 할 수 있다 

#### AOP를 설정하는 방법은 여러가지가 있지만 2가지 방법을 기술

### 1. Xml로 설정
- 우선 aop를 설정하기위해 라이브러리를 pom.xml을 통해 받아준다
```xml
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aop</artifactId>
  <version>5.2.7.RELEASE</version>
</dependency>
<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjweaver</artifactId>
  <version>1.9.4</version>
</dependency>

<dependency>
  <groupId>org.aspectj</groupId>
  <artifactId>aspectjrt</artifactId>
  <version>1.9.4</version>
</dependency>
```
- java src에 설정한 처리를 주시하다가 실행할 메소드를 만들어준다.
```java
package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {
  
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		
		System.out.println(signatureStr + "시작");	//실행 전
		
		try {
			Object obj = joinpoint.proceed(); //지정 클래스의 어떠한 메소드가 실행 시
			
			return obj;
		}finally {
			System.out.println("실행후:"+System.currentTimeMillis());
			
			System.out.println(signatureStr + "종료"); // 실행 후
		}
	}
}
```
- aop를 설정할 xml파일을 생성한다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop" <!-- aop의 namespace를 설정 -->
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd">
		
	<!-- 객체 생성
		xml: <bean   ==  <beans:bean id=
		jsp: <jsp:useBean
		java: Object obj = new Object
	
	 -->

	<!-- AOP Object 생성 -->
	
	<bean id="logAop" class="com.aop.LogAop"/>
	<!-- LogAop logAop = new LogAop() 와 같은 의미-->
	
	<!-- AOP 설정(자동호출) - 자동호출 함수를 callback함수라고 부른다 -->
	<aop:config>
		<aop:aspect id="logger" ref="logAop">  <!-- 감시자 생성 (위에 생성한 LogAop클래스)  -->
			<aop:pointcut expression="within(com.dto.*)" id="publicM"/>		<!-- 어디에 있는것을 감시할거냐 패키지 명 *는 모든 파일-->
			<aop:around method="loggerAop" pointcut-ref="publicM"/>			<!-- 감시하고 실행되는 함수명(여기서는 logAop클래스의 loggerAop메소드 실행 -->
		</aop:aspect>
	</aop:config>
</beans>
```
- dto의 메소드가 실행될때 aop가 처리되도록 설정하였다. dto를 생성하도록 한다.
```java
package com.dto;

public class Cat {

	private String name;
	private int age;
	private String color;
	
	public Cat() {
	}

	public Cat(String name, int age, String color) {
		super();
		this.name = name;
		this.age = age;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void catInfo() {
		System.out.println("이름:"+name);
		System.out.println("나이:"+age);
		System.out.println("색깔:"+color);
	}
}
```
- 그리고 xml 파일에서 Cat dto를 생성한다
```xml
<bean id="myCat" class="com.dto.Cat">
  <property name="name" value="야옹이"/>
  <property name="age" value="2"/>
  <property name="color" value="black"/>
</bean>
```  
- 이제 main 클래스에서 dto의 메소드를 실행해본다
```java
package com.main;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.dto.Cat;

public class mainclass {

	public static void main(String[] args) {
		// java에서 xml 실행시 필요함
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("bean.xml");
		
		//xml에서 생성된 Object를 read
		Cat mycat = ctx.getBean("myCat", Cat.class);
		
		mycat.catInfo();
		
	//	Cat youcat = new Cat("나비", 5, "흰색");	//생성만할때는 aop 호출 X
	//	youcat.catInfo();	//현재는 일반 자바main으로 실행이 안된다
	}
}
```
-결과
```
Cat.catInfo()시작
이름:야옹이
나이:2
색깔:black
실행후:1599219702567
Cat.catInfo()종료
```

### 2. Annotation으로 설정
- dto와 main 클래스는 동일하게 진행


- AOP설정하는 xml에서 Annotation을 사용하기위해 설정해 준다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:aop="http://www.springframework.org/schema/aop"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd">
	
	<!-- AOP Object 생성 -->
	
	<bean id="logAop" class="com.aop.LogAop"/>
	<!-- LogAop logAop = new LogAop() -->
	
	<!-- AOP Annotation 설정-->
	<aop:aspectj-autoproxy/>
  
</beans>
```
- java AOP클래스에서 어노테이션을 달아주고 설정해준다
```java
package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect	//어노테이션으로 aop를 사용하기위해 클래스에 설정
public class LogAop {

	@Around("within(com.dto.*)") //아까 xml의 around이며 실행할 callback함수에 붙여준다
	public Object loggerAop(ProceedingJoinPoint joinpoint)throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		
		System.out.println(signatureStr + " - 시작");	//실행 전
		
		try {
			Object obj = joinpoint.proceed(); //지정 클래스의 어떠한 메소드가 실행 시
			
			return obj;
		}finally {
			System.out.println("실행후:"+System.currentTimeMillis());
			
			System.out.println(signatureStr + "- 종료"); // 실행 후
		}
	}
}
```
- 결과는 위와 동일하다
