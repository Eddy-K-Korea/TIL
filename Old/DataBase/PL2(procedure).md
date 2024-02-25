# Procedure 

- 매개변수를 받고 처리를 하는 Procedure
- 대표적으로 Insert, Update, Delete을 Procedure로 만들어 사용한다.
- 수동 호출
- 보통 Procedure는 DB 값에 대한 영향을 준다.

## 기본적인 Procedure 형태
```sql
CREATE PROCEDURE HELLOPROC
IS                                      ★ 선언부
    msg VARCHAR2(10);
BEGIN                                   
    msg := 'hello Proc';
    
    dbms_output.put_line(msg||' 호출 되었음');
END;
/

EXEC HELLOPROC;                          ★ 실행을 위해서는 EXEC 또는 EXECUTE를 이용하고 Procedure를 호출하여 사용한다.
```

## 매개변수를 받는 형태
```sql
CREATE PROCEDURE MYPROC(inNum IN NUMBER, outNum OUT NUMBER)  ★ 자료형에 크기를 적어주지 않는다. IN 은 들어오는 값  OUT은 나가는 RETURN 값
IS
    -- 선언부
BEGIN
    dbms_output.put_line('inNum' || inNum);
    
    outNum := 333;
END;
/
```
### 호출
```sql
-- 호출
VAR val NUMBER;        ★ Integer val; 변수 선언

EXEC myproc(111,:val); ★ 변수 앞에다가 콜론을 붙여야 out의 값을 받을 수 있다.

PRINT val;             ★ out 값 출력 , 실행시 한 줄 씩 실행
```
VAR 의 경우 변수 선언시 앞에 붙여주어야한다.

Procedure 호출시 out에 대한 매개변수를 받기 위해 val의 변수를 넣어줄때 : 을 앞에 붙여주어야 값을 받을 수 있다.

PRINT를 적어주고 out값을 출력 할 수 있다.


## INSERT 역할을 가진 Procedure
```sql
CREATE OR REPLACE PROCEDURE add_dept (p_deptno IN departments.department_id%TYPE,       ★ p_deptno IN NUMBER 로 해도 괜찮다.
                                      p_deptname IN departments.department_name%TYPE,
                                      p_deptloc IN departments.location_id%TYPE)
IS

BEGIN
    INSERT INTO departments(department_id, department_name,location_id)
    VALUES(p_deptno,p_deptname,p_deptloc);

    
EXCEPTION WHEN OTHERS THEN          ★ 추가하지 못 할 경우 예외를 위해 Exception을 걸어준다.
    dbms_output.put_line('추가에 실패하였습니다.');

END;
/
```

## UPDATE 역할을 가진 Procedure
- 사원번호를 입력 받아서 월급을 30% 인상시키는 PROCEDURE 작성
```sql
create or replace PROCEDURE updateSal(v_empno IN NUMBER)
IS

BEGIN

    UPDATE employees
    SET salary = salary *1.3
    WHERE employee_id = v_empno;
    

EXCEPTION WHEN OTHERS THEN
dbms_output.put_line('수정에 실패 했습니다.');
END;
/
```
### 호출
```sql
ACCEPT empno PROMPT '사원번호'
EXEC updatesal(&empno);
```

## SELECT Procedure
- 보통 Function 으로 많이 select을 사용하지만 procedure로도 사용 할 수 있다.
```sql
CREATE OR REPLACE PROCEDURE emp_info(p_empno IN NUMBER)
IS
    v_emp employees%ROWTYPE;    ★ employees의 모든 타입을 불러온다.
BEGIN
    SELECT first_name, hire_date, salary
        INTO v_emp.first_name, v_emp.hire_date, v_emp.salary     
    FROM employees
    WHERE employee_id = p_empno;
    
    dbms_output.put_line('이름:'|| v_emp.first_name);
    dbms_output.put_line('입사일:'|| v_emp.hire_date);
    dbms_output.put_line('월급:'|| v_emp.first_name);
END;
/
```

---
# Cursor
- 저장 주소 공간 (= Pointer)
1. 암시적 Cursor (자동생성)
- SQL%ROWCOUNT : ROW의 수     SIZE()
- SQL%FOUND : ROW의 수가 한 개이상의 경우
- SQL%NOTFOUND : ROW의 수가 0  

2. 명시적 Cursor (수동생성)

## 1. 암시적 Cursor
자동 생성된 명령어를 호출하여 사용 할 수 있다. '검색'의 부분은 row의 값이 하나 일 경우 출력이 가능하다. 

그렇지 않으면 에러가 발생 한다.

```sql
CREATE or REPLACE PROCEDURE implicit_cursor(p_empname IN employees.first_name%TYPE)
IS
    v_sal employees.salary%TYPE;
    v_update_row NUMBER;      ★  몇개의 행이 수정되는지 조사하는 부분의 변수
BEGIN
 /*   ★ 검색  데이터가 한개가 있어야 한다. 한개 이상일 경우 에러가 발생
    SELECT salary INTO v_sal
    FROM employees
    WHERE first_name = p_empname          AND last_name = p_emplastname;  -- 보통은 이렇게 한다.
    
    ★ 검색된 데이터 (row)가 있는 경우
    IF SQL%FOUND THEN 
        dbms_output.put_line('검색된 데이터가 있습니다');
    END IF;
*/
    ★ 수정     -- 검색시 여려명일 경우는 한명만 되고 수정할 경우 여려명이여도 괜찮다. 
    UPDATE employees
    SET salary = salary*1.1
    WHERE first_name = p_empname;
    
    v_update_row := SQL%ROWCOUNT;
    dbms_output.put_line('급여가 인상된 사원수:' || v_update_row);
    
EXCEPTION WHEN NO_DATA_FOUND THEN
    dbms_output.put_line('검색된 데이터가 없습니다');
    
END;
/
```

## 2. 명시적 Cursor

```sql
CREATE or REPLACE PROCEDURE  expCursor_test(v_deptno IN departments.department_id%TYPE)
IS
    CURSOR dept_avg             ★ 커서를 선언해 준다.
    IS
    SELECT d.department_name, COUNT(e.employee_id) as CNT, ROUND(AVG(salary),3) as SAL
    FROM employees e, departments d 
    WHERE e.department_id = d.department_id AND e.department_id = v_deptno  ★ 외부로부터 입력된 부서번호
    GROUP BY d.department_name; 
    
    ★ cursor에  fatch하기 위한 변수들을 선언
    v_dname departments.department_name%TYPE;
    EMP_cnt NUMBER;
    sal_avg NUMBER;

BEGIN
 
    ★ CURSOR OPEN    커서를 Fetch 해준다(연결)
    OPEN dept_avg;
    ★ CURSOR FETCH
    FETCH dept_avg INTO v_dname, emp_cnt,sal_avg;
    
    dbms_output.put_line('부서명'|| v_dname);
    dbms_output.put_line('사원수'|| emp_cnt);
    dbms_output.put_line('평균 월급'|| sal_avg);
    
    -- CURSOR CLOSE   끝난 이후에 닫아준다.
     CLOSE dept_avg;
END;
/
```
