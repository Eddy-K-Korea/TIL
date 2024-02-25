# ※ Select 2
## 1. ORDER BY (sorting)
기본 형태
```sql
SELECT *
    FROM   TABLE
    ORDER BY sal ASC   -> 오름차순
    ORDER BY sal DESC  -> 내림차순
    
    ORDER BY sal -> 오름차순 (ASC는 생략가능)
```

```sql
↓ 오름차순
SELECT ENAME,SAL
FROM EMP
ORDER BY SAL ASC;

↓ 내림차순
SELECT ENAME, SAL
FROM EMP
ORDER BY SAL DESC;
```
### alias
- alias (annsal) 으로 sort 가능
```sql
SELECT empno, ename, sal*12 AS annsal
FROM emp
ORDER BY annsal DESC;
```

- null 값을 제일 위로
```sql
SELECT ename, comm
FROM emp
ORDER BY comm NULLS FIRST;
```

- null 값을 제일 아래로
```sql
SELECT ename, comm
FROM emp
ORDER BY comm NULLS Last;
```
- job id 부터 sorting 되고 그 안에서 salary 내림차순 sorting
```sql
SELECT employee_id, job_id, salary
FROM employees
ORDER by job_id asc, salary desc;
```

## 2. 날짜 및 시간 관련 함수
-  months_between 두 날짜 간의 월수(월간격)을 구하는 함수
```sql
SELECT MONTHS_BETWEEN('2020-12-13', '2020-05-04')
FROM dual;
```
- add_months : 특정 날짜 월에 정수를 더한다음 해당 날짜를 반환
```sql
select add_months('2020-06-19',3)
from dual;
```

- 날짜 정보에서 특정한 연도, 월, 일, 시, 분, 초  구하는 함수 (EXTRACT)
```sql
SELECT EXTRACT(year from to_date('200619', 'yymmdd')) as 연도,
    EXTRACT(month from to_date('200815', 'yymmdd')) as 월,
    EXTRACT(day from sysdate) as 일
FROM dual;

SELECT EXTRACT(hour from CAST(datetime AS TIMESTAMP)) as 시,
    EXTRACT(minute from CAST(datetime AS TIMESTAMP)) as 분,
    EXTRACT(second from CAST(datetime AS TIMESTAMP)) as 초
FROM (
    select to_date('2020-06-19 09:42:01', 'yyyy-mm-dd hh24:mi:ss')as datetime from dual);

```
## 3. GROUP BY (그룹 관리)
- 통계용으로 사용을 많이 하며 HAVING 조건절과 같이 사용을 한다.
```sql
SELECT department_id, employee_id  ★ 그룹으로 묶은 후에는 개개인적으로 가지고 있는 정보는 출력 불가능 (특징)
FROM employees
GROUP BY department_id
ORDER BY DEPARTMENT_ID ASC;
```
위의 소스코드로 실행을 하게 되면 오류가 뜬다. department_id 로 그룹후 컬럼은 department_id 또는 통계 함수만 명시가 가능하며

employee_id 와 같은 개개인적인 정보 (기본키) 값은 명시가 불가능하다.

- GROUP BY 함수들
```sql
    COUNT
    SUM
    AVG
    MAX
    MIN
```
- IT_PROG의 job_id를 가진 사람들을 다음 함수들을 이용하여 표시할 수 있다.
```sql
SELECT COUNT(salary), COUNT(*), SUM(SALARY), AVG(SALARY), MAX(SALARY), MIN(SALARY)
FROM employees
WHERE job_id = 'IT_PROG';
```
- job_id로 그룹화 하여 그 안에서 통계를 낼 수 있다.
```sql
SELECT job_id, COUNT(salary),SUM(salary),AVG(salary)
FROM employees
GROUP BY job_id;
```
- HAVING 절 : 반드시 GROUP BY와 같이 사용 GROUP BY 밑에 사용
```sql
SELECT job_id, SUM(SALARY)
FROM employees
GROUP BY job_id
HAVING SUM(SALARY) >= 100000;
```
### EX
- 급여가 5000이상 받는 사원만으로 합계를 내서 업무(job_ID)로 그룹화하여 급여의 합계가 20000을 초과하는 업무명을 구하라
```sql
SELECT job_id, SUM(salary)  ★ 그룹으로 묶은 후 그룹함수 및 그럼으로 묶은 컬럼만 명시 할 수 있다.
FROM employees
WHERE salary >= 5000
GROUP BY job_id
HAVING SUM(SALARY) > 20000
ORDER BY SUM(SALARY) ASC;
```
