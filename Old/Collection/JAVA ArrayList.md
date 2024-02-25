# Collection
## List
- 데이터를 유동적으로 관리할 수 있도록 한다.
### 특징
1. 배열처럼 사용 할 수 있는 목록
2. 선형구조(o-o-o-o-o) 
3. 검색속도가 빠르다.
4. index number로 관리된다. (0 ~ n-1)

#### 종류
1. ArrayList (다른 list보다 검색이 빠르다.)
2. LinkedList (ArrayList와 특징은 동일하나 추가, 삭제가 더 빠르다.)

## Map

### 특징
1. key, value로 관리된다.
2. key로 접근한다.
3. "apple" : "사과"
4. key 값의 중복을 허용하지 않는다.key는 하나만 존재한다.
5. Tree 구조

#### 종류
1. hashmap
2. Treemap (추가적으로 sorting을 할 수 있다.)

---
## ArrayList

### 형식
ArrayList<자료형,Object, 등> lis이름 = new ArrayList<자료형,Object, 등>();

- 예) ArrayList<MemberDto> memList = new ArrayList<MemberDto>();
- 추가
```java
MemberDto dto = new MemberDto(101,"홍길동");
memList.add(dto);
		
memList.add(new MemberDto(102, "일지매"));
		// 원하는 위치
memList.add(1, new MemberDto(111, "성춘향"));
```


- 삭제
```java
memList.remove(2);
```
- 검색

한개의 데이터만 검색 할때
```java
String findName = "성춘향";
		int findIndex = -1;
		for (int i = 0; i < memList.size(); i++) {
			MemberDto mem = memList.get(i);
			if(findName.equals(mem.getName())) {
				findIndex = i;
				break;
		
			}
		}
```
두개 이상 (예를 들면 "성춘향"이라는 이름이 동명이인으로 2명일때)

```java
ArrayList<MemberDto> findList = new ArrayList<MemberDto>();
		findName  = "성춘향";
		for (int i = 0; i < memList.size(); i++) {
			MemberDto mem = memList.get(i);
			if(findName.equals(mem.getName())) {
				findList.add(mem);
			}
		}
``` 
findList 라는 list배열을 한번더 잡아주고 if 조건문을 걸어 같은 경우 list 배열에 전부 넣어줄 수 있다.
- 수정
```java
MemberDto updateMem = new MemberDto(104, "향단");
		
		memList.set(3, updateMem);
```
set 이라는 메서드를 통해 수정 할 수 있다.
