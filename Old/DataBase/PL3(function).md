# Function (함수)
- 매개변수를 가진다.
- 무조건 return 값을 가진다.
- Select 를 주로 이용한다 (Insert,Update, Delete)는 실행이 안된다.

## 기본 형태
```sql
CREATE FUNCTION func(p_val IN NUMBER) RETURN NUMBER
IS
    v_val NUMBER;
BEGIN
    v_val := p_val;
    v_val := v_val*2;
    
    RETURN v_val;
END;
/
```
NUMBER 자료형으로 return값을 받는 함수로 입력받은 매개변수에 *2를 하는 Function이다.

### 값 확인
```sql
SELECT func(3)
FROM dual;
```
값은 6이 나온다.

```sql
VAR v NUMBER;
EXEC :v := func(12);    ★  변수앞에 ':' 을  붙여주어야 한다. (주의)
PRINT v;
```
변수는 VAR 을 통해 생성하고 함수를 이용해 12 *2 = 24 의 값을 v에 넣어주고 PRINT를 통해 v의 값을 확인 할 수 있다.


### 함수 사용한 예제
- 문제 : 월급과 커미션을 합친금액의 세금을 계산
```sql
CREATE FUNCTION tax2(p_sal IN employees.salary%TYPE, p_bonus IN employees.commission_pct%TYPE) RETURN NUMBER
IS
BEGIN
    RETURN ((p_sal+NVL(p_bonus,0)*p_sal)*0.15);
END;
/
```
RETURN 값으로 바로 계산을하여주었다.

```sql
SELECT first_name,salary+salary*NVL(commission_pct, 0) as 실급여,
        tax2(salary,commission_pct) as 세금
FROM employees;
```
실급여와 세금이 컬럼이 2개로 나뉘어 계산되어 값을 명시해준다.

- 문제: 사원번호를 입력하면, 업무명을 취득할 수 있는 함수
```sql
CREATE OR REPLACE FUNCTION getJobName(p_empno IN NUMBER)RETURN VARCHAR2
IS
    v_jobName jobs.job_title%TYPE;
BEGIN
    SELECT j.job_title INTO v_jobname
    FROM employees e, jobs j
    WHERE e.job_id = j.job_id AND e.employee_id = p_empno;
    
    RETURN v_jobName;
    
END;
/
```
### 값 호출
```sql
ACCEPT p_empno PROMPT '사원번호';
EXEC :jobname := getjobname(&p_empno);
PRINT jobname;
```
