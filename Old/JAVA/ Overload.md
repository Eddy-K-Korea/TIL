## Overload

- 함수명은 같고 매개변수(인수, 인자)의 자료형이나 갯수가 다른것을 의미

함수(메서드)의 이름은 같다 하지만 생성시 받는 인수의 차이가 있다.

```JAVA
   아래 함수의 이름은 모두 같다

  
  static void funcName(){
    System.out.println("funcName() 호출")
  } 
  
	static void funcName(char c) {
		System.out.println("funcName(char c) 호출");
	}
	
	static void funcName(int i) {
		System.out.println("funcName(int i) 호출");
	}
	
	static void funcName(char c, int i) {	// getter
		System.out.println("funcName(char c, int i) 호출");
	}
	
	static void funcName(int i, char c) {	// getter
		System.out.println("funcName(int i, char c) 호출");
	}
```

다만 main 메서드에서 불러올시 인수에 맞게 구현을 해야 해당 그 함수가 불러와 진다.
