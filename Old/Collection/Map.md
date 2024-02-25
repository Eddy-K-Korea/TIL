# HashMap

## 특징
- 사전과 비슷하다.
- key 값으로 value값을 알려준다.
- key 값은 중복이 불가능하다.(만약 같은 key 값으로 대입이 되면 덮어씌우게 된다)
- Tree구조이기 때문에 검색이 빠르다.(이중으로 값이 나누어져 있고 값을 찾을때 반을 쪼개어서 검색을 한다)

## TreeMap
- HashMap과 동일하나 추가적인 기능으로 Sorting을 가능하다.

## 형태
- Map<Integer, String> map = new HashMap<Integer, String>();
key의 자료형, value의 자료형을 입력하고 생성해준다. List와 마찬가지로 Map 인터페이스를 불러오고 필요에 따라 HashMap, TreeMap클래스 선택가능

### 1. 추가
```java
hMap.put(111, "백십일");
hMap.put(222, "이백이십이");
hMap.put(333, "삼백삼십삼");
```
put 을 이용하여 값을 저장한다.

### 2. value 값 취득
```java
String value = hMap.get(222);
System.out.println("value:" + value);
```
key값으로 value값을 찾는다고 볼 수 있다.

### 3. 삭제
```java
String val = hMap.remove(222);	
```
remove의 return값이 있다. 넘겨주고 지워진다.

### 4. 검색
```java
// 있다/없다 (key 값을 가지고)
boolean b = hMap.containsKey(new Integer(333));
System.out.println("b : "+ b);
		
//값을 취득
if(hMap.containsKey(333)) {
value = hMap.get(333);
System.out.println("value:"+value);
}
```  
containsKey를 통해 key 값이 있는지 없는지 true, false로 확인 할 수 있으며, 그 key의 값으로 값을 취득할 수 있다.

### 5. 수정
```java
hMap.put(111, "100+ 10 + 1");   //(같은 key 값을 쓰게 되면 덮어 씌우게 된다.)
		
hMap.replace(222, "200 + 20");  // 위 방법과 동일하지만 replace 정석이다.
```
### 6. allPrint
```java
Iterator<Integer> it = hMap.keySet().iterator();
		
while (it.hasNext()) {
  Integer key = it.next();
  System.out.println("key:"+ key);
			
  String _value = hMap.get(key);
  System.out.println("value:"+_value);
}
```
Iterator을 이용하여 반복할 key 값의 자료형(클래스)를 지정하고 keySet() 메서드를 부르고 다시 iterator함수를 부른다.

wihle을 통해 모든 key값을 반복하고 출력할 수 있다.

## 정렬
- HashMap -> TreeMap

Iterator을 이용하여 전체 출력과 비슷하지만 Iterator<Integer> it = hMap.keySet().iterator();

소스코드를 구현 할 때 keySet()은 오름차순으로 정렬 후 모두 출력이 되고 

keySet() 대신에 descendingKeySet()을 메서드로 사용하게 되면 내림차순으로 정렬을 하여 출력을 하게된다.
