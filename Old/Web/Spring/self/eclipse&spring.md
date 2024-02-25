# 이클립스와 스프링 연동

- DI.md에서 자바를 통해 구현 하였던 예제의 main
```java
    Exam exam = new NewlecExam();
		ExamConsole console = new GridExamConsole();
		
		console.setExam(exam);
```    
- 위의 코드들을 spring을 통해 외부에서 설정하고 실행하도록 한다

1. 이클립스 마켓에서 Spring을 다운 받는다.
2. spring.di  패키지에 spring bean configuration file을 생성한다 (setting.xml)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
	<!-- Exam exam = new NewlecExam();  이것을 진행 해야한다 아래 패키지 형을 전부 넣어준다 (중복방지)-->
	<bean id ="exam" class="spring.di.emtity.NewlecExam"></bean>
  
	<!-- ExamConsole console = new GridExamConsole(); -->
	<bean id="console" class="spring.di.ui.InlineExamConsole">
  
		<!-- console.setExam(exam); setExam을 아래 name에 set을 제거하고 소문자 시작으로 넣어준다 -->
		<property name="exam" ref="exam"></property>
		<!-- 그다음  설정(조립)해야할 객체의 이름을 넣어줄 수 있다. value (값 형) 또는 ref (레퍼런스형)  -->
	</bean>
</beans>
```
3. 해당 자바 프로젝트를 maven 프로젝트로 변경해준다.
4. 이제 메인에서 setting.xml을 읽어올 인터페이스 ApplicationContext 를 사용하기위해 라이브러리가 필요하여 maven빌더를 통해 불러온다.
5. pom.xml에 spring 라이브러리 경로를 추가해준다
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.newlecture</groupId>
  <artifactId>spring</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  
  <!-- 스프링프레임워크 라이브러리 url을 추가한 부분 -->
  <dependencies>
  	<!-- https://mvnrepository.com/artifact/org.springframework/spring-context -->
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>5.2.6.RELEASE</version>
	</dependency>
  	
  </dependencies>
  
  <build>
    <sourceDirectory>src</sourceDirectory>
    <resources>
      <resource>
        <directory>src</directory>
        <excludes>
          <exclude>**/*.java</exclude>
        </excludes>
      </resource>
    </resources>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.0</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```
6. 메인에 Application 인터페이스를 import하고 생성한다. 그리고 classPathXmlApplicationContext 클래스로 생성한다.
![캡처](https://user-images.githubusercontent.com/65350890/91636281-4f708b80-ea3a-11ea-887a-0875aaac7ec8.PNG)
- classPathXml은 클래스파일의 경로를 찾아갈때 사용
- FileSystemXml은 현재 로컬 저장소의 경로를 찾아갈때(예시 : C://temp...)
- XmlWeb은 웹 서버의 경로로 찾아갈때 사용
- AnnotationConfig는 어노테이션을 찾아갈때 추후 학습예정

7. 이제 setting.xml의 경로를 넣어주면 그안의 설정된 객체들을 읽어준다
```java
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/di/setting.xml"); //setting.xml의 패키지 경로까지 적어준다
		
		ExamConsole console = (ExamConsole)context.getBean("console"); // 이렇게 생성해주는 툴이 스프링이다 
		//ExamConsole console = context.getBean(ExamConsole.class); 이 방식을 더 보편적으로 사용(형변환하지 않고 바로 사용)
        //setting.xml안에는 <bean id="console" class="spring.di.ui.GridExamConsole">
		                          <property name="exam" ref="exam"></property>
	                         </bean>
                           으로 되어있다. 자세히 보면 아래 코드ㅡ의 내용이 들어가 있는것을 알 수 있다.(id = 인터페이스 이름)(class = 생성한 객체)(property = 조립한 exam 클래스(total,avg))
    
    == Exam exam = new NewlecExam();
		   ExamConsole console = new GridExamConsole();
    
    
    
		console.print();
```
- .getBean("setting.xml에서 실행할 객체(bean)의 id(이름)")을 넣어주면 그안에서 설정된 값들을 읽어온다.






