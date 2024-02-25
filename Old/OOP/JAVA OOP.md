# Object Oriented Programming

> 절차지향 -> 객체 지향 (설계지향)

> class MyClass{<br>
  변수(멤버변수)	-> 접근지정(외부, 내부)<br>
  함수(메서드) -> 처리<br>
  }    
  
절차 지향에서는 순서를 중요시하였다. 하지만 객체 지향 에서는 처리 방식이 무엇이 있는지 <br>
만 신경쓰면 나머지는 순서에 맞게 호출만 해주면 된다.
  
  ```
클래스명 변수(instance) = new 클래스명();<br>
	   -> MyClass cls = new MyClass<br>
	   	cls 는 스택 영역에 올라가게된다.  new MyClass 는 힙에 올라간다.<br>
	   	MyClass cls = null; -> (0) 로 셋팅한다.
 ```
 
 ## Sorting (객체화)
 
 ```java
 package sortingClass;

import java.util.Scanner;

public class Sorting {
	// 멤버 변수(2가지 이상 처리(메서드)에서 접근 해야하는경우), 아래 멤버 함수에 모두 접근이 가능하다.
  
	int number[];
	boolean updown;

	// 처리
	public void input() {
		Scanner sc = new Scanner(System.in);

		System.out.println("정렬할 갯수: ");
		int count = sc.nextInt();

		number = new int[count];

		for (int i = 0; i < number.length; i++) {
			System.out.println((i + 1) + "번째 수 : ");
			number[i] = sc.nextInt();
		}

		System.out.println("오름(1)/내림(2) = ");
		int ud = sc.nextInt();
		if (ud == 1)
			updown = true;
		else
			updown = false;
	}

	public void sorting() {
		for (int i = 0; i < number.length - 1; i++) {
			for (int j = i + 1; j < number.length; j++) {
				if (updown) {
					if (number[i] > number[j]) {
						swap(i, j);
					}
				} else {
					if (number[i] < number[j]) {
						swap(i, j);
					}
				}
			}
		}
	}

	public void swap(int i, int j) {
		int temp = number[i];
		number[i] = number[j];
		number[j] = temp;
	}

	public void result() {
		for (int i = 0; i < number.length; i++) {
			System.out.println(i + ":" + number[i]);
		}
	}
}

 ```
 Sorting 클래스를 만들고 안에 멤버 변수와 그를 이용한 메서드를 생성하였다.
 
 ```java
 package sortingClass;

public class MainClass {

	public static void main(String[] args) {

		Sorting sort = new Sorting();
		sort.input();
		sort.sorting();
		sort.result();
		
	}
}
 ```
 Main클래스에서 인스턴트를 생성하고 Sorting 클래스의 매서드를 이용하여 user가 입력한 수를 정렬하는 프로그램을 만들었다.
 
이전 방식보다 소스코드가 더 명확해지고 가독성이 좋아졌다.
