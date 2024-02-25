# LinkedList

## 특징
- ArrayList와 같은 List로 메서드와 이용 방식은 비슷하다.
- 차이점 : 추가, 삽입시 처리 속도가 ArrayList보다 빠르다.
- *이유*: arrylist는 션형구조이기 때문에 한번 정렬을 하는 처리가 포함되어있다.(index 로 관리) <br>
또한 LinkedList는 메모리에 무작위로 저장을 하기 때문에 추가시 정렬을 거치지 않고 바로 저장된다.

```java
List<String> elist = new ArrayList<String>();

List 는 인터페이스 	    자식 클래스
		
List<String> alist = new LinkedList<String>();
```
위와 같은 형식으로 List(인터페이스)를 생성하고 그에 맞는 collection을 불러와 줄 수 있다.(호환에 더 좋다)

## LinkedList 관련 method
### 1. 값의 존재 유무 확인
```java
LinkedList<String> alist = new LinkedList<String>();

alist.add("tigers");
alist.add("lions");
alist.add("twins");

if(blist.isEmpty()) {	      
			
}
```
alist안의 값이 비어있는지 true, false로 값을 돌려줌

### 2. Element 의 값을 맨앞, 맨뒤에 추가
```java
alist.addFirst("Giants");

alist.addLast("bears");
```
맨앞에는 Giants 를 맨뒤에는 bears를 추가하게 된다.

### 3.반복자 Iterator
```java

Iterator<String> it = alist.iterator();
		
		while(it.hasNext()) {		              //hasNext 있을때 까지 돌린다. (있으면 true, 없으면 false)
			String value = it.next();
			System.out.println("it: "+value);			
		}
```
it.next()는 다음 값으로 넘어간다는 의미로 볼 수 있다. 

반복자 Iterator는 해쉬맵과 트리맵에서 더 많이 사용 된다.

