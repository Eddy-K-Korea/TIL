# Thread
- 동작하고 있는 프로그램을 프로세스(Process)라고 한다. 보통 한 개의 프로세스는 한 가지의 일을 하지만, <br>
  이 쓰레드를 이용하면 한 프로세스 내에서 두 가지 또는 그 이상의 일을 동시에 할 수 있게 된다.
  
## 예제 
- Main Class
```java
package main;

public class Main {

	public static void main(String[] args) {

		Thread thread1 = new ThreadEx("퐁");
		Thread thread2 = new ThreadEx("당");
		Thread thread3 = new ThreadEx("!!!");
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
```
- Thread 
```java
public class ThreadEx extends Thread{

	String msg;
	
	public ThreadEx(String msg) {
		this.msg = msg;
	}

	@Override
	public void run() {
		super.run();
		 ★ for for for 3개가 한꺼번에 작동 한다. 먼저 출력되는건 순서가 다를 수 있음. 하지만 공평하게 횟수는 같다.
		for (int i = 0; i < 10; i++) {
			System.out.println(msg);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
```
쓰레드에 문자열을 입력하여 for문을 돌렸다. 동시에 3개의 for문이 돌아가면서 프로세스를 실행한다. 

각 프로세스의 순서는 일정하지 않을 수 있다. 

여기서 sleep의 경우 쓰레드의 run 템포를 조절 할 수 있다. 1000 == 1초
