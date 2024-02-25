# Table Space
## Table space 란?
오라클은 데이터를 관리하는 데이터베이스입니다. 데이터를 어딘가에 저장해놓고 사용하는 시스템이라고 볼 수 있습니다. 
그리고 데이터 저장 단위 중 가장 상위에 있는 단위를 테이블 스페이스라고 합니다. 데이터 저장 단위는 물리적, 논리적단위로 나눌 수 있습니다. 
물리적 단위는 파일을 의미하고 논리적 단위는 데이터블록 -> 익스텐트 -> 세그먼트 -> 테이블스페이스 이렇게 나뉩니다. 
데이터 블록 여러개가 모여 익스텐트 하나를 만들고, 익스텐트 여러개가 모여 하나의 세그먼트를 구성하는 식입니다. 
### 테이블 스페이스는 가장 상위개념입니다.

## Table Space 생성
```sql
create tablespace TABLESPACE3               [테이블 스페이스명]
DATAFILE 'C:\test\TEST_Test_03.DBF'         [파일경로]
SIZE 10M                                    [초기 데이터 파일 크기 설정]
AUTOEXTEND ON NEXT 1M MAXSIZE UNLIMITED     [추가되는 용량]
LOGGING                                     [로깅을 사용]
EXTENT MANAGEMENT LOCAL AUTOALLOCATE        [로컬]
BLOCKSIZE 8K
SEGMENT SPACE MANAGEMENT AUTO
FLASHBACK ON;
```
## Table Space 수정
1. 이름 수정
```sql
ALTER TABLESPACE TABLESPACE3
RENAME TO TB_TEST_NEW;
```
2. 용량 수정
```sql
ALTER DATABASE
DATAFILE 'C:\TEST\TEST_TBS1_03.DBF' RESIZE 7M;
```
## Table Space 삭제
```sql
DROP TABLESPACE TB_TEST_NEW
INCLUDING CONTENTS AND DATAFILES
CASCADE CONSTRAINTS;
```

---

# Table
## Table create
```sql
CREATE TABLE TB_TEST01(
    COL_01 VARCHAR2(10),
    COL_02 VARCHAR2(10),
    COL_03 VARCHAR2(10),
    COL_04 VARCHAR2(10)
);
```
칼럼은 생성하고 자료형태와 용량을 생성시 기재해 준다.

```sql
CREATE TABLE TB_TEST02(
    COL_01 VARCHAR2(10),
    COL_02 VARCHAR2(10),
    COL_03 VARCHAR2(10),
    COL_04 VARCHAR2(10)
)
TABLESPACE TABLESPACE2;
```
Table Space를 지정하여 테이블을 생성할 수 있다.


## Table Copy
```sql
CREATE TABLE TB_TEST03
AS
SELECT employee_id, SALARY, first_name
FROM employees;
```
select from 을 이용하여 불러오고 싶은 칼럼과 테이블을 지정하여 데이터를 똑같이 가진 테이블을 생성 할 수 있다.

```sql
CREATE TABLE TB_TEST04
AS
SELECT *
FROM departments
WHERE 1 = 2;
```
마지막 WHERE 절에서 성립하지 않는 식을 기재하면 해당 테이블이 생성될때 값은 불러오지 않고 칼럼만 끌어와 테이블을 생성한다.

```sql
CREATE TABLE DEPT_INFO(EMPNO, SAL, DNAME, LOC) -- 컬럼명을 바꿀 수 있다.
AS
SELECT e.employee_id, e.salary,d.department_name,d.location_id
FROM employees E, departments D
WHERE e.department_id = d.department_id;
```
JOIN한 내용의 값을 가진 Table로 생성할 수 있다. 생성시 컬럼 명을 변경하여 생성 할 수 있다.

## Table 수정
### 1. 테이블 이름 수정
```sql
ALTER TABLE TB_TEST04
RENAME
TO TB_TEST99;
```
### 2. 단일 column 추가
```sql
ALTER TABLE TB_TEST99
ADD
COL_NEW1 VARCHAR2(30);
```
### 3. 다중 column 추가
```sql
ALTER TABLE TB_TEST99
ADD (COL_NUM2 NUMBER, COL_NUM3 DATE);
```
### 4. 단일 column 수정
```sql
ALTER TABLE TB_TEST99 MODIFY
COL_NEW1 VARCHAR2(20);
```
자료형태, 크기 등을 변경할 수 있다.
### 5. 다중 column 수정
```sql
ALTER TABLE TB_TEST99
MODIFY
(COL_NUM2 VARCHAR2(30), COL_NUM3 VARCHAR2(20));
```
컬럼 두개의 크기를 변경하였다.

### 6. 단일 column 삭제
```sql
ALTER TABLE TB_TEST99
DROP
COLUMN COL_NEW1;
```
### 7. 다중 column 삭제
```sql
ALTER TABLE TB_TEST99
DROP
(COL_NUM2,COL_NUM3);
```
### 8. column명 수정
```sql
ALTER TABLE TB_TEST99
RENAME
COLUMN DEPARTMENT_ID TO DEPTNO;
```
## Table 삭제
```sql
DROP TABLE TB_TEST01;
DROP TABLE TB_TEST02;
DROP TABLE TB_TEST03;
```

---
# Table data 
## DATA INSERT
```sql
INSERT INTO TB_TEST99(DEPTNO,DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID)
VALUES(100, '기획',20, 200);

INSERT INTO TB_TEST99(DEPTNO,DEPARTMENT_NAME)
VALUES(101, '관리부');

INSERT INTO TB_TEST99
VALUES(102, '영업부',45, 320);

INSERT INTO TB_TEST99(DEPTNO,MANAGER_ID,LOCATION_ID,DEPARTMENT_NAME)
VALUES(103,520, 203,'인사부');
```
위의 코드 모두 데이터가 추가가 되며 각 컬럼에 맞게 같은 순서로 추가가 된다.

## DATA DELETE
```sql
DELETE FROM TB_TEST99
WHERE department_name = '인사부';

DELETE FROM TB_TEST99
WHERE manager_id IS NULL;
```
delete를 이용하여 FROM 뒤에 테이블 명 그리고 WHERE 조건을 통해 삭제할 행을 지시할 수 있다.

## DATA UPDATE
```sql
UPDATE TB_TEST99
SET manager_id = 70   ★ 바꿔야할 컬럼과 값
WHERE department_name = '영업부';  ★ 조건을 걸어주고 맞는 행을 찾는다

UPDATE TB_TEST99
SET manager_id = 30, location_id = 150
WHERE deptno = 100;
```
