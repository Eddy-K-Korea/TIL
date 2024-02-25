# Eusild
## 최대공약수를 구하는 방법

### 예제 1
```java
public class Euclid {

	public static void main(String[] args) {

		int result = gcd(10,8);
		System.out.println(result);
	}
★  m>=n 을 가정한다
	public static int gcd(int m, int n) {
		if(m<n) {
			int tmp = m;
			m = n;
			n =tmp;
		}
		if(m%n == 0) {
			return n;
		}
		else {
			return gcd(n, m%n);
		}
			
	}
  ```
  - 위의 코드는 m,과 n의 크기에 대한 조건이 붙는다.
  - m과n의 크기에 대해 스왑을 한후 나머지 값을 구해 최대 공약수를 구한다. 이때도 역시 재귀함수를 활용하였다.
  
  ![유클리드](https://user-images.githubusercontent.com/65350890/86501632-a1e45180-bdd5-11ea-91fb-2d42ac7c7947.PNG)
  
  ### 예제 2
  ```java
 public class Euclid {

	public static void main(String[] args) {

		int result = gcd2(10,8);
		System.out.println(result);
	}
  	public static int gcd2(int p , int q) {
		if(q== 0) {
			return p;
		}
		else {
			return gcd2(q,p%q);
		}
	}
}
```
- 좀 더 단 순한 버전으로 마찬가지로 재귀 함수를 활용하여 값을 구하는 코드이다.
- q가 0아 아닐때 gcd2 메소드를 계속 반복하면서 나머지 값이 0이 될때까지 실행되고 q가 0이 되었을때의 p가 최대공약수가 된다.
