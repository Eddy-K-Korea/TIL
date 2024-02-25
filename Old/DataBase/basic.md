# Oracle 기본 문법
## 1. 자료형
```
   java             oracle 
   
    int             Integer, NUMBER
    double          NUMBER(정수의 자리수, 소수의 자리수)
    String          VARCHAR2, CHAR
    Date            DATE
```

- 우선 자료형을 확인하기 위해 테이블 생성과 값을 넣어주는 기본적인 예약어를 알아보자
```
create table 테이블명(
    컬럼명1 자료형,
    컬럼명2 자료형,
    컬럼명3 자료형
);
```
위 소스코드는 테이블을 생성하는 기본적인 형태이다.

```
INSERT INTO 테이블명(컬럼명, 컬럼명, 컬럼명...)
    VALUSE(값, 값, 값...);
```

위의 소스코드는 칼럼에 값을 넣는 형태이다.

### 문자열 CHAR
고정길이의 문자열을 저장하는 
```
create table TB_CHAR(
    COL_CHAR1 CHAR(10 BYTE),
    COL_CHAR2 CHAR(10 CHAR),
    COL_CHAR3 CHAR(10)
);
```
```
INSERT INTO tb_char(COL_CHAR1, COL_CHAR2, COL_CHAR3)
VALUES('ABC', 'ABC', 'ABC');    ★ 영문자 1문자의 크기 1 BYTE
```
```
SELECT * FROM tb_char;  ★ * 모든이라는 의미 / 테이블 으로부터 *(모든자료를) 검색하라
```
tb_char의 각 칼럼에 'ABC'가 들어가 있는것을 확인 할 수 있다.
```
INSERT INTO tb_char(COL_CHAR1, COL_CHAR2, COL_CHAR3)
VALUES('가', '나', '다');  ★ 한글의 한문자의 크기 3 BYTE
```
```
INSERT INTO tb_char(COL_CHAR1, COL_CHAR2, COL_CHAR3)
VALUES('가나', '가나', '가나');
```
```
INSERT INTO tb_char(COL_CHAR1, COL_CHAR2, COL_CHAR3)
VALUES('가나다', '가나다', '가나다');
```
위의 3개의 크기를 확인해 보면
```
SELECT COL_CHAR1, COL_CHAR2, COL_CHAR3, LENGTHB(col_char1),LENGTHB(col_char2),LENGTHB(col_char3) FROM tb_char;
```
CHAR : 10 BYTE 00000 00000 <br>
ABC ->         ABC00 00000<br>

가(3BYTE) + 9 = 12<br>
가나(6BYTE) + 8 = 14<br>
가나다(9BYTE) + 7 = 16<br>

으로 메모리에 저장이 되는것을 알 수 있다.

### 문자열 VARCHAR2
CHAR 와는 다른 문자열로 가변길이만큼 저장이 된다.
```
create table TB_VARCHAR2(
    COL_CHAR1 VARCHAR2(10 BYTE),
    COL_CHAR2 VARCHAR2(10 CHAR),
    COL_CHAR3 VARCHAR2(10)
);
```
```
INSERT INTO TB_VARCHAR2(COL_CHAR1, COL_CHAR2, COL_CHAR3)
VALUES('ABC', 'ABC', 'ABC');
```
```
INSERT INTO TB_VARCHAR2(COL_CHAR1, COL_CHAR2, COL_CHAR3)
VALUES('가나다', '가나다', '가나다');
```
```
SELECT COL_CHAR1, COL_CHAR2, COL_CHAR3, LENGTHB(col_char1),LENGTHB(col_char2),LENGTHB(col_char3) FROM TB_VARCHAR2;
```
각 문자열의 길이만큼 byte가 사용됨을 알 수 있다.

### Long 
LONG 은 VARCHAR2의 최대크기 제한보다 큰 텍스트 문자열을 저장 할 때 사용 하며 최대 2GB까지 저장 가능  

단, 몇가지 조건이 있다.<br>
  한 테이블에 오직 하나의 LONG 컬럼이 허용 <br>
  LONG 형 컬럼은 인덱스 불가<br>
  LONG 형 컬럼은 무결성 제약 조건에 사용 불가<br>
  LONG 형 컬럼은 SELECT 문에서 WHERE, GROUP BY, ORDER BY, CONNECT BY, DISTICNT와 사용 불가<br>
  LONG 형 컬럼은 함수에서 사용 불가<br>

### 숫자 INTEGER (정수)
```
CREATE TABLE TB_INTEGER(
    COL INTEGER,
    COL2 INTEGER
);
```
```
INSERT INTO TB_INTEGER(COL, COL2)
VALUES(123, 456);
```
숫자를 바로 저장할 수 있다.

### 숫자 NUMBER (정수, 실수)
```
CREATE TABLE TB_NUMBER(
    COL_NUM1 NUMBER,
    COL_NUM2 NUMBER(5),     -- 5자리
    COL_NUM3 NUMBER(5,2),   -- 5자리 소수점 2자리까지
    COL_NUM4 NUMBER(*,2)    -- 자리수 관여x , 소수점 2자리까지
    
);
```
```
INSERT INTO TB_NUMBER(COL_NUM1 , COL_NUM2 , COL_NUM3 , COL_NUM4)
VALUES(1234.5678, 12345.12, 123.456, 1234.56789);
```
첫번째 숫자는 전부 저장이된다. 두번째 숫자는 소수점은 버려지고 저장된다. 세번째 숫자는 마지막 소수점은 버려지고 2자리까지만 저장된다. 

마지막 숫자는 앞 정수는 전부 저장되고, 소수점 2자리까지 저장된다.


### 날짜 DATE
날짜의 정보를 자료형으로 하여 저장을 할 수 있다.
```
CREATE TABLE TB_DATE(
    COL_DATE1 DATE,
    COL_DATE2 DATE
);
```
- sysdate 는 오늘 날짜, 시간을 넣을 수 있다.
```
INSERT INTO TB_DATE(COL_DATE1, COL_DATE2)
VALUES(SYSDATE, SYSDATE-1);     ★ DATE이기 때문에 날짜를 하루 빼게 된다.
```
```
INSERT INTO TB_DATE(COL_DATE1, COL_DATE2)
VALUES(SYSDATE, TO_DATE('2020-07-17 12:32:45','YYYY-MM-DD HH:MI:SS'));     ★ TO_DATE(날짜 값 ,형식) 
```
```
INSERT INTO TB_DATE(COL_DATE1, COL_DATE2)
VALUES(SYSDATE, '20/08/26');                ★ 위의 형식에서 그냥 TO DATE를 빼고 문자열로 넣으면 들어가 진다.
```
```
INSERT INTO TB_DATE(COL_DATE1, COL_DATE2)
VALUES(SYSDATE, TO_DATE('20200717123245','YYYYMMDDHHMISS'));
```
VARCHAR2(문자열) -> DATE(문자열,날짜형태)  -> TO_DATE <br>
DATE( 문자열, 날짜형태) -> VARCHAR2(문자열) -> TO_CHAR
