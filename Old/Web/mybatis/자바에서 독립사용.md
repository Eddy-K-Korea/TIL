# 마이바티스 (독립프레임워크로 사용)

## mybatis를 이용하여 db 접근 하기
![캡처1](https://user-images.githubusercontent.com/65350890/91830462-c6e22d00-ec7d-11ea-9573-7a423186ad3a.PNG)

### 1. mybatis와 jdbc 라이브러리를 받아준다.
![캡처](https://user-images.githubusercontent.com/65350890/91828034-9cdb3b80-ec7a-11ea-9f0c-d2144d362482.PNG)

### 2. config.xml을 생성해준다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
  
 <!-- 마이바티스  클래스 설정 -->
<configuration>
	<environments default="development">	<!-- 관리를 하는 설정 default값을 지정 -->
		<environment id="development">
			<transactionManager type="JDBC"/>	<!-- 어떤 디비 인지 설정 -->
			<dataSource type="POOLED"> <!-- database안에 있는 공간 설정 -->
				<property name="driver" value="oracle.jdbc.driver.OracleDriver"/>
				<property name="url" value="jdbc:oracle:thin:@localhost:1521:xe"/>
				<property name="username" value="hr"/>
				<property name="password" value="hr"/>
			</dataSource>
		</environment>
	</environments>

	<mappers>
		<mapper resource="mybatis/MemberMapper.xml"/>
	</mappers>
</configuration>
```

### 3. main에서 실행을 시켜줄때 가장 먼저 실행을 해주는 xml 파일을 접근하고 불러온다. 
```java
package main;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import dto.BbsDto;
import dto.Bbsparam;
import dto.MemberDto;

public class mainClass {

	public static void main(String[] args) throws Exception {

		// mybatis 설정 파일(config.xml)을 읽어 들인다.
		InputStream is = Resources.getResourceAsStream("mybatis/config.xml");
		
		//sqlSessionFactory 객체를 취득
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		
		//sqlSession 객체를 취득 <- 실질적으로 접근할 객체
		SqlSession session = factory.openSession();
   }
}   
```    
### 4. sqlSession이 오픈되고 session을 통해 이제 mapper의 쿼리문을 읽는다.
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
  PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  
<mapper namespace="mybatis.MemberMapper">

<!--  쿼리문 형식
	<insert , delete, select , update
		parameterType="dto"
		resultType="dto">  select 는 resultType을 반드시 적어주어야한다
		
		Query #{외부로부터 들어 오는 값} - core tag도 사용 가능
			  
	</ insert , delete, select , update

 -->
  <!-- insert -->
	<insert id="add" parameterType="dto.MemberDto">	<!-- parameterType은 패키지 명부터 객체를 넣어야한다 -->
		INSERT INTO MEMBERDTO(ID, PWD, EMAIL)
		VALUES(#{id},#{pwd},#{email})	<!-- class에 적혀진 변수명 과 동일하게 매칭시켜야한다  -->
	</insert>
	
	<!-- delete -->
	<delete id="delete" parameterType="String"> <!-- String 으로 넣어도 되고 java.lang.String 도 된다-->
		DELETE FROM MEMBERDTO
		WHERE ID = #{id}
	</delete>
	
	<!-- update -->
	<update id="update" parameterType="dto.MemberDto">
		UPDATE MEMBERDTO
		SET PWD = #{pwd}
		WHERE ID = #{id}
	</update>


	<!-- select 데이터 한개 -->
	<select id="find" parameterType="java.lang.String" resultType="dto.MemberDto">
		SELECT * FROM MEMBERDTO
		WHERE ID=#{id}
	</select>
	
	<!-- select 다수 데이터  -->
	
	<select id="allData" resultType="dto.MemberDto">  <!-- 다수일 경우 resultType만 있다 -->
		SELECT * FROM MEMBERDTO
	</select>
	
	<!-- bbs select  조건문 예제-->
	
	<select id="search" parameterType="dto.Bbsparam" resultType="dto.BbsDto"> <!-- 값을 받을때 Bbsparam의 변수에 있는 s_category와 keyword를 사용하고 값을 return할때 BbsDto에 넣는다-->
		SELECT * FROM BBS
		WHERE 1=1
		<if test="s_category == 'title'">
			AND TITLE LIKE '%'||#{keyword}||'%'
		</if>
		<if test="s_category == 'content'">
			AND CONTENT LIKE '%'||#{keyword}||'%'
		</if>
	
	</select>
	
	<select id="choice" parameterType="dto.Bbsparam" resultType="dto.BbsDto">
		SELECT * FROM BBS
		WHERE 1=1
		<choose>
			<when test="s_category == 'title'">		<!-- if 문 -->
				AND TITLE LIKE '%'||#{keyword}||'%'
			</when>
			<otherwise>								<!-- else 문 -->
				AND CONTENT LIKE '%'||#{keyword}||'%'
			</otherwise>
		</choose>
	</select>
	
	<select id="pick" parameterType="dto.Bbsparam" resultType="dto.BbsDto">
		SELECT * FROM BBS
		WHERE 1=1
		<choose>
			<when test="s_category == 'title'">		<!-- if 문 -->
				AND TITLE LIKE '%'||#{keyword}||'%'
			</when>
			<when test="s_category == 'content'">		<!-- if 문 -->
				AND CONTENT LIKE '%'||#{keyword}||'%'
			</when>
			<when test="s_category == 'id'">		<!-- if 문 -->
				AND ID = #{keyword}
			</when>
		</choose>
	</select>
</mapper>
```
### 5. main에서 mapper에 설정한 쿼리문을 사용하여 db에 값을 넣고 불러 올 수 있다.
```java
//  insert
MemberDto dto = new MemberDto("aaa","111","aaa@aaa");
//MemberDto dto = new MemberDto("bbb","222","bbb@bbb");
//MemberDto dto = new MemberDto("ccc","333","ccc@ccc");
int n = session.insert("add", dto);		//앞의 문자열은 아까 mapper에서 지정한 id값 그리고 뒤에는 insert할 객체

if(n>0) {
  session.commit();
  System.out.println("추가성공");
}else {
  session.rollback();
  System.out.println("추가실패");
}

// delete
int n = session.delete("delete","aaa");
if(n>0) {
  session.commit();
  System.out.println("삭제성공");
}else {
  session.rollback();
  System.out.println("삭제실패");
}

// update
MemberDto dto = new MemberDto("aaa","222","aaa@aaa"); 
int n = session.update("update", dto);
if(n>0) {
  session.commit();
  System.out.println("변경성공");
}else {
  session.rollback();
  System.out.println("변경실패");
}


//select
// 한개 데이터
String id = "ccc";
MemberDto dto = session.selectOne("find", id);
System.out.println(dto.toString());

// 다수 데이터
List<MemberDto> list = session.selectList("allData");
for (MemberDto m : list) {
  System.out.println(m.toString());
}
/*
bbs 검색
category : 제목, 내용, 작성자
keyword : 검색어
*/
// s_category에 title이고 keywor는 코로나 로 설정하여 검색 그리고 bbsdto로 받아서 해당하는 값 전부 출력
Bbsparam search = new Bbsparam("title", "코로나");
List<BbsDto> slist = session.selectList("search", search);
for (BbsDto bbs : slist) {
  System.out.println(bbs.toString());
}

//id가 choice인 쿼리문을 진행
Bbsparam search = new Bbsparam("content", "입니다");
List<BbsDto> slist = session.selectList("choice", search);
for (BbsDto bbs : slist) {
  System.out.println(bbs.toString());
}

//id가 pick인 쿼리문으로 진행
Bbsparam search = new Bbsparam("id", "aaa");
List<BbsDto> slist = session.selectList("pick", search);
for (BbsDto bbs : slist) {
  System.out.println(bbs.toString());
}

```
#### 해당 구현에 사용된 dto
1. MemberDto
```java
package dto;

import java.io.Serializable;
/*
  CREATE TABLE MEMBERDTO(
	ID VARCHAR2(50) NOT NULL,
	PWD VARCHAR2(50) NOT NULL,
	EMAIL VARCHAR2(50) NOT NULL
);
  
*/

public class MemberDto implements Serializable {
	private String id;
	private String pwd;
	private String email;
	
	public MemberDto() {
	}
	
	

	public MemberDto(String id, String pwd, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pwd=" + pwd + ", email=" + email + "]";
	}
}
```
#### 2. Bbsparam
```java
package dto;

import java.io.Serializable;

public class Bbsparam implements Serializable {

	private String s_category;
	private String keyword;
	
	public Bbsparam() {
	}

	public Bbsparam(String s_category, String keyword) {
		super();
		this.s_category = s_category;
		this.keyword = keyword;
	}

	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
```
#### 3. BbsDto
```java
package dto;

import java.io.Serializable;
/*
DROP TABLE BBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_BBS;

CREATE TABLE BBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_BBS
START WITH 1
INCREMENT BY 1;

ALTER TABLE BBS
ADD CONSTRAINT FK_BBS_ID FOREIGN KEY(ID)
REFERENCES MEMBER(ID);
*/
// BBS: Bulletin Board System (게시판)
public class BbsDto implements Serializable {

	private int seq;		// sequence
	private String id;		// 작성자
	
	private int ref;		// 그룹번호
	private int step;		// 행(row)번호
	private int depth;		// 깊이
	
	private String title;	// 제목
	private String content;	// 내용
	private String wdate;	// 작성일
	
	private int del;		// 삭제
	private int readcount;	// 조회수
	
	public BbsDto() {
	}

	public BbsDto(int seq, String id, int ref, int step, int depth, String title, String content, String wdate, int del,
			int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
	}

	public BbsDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		return "BbsDto [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", title="
				+ title + ", content=" + content + ", wdate=" + wdate + ", del=" + del + ", readcount=" + readcount
				+ "]";
	}
}
```

