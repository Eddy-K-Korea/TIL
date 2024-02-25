# Table의 무결성(CONSTRIANT)

### - 무결성이란? 
#### column의 값을 지정하는 성질을 설정

### 종류
#### 1. Primary Key (기본키) : Null을 허용하지 않음.(반드시 값을 넣어야한다), 중복을 허용하지 않는다.(회원 ID, 주민번호)

#### 2. Unique Key (고유키) : Null을 허용한다. 중복을 허용하지 않음.(E-mail)

#### 3. Foreign Key (외래키) : 테이블과 테이블을 연결하는 목적의 성질이다.(JOIN의 목적이다)(Null을 허용)(JOIN되는 테이블에 값이 있어야함)<br> 외래키로 설정된 column은 연결된 테이블에서 PK나 UK로 설정되어 있어야 한다.
                         
#### 4. CHECK : 범위를 설정. NULL을 허용

#### 5. NOT NULL : null을 허용하지 않는다.
---
## Primary Key (기본키)
- 식별자, 한 테이블에서 최대 32개 컬럼 까지 지정할 수 있다.
- = Unique + Not null
### 생성방법 1
```sql
CREATE TABLE TEST_01(
    PKCOL VARCHAR2(10) CONSTRAINT PK_TEST_01 PRIMARY KEY, 
   ★ PK_TEST_01 는 사용자 마음대로 설정 가능( 나중에 성질을 삭제할때 이 이름을 사용해야한다)
    COL1 VARCHAR2(20),
    COL2 VARCHAR2(30)
);
```
### 생성방법 2
```sql
CREATE TABLE TEST_01(
    PKCOL VARCHAR2(10)PRIMARY KEY, 
    ★  PK_TEST_01 로 설정을 안하고 생성할 경우 시스템에서 임의로 이름을 붙여주고 생성한다.
    COL1 VARCHAR2(20),
    COL2 VARCHAR2(30)
);
```
### 생성방법 3
다중 기본키를 지정할때 아래와 같이 해야한다.
```sql
CREATE TABLE TEST_01(
    PKCOL VARCHAR2(10), 
    COL1 VARCHAR2(20),
    COL2 VARCHAR2(30),
    CONSTRAINT PK_TEST_01 PRIMARY KEY(PKCOL,COL1)           -- 최대 32개 까지 지정 가능
);
```

만약 아래와 같이 구현을 하면 에러가 난다.
```sql
CREATE TABLE TEST_01(
    PKCOL VARCHAR2(10) CONSTRAINT PK_TEST_01 PRIMARY KEY, 
    COL1 VARCHAR2(20)CONSTRAINT PK_TEST_02 PRIMARY KEY,
    COL2 VARCHAR2(30)
);
```
### 생성 방법 4 (실무)
```sql
CREATE TABLE TEST_01(
    PKCOL VARCHAR2(10), 
    COL1 VARCHAR2(20),
    COL2 VARCHAR2(30)
);

ALTER TABLE TEST_01
ADD CONSTRAINT PK_TEST_01 PRIMARY KEY(PKCOL,COL1);
```

만약 기본키 설정을 해제하기 위해서는 아래와 같이 소스코드를 기재하면된다.
```sql
ALTER TABLE TEST_01
DROP CONSTRAINT PK_TEST_01
```

사용자가 지정하였던 Primary Key 이름을 사용해서 설정을 해지한다.

---
## UNIQUE Key
- 고유키 . 중복된 값은 입력불가. NULL은 허용
### 생성 방법
```sql
CREATE TABLE TEST_02(
    UKCOL VARCHAR2(10) CONSTRAINT UK_TEST_02 UNIQUE,
    COL_01 VARCHAR2(20),
    COL_02 VARCHAR2(30)
);
``` 
방법은 Primary Key 생성과 동일하며 끝에 UNIQUE만 붙여주면 된다.

### 고유키 설정 해지
```sql
ALTER TABLE TEST_02
DROP CONSTRAINT UK_TEST_02;
```

---
## FOREIGN KEY (외래키)
- 테이블과 테이블을 연결하기 위한 무결성 제약 조건이다.
- 다른 테이블(부모테이블)에서는 PK, UK로 컬럼이 설정되어 있어야한다.
- NULL 허용

#### 우선 외래키는 JOIN을 하기위한 목적으로 설정되는 경우가 많다. 따라서 우선 기본키, 고유키를 가진 테이블을 하나 우선 생성하여 활용해 보겠다.
 1. Table 1 (PK)
```sql
CREATE TABLE TB_PARENT(
    PKCOL01 VARCHAR2 (10),
    COL_01 VARCHAR2 (20),
    COL_02 VARCHAR2 (30),
    CONSTRAINT PK_TB_PARENT PRIMARY KEY(PKCOL01)
);
```
TB_PARENT 에 값을 넣어주는 코드
```sql
INSERT INTO TB_PARENT(PKCOL01,COL_01,COL_02)
VALUES('AAA','aaa','가가가');

INSERT INTO TB_PARENT(PKCOL01,COL_01,COL_02)
VALUES('BBB','bbb','나나나');

INSERT INTO TB_PARENT(PKCOL01,COL_01,COL_02)
VALUES('CCC','ccc','다다다');
```
 2. Table 2 (FK)
```sql
CREATE TABLE TB_CHILD(
    KEY_01 VARCHAR2(10),
    KEY_02 VARCHAR2(20),
    FKCOL01 VARCHAR2(10),
    CONSTRAINT FK_TB_CHILD FOREIGN KEY(FKCOL01)
    REFERENCES TB_PARENT(PKCOL01)
);
```
*※ 외래키를 가진 테이블을 생성시 PK를 가진 테이블의 PK가 설정된 컬럼의 용량, 자료형태는 FK가 설정된 컬럼과 동일하게 진행 되어야 한다.*

*한 먄약 PARENT 테이블에 기본키나 고유키가 없으면 생성시 에러가 난다.*

TB_CHILD 에 값을 넣어주는 코드
```sql
INSERT INTO TB_CHILD(KEY_01,KEY_02,FKCOL01)
VALUES('123','가나다','BBB');

INSERT INTO TB_CHILD(KEY_01,KEY_02,FKCOL01)
VALUES('456','라마바','');
```
*만약 TB_PARENT에 없는 값을 TB_CHILD 테이블에 값을 넣으려고 하면 에러가 난다.*

### 외래키를 사용한 목적( JOIN )
```sql
SELECT C.KEY_01, C.KEY_02, C.FKCOL01,P.PKCOL01, P.COL_01,P.COL_02
FROM TB_CHILD C , TB_PARENT P
WHERE C.FKCOL01 = P.PKCOL01;
```
JOIN을 하여 값을 구할 수 있는것을 확인 할 수 있다.

- 123	가나다	BBB	BBB	bbb	나나나

---
## CHECK
- 지정된 값(범위)만 입력할 수 있고 NULL을 허용, 중복 허용
```SQL
CREATE TABLE TB_CHECK(
    COL_01 VARCHAR2(10),
    KEY_01 VARCHAR2(20),
    CONSTRAINTS CHK_TB_CHECK01 CHECK(COL_01 IN('사과','배','바나나')),
    CONSTRAINTS CHK_TB_CHECK02 CHECK(KEY_01 > 0 AND KEY_01 <= 100)
);
```
위 테이블은 두개의 컬럼을 가지고 있고 COL_01 은 '사과', '배', '바나나' 중 하나 선택하여 데이터를 넣을 수 있다.

또한 KEY_01은 값의 범위를 지정하여 0부터 100까지의 숫자만 넣을 수 있다.

중복이 가능하며, NULL값을 넣을 수 있다.

---
## NOT NULL
```SQL
CREATE TABLE TB_TEST(
    COL_01 VARCHAR2(10) NOT NULL,
    COL_02 VARCHAR2(20)
);
```
테이블 생성시 NOT NULL을 지정하게 되면 해당 컬럼은 NULL값을 넣을 수 없고 무조건 값을 넣어 주어야 한다.






