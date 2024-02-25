# Static

## 변수

method안에서 변수를 선언하게 되면 해당 변수를 local변수라고 한다.(자동으로 메모리에 올라갔다가 없어진다.)

이런 지역 변수는 무조건 초기화를 해주어야 한다.

멤버 변수는 클래스를 만들고 선언할때 자동 초기화가 되며, 클래스의 인스턴트를 생성할때 마다 같이 생성이 된다

하지만 static 변수는 메모리의 static에 올라가 한번 생성이되면 계속 유지가 된다.(정적)

예를 들어 MyClass에 두가지 변수를 생성하도록 한다.

```java
public class MyClass {

	int memNum; 
	
	static int staticVar; 
  
  public void method() {		
		
		memNum = memNum +1;			// member
		System.out.println("memNum = "+ memNum);
		
		staticVar = staticVar +1;		//static
		System.out.println("staticVar = "+ staticVar);
}
```

위의 소스코드처럼 memNum과 staticVar변수를 1씩 더하여 Main클래스에서 출력하도록 해보았다.

```java

cls.method();
cls.method();
cls.method();
    
MyClass cls2 = new MyClass();
		
		cls2.method();
    
				//결과
//				memNum = 1
//				staticVar = 1
//				memNum = 2
//				staticVar = 2
//				memNum = 3
//				staticVar = 3
//				memNum = 1
//				staticVar = 4
```
cls 인스턴스를 통해 둘다 1 ~ 3까지 값이 증가하였으며, cls2 객체를 새로 생성하여 출력했을때 memNum은 멤버변수도 새로 생성이되어

다시 0에서 +1 한값이 나오고 staticVar은 값이 유지되어 4까지 증가한것을 확인 할 수 있다.

## 메소드
메소드에서도 static 예약어는 정적인 성격을 볼 수 있다.

아래 새로운 클래스를 생성하였다.

```java
public class Member {

	public Member() {
		System.out.println("Member Member()");
	}
	
	public void init() {
		System.out.println("Member init()");
	}
	
	public void random() {
		System.out.println("Member random()");
	}
	
	public void input() {
		System.out.println("Member input()");
	}
	
	public static Member getInstance() {       ★
		Member m = new Member();
		m.init();
		m.random();
		m.input();
		return m;
	}
}
```
그리고 main 클래스에서 static메서드를 이용하여 한번에 호출을 할 수 있다.

```java
Member mem2 = Member.getInstance();
```
Member 클래스의 인스턴스를 생성하고 바로 Member클래스의 getInstance()메서드를 호출하여 한번에 Member클래스의

함수들을 출력하였다. 

static의 변수는 메모리의 낭비가 있을수 있기 때문에 잘 사용하지 않으나 static메서드는 위와 같은 방법으로 사용을 한다.
