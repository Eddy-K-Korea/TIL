# PL 예제
## 1번
- EVEN_ODD(
    ID:NUMBER(4) 
    GUBUN:VARCHAR2(10)
) 
테이블을 작성하고 START숫자와 END숫자를 입력 받아 그 사이의 숫자를 ID에 저장하고  
ID의 숫자가 짝수이면 GUBUN에 “짝수”를 홀수이면 GUBUN에 “홀수”라고 입력하는 
SCRIPT를 WHILE문으로 작성하여라.
```sql
CREATE TABLE EVEN_ODD(
    ID NUMBER(4),
    GUBUN VARCHAR2(10)
);

ACCEPT start PROMPT 'START'
ACCEPT end PROMPT 'END'

DECLARE
    v_start NUMBER := &start;
    v_end NUMBER := &end;
BEGIN
  IF v_start > v_end THEN
        dbms_output.put_line('start가 end보다 큽니다');
    ELSE
        WHILE v_start <= v_end Loop
            IF MOD(v_start,2) = 0 THEN
                INSERT INTO even_odd(id,gubun)
                VALUES(v_start,'짝수');
            ELSE
                INSERT INTO even_odd(id,gubun)
                VALUES(v_start,'홀수');
            END IF;
            v_start := v_start +1;
        END Loop;
        dbms_output.put_line(&start || '로부터 ' || &end || '까지 ' || TO_CHAR(&end - &start + 1)||'개의 자료가 입력 되었습니다.');
    END IF;
END;
/
```
## 2번 
- 예제2)사원번호를 입력 받으면 다음과 같이 출력되는 PROCEDURE를 작성하라 Purchasing 부서명의 사원입니다
```sql
CREATE or REPLACE PROCEDURE pro_test(p_empno IN NUMBER)
IS
    p_dept departments.department_name%TYPE;
    
BEGIN
    SELECT d.department_name INTO p_dept  -- ,dname 해도 된다.
    FROM employees e, departments d
    WHERE e.department_id = d.department_id AND e.employee_id = p_empno;

    dbms_output.put_line(p_dept || ' 부서명의 사원 입니다');
END;
/
```
### 호출
```sql
EXEC pro_test(132);
```
## 3번
- 예제3)사원번호를 입력받고, 소속부서의 최고, 최저연봉 차액을 파라미터로 출력하는 PROCEDURE를 작성하라.
```sql
CREATE or REPLACE PROCEDURE pro_sal(p_empno IN NUMBER, subSal OUT NUMBER)
IS
    higtSal NUMBER;
    lowSal NUMBER;
    
    -- deptno employees.department_id%TYPE;
BEGIN
    SELECT MAX(salary),MIN(salary)INTO higtSal,lowSal
    FROM employees
    WHERE department_id =(SELECT department_id
          FROM employees
          WHERE employee_id = p_empno);
    subSal := higtSal-lowSal;
END;
/
```
### 호출
```sql
VAR subSal NUMBER;
EXEC pro_sal(120,:subSal);
PRINT subSal;
```
## 4번
- 예제4) 두 숫자를 제공하면 덧셈을 해서 결과값을 반환하는 함수를 정의하시오.(함수명 add_num)
```sql
CREATE FUNCTION sum_test(n1 IN NUMBER, n2 IN NUMBER)RETURN NUMBER
IS
    result NUMBER;
BEGIN
    result := n1 + n2;
    return result;
END;
/
```
## 5번
- 예제5) 부서번호를 입력하면 해당 부서에서 근무하는 사원 수를 반환하는 함수를 정의하시오. (함수명 get_emp_count)
```sql
CREATE or REPLACE FUNCTION get_emp_count(deptno_no IN NUMBER)RETURN NUMBER
IS
    count_emp NUMBER;
BEGIN
    SELECT COUNT(empno)INTO count_emp
    FROM emp
    WHERE deptno = deptno_no
    GROUP BY deptno;
    
    return count_emp;
END;
/
```
## 6번
- 예제6) emp테이블을 이용해서 입사일을 제공하면 근무연차를 구하는 함수를 정의하시오.(소수점 자리 절삭, 함수명 get_info_hiredate)
``` sql
CREATE OR REPLACE FUNCTION get_info_hiredate(hire_Date emp.hiredate%TYPE)RETURN NUMBER
IS
   v_year NUMBER;
BEGIN
   v_year := TRUNC(MONTHS_BETWEEN(sysdate,hire_date)/12);
   RETURN v_year;
    
END;
/
```
## 7번
- 예제7) emp테이블을 이용해서 사원번호를 입력하면 해당 사원의 관리자 이름을 구하는 함수를 정의하시오.(함수명 get_mgr_name)
```sql
CREATE or REPLACE FUNCTION get_mgr_name(epm_no IN NUMBER)RETURN VARCHAR2
IS
    m_name VARCHAR2(10);
BEGIN
    SELECT b.ename INTO m_name
    FROM emp a, emp b
    WHERE a.mgr = b.empno AND a.empno = epm_no;
    
    return m_name;
END;
/
```
## 8번
- 예제8) emp테이블을 이용해서 사원번호를 입력하면 급여 등급을 구하는 함수를 정의하시오.
- (4000~5000 A, 3000~4000미만 B, 2000~3000미만 C, 1000~200미만 D, 1000미만 F,
- 함수명 get_sal_grade)
```sql
CREATE or REPLACE FUNCTION get_sal_grade(emp_no IN NUMBER)RETURN VARCHAR2
IS
    grade VARCHAR2(10);
BEGIN
    SELECT
        CASE WHEN sal >= 4000 AND sal <= 5000 THEN 'A'
            WHEN sal >= 3000 AND sal <= 4000 THEN 'B'
            WHEN sal >= 2000 AND sal <= 3000 THEN 'C'
            WHEN sal >= 1000 AND sal <= 2000 THEN 'D'
            ELSE 'F'
        END INTO grade
    FROM emp
    WHERE empno = emp_no;
    
    return grade;
    
END;
/
```
