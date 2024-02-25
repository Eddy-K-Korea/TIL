# JAVA OOP 
## 1. 은닉성 (캡슐화 encapsulation)

외부와의 차단으로 변수들을 관리할 수 있다. class의 내부에서 접근 (처리)이 가능하도록 접근 지정자로 제어 할 수 있다.

### 종류
1. private    → 주로 멤버변수에 쓰임
2. public     → 주로 멤버메서드에 쓰임
3. protected  → 상속

#### Private
- 멤버변수에 사용하며 외부로 부터 변수에대한 접근을 차단한다. 
- 접근을 위해서 setter/ getter 를 설정하여 변수를 관리한다.
- 만약 setter없이 getter만 설정하면 '읽기전용'으로 적용된다.

> #### this
> - setter를 생성할때 자기 자신을 가르키는 예약어이다.
> - 클래스 내부의 멤버변수 자신을 가르키며 메서드의 파라미터와 구분하여 지정할 때 사용한다.
---

### 예제
- #### 문제
MyTv2클래스의 멤버변수 isPowerOn, channel, volume을 클래스 외부에서 접근할
수 없도록 제어자를 붙이고 대신 이 멤버변수들의 값을 어디서나 읽고 변경할 수 있도록
getter와 setter메소드를 추가하라.작성한 MyTv2클래스에 이전 채널(previous channel)로 이동하는
기능의 메소드를 추가해서 실행결과와 같은 결과를 얻도록 하시오.
- #### MyTv2 클래스
```java
public class MyTV2 {

	private boolean isPowerOn;
	private int channel;
	private int volume;
	private int prev;
	
	
	final int MAX_VOLUME = 100;
	final int MIN_VOLUME = 0;
	final int MAX_CHANNEL = 100;
	final int MIN_CHANNEL = 1;
	
	
	public boolean isPowerOn() {
		return isPowerOn;
	}
	public void setPowerOn(boolean isPowerOn) {
		this.isPowerOn = isPowerOn;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {         ★★★★★
		prev = this.channel;
		this.channel = channel;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public void gotoPrevChannel() {           ★★★★★
		this.setChannel(prev);
	}
}
```
각 멤버변수에 private를 이용하여 접근제어를 하였고 get/set을 하여 관리할 수 있게 클래스를 생성하였다. 
★로 표시된 곳은 _이전 채널_의 함수를 적용하기 위한 작업이다. 

```java
public void setChannel(int channel) {         
		prev = this.channel;
		this.channel = channel;
	}
```
1. prev 멤버변수에 처음채널||이전 설정된 멤버변수의 채널 을 저장해준다.
2. 그후 메서드의 파라미터 int channel로 받은 값을 멤버 변수로 넣어준다.

```java
	public void gotoPrevChannel() {           
		this.setChannel(prev);
	}
```
setChannel() 메서드에 이전의 저장하였던 채널의 값을 파라미터로 넣은 식을 설정해준다.
이러면 gotoPrevChannel()메서드를 외부에서 실행하였을때 prev의 채널값이 설정이되고 이전채널로 돌아가게 된다.
```java

public class MainClass {

	public static void main(String[] args) {

		MyTV2 t = new MyTV2();
		
		t.setChannel(10);
		System.out.println("CH: "+ t.getChannel());
		t.setChannel(20);
		System.out.println("CH: "+ t.getChannel());
		t.gotoPrevChannel();
		System.out.println("CH: "+ t.getChannel());
		t.gotoPrevChannel();
		System.out.println("CH: "+ t.getChannel());
		
	}
}

//결과 
CH: 10
CH: 20
CH: 10
CH: 20
```
