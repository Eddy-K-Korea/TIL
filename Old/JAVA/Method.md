## 함수 (method, function)

(인수,인자 매개변수, 파라미터)를 value로 넣음

-> 처리 

-> return 값으로 반환 (return값은 하나 or 없음)

### 종류

1. 파라미터 x 나오는 값 x
2. 파라미터 o 나오는 값 x
3. 파라미터 다수 return 값 x
4 파라미터 다수 return 값 o
5. 파라미터 x return 값 o

#### 목적
- 코드의 간략화
- 동일한 코드(같은 처리)를 집약적으로 설정
- 가독성을 높이기 위해

#### 형식				
```
                            ↓ (인수, 인자, 매개변수, 파라미터)
return 값 (자료형) 함수명(자료형 가상인수, ...){

           ↓   
----------처리-------------
	
           ↓ 
	return 값;  <- 없을 수도 있음
}

return 값 (자료형): char int double int[] String
void(return값이 없을때)
```

#### 예시
```
int funcName(char c, double d){
처리
return 값; <- 정수(int)
```

---

##### 종류의 예시
```
// 1. 들어오는 파라미터 x 값 x
	static void function() {
		
		System.out.println("function() 호출");
	}
	// 2. 들어오는 파라미터 1 값 x
	static void function1(int n) {
		System.out.println("function1(int n) 호출 ");
		System.out.println("파라미터를 통한 가인수 n = "+ n);
	}
	//3. 들어오는 파라미터 n개 값 x
	static void function2(String str, double d) {
		System.out.println("function2(String str, double d) 호출");
		System.out.println("str = "+ str);
		System.out.println("d = "+ d);
	}
	//4. 들어오는 파라미터 1개 값 1
	static int function3(double d) {
		System.out.println("function3(double d) 호출");
		System.out.println("d = "+ d);
		return (int)d;      //double 형이 데이터가 더 커서 강제 형변환을 해주어야 한다.
	}
	//5. 들어오는 파라미터 x 값 만 있는 경우
	static int function4() {
		System.out.println("function4() 호출");
		return 24;
	}
```
---

### value, address 의 할당

```

int arrNum[]= {1,2,3};
		function2(arrNum);

```
위의 코드 처럼 main 함수에서 배열을 초기화하고 function2의 함수를 구현했다고하자.

```

                          ↓ arrNum[] 이 직접 넘어가서 값이 대입이 된다. 해당 부분이 return 이된다.
	static void function2(int arr[]) {		          //address 에 의한 할당
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i] *2;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.println("arr[" + i+ "] = "+ arr[i]);
		}
	}
```
이때 arr[]을 파라미터로 받는다고 하였으며 보통은 value의 값이 대입이 되지만 배열의 경우 address가 대입이 되어

그 자체 arrNum[]이 대입이 된다. 따라서 아래 처리(곱셈 2 )가 처리가 되고 return을 하지 않아도 자동으로

arrNum으로 값이 설정이 되어지게 된다.

#### 예제 

- 두수를 나누고 몫과 나머지를 구해라 (조건 : 구현 method 는 한개)

```

int num1, num2;
int value, tag[];
tag = new int[1];

value = div(num1, num2, tag[]);
System.out.print("몫 :"+ value +" 나머지: "+tag[0]); 
```

- Method
```

static int div(int n1, int n2, int tag[]){
  int v;
  v = n1 / n2;
  tag[0] = n1 % n2;

  return v;
}
```

main 부분에서 tag라는 배열을 생성하고 Method부분에서 배열을 이용하여 나머지값을 넣는 용도로 사용하였다.

그 이유는 조건이 method는 하나만 구현할 수 있었기 때문에 return값에는 몫을 대입하고 address할당을

이용하여 tag[0]을 출력하여 나머지 값을 대입하여 출력할 수 있도록 구현 하였다.

---
### 가변 인수

가변 인수 (변하는 파라미터)

매개변수(파라미터)가 유동적이다.

개발자용

```

static void allocParam(int...num) {			// ... 3번 써야함 
int sum = 0;
		
	for (int i = 0; i < num.length; i++) {		// num이 배열의 형태로 들어온다.
		sum = sum + num[i];
	}
System.out.println("합계 : " + sum);
}
```
가변인수를 사용하게 되면 함수를 사용할때 여러개의 파라미터를 받을 수 있다. ... 은 3번 사용해야하며

가변인수는 항상 맨 마지막에 사용하도록 한다.

```
static void func(String str, String...num) {	// 가변인수를 항상 맨 뒤에 위치
	System.out.println("str = "+str);
	for (int i = 0; i < num.length; i++) {
		System.out.print(num[i]+ " ");
	}
System.out.println();
}
```
