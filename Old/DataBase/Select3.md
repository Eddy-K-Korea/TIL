# ※ Select (JOIN)
## JOIN의 정의

#### 두개 이상의 테이블을 연결해서 데이터를 검색하는 방법이다.
#### 보통 두개이상의 행(ROW)들의 공통된 값 Primary Key (기본키), Foreing Key(외래키) 값을 
#### 사용해서 JOIN 한다.

- Primary Key(기본키) : 테이블에서 중복이 되지 않는 키
- Foreign Key(외래키) : 다른 테이블에서 PK,UK 인 경우가 많다.
```
                                               (중요도)
   1. inner JOIN : 교집합                     ★★★★★★
   2. fullouter JOIN 
   3. cross JOIN 
   4. outer  
           left                               ★★★
           rigth                              ★★★
   5. self JOIN : 같은 테이블의 데이터를 산출   ★★★★★★
```   

![JOIN 모형](https://user-images.githubusercontent.com/65350890/85114684-42266c00-b255-11ea-9ec9-a766e6395813.jpg)

### inner JOIN
- Ansi SQL(모든  SQL에서 언어가 공통된다) , 오라클 언어 두개 모두 기재한다.
```sql
SELECT e.employee_id, e.first_name, e.department_id, d.department_id,d.department_name 
FROM employees e INNER JOIN departments d
    ON e.department_id = d.department_id;
```
위의 검색의 목적은 d.department_name을 가져오기 위함을 잊지 말자.

FROM절 이후의 불러와야할 테이블을 불러온다(alias 하여 이용하면 가독성이 좋아진다)

- Oracle
```sql
SELECT employee_id, first_name,
    e.department_id, d.department_id, d.department_name, d.location_id
FROM employees e, departments d
where e.department_id = d.department_id   ★ 여기까지가 교집합
    and e.first_name = 'Adam';
```
1. employees 테이블과 departments 테이블을 불러온다.
2. WHERE 조건절에서 departments_id가 같다는 조건을 걸어준다.
3. 그리고 검색때 departments_name이 포함된 departments 테이블에서 탐색하여 출력한다.

### Cross JOIN
- Ansi SQL
```sql
SELECT e.employee_id, e.first_name,
    e.department_id, d.department_id,
    d.department_name
FROM employees e CROSS JOIN departments d;
```
```sql
SELECT e.employee_id, e.first_name,
    e.department_id, d.department_id,
    d.department_name
FROM employees e, departments d;
```
Cross JOIN의 경우 모든 사원에게 각 부서별 이름을 붙여주는 형식이다. 거의 잘 쓰이지 않는다.

### Full outer join
```sql
SELECT e.employee_id, e.department_id,
     e.department_id, d.department_id,
     d.department_name
FROM employees e FULL OUTER JOIN departments d
    ON e.department_id = d.department_id;
```
### Outer JOIN (left, right)
- 1. Ansi left
```sql
SELECT e.employee_id, e.first_name,
     e.department_id, d.department_id,
     d.department_name
FROM employees e LEFT OUTER JOIN  departments d
    ON e.department_id = d.department_id;
```
From의 첫번째로 오는 테이블이 기준이되어 LEFT OUTER가 되면 교집합을 제외한 employees 테이블만 남게 된다.

- 2. Oracle left
```sql
SELECT e.employee_id, e.first_name,
     e.department_id, d.department_id,
     d.department_name
FROM employees e, departments d
where e.department_id = d.department_id(+);
```
(+) 가 붙은 반대쪽 테이블이 기준이 된다.

- 3. Oracle right
```sql
SELECT e.employee_id, e.first_name,
     e.department_id, d.department_id,
     d.department_name
FROM employees e, departments d
where e.department_id(+) = d.department_id;
```
### Self JOIN
- 동일한 테이블을 JOIN
```sql
SELECT a.employee_id, a.first_name,
    a.manager_id, b.employee_id,
    b.first_name
FROM employees a, employees b      ★ a : 사원 b : 상사
WHERE a.manager_id = b.employee_id;
```
같은 테이블 안에서 사원과 상사를 나누어 탐색한다.

사원의 상사를 구하기 위해 manager_id 와 employee_id이 같은경우 b 테이블에서 불러온 이름의 사람이 상사인것을 확인 할 수 있다.

### 계층형 구조 (오름,내림)
```sql
SELECT a.employee_id, a.first_name AS "사원",
    a.manager_id AS "사원의 상사", b.employee_id,
    b.first_name AS "상사"
FROM employees a, employees b
WHERE a.manager_id = b.employee_id(+)
CONNECT BY  a.manager_id = PRIOR a.employee_id;
```
위의 소스코드는 하향식으로 가장 상위에 있는 상사를 아래로 하나씩 정렬되어 출력한다.
```sql
SELECT a.employee_id, a.first_name AS "사원",
    a.manager_id AS "사원의 상사", b.employee_id,
    b.first_name AS "상사"
FROM employees a, employees b
WHERE a.manager_id = b.employee_id(+)
CONNECT BY PRIOR a.manager_id = a.employee_id;
```
상향식이며, 아래 사원의 manager_id를 확인하여 위 상사를 하나씩 출력한다.
