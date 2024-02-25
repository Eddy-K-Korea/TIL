# Interface 

## 용도
- 협업을 하면서 설계의 사양을 빠르게 파악 할 수 있게 해준다.
- 확정성에 용이하며, 관리를 효율적으로 하게 해준다.

## 특징
- 다중 상속이 가능하다 (Abstract Class는 다중상속 불가)
- 껍데기만 있기 때문에 인터페이스만 생성이 안된다.
- 변수 선언이 불가하며, method의 경우에도 이름만 선언이 가능하다(처리내용 선언불가)

## Example

### NameCard

#### 1. NameCard Class
```java
public class NameCard {

	String name;
	String phone;
	String email;
	
	//class가 아닌 interface
	
	PrintNameCard printNameCard;
	
	public NameCard(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public void setPrintNameCard(PrintNameCard p) {
		this.printNameCard = p;
	}
	
	public void print() {
		printNameCard.print(this);
	}
}
```
#### 4. PrintNameCard Interface
```java
public interface PrintNameCard {

	public void print(NameCard nc);
}
```

#### 5. PrintNameCard1 Class
```java
public class PrintNameCard1 implements PrintNameCard {

	@Override
	public void print(NameCard nc) {

		System.out.println("이름:"+nc.name);
	}
}
```
PrintNameCard 인터페이스를 implements 하여 메서드를 받아 오버라이드 하였다. NameCard의 인스턴스를 받으면 이름만 출력하도록한 클래스

#### 6. PrintNameCard2 Class
```java
public class PrintNameCard2 implements PrintNameCard {

	@Override
	public void print(NameCard nc) {

		System.out.println("이름:"+nc.name);
		System.out.println("번호:"+nc.phone);
		System.out.println("이메일:"+nc.email);
	}
}
```
PrintNameCard 인터페이스를 implements 하여 메서드를 받아 오버라이드 하였다. NameCard의 인스턴스를 받으면 이름,번호,이메일을 출력

#### 7. Main Class
```java
public class MainClass {

	public static void main(String[] args) {

		NameCard nCard = new NameCard("홍길동", "123-4567", "abc@naver.com");
		
		nCard.setPrintNameCard(new PrintNameCard2());
		nCard.print();
	}
}
```
nCard라는 인스턴스를 생성하였고 생성자를 통해 정보는("홍길동", "123-4567", "abc@naver.com")이다. 

setPrintNameCard의 파라미터로 PrintNameCard2를 생성하여 넣어주었다.

PrintNameCard2의 클래스는 이름,번호,이메일을 모두 출력하는 클래스로 nCard.print() 실행시 모든 정보를 볼 수 있다.

### ※ 여기서는 인터페이스를 통해 PrintNameCard1, PrintNameCard2 의 method 부분을 오버라이드 할 수 있다는 것을 알 수 있다.

