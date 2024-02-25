# 피보나치 수열
- #### 피보나치 수열이란?
0번째 항은 0, 1번째 항은 1, 그 외 항은 전번, 전전번 항의 합으로 표현

a b c 변수 준비
  a b c  -> 으로 반복 할때 마다 a = b , b = c 가 된다.
  
```java
long a, b, c;
long arrNum[] = new long[30];
		
		
//초기값
a = 0;
b = 1;
		
arrNum[0] = a;
arrNum[1] = b;
```
우선 첫번째 값과 2번째 항의 값을 배열에 넣어준다.
```java
int w = 0;
while(w <28) {
		
c= a+b;
arrNum[w + 2] = c;
// 값을 갱신
a = b;
b = c;
			
w++;
}
```
loop를 반복하면서 
a b c 
  a b c
  
의 형태로 값이 대입되면서 쭉 진행된다.


## 재귀함수 활용
```java
public int  fibonacci(int n) {
	if(n<2)
		return n;
	else
		return fibonacci(n-1) + fibonacci(n-2);
}
```
- n번째 항까지 재귀 함수를 통해 그 항의 값을 구하고 다시 돌아와서 또 다음항의 값을 주는 순환으로 N까지 가게된다.
- 피보나치 수열의 시작은 0 또는 1이다. 따라서 3번째 항부터 전전항과 전항의 합이기때문에 조건을 if(n<2)로 한다.
