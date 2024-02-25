# Recusion 재귀함수, 순환함수

### - 반복문과 같이 반복해서 함수를 수행하는 함수.
### - 무한반복 할 수 있으며, 조건을 달아주면 원하는 만큼 메소드를 반복하여 값을 얻을 수 있다.

## 1. 기본 (n까지의 합)
```java
public class Main {

	public static void main(String[] args) {

		
		int result = func(4);
		System.out.println(result);
	}

	public static int func(int n) {
		if (n == 0)       ★ Base case
			return 0;
		else
			return n + func(n - 1);  ★ Base case 조건
	}
}
```
 - 조건 무한 반복에 빠지지 않으려면 무한이 반복되지 않는 base case( 끝나는 지점이)가 있어야한다.  
 - base case 까지 갈 수 있는 조건이 있어야한다. 
 
 ![func(int n) 풀이](https://user-images.githubusercontent.com/65350890/86501308-ad824900-bdd2-11ea-9be0-6970539b0a99.PNG)
 
 ## 2. 제곱수 (n^n)
 ```java
 public class Main2 {

	public static void main(String[] args) {

		double result = power(2.0,2);
		System.out.println(result);
	}

	public static double power(double x, int n) {
		if(n==0)
			return 1;
		else
			return x*power(x,n-1);
	}
}
```
- x와 n승의 값을 구할때 재귀함수를 통해 구할 수 있다. 
- 코드 변화는 2*power(2.0,1)  ☞  2*power(2.0, 0) == 2* 1 (return 값을 받음) ☞ 2*2 ☞ result = 4

