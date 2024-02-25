# Join 예제

### 문제
EMPLOYEES 테이블에서 left join하여 관리자(매니저)를 출력하고
매니저 아이디가 없는 사람은 배제하고 하향식으로 하며, 급여는 역순으로
출력하라.
```sql
SELECT a.employee_id, a.first_name , a.salary, a.manager_id, b.employee_id, b.first_name
FROM employees a, employees b           ★ a: 사원   b: 관리자(사원)
WHERE a.manager_id = b.employee_id(+)   
START WITH a.manager_id is not null
CONNECT BY  a.manager_id = PRIOR a.employee_id    ★ 목적은 연결 PRIOR의 위치에 따라 하향식인지 상향식인지 구분
ORDER BY a.salary DESC;
```
left joni 하여 사원번호를 중심으로 조건을 걸어준다. 또한 메인 목적이 관리자를 출력하는것이기 때문에 left join 한다.


### 문제
문제9) EMPLOYEES 테이블에서 right join하여 관리자(매니저)가 108번
상향식으로 급여는 역순으로 출력하라.
```sql
SELECT a.employee_id,a.first_name as "사원명", a.salary, a.manager_id, b.employee_id, b.first_name as "상사명"
FROM employees a, employees b
WHERE a.manager_id(+) = b.employee_id and a.manager_id = '108'
CONNECT BY PRIOR a.manager_id = a.employee_id
ORDER BY a.salary DESC;
```
사원중에서 매니저의 번호가 108번인 사람을 구하는것 이기 때문에 right join을 하여 사원을 구하는것의 목적을 생각하자.
다음 조건인 108 을 WHERE 조건에서 걸어준다.


### 문제
근무지별로 근무하는 사원의 수가 5명 이하인 경우, 인원이 적은 도시순으로 정렬하시오.
(근무 인원이 0명인 곳도 표시)
```sql
SELECT d.loc, count(e.empno)
FROM emp e, dept d
WHERE e.deptno(+) = d.deptno    ★ 공통 부분 JOIN , 근무지는 dept에 있기 때문에 right join을 해주어야 한다.
GROUP BY d.loc
HAVING count(e.empno) <= 5
ORDER BY count(d.loc) ASC;
```

### 문제
57) 사원의 이름 및 사원번호를 관리자의 이름과 관리자 번호와 함께 표시하고 
각각의 열 레이블은 employee, emp#, manager, mgr#로 지정하는데 
King을 포함하여 관리자가 없는 모든 사원을 표시하도록 하고 
결과를 사원번호를 기준으로 정렬
```sql
SELECT a.ename as employee, a.empno as emp#,b.ename as manager, b.empno as mgr#
FROM emp a, emp b
WHERE a.mgr = b.empno(+)
ORDER BY a.empno ASC;
```
모든 사원을 표시하라고 했기때문에 OUTER쪽으로 생각할 수 있으며, 사원을 표기함으로 left join으로하여 출력한다.

### 문제
58) 지정한 부서번호, 사원이름 및 지정한 사원과 동일한 부서에서 근무하는 
모든 사원을 표시하도록 질의를 작성하고 
부서번호는 department, 사원이름은 employee, 동일한 부서에서 근무하는 사원은 colleague로 표시하시오.
(부서번호, 사원이름,동료 순으로 오름차순 정렬)
```sql
SELECT a.empno,a.ename employee, a.deptno department, b.deptno, b.empno colleague, b.ename
FROM emp a , emp b 
WHERE a.deptno = b.deptno
    AND a.empno != b.empno  ★ 자기 자신을 비교 하는것은 안나온다.
ORDER BY a.deptno, a.ename, b.empno ASC;
```
지정한 사원과 동일한 부서에서 근무하는 사원을 비교 해야하기 때문에  WHERE 절에서 한번 같은 동료로 조건을 걸어준다.

자기 자신과 비교하는 것을 조건으로 제외해주고, 정렬해준다.

### 문제
59)10번 부서에서 근무하는 사원들의 
부서번호, 부서이름, 사원이름, 월급, 급여등급을 출력하시오.
```sql
SELECT e.deptno, d.dname, e.ename, e.sal, s.grade
FROM emp e, dept d, salgrade s
WHERE e.deptno = d.deptno 
    AND e.deptno = 10 
    AND (e.sal >= s.losal And e.sal <= s.hisal);   ★  e.sal BETWEEN s.losal AND s.hisal; 둘다 가능
```
sal 등급 테이블을 불러와야한다. WHERE 절에서 join, 동일 부서 조건, 그리고 sal의 등급 범위 조건을 걸어준다.
