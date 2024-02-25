```java
package main;

import java.util.Observable;

public class MyClass extends Observable { // 일반클래스 (Human)

	private String preArg = null;

	private String id;
	private String password;

	// 일반 메소드

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void notifyObservers(Object arg) {

		String str = (String) arg;

		// 클래스의 변화가 없다 -> 통지 안함.
		if (str.equals(preArg))
			return;

		// 변화가 있음
		preArg = str;

		setChanged(); // reset

		super.notifyObservers(arg);
		clearChanged();
	}
}
```
일반 클래스에 Observable 클래스를 상속한다.

일반 클래스에서 get set 함수를 이용하여 값을 지정해줄때 override 된 notifyObservers 메서드를 통해 

변화를 알 수 있다. 
