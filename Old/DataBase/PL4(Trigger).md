# Trigger 
- 자동 호출되는 함수
- 매개 변수가 없다.
```
before, after
                    OLD          NEW
            
            INSERT                O
            DELETE   O            O
            UPDATE   O            O
```
## 기본 Trigger 형태
```sql
CREATE TRIGGER trigger_test
    BEFORE UPDATE ON departments
    FOR EACH ROW
    
BEGIN
    dbms_output.put_line('변경전 칼럼의 값:' || :OLD.department_name);
    dbms_output.put_line('변경후 칼럼의 값:' || :NEW.department_name);
    
END;
/
```
- CREATE [OR REPLACE] TRIGGER [schema.]trigger : 트리거 생성, 재생성 명령이다?
- BEFORE : INSERT, UPDATE, DELETE문이 실행되기 전에 트리거가 실행 된다.
- AFTER : INSERT, UPDATE, DELETE문이 실행된 후 트리거가 실행 된다.
- FOR EACH ROW : 이 옵션이 있으면 행 트리거가 된다.

## 트리거(TRIGGER)  생성시 고려사항

1. 트리거는 각 테이블에 최대 3개까지 가능하다

2. 트리거 내에서는 COMMIT,ROLLBACK 문을 사용할 수 없다.

3. 이미 트리거가 정의된 작업에 대해 다른 트리거를 정의하면 기존의 것을 대체한다.

4. 뷰나 임시 테이블을 참조할 수 있으나 생성 할 수는 없다.

5. 트리거 동작은 이를 삭제하기 전까지 계속된다.

### 예제 문제
- row를 추가하는데 항상 평균 급여를 확인
```sql
CREATE or REPLACE TRIGGER avg_trigger
    BEFORE 
        INSERT OR UPDATE ON employees  ★ 두가지 경우를 사용할 경우
        FOR EACH ROW
DECLARE
    avg_sal NUMBER;

BEGIN
    SELECT ROUND(AVG(salary),3) INTO avg_sal
    FROM employees;
    
    dbms_output.put_line('급여 평균'|| avg_sal);
END;
/
```
값을 삽입하거나 수정할때마다 평균 급여가 계산되어 자동으로 호출 된다.

- 수정되지 않도록 하는 경우
```sql
CREATE TRIGGER emp_trigger
    BEFORE
        UPDATE OR DELETE OR INSERT ON employees
        FOR EACH ROW
BEGIN
    IF UPDATING THEN   ★ 사용되는 구문이다.
        IF :OLD.employee_id = '100' THEN
            RAISE_APPLICATION_ERROR(-20001, '이 번호는 수정할 수 없습니다.');  ★ 오류 발생시 출력할 문장을 적기위해 RAISE_APPLICATION_ERROR 적어주어야한다.
        END IF;
    END IF;
END;
/
```

- 입력불가 설정
```sql
CREATE TRIGGER nodata_trigger
    AFTER INSERT
    ON employees
BEGIN
    RAISE_APPLICATION_ERROR(-20000, '데이터의 입력을 시도 하였습니다');
END;
/
```
