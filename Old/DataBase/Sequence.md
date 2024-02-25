# Sequence

- 유일한 값을 생성해 주는 Oracle Object
- 회원번호, 게시판 관리번호, 
- 초기화 불가능. 삭제 후 다시 생성.

## Sequence 생성
```sql
CREATE SEQUENCE TEST_SEQ  -- TEST_SEQ는 임의 설정
INCREMENT BY 1            -- 1씩 증가 ++
START WITH 10             -- 시작 숫자
MAXVALUE 100 
MINVALUE 1;
```

## CURRVAL  
- 현재의 SEQ 값 (NEXTVAL 을 한번 실행하고 해야한다)
```sql
SELECT TEST_SEQ.CURRVAL
FROM DUAL;
``` 

## NEXTVAL : 진행 값
```sql
SELECT TEST_SEQ.nextval
FROM DUAL;      ★ 초기값이 한번 나온다.
```

## SEQUENCE 수정
```sql
ALTER SEQUENCE TEST_SEQ
INCREMENT BY 3;   ★ 증가하는 값이 3으로 수정된다.
```

## SEQUENCE 삭제 (초기화 불가로 삭제했다가 다시 생성)
```SQL
DROP SEQUENCE TEST_SEQ;
```
