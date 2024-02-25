# 스프링 부트

### REST
- Representational State Transfer :  자원을 이름으로 구분하여 해당 자원의 상태를 주고 받는 모든 것

<br>
- 자원
<br>
1. 이미지 
2. 데이터(byte, String)          --->  자원의 표현(Json) "Key": value 의 형태로 날려준다<br>
= Web의 장점을 최대한 활용 할 수 있는 아키텍쳐 스타일



![캡처](https://user-images.githubusercontent.com/65350890/93596202-eace9e00-f9f3-11ea-8427-555cbb0c556b.PNG)

### 부트 목적
- 완전히 백과 프론트를 구분 짓는다
- 백엔드를 독립적으로 사용하기 위해 (REST ful) (MVC방식을 사용하는것은 목적과 다를 수 있다) 그래서 타임리프사용 

## pom.xml 설정

1.  spring boot starter web 붙여넣기 하고 버전을 지워준다
2. Spring Boot Starter Tomcat 서버를 추가
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.4.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.example</groupId>
	<artifactId>SpringBootTest</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>SpringBootTest</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
		
    <!-- 추가된 부분 -->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-tomcat</artifactId>
		</dependency>
		
		<!-- jdbc -->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-jdbc</artifactId>
		</dependency>
		<!-- mybatis -->
		<dependency>
		    <groupId>org.mybatis.spring.boot</groupId>
		    <artifactId>mybatis-spring-boot-starter</artifactId>
		    <version>1.3.2</version>
		</dependency>
		
		<dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis-spring</artifactId>
           <version>1.3.2</version>
        </dependency>  
           
        <dependency>
           <groupId>org.mybatis</groupId>
           <artifactId>mybatis</artifactId>
           <version>3.4.0</version>
        </dependency>
        
        <!-- 자바용 json 라이브러리(XML/YAML/CSV) data-processing 툴 --> 
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
                           
      <!-- ojdbc6 위의 경로(Repository)를 추가해 줄것-->
      <dependency>
          <groupId>com.oracle.database.jdbc</groupId>
          <artifactId>ojdbc6</artifactId>
          <version>11.2.0.4</version>
      </dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```
3. 서버 포트넘버 충돌로 application.properties에서 포트를 변경해준다
```

# port
server.port=3000


#DB
spring.datasource.driver-class-name=oracle.jdbc.driver.OracleDriver
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=hr
spring.datasource.password=hr
```
4. ajax 할때 보안을 허가해주어야한다. WebMvcConfigurer 상속받은 클래스 WebConfig 생성 (addCorsMappings 오버라이드)
5. @Configuration 달아주고 오버라이드 해준다
```java
package bit.com.a;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration	//설정파일로 지정을 해주어서 이것부터 읽는다
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		//WebMvcConfigurer.super.addCorsMappings(registry);
		
		registry.addMapping("/**").allowedOrigins("http://localhost:8090"); //현재 해당 주소에서 들어오는것을 허가해준다. /**는 모든 주소를 허용한다라는 의미
	}
}
```
## 이렇게 설정후 view쪽 ajax와 서로 값을 주고 받을 수 있게 된다 (프론트)
### 예를 들어
- view (이클립스로 서버를 따로 돌려서 뷰만 표현)
![image](https://user-images.githubusercontent.com/65350890/93600101-18b6e100-f9fa-11ea-80fa-e95b64cc58ee.png)

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<p id="demo"></p>

<button type="button">click</button>

<script type="text/javascript">
$(document).ready(function(){
	$("button").click(function(){
		//alert("click");
		/*
		$.ajax({
			url:"http://localhost:3000/test",
			type:"get",
			success:function(data){
			//	alert('success');
				//alert(data);
				$("#demo").text(data);
				
			},
			error:function(){
				alert('error');		//보안에 걸려서 에러가 난다. 그래서 허가를 내주어야 한다
			}
		});
		*/
		/*
		$.ajax({
			url:"http://localhost:3000/member",
			type:"get",
			success:function(data){	//자동 적으로 json으로 넘어간다
			//	alert('success');
			//	alert(data);

				$("#demo").append(data.id + "<br>");
				$("#demo").append(data.pwd + "<br>");
				$("#demo").append(data.name + "<br>");
				$("#demo").append(data.email + "<br>");
			},
			error:function(){
				alert('error');	
			}
		});
		*/
		/*
		$.ajax({
			url:"http://localhost:3000/dbtest",
			type:"get",
			success:function(data){
				//alert('success');
				
			//	alert(JSON.stringify(data));	//json을 string으로
			
				$.each(data, function(i,item){
					$("#demo").append(item.id+" ");
					$("#demo").append(item.pwd+" ");
					$("#demo").append(item.name+" ");
					$("#demo").append(item.email+"<br>");
				});
			},
			error:function(){
				alert('error');	
			}
		});
		*/
		/*
		$.ajax({
			url:"http://localhost:3000/conn_param",
			type:"get",
			//data:'title='+"제목입니다", 이것도 되고
			data:{title:"두번째 테스트"},
			success:function(data){
				//alert('success');
				
				alert(data);
				
			},
			error:function(){
				alert('error');	
			}
		});
		*/
		$.ajax({
			url:"http://localhost:3000/conn_param_post",
			type:"post",
			data:{id:"abc", pwd:"abc",name:'김두한'},
			success:function(data){
				alert('success');
				
				//alert(data);
				
			},
			error:function(){
				alert('error');	
			}
		});
	});
});
</script>
</body>
</html>
```
### 백엔드 
- 어노테이션에 그냥 컨트롤러가 아닌 RestController를 
```java
package bit.com.a.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bit.com.a.dto.MemberDto;
import bit.com.a.service.MemberService;

@RestController		// == @Controller + @Responsbody   ->  Restful
public class HelloController {

	@Autowired
	MemberService service;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() throws Exception{
		System.out.println("test()");
		return "test";
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public MemberDto getMember() {
		System.out.println("getMember()");
		MemberDto dto = new MemberDto("aaa", "111", "홍길동", "aaa", 3);
		
		return dto;
	}
	
	@RequestMapping(value = "/dbtest", method = RequestMethod.GET)
	public List<MemberDto> dbtest(){
		System.out.println("dbtest()");
		List<MemberDto> list = service.allMember();
		
		return list;
	}
	@RequestMapping(value = "/conn_param", method = RequestMethod.GET)
	public String conn_param(String title) {	//http://localhost:3000/conn_param?title=xxx
		System.out.println("conn_param()");
		System.out.println("title:"+title);
		return "conn success";
	}
	@RequestMapping(value = "/conn_param_post", method = RequestMethod.POST)
	public String conn_param_post(MemberDto dto) {
		System.out.println("conn_param_post()");
		System.out.println(dto.toString());
		return "YES";
	}
}
```
## DB 연동은 아래와 같다
### 1. configration으로 클래스 생성 셋팅
```java
package bit.com.a;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class DatabaseConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception {
		System.out.println("DatabaseConfig SqlSessionFactory");
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("classpath:sqls/*.xml");
		sqlSessionFactoryBean.setMapperLocations(arrResource);
		sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
		
		return (SqlSessionFactory)sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
```

### 2.  application.properties에서 DB 주소 입력
- 아까 application.properies 참고

### 3. application클래스에서 mapperscan 추가
```java
package bit.com.a;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "bit.com.a")
public class SpringBootTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}

}
```
#### 그리고 sqls 폴더 생성후 xml 파일 생성은 동일하나 약간 다른점은 namespace부분이다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="bit.com.a.dao.MemberDao">	<!-- 대상을 정확하게 적어준다 -->

<select id="allMember" resultType="bit.com.a.dto.MemberDto">	<!-- ID 명과 dao의 메소드 명이 동일 해야한다 -->
	SELECT* FROM MEMBER
</select>
</mapper> 
```

### dao는 인터페이스를 생성하고 이전 spring 에서 처럼 인터페이스를 상속받는 클래스는 생성하지 않고 위에 적어준 namespace를 따라가기 때문에 메소드만 선언한다
```java
package bit.com.a.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import bit.com.a.dto.MemberDto;

@Mapper			//인터페이스에 어노테이션을 만들어준다
@Repository
public interface MemberDao {

	public List<MemberDto> allMember();		//xml의 id값과 동일
		
	
}
```
### service는 인터페이스를 생성하지 않고 바로 클래스를 생성하고 Transactional 어노테이션을 추가한다
```java
package bit.com.a.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bit.com.a.dao.MemberDao;
import bit.com.a.dto.MemberDto;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberDao dao;
	
	public List<MemberDto> allMember(){
		return dao.allMember();
	}
}
```
