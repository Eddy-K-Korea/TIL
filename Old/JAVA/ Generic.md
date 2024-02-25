# Generic

## 제네릭이란?
- template(형태)라고 할 수 있으며, 자료형의 변수이다.
- 같은 클래스에 다양한 자료형을 사용할 때 쓰인다.

```java
class Box<T>{			//  <T> : Generic   -> T 는 사용자 지정 가능하다.
	
	T temp;
	
	public Box(T temp) {
		this.temp = temp;
	}
	
	public T getTemp() {
		return temp;
	}
	
	public void setTemp(T temp) {
		this.temp = temp;
	}
}
```
T 라는 형태를 생성하고 다른 자료형처럼 지정하고 불러올 수 있다.

```java
public static void main(String[] args) {
    Box<Integer> box = new Box<Integer>(123);  // <>안에는 오브젝트가 들어가야 한다.
		System.out.println(box.getTemp() +1 );
		
		
		Box<String> sBox = new Box<String>("my world");
		System.out.println(sBox.getTemp());
}   
```

앞서 선언하였던 box 클래스의 T (형태)부분에 Integer로 초기화를 해주면 box 클래스의 T 였던 부분이 모두 Integer 형태로 변환되고

Integer의 값을 대입 할 수 있는것을 알 수 있다.

```java
class BoxMap<Key,Value>{		// 여러개를 지정할 수 있다. ',' 를 이용하여
	Key key;
	Value value;
	
	public BoxMap(Key key, Value value) {
		super();
		this.key = key;
		this.value = value;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
}
```
key, Value와 같이 두개의 형태를 ',' 를 이용하여 사용 할 수 있다.
