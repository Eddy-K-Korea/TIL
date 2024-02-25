# Singleton Pattern

### - 하나의 instance에 접근 하여 송수신 할 수 있는 패턴

### 기본 형태
```java
public class SingletonClass {

	private static SingletonClass sc = null;
	
	private SingletonClass() {
	}
	
	public static SingletonClass getInstance() {
		if(sc == null) {
			sc = new SingletonClass();
		}
		return sc;
	}
}
```

getInstance()를 이용해 싱글톤 인스턴스를 불러올 수 있다. 싱글톤 인스턴스는 하나 밖에 생성을 하지 못하기 때문에

다른 Class사이에서 변수,Object 등의 정보를 넘겨줄때 중계자로써 역할을 해준다.

이전의 싱글톤이 없는 방식은 main에서 get/set 메서드를 이용하여 넘겨주는 것과는 더 효율적으로 값을 넘겨 줄 수 있다.

### - 싱글톤을 이용하여 [BaseBall member]를 다시 구조를 변경하였다. 해당 소스코드를 보고 이해를 해보자.
