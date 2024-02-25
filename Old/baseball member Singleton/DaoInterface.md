# DaoInterface

- 인터페이스를 통해 각 dao의 클래스에게 process라는 메서드를 오버라이드 하였다.
- 각 클래스는 process 메서드만 처리를 하며 메인에서 보면 인터페이스 객체를 통해 process의 처리가 간략하게 표현되는것을 볼 수 있다.

```java
public interface DaoImpl {

	public void process();
		
}
```
