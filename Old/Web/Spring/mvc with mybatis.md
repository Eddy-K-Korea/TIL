# MVC with Mybatis

### 1. mybatis를 다운 받기 위해 pom.xml에 코드를 추가해 준다.
```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>sample03</groupId>
  <artifactId>sample03</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <packaging>war</packaging>
  <build>
    <sourceDirectory>src</sourceDirectory>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <plugin>
        <artifactId>maven-war-plugin</artifactId>
        <version>3.2.3</version>
        <configuration>
          <warSourceDirectory>WebContent</warSourceDirectory>
        </configuration>
      </plugin>
    </plugins>
  </build>
  
  <dependencies>
  	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-webmvc</artifactId>
	    <version>5.2.7.RELEASE</version>
	</dependency>
	
	<dependency>
	    <groupId>log4j</groupId>
	    <artifactId>log4j</artifactId>
	    <version>1.2.17</version>
	</dependency>
		
	<dependency>
	    <groupId>org.slf4j</groupId>
	    <artifactId>slf4j-simple</artifactId>
	    <version>1.7.25</version>
	</dependency>
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>servlet-api</artifactId>
		<version>2.5</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet.jsp</groupId>
		<artifactId>jsp-api</artifactId>
		<version>2.1</version>
		<scope>provided</scope>
	</dependency>
	<dependency>
		<groupId>javax.servlet</groupId>
		<artifactId>jstl</artifactId>
		<version>1.2</version>
	</dependency>
	
	<!-- 런타임에 동적으로 자바 클래스의 프록시(대리(인))를 생성해주는 기능을 제공한다 -->
	<dependency>
	    <groupId>cglib</groupId>		
	    <artifactId>cglib</artifactId>
	    <version>3.3.0</version>
	</dependency>
	<dependency>
	    <groupId>commons-digester</groupId>
	    <artifactId>commons-digester</artifactId>
	    <version>1.8</version>
	</dependency>
	
	<dependency>
  		<groupId>commons-logging</groupId>
  		<artifactId>commons-logging</artifactId>
  		<version>1.1.3</version>
  	</dependency>
  	
  	<!-- Ajax 사용 설정[jackson] -->
  	<dependency>
  		<groupId>org.codehaus.jackson</groupId>	
  		<artifactId>jackson-core-asl</artifactId>
  		<version>1.9.12</version>
  	</dependency>
  	<dependency>
  		<groupId>org.codehaus.jackson</groupId>
  		<artifactId>jackson-mapper-asl</artifactId>
  		<version>1.9.12</version>
  	</dependency>
  	
  	<!-- 의존성을 추가 --> 
  	<dependency>
  		<groupId>javax.inject</groupId>
  		<artifactId>javax.inject</artifactId>
  		<version>1</version>
  	</dependency>
  	
  	<!-- JCL(자카르타 커먼스 로깅)을 사용 --> 	
  	<dependency>
  		<groupId>org.slf4j</groupId>
  		<artifactId>jcl-over-slf4j</artifactId>
  		<version>1.7.30</version>
  	</dependency>

	<!-- XML 파싱 -->
  	<dependency>
	    <groupId>jdom</groupId>
	    <artifactId>jdom</artifactId>
	    <version>1.0</version>
	</dependency>
	
	<!-- 마이 바티스 스프링 사용시 -->
  	<dependency>
	    <groupId>org.mybatis</groupId>
	    <artifactId>mybatis-spring</artifactId>
	    <version>2.0.3</version>
	</dependency>
	
  	<dependency>
	    <groupId>org.mybatis</groupId>
	    <artifactId>mybatis</artifactId>
	    <version>3.5.3</version>
	</dependency>
	
	<!-- 마이 SQL 사용시 -->	
  	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>8.0.18</version>
	</dependency>
	
	<!-- XML 파싱 -->
  	<dependency>
	    <groupId>org.jdom</groupId>
	    <artifactId>jdom</artifactId>
	    <version>2.0.1</version>
	</dependency>

	<!-- SLF4J API를 사용하도록 -->
  	<dependency>
  		<groupId>org.slf4j</groupId>
  		<artifactId>slf4j-api</artifactId>
  		<version>1.7.30</version>
  	</dependency>


  	
  	<dependency>
	    <groupId>commons-dbcp</groupId>
	    <artifactId>commons-dbcp</artifactId>
	    <version>1.4</version>
	</dependency>
  	
  	<!-- Map을 Bean객체로 바꾸어주는 클래스 -->
  	<dependency>
  		<groupId>commons-beanutils</groupId>
  		<artifactId>commons-beanutils-core</artifactId>
  		<version>1.8.2</version>
  	</dependency>
  	
  	<!-- DBCP : DB Connection Poll 사용 --> 
  	<dependency>
  		<groupId>com.kenai.nbpwr</groupId>
  		<artifactId>org-apache-commons-dbcp</artifactId>
  		<version>1.2.2-201002241055</version>
  		<type>nbm</type>
  	</dependency>
  	
  	<!-- IO 기능 개발을 지원하는 유틸리티 라이브러리  다운안됨 -->
  	<dependency>
  		<groupId>org.apache.commons</groupId>
  		<artifactId>commons-io</artifactId>
  		<version>1.3.2</version>
  	</dependency>
  	
  	<dependency>
  		<groupId>org.apache.commons</groupId>
  		<artifactId>commons-lang3</artifactId>
  		<version>3.9</version>
  	</dependency>
  	
  	<dependency>
  		<groupId>org.apache.commons</groupId>
  		<artifactId>commons-pool2</artifactId>
  		<version>2.8.0</version>
  	</dependency>
  	<dependency>
  		<groupId>org.slf4j</groupId>
  		<artifactId>slf4j-log4j12</artifactId>
  		<version>1.7.30</version>
  	</dependency>
  	
  	<!-- spring -->
	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-aop</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-beans</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-context</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>  	
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-context-support</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>  	
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-core</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-expression</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-jdbc</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-orm</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-test</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-tx</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-web</artifactId>
  		<version>5.2.7.RELEASE</version>
  	</dependency>
  	
  	<!-- Java용 json 라이브러리(XML/YAM/CSV) data-processing 툴 -->
  	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-core</artifactId>
	    <version>2.10.1</version>
	</dependency>
	
	<dependency>
	    <groupId>com.fasterxml.jackson.core</groupId>
	    <artifactId>jackson-databind</artifactId>
	    <version>2.10.1</version>
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
		<dependency>
	    <groupId>com.oracle.database.jdbc</groupId>
	    <artifactId>ojdbc6</artifactId>
	    <version>11.2.0.4</version>
	</dependency>
	
	
	<dependency>
	    <groupId>org.json</groupId>
	    <artifactId>json</artifactId>
	    <version>20190722</version>
	</dependency>

 </dependencies>

	<repositories>
	  	<repository>
			<id>codelds</id>		<!-- ojdbc6 와 함께 추가 -->
			<url>https://code.lds.org/nexus/content/groups/main-repo</url>
		</repository>
	</repositories> 
</project>
```
### 2.web.xml 은 이전과 동일하고 servlet-context.xml의 경로와 applicationContext경로, listener가 제대로 설정 되어있는지 확인해 준다
```xml
<servlet>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<servlet-class>
  		org.springframework.web.servlet.DispatcherServlet
  	</servlet-class>
  	
  	<init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>
  			/WEB-INF/spring/servlet-context.xml <!-- 우리가 사용할 view의 확장자와 경로를 셋팅해주는 것 -->
  		</param-value>
  	</init-param>
  	
  	<load-on-startup>1</load-on-startup> <!-- 이것부터 읽어라 라는 표시  -->
 </servlet>
<servlet-mapping>
  	<servlet-name>dispatcherServlet</servlet-name>
  	<!-- <url-pattern>/</url-pattern> -->
  	<url-pattern>*.do</url-pattern>
  </servlet-mapping>
  
  <context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>
  		/WEB-INF/spring/applicationContext.xml
  	</param-value>
  </context-param>
  
  <listener>
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>
 ... 아래는 한글 인코딩
```  
### 3. 스프링에서 mybatis를 쓰기위해 spring 폴더에 applicationContext.xml을 spring bean xml 파일로 생성해준다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<!-- Database 설정 -->
	
	<!-- db Setting 위치 -->
	
	<bean id="propertyConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
		<property name="locations">
			<list>
				<value>classpath:properties/jdbc.properties</value>	<!-- src의 기본 경로는 classpath이다 -->
			</list>
		</property>
	</bean>
	
	<!-- DBMS  -->
	<!-- jdbc.properties에 적어준 변수명들을 넣어준다 value 값에 -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
		<property name="driverClassName" value="${jdbc.driverClassName}"/>
		<property name="url" value="${jdbc.url}"/>
		<property name="username" value="${jdbc.username}"/>
		<property name="password" value="${jdbc.password}"/>
		<property name="initialSize" value="${jdbc.initialSize}"/>
		<property name="maxActive" value="${jdbc.maxActive}"/>
		<property name="minIdle" value="${jdbc.initialSize}"/>
		<property name="maxWait" value="3000"/> <!-- 디비 응답 대기시간 3초 -->
		<property name="poolPreparedStatements" value="true"/>
		<property name="maxOpenPreparedStatements" value="50"/>
	</bean>
	
	<!-- mybatis setting -->
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="dataSource"/> <!-- ref 는 외부에서 가져온 값을 대입하여 참조하는것이다 -->
		<property name="mapperLocations" value="classpath:sqls/*.xml"/>
	</bean>
	
	<!-- sqlSession 취득 -->
	<bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
		<constructor-arg index="0" ref="sqlSessionFactory"/>
		<constructor-arg index="1" value="SIMPLE"/>				<!-- value="BATCH" 에러날 경우 둘중 하나-->
	</bean>
	
	<!-- jdbc -->
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="dataSource"/>	
	</bean>
	
</beans>
```
- sqlSessionFactory와 sqlSession을 생성해주고 dbms의 id를 아래 sqlSessionFactory 와 jdbc를 생성할때 참조로 넣어준다
- sqlSessionFactory에 적어준 ```<property name="mapperLocations" value="classpath:sqls/*.xml"/>```의 classpath에 mapper를 넣어준다

### 4. src 경로에 있는 jdbc.properties를 생성해준다. (DBMS생성시 값들을 적어준 곳)
```xml
# oracle setting

jdbc.driverClassName = oracle.jdbc.driver.OracleDriver
jdbc.url = jdbc:oracle:thin:@localhost:1521:xe
jdbc.username = hr
jdbc.password = hr
jdbc.initialSize = 5
jdbc.maxActive = 20
```
### 5.  sqlSessionFactory에 적어준 mapper를 생성
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="Member">
<select id="allMember" resultType="bit.com.a.dto.MemberDto">
	SELECT*FROM MEMBER
</select>
</mapper>
```

### 6. 이제 MVC를 만들기 위해 servlet-context.xml 에 mvc 코드를 추가한다
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:mvc="http://www.springframework.org/schema/mvc"
	xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd
		http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<!-- spring MVC annotation(주석문, 지시문)을 사용하기 위한 설정 -->
	<context:annotation-config />
	
	<!-- viewResolver 설정(사용자의 view의 위치, 확장자명) -->
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
		<property name="prefix" value="/WEB-INF/views/"></property>	<!-- view 경로 명 -->
		<property name="suffix" value=".jsp"></property>	<!-- 확장자명 -->
	</bean>
	
	
	<!-- 
		위의 내용을 자바로 표현하면 아래와 같다
		
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.prefix = "/WEB-INF/views/";
	viewResolver.suffix = ".jsp";
	
	 -->
	
	<!-- java 공통 패키지 -->
	<context:component-scan base-package="bit.com.a" />
	
	<!-- ajax 주석문 사용 허가 설정 -->
	<mvc:annotation-driven/>
	
	<!-- spring 에서 처리할 수 없는 요청은 tomcat에 위임 -->
	<mvc:default-servlet-handler/>
	
</beans>
```
![캡처2](https://user-images.githubusercontent.com/65350890/91842427-fea5a080-ec8e-11ea-9397-9976ab715c0c.PNG)

### 7. dao, service, controller 을 생성해준다. dao, service는 인터페이스를 생성해준다. 그리고 어노테이션을 그 역할에 맡게 생성해주어야한다

- controller
```java
package bit.com.a.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;


@Controller		//view로 이동 , service에서 data를 주고 받음
public class MemberController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired //컨트롤에 오고 service 생성
	MemberService memberService; 
	
	@RequestMapping(value = "allMember.do", method = RequestMethod.GET)
	public String allmember(Model model) {
		logger.info("allmember"+ new Date());
		List<MemberDto> list = memberService.allMember();
		model.addAttribute("memlist", list);
		return "allMember";
	}

}
```
- service interface
```java
package bit.com.a.service;

import java.util.List;

import bit.com.a.dto.MemberDto;

public interface MemberService {

	List<MemberDto> allMember();
}
```
- service 
```java
package bit.com.a.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bit.com.a.dao.MemberDao;
import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;

@Service	//controller와 dao 중간에 위치 ..... DB에서 가져온 값을 가공하는게 목적 (여러개의 데이터,list나 map으로 완성 그리고 컨트롤러 그리고 view로 전달)
public class MemberServiceimpl implements MemberService {

	@Autowired					//ioc
	MemberDao memberDao;		// MemberDao memberDao = new MemberDaoImpl(); 과 동일
	
	@Override
	public List<MemberDto> allMember() {
		return memberDao.allMember();
	}
}
```
- dao interface
```java
package bit.com.a.dao;

import java.util.List;

import bit.com.a.dto.MemberDto;

public interface MemberDao {

	List<MemberDto> allMember();
}
```
- dao
```java
package bit.com.a.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bit.com.a.dao.MemberDao;
import bit.com.a.dto.MemberDto;

@Repository	// = 저장소
public class MemberDaoimpl implements MemberDao {

	@Autowired	//DI 쿼리문 접근을 위해 
	SqlSession sqlSession;     // SqlSession sqlSession = new SqlSessiontemplate(); 상속을 받은 클래스를 자동 생성 (1단계)
	
	String namespace = "Member.";  //sqls의 xml파일의 경로
	
	@Override
	public List<MemberDto> allMember() { 
		List<MemberDto> list = sqlSession.selectList(namespace + "allMember"); //sqls의 경로의 mapper의 id 를 적어줌으로써 어떤 쿼리문을 읽을지 지정
		return list;
	}
}

```

### 8. 다오를 거쳐 db의 값을 가지고 다시 service를 거쳐 컨트롤러 가고 return을 통해 jsp 파일(view)로 가게된다
```html
<%@page import="bit.com.a.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
List<MemberDto> list = (List<MemberDto>)request.getAttribute("memlist");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
for(int i = 0; i < list.size(); i++){
	out.println(list.get(i).toString()+"<br>");
}
%>

</body>
</html>
```
