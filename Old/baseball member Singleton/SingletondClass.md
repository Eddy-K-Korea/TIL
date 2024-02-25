# Singleton Class
- Dao 패키지의 각 클래스들은 Dto의 정보가 담긴 ArrayList를 필요로한다. 
- 여기서 싱글톤 패턴을 활용하여 각 클래스에게 ArrayList의 값을 한줄의 소스코드로 제공이 가능하다.
```java
SingletonCls sc = SingletonCls.getInstance();
```
아래 singletonClass는 BaseBallmember의 ArrayList를 담고 제공한다.
```java
import java.util.ArrayList;
import java.util.List;

import dto.Human;

public class SingletonCls {

	private static SingletonCls sc = null;
	public List<Human> list = null;
	
	private SingletonCls() {
		list = new ArrayList<Human>();
	}

	public static SingletonCls getInstance() {
		if (sc == null) {
			sc = new SingletonCls();
		}
		return sc;
	}
}
```
