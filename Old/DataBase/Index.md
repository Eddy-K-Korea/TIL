# Index (색인)

- 원하는 정보의 위치를 빠르고 정확하게 알아낼 수 있는 방법

1. 자동생성 : Primary Key, Unique
2. 수동생성 : Query문으로 생성

### 생성했을때 효율적인 경우
1. WHERE 절이나 JOIN조건 안에서 자주 사용되는 COLUMN
2. NULL값이 많이 포함되어있는 COLUMN 
3. WHERE절이나 JOIN조건 안에 두개 이상을 사용하는 COLUMN

### 생성했을때 비효율적인 경우
1. 테이블의 DATA의 수(ROW)가 적을때(3000개 이하)
2. 테이블이 자주 갱신되는 경우
    
    
```sql
CREATE TABLE EPM_COPY
AS
SELECT * FROM employees;
```
우선 employees 테이블 전체르 복사한다.

- 자동 생성
```sql
ALTER TABLE EMP_COPY
ADD CONSTRAINT PK_EMPCOPY_01 PRIMARY KEY(EMPLOYEE_ID);
```
기본키는 생성을 하면 자동적으로 Index가 생성된다.


- 강제 생성
```sql
CREATE INDEX EMP_INDEX01
ON EMP_COPY(MANAGER_ID);
```


- INDEX 확인
```sql
SELECT
    *
FROM ALL_INDEXES
WHERE INDEX_NAME IN ('EMP_INDEX01');
```
