# View

- view = 가상 테이블

다른 테이블들을 접근하기 위한 가상 테이블

Table <--------------------------- view -------- 개발자 접근

한개의 view로 여러개의 Table을 검색할 수 있다.

- 속도가 빠르다.

- 제한 설정이 가능하다. ----> readonly


### 기본 형태
```sql
CREATE VIEW 사용자 지정(뷰 이름) (view 칼럼 명칭)
AS
SELECT 불러올 컬럼
FROM 불러올 테이블
```
- 예제
```sql
CREATE VIEW UB_TEST_01(JOB_ID, JOB_TITLE, MIN_SALARY)
AS
SELECT JOB_ID, job_title, MIN_SALARY
FROM JOBS;

```
만들어진 view를 통해서 검색이 가능 (사용자가 지정한 컬럼 및 내용만을 볼 수 있게 설정한다)
```sql
SELECT
    *
FROM ub_test_01;  ★ VIEW 를 통해서 검색
```
해당 view에 삽입을 하지만 실제 테이블에도 추가가 된다.
```sql
INSERT INTO ub_test_01(JOB_ID, JOB_TITLE, MIN_SALARY)
VALUES('DEVELOPER','개발자',10000);
```
### 읽기 전용
데이터의 추가 삭제 수정이 불가하고 검색만 가능하게 된다.
```sql
CREATE OR REPLACE VIEW DEPTVIEW(
    "부서번호",
    "부서명",
    "지역명"
)
AS 
SELECT DEPARTMENT_ID, DEPARTMENT_NAME, LOCATION_ID FROM departments
WITH READ ONLY;   ★ 읽기 전용 명령어
```

- view의 경우 개발자가 필요한 테이블의 컬럼의 값을 빠르게 보기위해 별도 설정을하여 생성한다. 특히 JOIN을 하거나 여러 SUBQUERY가 있는
긴 문장의 QUERY문의 경우 view를 통해 간략하고 가독성이 좋게 할 수 있다.
