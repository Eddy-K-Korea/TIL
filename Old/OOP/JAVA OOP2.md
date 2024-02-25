# OOP2 
## 객체 지향의 특징 2
### 상속 & 다형성

- 상속 : 부모클래스에서 기능을 상속한다. 추가로 기능을 확장하는 경우
- 다형성 : 상속 후에 여러형태로 자식클래스가 구현되는것을 의미 

#### Over Ride
- 받은 재산을 수정해서 확장 하는 목적 ( 관리를 효율적으로 하기 위해 이용)
- 각 자식 클래스 마다 오버라이딩 한 메서드의 내용은 달라진다.
- 형식이 완전히 똑같아야 Over Ride가 된다. 
```java
Parent p = new Child();
p.method();         // Child 메서드가 호출된다.(오버라이딩만된것만)
p.func; (Error)		 // Child 의 func 메서드는 못 찾는다. (p(인스턴스)는 Parant의 인스턴스로 생성되어서)
```
★ 만약 Parent 부모 클래스가 있고 Child의 자식 클래스가 있을때 같은 메서드(즉 Over Ride) 된 메서드를 출력한다.
만약 Child 클래스에 func 메서드가 있으나 부모클래스에는 해당 메서드가 없다면, 인스턴스 p 는 func 메서드를 호출 하지 못한다.

```java
Parant human[] = new Parant[4];
		
		human[0] = new ChildOne();
		human[1] = new ChildTwo();
		human[2] = new ChildTwo();
		human[3] = new ChildOne();
		
		// 한번에 다 넣고 man 일때 lady 일때 구분해서 만들어 줄 수 있다.
		
		for (int i = 0; i < human.length; i++) {
			human[i].method();
		}
		
		//결과 
//		ChildOne method()
//		ChildTwo method()
//		ChildTwo method()
//		ChildOne method()
		*/
```

```java
Parant p1 = new ChildOne();
		Parant p2 = new ChildTwo();
		
		p1.method();
		p2.method();
		
		// 아래와 같이 캐스트 변환을 해줘야 한다.
  ChildOne co = (ChildOne)p1;   // 캐스트 변환 (강제 형변환) p1을 ChildOne의 인스턴스로 바꿔준다. 
							  // ChildOne의 함수를 사용할 수 있게 된다.
co.func();
((ChildOne)p1).func();   // 위처럼 새로운 인스턴스에 p1을 넣어도 되고 바로 캐스트 변환해서 사용해도된다.
```
만약 Parant 클래스 인스턴스로 생성했으나 ChildOne 이나 ChildTwo 에 있는 method를 불러오기 위해서는 캐스트 변환을 해주어야 한다.


#### super
- 해당 예약어를 통해 부모 클래스의 생성자를 선택 할 수 있다. 
- 생략이 가능하다. 
- 입력하지 않으면 자동적으로 기본 생성자 생성

> 자식 클래스를 생성할때 상속클래스를 우선적으로 내려 받고 자식클래스의 내용을 호출한다.

#### instanceOf
- 상속 받은 Object를 부모 클래스의 instance로 생성

ChildOne   -> Parant<br>
ChildTwo   -> Parant

- 생성된 instance에 어떤 자식클래스가 생성되었는지 판별할 수 있는 제어자

```java
Parant arrpar[] = new Parant[3];
		arrpar[0] = new ChildOne();
		arrpar[1] = new ChildTwo();
		arrpar[2] = new ChildOne();
	
	
		for (int i = 0; i < arrpar.length; i++) {		
			arrpar[i].method();                        // over ride 된 method for문을 통해 전부 호출 가능
			
			if(arrpar[i] instanceof ChildOne) {	       // istanceOf 를 통해 True false를 알려준다.
				((ChildOne)arrpar[i]).oneMethod();	    // over ride가 아닌 method
			}
		}
```
자식 클래스에 오버라이드가 되지 않은 메서드를 호출하고 싶을때 캐스트변환을 해주어야한다. 

다만 어느 클래스에 있는지 매번 찾지 않고 instanceOf 예약어를 이용하여 호출해 줄 수 있다.
