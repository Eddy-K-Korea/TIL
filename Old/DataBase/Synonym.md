# Synonym (동의어)
- 객체의 별명
- 테이블의 명이 길때 별명을 사용하여 임의 재설정 후 사용 할 수 있다.

```sql
CREATE SYNONYM "사원들"
FOR EMPLOYEES;
```
"사원들" 으로 employees 테이블을 명칭을 임의 설정 할 수 있다.

```sql
INSERT INTO "사원들"(EMPLOYEE_ID, LAST_NAME,EMAIL, HIRE_DATE, JOB_ID)
VALUES(EMPLOYEES_SEQ.nextval,'일','IJM@DAUM.NET',SYSDATE,'ST_MAN');
```
데이터를 삽입 할때 "사원들" 으로 기재 후 실행 할 수 있다.
