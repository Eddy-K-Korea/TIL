```java
package main;

import observer.ObserverA;
import observer.ObserverB;

public class MainClass {

	public static void main(String[] args) {

		/*
		 
		 Observer : 감시, 정찰
		 			감시자 패턴 => class 감시
		 */
		
		MyClass cls = new MyClass();
		
		//감시자를 추가
		cls.addObserver(new ObserverA());
		cls.addObserver(new ObserverB());
		
		cls.setId("abc123");
		cls.setPassword("1004");
		
		cls.notifyObservers(cls.getPassword());
	}

}
```
결과는  observer A 와 observer B 모두 getPassword()를 통해 1004를 읽어 내고 출력하였다.
