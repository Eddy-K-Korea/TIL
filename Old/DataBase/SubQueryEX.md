# SubQueryEX
### 문제3) EMPLOYEES 테이블에서 100번 부서의 최소 급여보다 최소 급여가 많은 다른 모든 부서를 출력하라  
```sql
SELECT department_id, MIN(salary)
FROM employees
GROUP BY department_id
HAVING MIN(salary) >(SELECT MIN(salary) FROM employees WHERE department_id = 100);
```

우선 서브쿼리로 값을 구해야하는 부분을 생각해보자 

1. EMPLOYEES 테이블에서 100번 부서의 최소 급여를 우선적으로 구해야한다.
2. 이후 그 급여와 다른 부서의 최소급여를 비교해야한다.
3. 다른 모든 부서의 최소급여를 알기 위해서는 그룹으로 부서별로 묶어줘야하고 그룹의 조건절인 HAVING 절을 통해 아까 100번 부서와의 최소급여를 비교한다.

### 문제4) 업무별로 최소 급여를 받는 사원의 정보를 사원번호,이름,업무,부서번호를 출력하여라. 단 업무별로 정렬하여라.
```sql
SELECT employee_id, last_name, job_id, salary 
FROM employees
WHERE (job_id,salary) in(SELECT job_id, MIN(salary)FROM employees GROUP BY job_id)
ORDER BY job_id ASC;
```
여기서도 서브쿼리로 먼저 구해야할 값이 무엇인지 생각을 해야한다.
1. 업무별 (그룹) 최소급여를 받는 사원이 누구인지 구해야한다. 업무별로 구해야하기 때문에 그룹으로 묶고 최소 급여를 구해준다.
2. 그리고 업무와 급여와 서브쿼리의 지정된 값들이 같은경우의 사원 정보를 출력해준다.
3. 그리고 업무별로 정렬을 맨 마지막에 해준다.

### 문제5) EMPLOYEES 과 DEPARTMENTS 테이블에서 업무가 세일드맨 사원의 정
보를 이름,업무,부서명,근무지를 출력하라.

#### 1
```sql
SELECT e.first_name, e.job_id, d.department_name, d.location_id
FROM employees e, departments d
WHERE e.department_id = d.department_id and e.job_id in(SELECT job_id FROM employees WHERE job_id like 'SA_MAN');
``` 
WHERE 절에 서브쿼리를 이용하여 값을 출력하는 방법

#### 2
```sql
SELECT first_name,job_id,e.department_id,d.department_name,d.location_id
FROM (SELECT first_name,job_id,department_id
    FROM employees
    WHERE job_id = 'SA_MAN') e, departments d
WHERE e.department_id = d.department_id;
```
FROM 절에 서브쿼리를 두어 한번 불러올 테이블을 걸러내고 그안에서 WHERE절에서 조건을 맞추어 출력하는 방법

### 문제6) EMPLOYEES 테이블에서 가장 많은 사원을 갖는 MANAGER의 사원번호를 출력하라.
```sql
SELECT manager_id
FROM employees
GROUP BY manager_id
HAVING COUNT(MANAGER_id) = (SELECT MAX(COUNT(*)) FROM employees GROUP BY manager_id);
```
서브쿼리는 동일한 매니저 번호를 가진 사람들을 카운트하고 거기서 MAX를 한 값을 출력한다.

### 문제8) EMPLOYEES 테이블에서 사원번호가 123인 사원의 직업과 같고 사원번호가 192인 사원의 급여(SAL)보다 많은 사원의 사원번호, 이름, 직업, 급여를 출력하라.
```sql
SELECT employee_id, last_name, job_id, salary
FROM employees
WHERE job_id = (SELECT job_id FROM employees WHERE employee_id = '123') 
    AND salary > (SELECT salary FROM employees WHERE employee_id = '192');
```
두개의 서브쿼리를 이용하여 조건을 걸어주고 값을 출력하는 방식

### 68) CHICAGO 지역에서 근무하는 사원의 평균 급여보다 높은 급여를 받는 사원의 이름과 급여, 지역명을 출력하시오.
- 해당 문제는 여러번의 쿼리가 포함되고 포함한다. 따라서 문제를 잘 파악하고 해결을 순차적으로 생각해서 풀어야한다.
1. 지역번호
2. 평균 급여
3. 비교
```sql
SELECT e.ename, sal, d.loc
FROM emp e, dept d
WHERE e.deptno = d. deptno and sal >(SELECT AVG(sal)             -- 2. 평균급여    -- 3. 비교
                                     FROM emp
                                     WHERE deptno = (SELECT deptno FROM dept WHERE loc = 'CHICAGO')); -- 1. 지역 번호
```
CHICAGO로 부서 번호를 출력하고 그 부서의 평균 급여를 구한다.

이후 평균급여보다 많이 받는 사원의 이름과 급여를 출력, JOIN을 통해 근무지를 출력하는 방식
                                     
