# PL/SQL (Procedural Language extension to SQL)
```
SQL을 확장한 순차적 언어 -> Procedure, Function, Trigger
                             in out     in out
                             insert     select
                             delete
                             update
```

### script 구조
1. 선언부 : 변수, 상수, 초기화
2. 실행부 : 실제로 처리할 Query 부분이나 제어문 활용해서 처리하는 부분
3. 예외처리부: 각종 오류 처리

### script 
기본적인 틀
```sql
DECLARE
  선언부
BEGIN
  실행부
END;
/
```
#### ※ Oracle 기본 툴에서 콘솔에 출력시 사용 명령어
```sql
SET SERVEROUTPUT ON   ★ 콘솔에 출력을 위해서는 ON 해주어야 한다.

BEGIN
  dbms_output.put_line('Hello Pl');
END;
/
```  

기본적인 문법은 JAVA와 비슷하지만 몇몇 부분이 다르기 때문에 사용하면서 주의해야한다. 예를 들면 := 는 == 와 같고 변수 선언시 자료형은 변수 명 뒤에 기재한다.
```sql
DECLARE -- 선언부
    message VARCHAR2(10);   ★ String message와 동일

BEGIN -- 실행부
    message := 'hello pl';   ★ message = "hello pl" 콜론을 붙여주면 에러가 사라진다.
    dbms_output.put_line('message = ' || message);
    
            ★ 예외처리를 하는 위치
END;
/
```

### IF 제어문
```sql
DECLARE
    counter INTEGER;
BEGIN
    counter := 1;
    counter := counter + 1;
    
    IF counter IS NULL THEN           ★  IF counter = 0 THEN 으로 사용 할 수 있다.
        dbms_output.put_line('result: counter is null');
    ELSE 
        dbms_output.put_line('result: counter is not null');
    END IF;
END;
/
```
### FOR 반복문
```sql
DECLARE
    counter INTEGER;
    i INTEGER;
BEGIN
    
    FOR i IN 1..9 LOOP       ★ 안에 1..9 는 범위
        dbms_output.put_line('i =' || i);   
        counter := i*2;
        dbms_output.put_line('2*' || i || '=' ||counter);
    END LOOP;
END;
/
```

### LOOP
```sql
DECLARE
    v_count NUMBER := 0;
    v_total NUMBER := 0;
BEGIN
    LOOP
        EXIT WHEN v_count = 10;     ★ 값이 TRUE일때 탈출하게 된다. 또한 := 을 사용하지 않고 = 만 사용한다.
        
        v_count := v_count + 1;
        v_total := v_total + v_count;
    END LOOP;
    
    dbms_output.put_line('total:' || v_total);
END;
/
```
### WHILE
```sql
DECLARE
    v_count NUMBER :=0;
    
BEGIN
    WHILE v_count < 10
    LOOP
        v_count := v_count +1;
        dbms_output.put_line('v_count = ' || v_count);
    END LOOP;
END;
/
```
JAVA의 WHILE문과 동일

### GOTO
조건에 맞는 출력문으로 이동하여 실행한다.
```sql
DECLARE
    v_name VARCHAR2(10) := 'Lee';
    v_case NUMBER := 0;
BEGIN
    CASE 
        WHEN MOD(v_case, 2) = 0 THEN
            GOTO test1;
        WHEN MOD(v_case, 2) = 1 THEN
            GOTO test2;
        ELSE
            GOTO err;
    END CASE;
    
<<test1>>
    dbms_output.put_line(v_name || ' is woman');
    GOTO sub_end;
<<test2>>
    dbms_output.put_line(v_name || ' is men');
    GOTO sub_end;
<<err>>
    dbms_output.put_line('ERROR');
    
<<sub_end>>
    dbms_output.put_line('Exit');
END;
/
```
### 예외처리
만약 0으로 정수를 나누려 할때 예외가 발생한다. 이때 EXCEPTION WHEN OTHERS THEN 을 이용하여 처리해준다.
```sql
DECLARE
    counter INTEGER;
BEGIN
    counter := 1;
    counter := counter / 0;
    
EXCEPTION WHEN OTHERS THEN
    dbms_output.put_line('예외가 발생하였습니다.');
END;
/
```
### varray
배열과 같은 의미의 자료형태이다. 
```sql
DECLARE
    TYPE varray_test IS VARRAY(3)OF INTEGER;      ★  int varray_test = new int[3]; 와 같으나 오라클은 타입을 설정 varray_test 는 생성 변수
    vArr varray_test;                             ★ 실제 배열 변수는 vArr (값을 넣을땐 vArr 변수를 이용해야한다.)
BEGIN
    vArr := varray_test(11,22,33);
   
    dbms_output.put_line('vArr(1)=' || vArr(1));
END;
/
```
오라클은 0 번째가 없기 때문에 11이 출력되는것을 알 수 있다.

### ACCEPT (WINDOW창에 값 입력)
```sql
ACCEPT p_deptno PROMPT '부서번호를 입력하시오(급여의 합)'

DECLARE 
    v_salTotal NUMBER;
BEGIN
    SELECT SUM(salary) INTO v_salTotal        ★ 합계의 값이 v_salTotal로 넘긴 후 출력 하도록 해야한다.
    FROM employees
    WHERE department_id = &p_deptno;          ★ 외부에서 값을 입력받은 변수를 기재할때는 & 을 붙여야 한다.
    
    dbms_output.put_line(&p_deptno|| '번 부서 급여 합 = ' || TO_CHAR(v_salTotal,'$999,999,999'));
END;
/
```
만약 문자열로 입력을 받을 경우
```sql
ACCEPT p_empname PROMPT '사원명 입력(급여)'

DECLARE
    v_name employees.last_name%TYPE := '&p_empname'; ★ 문자열은 ''안에 넣어야한다.
```
변수를 만들어 주고 ''안에 넣어주고 이용을 해야한다.
