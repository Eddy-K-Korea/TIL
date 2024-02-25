# Function
- 우선 결과 확인을 할 수 있는 가상 TABLE인 Dual을 사용하여 함수의 결과를 확인 할 수 있다.

```
SELECT 1 FROM DUAL;
SELECT 'A' FROM DUAL;
SELECT '가' FROM DUAL;
SELECT 23* 45 FROM DUAL;
```
간단한 연산도 가능하다.

## 문자함수 CHR(n)
- ASCII 값을 문자로 변환
```
SELECT CHR(65) FROM DUAL;
SELECT CHR(97) FROM DUAL;

결과는 둘다 'A' 와 'a'가 나온다.
```
오라클에서는 문자열간의 합을 + 대신 ||을 사용한다.
```
SELECT '내 점수는' || CHR(65) || ' 입니다' FROM DUAL;
```
## LPAD(RPAD) 
나머지를 빈칸(지정문자)으로 채운다.
```
SELECT LPAD('BBB',10) FROM DUAL;    ★ 왼쪽에 빈칸으로 7칸이 채워지고 BBB가 저장
SELECT RPAD('BBB',10) FROM DUAL;    ★ 오른쪽에 빈칸으로 7칸이 채워지고 BBB가 저장
SELECT LPAD('BBB',10,'-') FROM DUAL;  ★ 왼쪽에는 -으로 7칸을 채우고 나머지 3칸은 BBB로 채움
```
## INSTR
java의 indexOf(a) 와 비슷한 함수이다.
```
SELECT INSTR('123ABC456EDF','A')FROM DUAL; ★ A의 위치의 인덱스 넘버 출력
SELECT INSTR('123ABC456EDF','1')FROM DUAL; ★ 오라클은 0부터 카운트하지 않고 1부터 한다.
SELECT INSTR('123ABC456EDFABC','A',7)FROM DUAL;   ★ 7은 7번째 이후로 탐색을 하라는 명령어
SELECT INSTR('123ABC456EDFABCABC','A',7, 1)FROM DUAL;   ★ 7번째 이후로 1번째 A를 탐색
SELECT INSTR('123ABC456EDFABCABC','A',7, 2)FROM DUAL;   ★ 7번째 이후로 2번째 A를 탐색
SELECT INSTR('123ABC456EDFABCABC','Y')FROM DUAL;  ★ 없을 경우 0이 나온다
```
## REPLACE  
문자열 치환
```
SELECT REPLACE('AAAAABCD','A') FROM DUAL;     ★ A라는 문자를 빈문자로 바꿔라
SELECT REPLACE('AAAAABCD','A','a') FROM DUAL;  ★ A라는 문자를 a로 바꿔라
SELECT REPLACE('AAAAABCD','AA','a') FROM DUAL; ★ 짝수로 바뀌고 남은 A는 그냥 남아 있는다.
```
## TRANSLATE
문자 치환
```
SELECT TRANSLATE('AAAAABCD','A', 'a') FROM DUAL;
SELECT TRANSLATE('AAAAABCD','AA','a') FROM DUAL;  ★ AA라고 되어있지만 A만 인식되어서 전부 a로 바뀐다.
```
## 숫자
### 1. 올림 CEIL
```
SELECT CEIL(13.1) FROM DUAL;
```
### 2. 내림 FLOOR
```
SELECT FLOOR(13.9) FROM DUAL;
```
### 3. 나눈 나머지 MOD
```
SELECT MOD(3,2) FROM DUAL;  ★ 3을 2로 나눈 나머지 출력 
```

### 4. 승수 POWER
```
SELECT  POWER(3,2) FROM DUAL;
```
3의 2승으로 자바와 같은 함수명을 사용

### 5. 반올림 ROUND
```
SELECT ROUND(13.5) FROM DUAL;
``` 
### 6. 부호 SIGN
양수(1) , 0 , 음수(-1)를 숫자 1,0,-1  로 나타내주는 함수
```
SELECT SIGN(13.4) FROM DUAL;
SELECT SIGN(0) FROM DUAL;
SELECT SIGN(-2) FROM DUAL;
```
### 7. 버림 TRUNC
```
SELECT TRUNC(12.3456) FROM DUAL;
SELECT TRUNC(12.3456,2) FROM DUAL;   ★ 소수점 2번째 짜리 까지
SELECT TRUNC(12.3456, -1) FROM DUAL; ★ 반대로 올라간다 10이 나온다.
```
### 8. ASCII -> CHR
받은 문자를 아스키 코드숫자로 변환해서 나타내준다.
```
SELECT ASCII('A') FROM DUAL;
```
### 변환함
#### TO_CHAR
DATE -> VARCHAR2 로 변환 문자열로 변환해서 출력
```
SELECT TO_CHAR(SYSDATE)FROM DUAL;  ★ 문자열로 변환해서 나옴
SELECT TO_CHAR(SYSDATE, 'YYYY-MM-DD-HH-MI-SS') FROM DUAL;  ★ 원하는 형태로 변환 후 문자열로 변환
SELECT TO_CHAR(SYSDATE, 'YYYY/MM/DD HH:MI:SS') FROM DUAL; 
SELECT TO_CHAR(1000000000000000, '$999,999,999,999,999,999') FROM DUAL;
```
#### TO_DATE
VARCHAR2 -> DATE 문자열에서 날짜 형태로 변환
```
SELECT TO_DATE('20200617')FROM DUAL;   ★ 날짜 형태로 변환되어서 
SELECT TO_DATE('20201225','YYYYMMDD')FROM DUAL;
SELECT TO_DATE('12252020','MMDDYYYY')FROM DUAL;
```
#### TO_NUMBER
VARCHAR2 -> NUMBER   자바의 parseInt() 함수와 같다. 문자열을 숫자형태로 변환
```
SELECT TO_NUMBER('123')+12 FROM DUAL;
```
#### LAST_DAY
그 달의 마지막 날짜를 알 수 있다.
```
SELECT LAST_DAY('20/02/01')FROM DUAL;

SELECT LAST_DAY(TO_DATE('200201','YYMMDD'))FROM DUAL; ★ 정석이다.
```
#### SUBSTR
substring(1,3)     "ABCDE" -> BC 지정한 숫자의 순번째부터 지정한 숫자의 순번째까지 문자를 출력
```
SELECT SUBSTR('ABCDE',3)FROM DUAL;    --3번지부터 끝까지

SELECT SUBSTR('ABCDE',3,2) FROM DUAL; -- 3번지부터 2문자 불러오는 함수
```
#### UPPER / LOWER
```
upper/lower(컬럼명 또는 문자열) 하게되면 upper는 대문자로 lower는 소문자로 변형되어 출력된다.
```
#### length
```
length(문자열, 컬럼명)을 넣게 되면 문자열의 길이, 컬럼 값의 문자 길이의 값이 나온다.
```
