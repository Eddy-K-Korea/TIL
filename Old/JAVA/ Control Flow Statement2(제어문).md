### 반복문 (for)
  - 주로 반복횟수를 알고 있을 때 사용
  - 컬렉션과 배열을 처리할 때 더 효과적인 문법
  
  for 문의 형식
  
```
for( 초기화; 조건 ; 연산식){
    처리
}
```
for 문의 처리 순서

```
for( 초기화(1); 조건문(4)(7)(10) ; 연산식(3)(6)(9)){
        처리(2)(5)(8)
					
}
출력(11)
```
(1) 부터 (11) 까지 위와 같은 방식으로 처리를 한다.


- *for 문의 쓰임의 예제*

1. 범위의 합

```
int sum = 0;  
		
for(int i = 1; i <=1000; i++) {
  sum = sum + i;
}
```
해당 for문은 1부터 1000까지의 모든 수의 합을 알 수 있다.

  ◇ 이중 for 문

 - for문안에 for문이 들어가는 형식이며 아래 예제를 통해 설명을 할 수 있다.
 
 1. 구구단
 ```
for(int i = 2; i <= 9; i++) {
  System.out.println(i+" 단");
  
  for(int j = 1; j <= 9; j++) {
    int result = i*j;
    System.out.println(i+ " x "+ j + " = "+ result);
  }
}
 ```
 첫번째 for 문은 2부터 9까지를 반복하며 2일때 아래 for문이 1부터 9까지 반복을하여 최종적으로
 2일때 1부터 9까지 수를 반복하여 보여준다.
 
 2. 별 찍기
 ```
for(int i = 1; i <= 9; i++) {
  for(int j = 1; j <= 5; j++) {
    System.out.print("*");
  }
  System.out.println();
}
 ```
 가장 기본이 되는 별찍기 코드이다. 5개의 일렬로 찍힌 별들이 9층으로 된 형식이다.
 두번째 for문의 조건의 식에 따라 다양한 별 형태를 반들 수 있으며, 큰 for문안에 if 문을 걸어 다른 규칙성을 가진 별찍기
 코드를 구현 할 수 있다.
 ```
for (int i = 1; i <= 9; i++) {
    if (i <= 5) { 	// 별 증가
      for (int j = 1; j <= i; j++) {
      System.out.print("*");
    }
    System.out.println();
    } else {    	// 별 감소
      for (int j = 1; j <= 10 - i; j++) {
      System.out.print("*");
      }
    System.out.println();
    }
}
 ```
 위의 코드는 5번째까지 별이 1부터 5까지 늘어났다가 다시 1까지 줄어드는 형태의 별찍기 코드이다.
 3. 배열을 이용한 별찍기
 ```
**
*
***
*****
*
****

int stars[] = { 2, 1, 3, 5, 1, 4 };
		
for(int i = 0; i < 6; i++) {
	for(int j = 0; j< stars[i]; j++) {
	  System.out.print("*");
	}
	System.out.println();
}
 ```
 위의 코드는 규칙이 없는 들쭉날쭉의 별찍기의 형식이다. 배열을 이용하여 별을 찍을 수 있다.
 
 ◇반복문과 조건문 동시 쓰임

**예제 1**
```
int sum = 0;
int sum2 = 0;
		
for(int i = 1 ; i <= 100; i++) {
  if(i%2==0) {
    sum = sum +i;
  }
  else {
    sum2 = sum2 + i;
  }
}
```
 위 예제는 일정 범위안에 수중 짝수와 훌수의 합을 구하는 코드 이다.
 
 **예제 2**
 ```
int num;
int n = 0;
for(int i = 0; i < 10; i++) {
  n = 10*i;                     // 0 10 20 30.... 90
  if(num > n && num < n +10) {
    System.out.println("num은"+ n + "보다 크고"+ (n+10)+"보다 작거나 같다.");
   }
}
 ```
 변수 num의 숫자에 따라 범위를 알려주는 코드이다. for문안에 if문의 조건을 걸어두었다.
 
 ### 반복문 (while)
 - 순환문으로 for문과 다르게 변수의 초기화를 밖에서 해준다.
 - 무한loop를 사용할 수 있으며 양식은 while(true)의 형식을 가지고 있다.
 
 모든 프로그램은 아래와 같은 양식을 가지는데 이때 while 을 사용 하는것을 알 수 있다.
 
 ```
	1. initialize (초기화) -> init
		 	
	2. loop(순환)
	while(true){
	탈출
	- 1. 입력
	- 2. 출력
	등 세분화 작업
	}
		 	
	3. release(해방)
```
무한 반복 코드

```
w1 = 0;
while(true) {
	System.out.println("w1 = "+ w1);
	w1++;
}
```
