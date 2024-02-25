# 특수 QUERY
### - CASE, DECODE = SWITCH , IF
## CASE
```sql
SELECT employee_id, first_name, phone_number,
    CASE SUBSTR(phone_number, 1, 3)
        WHEN '515' THEN '서울'
        WHEN '590' THEN '부산'
        WHEN '659' THEN '광주'
        WHEN '603' THEN '대전'
        ELSE '기타'
    END AS "지역"
FROM employees;
--------------------------------------------------------------------
SELECT employee_id, first_name, phone_number,
    CASE
        WHEN SUBSTR(phone_number, 1, 3) = '515' THEN '서울'
        WHEN SUBSTR(phone_number, 1, 3) = '590' THEN '부산'
        WHEN SUBSTR(phone_number, 1, 3) = '659' THEN '광주'
        WHEN SUBSTR(phone_number, 1, 3) = '603' THEN '대전'
        ELSE '기타'
    END AS "지역"
FROM employees;
```
위 소스코드와 아래의 소스코드는 동일한 소스 코드이다.

해당 CASE 쿼리는 전화번호 첫번째 부터 세번째를 추출하여 515 일때는 지역 서울, 590 일때는 지역 부산 ... 이처럼 구분 하여 출력하도록 한다.

## DECODE
```sql
SELECT employee_id, first_name, phone_number,
    DECODE( SUBSTR(phone_number, 1, 3),
            '515', '서울',
            '590', '부산',
            '659', '광주',
            '603', '대전',
            '기타') AS "지역"
FROM employees;
``` 
CASE와 같은 값을 나타내준다.

## OVER() 함수
- SELECT 절에서만 사용 가능
- GROUP by 를 보강하기 위해 등장
★ JOIN을 하고 Group by 를 사용하지 못할때 COUNT , SUM, AVG 등 함수를 이용하기 위해서 OVER를 사용할 수 있다.
```sql
SELECT first_name, department_id, COUNT(DISTINCT department_id)OVER() -- 킴벌리때문에 106이 출력
 FROM employees;
``` 
OVER함수를 통해 COUNT 함수를 사용하고 그안에서 부서번호를 중복없이 수를 세었다.

## PARTITION BY == GROUP BY
```sql
SELECT department_id, first_name, salary,
    COUNT(*)OVER(PARTITION BY department_id) ---------- ORDER BY department_id ASC; 로 정렬을 할 수 있다.
FROM employees;
```
★ OVER를 묶을때 PARTITION BY 를 이용해 그룹으로 묶고 COUNT,SUM,AVG 등 함수를 활욜 할 수 있고, 그뒤에 ORDER BY로 정렬을 할 수도 있다.

부서별로 묶고 그 안의 사원수를 카운트하고 부서번호, 이름, 급여를 출력한다. 맨끝에 나오는 숫자는 사원이 해당한 부서의 사원 수 이다.

## 집합 
- JOIN과 같은 값을 출력하지만 자동 정렬 되는 장점이 있다.
### 1. 합집합  = UNION
```sql
SELECT job_id
FROM employees
WHERE job_id IN ('AD_VP', 'FI_ACCOUNT')
UNION all ★  full outer JOIN 과 비슷
SELECT job_id
FROM jobs
WHERE job_id IN ('AD_VP','FI_ACCOUNT');
```
### 2. 교집합 = INTERSECT
```sql
 SELECT employee_id
 FROM employees
 INTERSECT
 SELECT manager_id
 FROM employees;
 
 
 --------------------------
 SELECT DISTINCT b.employee_id
 FROM employees a, employees b
 WHERE a.manager_id = b.employee_id;
```
위의 코드는 집합 아래는 INNER JOIN
### 3. 차집합 -- MINUS
```sql
매니저가 아닌 사원
 SELECT employee_id
 FROM employees
 MINUS
 SELECT manager_id
 FROM employees;
 
 ------------------------------
 차집합을 JOIN
 SELECT e.first_name, e.department_id, d.department_id
 FROM employees e, departments d
 WHERE e.department_id = d.department_id(+) AND e.department_id is null;
``` 
## 분석 함수
- 순위 함수

1. RANK()          1 2 3 3 5 6
2. DENSE_RANK()    1 2 3 3 4 5 
3. ROW_NUMBER()    1 2 3 4 5 6
4. ROWNUM --> 순서대로 번호를 매김

```sql
SELECT first_name, employee_id
FROM employees
WHERE employee_id >= 100 and employee_id <= 109; 

★ 연봉에 랭킹 붙이기 

SELECT first_name, salary,
    RANK()OVER(ORDER BY salary DESC) AS RANK,
    DENSE_RANK()OVER(ORDER BY salary DESC) AS DENSE_RANK,
    ROW_NUMBER()OVER(ORDER BY salary DESC) AS ROW_NUMBER
FROM employees;
```
### ROWNUM
```sql
SELECT ROWNUM, employee_id, first_name  
FROM employees
WHERE  ROWNUM <= 10;
```
처음부터 10까지 번호가 매겨지며 10까지 출력이 된다.

- 만약 11 ~ 20 까지 출력한다고 하면
```sql
SELECT ROWNUM, employee_id, first_name -- 2. 
FROM employees
WHERE  ROWNUM > 10 and ROWNUM <=20; -- 1. 수행 순서
```
위와 같이 설정을 하면 수행 순서로 인해 출력이 되지 않는다.

```sql
★ 1. DATA 설정 (급여 순위) - 정렬
★ 2. ROWNUM 설정
★ 3. 범위 설정
SELECT RNUM, employee_id, first_name, salary ★ 4. RNUM으로 정렬과 번호가 붙여진 값을 가지고 나와서 범위 설정(WHERE)절
FROM(SELECT ROWNUM AS RNUM, employee_id, first_name, salary ★ 2. ROWNUM 의 번호를 붙여 주고  ★ 3.SELECT 실행
     FROM(SELECT employee_id, first_name, salary ★ 1.
          FROM employees
          ORDER BY salary DESC))
WHERE RNUM > 10 AND RNUM <= 20;
```
별 및 순서대로 수행되기 때문에 위와 같은 코드형식으로 급여의 11위 부터 20위까지 출력이 가능하다.
