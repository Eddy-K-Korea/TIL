# Abstract Class (추상 클래스)

## 특징
- 추상 클래스만 생성이 불가능하다.
- 상속받은 클래스에서 정의를 한 후 사용이 가능하다.

## 형태
- abstract int method(char c, int i);

## Example
### AbstactClass
```java
public abstract class AbstractClass {   ★★ 추상 클래스
		//추상
	
	private int number;
	
	public void method() {		// 일반 메소드
		System.out.println("AbstractClass method()");
	}
	
	public abstract void abstractMethod();    ★★ 추상 메서드
}
```
### ChildClass
```java
public class ChildClass extends AbstractClass{

	@Override
	public void abstractMethod() {   
		System.out.println("ChildClass abstractMethod()");
	}
}
```

### MyClass
```java
public class MyClass extends AbstractClass {

	@Override
	public void abstractMethod() {
		System.out.println("MyClass abstractMethod()");
	}
}
```
### ChildClass 와 MyClass 모두 추상 메서드를 오버라이드 하였다. 

### Main Class
```java
public class MainClass {

	public static void main(String[] args) {
    AbstractClass cls1 = new ChildClass();  // 주로 사용하는 방법
		cls1.abstractMethod();			          //추상 클래스로 인해 over ride되어 "ChildClass abstractMethod()"으로 출력
		cls1.method();					              //기존 AbstractClass에 선언하였던 method로 출력
		AbstractClass cls2 = new MyClass();
		cls2.abstractMethod();		          	//추상 클래스로 인해 over ride되어 "MyClass abstractMethod()"으로 출력
		cls2.method();
	}
}
```
cls1 Method와 cls2 method는 다른 출력을 보여준다.
