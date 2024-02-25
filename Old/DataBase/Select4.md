# SUB Query
### - Query 안에 Query 
### - 한개의 행(row)에서 결과 값이 반환되는 Query

### 조건
```
SELECT절 : 단일 ROW 단일 COLUMN (산출되는 데이터가 한개, 컬럼도 한개)   (사용빈도가 낮음)
```
```
FROM 다중 ROW 다중 COLUMN
```
```
WHERE   다중 ROW 다중 COLUMN        (제일 많이 사용)
```
### 1. Select 절에서의 Sub Query 사용
```sql
SELECT employee_id, first_name,
    (SELECT first_name
    FROM employees
    WHERE employee_id = 100)    
FROM employees;
```
위의 소스코드는 사실 WHERE 절에 바로 employee_id = 100 을 명시하여도 결과값이 출력이 가능하다.

### 2. FROM 절에서의 Sub Query 사용
```sql
SELECT employee_id, first_name, last_name
FROM (SELECT employee_id, first_name, last_name
        FROM employees
        WHERE department_id = 20);
```
가져올 테이블에 조건을 걸어 한번 걸러주고 그 값안에서 SELECT하는 방식이다.

- 부서번호 50번, 급여가 6000이상인 사원
```sql
SELECT e.employee_id, e.salary
FROM (SELECT employee_id, salary
        FROM employees
        WHERE department_id = 50) e
WHERE salary >= 6000;
```
alias를 이용하여 걸러진 테이블을 끌어와 SELECT 하고 WHERE 절로 조건을 걸어줄 수 있다.

-급여의 합계, 인원수, 사원명, 월급
```sql
SELECT e.employee_id, e.salary, 
    e.job_id, j.job_id,
    j.급여합계, j."인원수"
FROM employees e, (SELECT job_id, sum(salary) as 급여합계, COUNT(*) as "인원수"
                    FROM employees
                    GROUP BY job_id) j
WHERE e.job_id = j.job_id;
```
JOIN 하여 나타 낼 수 있다.

### 3. WHERE 절에서의 Sub Query 사용
```sql
SELECT first_name,salary
FROM employees
WHERE salary > (SELECT AVG(salary) FROM employees);
```
기본적인 조건을 한번 걸러서 그 안에서 또 조건을 걸어 준다.

- 부서번호 90의 JOb_id만 산출
```sql
SELECT job_id, first_name, department_id
FROM employees
WHERE job_id IN(SELECT job_id FROM employees WHERE department_id = 90);

```

- 부서별로 가장 급여를 적게 받는 사원과 같은 급여를 받는 사원(후자는 전 사원 대상)
``` sql
SELECT first_name, salary, department_id
FROM employees
WHERE salary IN (SELECT MIN(salary) FROM employees GROUP BY department_id); 
                  ★ sub query 는 부서별 가장 적게 받는 사람들 출력
```
SUB QUERY 이후 부서별 최저 월급과 월급이 같은사원(전사원대상)으로 조건을 걸어 부합하는 이름과 월급 부서이름이 출력된다.

- 부서별로 가장 급여를 적게 받는 사원과 급여
```sql
SELECT department_id, first_name, salary
FROM employees
WHERE (department_id, salary)IN (SELECT department_id, MIN(salary) FROM employees GROUP BY department_id)
ORDER BY department_id ASC;
```
부서별 최서 월급과 부서 번호를 SUB QUERY로 구분하고 그 값들과 동일한 부서번호와 월급을 조건으로 건다.(후자는 전사원대상)

그리고 부서별로 정렬한다.
