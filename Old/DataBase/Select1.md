# ※ Select
## - DB에서 가장 많이 사용하고 가장 중요한 부분이 select 부분이다. 가장 중요하다.
#### - 데이터베이스는 데이터를 관리하는 목적으로 사용하며, file과 달리 원하는 자료만 추출하여 명세할 수 있다.

## select 의 기본 형태
```
SELECT
        (값, COLUMN(항목), 함수, SUB QUERY)
    FROM
        (TABLE명, SUB QUERY)
```

### 1. 현재 계정에 가지고 있는 모든 테이블 명세
```
SELECT * FROM TAB;
```
### 2. 테이블에서 원하는 컬럼의 값만 명세
```
SELECT 컬럼명
FROM 테이블 명;
```
### 3. 컬럼에 연산처리 값 명세가능
```
SELECT EMPNO, ENAME, SAL, SAL+ 300 
FROM EMP;

SELECT EMPNO, ENAME, SAL, SAL*12      ★ 연봉을 볼 수 있다.
FROM EMP;
```
위와 같이 출력을하면 모든 SAL의 값에 300 이 추가가 되어 명세가 된다.
### 4. ALIAS (컬럼의 명칭을 출력시 원하는 값으로 변경하여 명세)
```
SELECT EMPNO AS 사원번호, SAL AS "월급", SAL*12 "일년치 연봉"  
FROM EMP;
```
만약 "일년치 연봉" 와 같이 중간에 공백이 생기게 되면 ""를 사용하여 ALIAS가 어디까지 변환해야하는지 설정하면 오류가 없다.

### 5. 연결연산자 || 
```
SELECT ENAME || '의 월급은 ' || SAL || ' 입니다' AS "이름 + 월급"
FROM EMP;
```
맨위 칼럼명은 "이름 + 월급" 으로 표기가 되고 값부분에는 ||을 이용한 문자로 표시가 된다. 

여기서 문자열은 ' ' 으로 하는 것을 주의하자.

### 6. distinct : 중복행의 삭제 -- group by
```
SELECT DISTINCT JOB
FROM EMP;
```
위의 코드는 EMP 테이블의 JOB을 출력하지만 중복되는 값은 명세되지 않고 표기한다.

### 7. DESC : TABLE의 COLUMN들을 명세
```
DESC EMP;
```
EMP 테이블의 어떤 컬럼이 있는지 확인 할 수 있다.

### 8. NVL(컬럼명, 원하는값) : 컬럼이 NULL이 아니면 컬럼의 값을 출력. 만약 NULL이면 원하는 값 출력 
```
SELECT EMPLOYEE_ID,FIRST_NAME,SALARY, NVL(COMMISSION_PCT,0) , NVL(SALARY+COMMISSION_PCT*SALARY, 0)
FROM employees;
```
NULL 값으로 나오는 값들이 0으로 변경이 되어서 출력이 된다.

### 9. NVL2(컬럼, 컬럼이 null이 아닐 경우 설정할 값, 컬럼이 null의 경우 설정할 값) 
- 총급여sal+comm가 평균 급여보다 많은 급여를 받는 사람의 부서번호, 이름, 총급여, 
커미션을 출력하시오.(커미션은 유(O),무(X)로 표시하고 컬럼명은 "comm유무" 출력)
```sql
SELECT deptno, ename, sal+comm, NVL2(COMM, '유', '무')  as "comm유무" 
FROM emp
WHERE sal+comm > (SELECT AVG(sal) FROM emp);
```

---
## Where 절 
- if 와 같다 (조건절)

- 표현식
비교 연산자(>, <, >=, <=, =, !=, <>)    != 와 <> 는 같은 의미 <br>
NULL IS NULL, IS NOT NULL<br>
(), NOT, AND(&&), OR(||)<br>
 
- Query<br>
```
Select 
From
WHERE 
```
### 1. 대소문자 구분
```
SELECT first_name, last_name, salarY
FROM employees
WHERE first_name = 'Julia';  
```
대소문자를 구분하기 때문에 'Julia'와 'julia'의 결과 값은 다르다.

### 2. 비교 연산
```
--급여가 $9000 이상인 사원

SELECT first_name, salary
FROM employees
WHERE salary >= 9000;
```
```
-- 이름이 Shanta 보다 큰 이름
SELECT first_name
FROM employees
WHERE first_name >= 'Shanta';
```
이름의 알파벳들을 ASCII 코드 값으로 크기를 비교 한다.

### 3. null 과 '' 빈문자열 구분
```
SELECT first_name, last_name
FROM employees
where manager_id = ''; 
```
null 로 구분되어 있는 부분은 ''으로 인식 하지 못한다. 

```
SELECT first_name, last_name
FROM employees
where manager_id is null; 
```
''대신 is null을 이용하여 null인 부분을 출력하게 한다.

```
SELECT first_name, last_name
FROM employees
where manager_id is not null; 
```
steven king (유일하게 manager_id 값이 null인 사원) 만 제외하고 전부 출력

### 4. AND
```
SELECT first_name, salary
FROM employees
where first_name = 'John'
    and salary >= 5000; 
```
이름이 John 이면서 월급이 5000 이상인 사람 출력

### 5. OR
```
SELECT first_name, last_name
FROM EMPLOYEES
WHERE first_name = 'Shanta'
    OR first_name = 'John';
```
Shanta와 John 둘다 출력

### 6. ALL, ANY
```
SELECT * FROM employees
where first_name = All('Julia','John');
★ WHERE first_name = 'Shanta' and first_name = 'John';  와 동일
```
```
SELECT * FROM employees
where first_name = any('Julia','John');
★ WHERE first_name = 'Shanta' or first_name = 'John';  와 동일
```
```
SELECT first_name, salary
FROM employees
where salary = any(8000, 3200, 6000); -- 보통 이렇게 많이 쓰임
```
월급이 8000, 3200, 6000인 사람만 출력을 하게 된다.

### 7. IN, NOT IN
```
SELECT first_name,salary  
FROM employees
where salary in(8000,3200);   -- in은 any와 동일
```
```
SELECT first_name,salary  
FROM employees
where salary not in(8000,3200); -- 지정된 값 이외의 포함하지 않은 값들을 출력
```
8000과 3200이 아닌 사람 모두 출력이 된다.

### 8. BETWEEN 
- salary >= 3200 AND salary <= 9000
- salary BETWEEN 3200 AND 9000
위의 두 소스 코드는 같다.
- 범위 (이상,이하)를 구할때 이용한다.

### 9. LIKE
- 글자 단위 탐색 
```
SELECT first_name
FROM employees
where first_name like 'G_ra_d'; 
```
_ 는 한글자가 무엇이든지 허용

```
SELECT first_name
FROM employees
where first_name like 'K%y'; 
```
% 는 글자수에 관계없이 모두 허용 (꼭 3글자가 아닐 수 있다.)

```
SELECT first_name
FROM employees
where first_name like 'A%';   
```
맨 앞에 A로 시작하는 사람들 모두 출력
```
SELECT first_name
FROM employees
where first_name like '%y';  
```
맨끝에 y로 끝나는 사람 모두 출력

```
SELECT first_name
FROM employees
where first_name like '%e%'; 
```
맨끝이 e 이거나 중간에 있는 사람 모두 출력 (e라는 문자만 있으면 출력)

```
SELECT first_name, hire_date
FROM employees
where hire_date like '06/01%';
```
2006년 1월 이후의 모든 날짜의 입사날짜가 탐색된다.

```
SELECT ename,deptno
FROM emp
where deptno = 20
    and ename not like('%S%');
```
not like 를 활용하여 이름에 S 가 없는 사람중 부서번호가 20인 사람을 조회하여 이름과 부서 번호를 명세
