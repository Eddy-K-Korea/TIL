## String Class 2
#### - 숫자인지 문자열인지 판별하는 코드 구현
#### - 정수인지 실수인지 판별하는 코드 구현


### 1. 숫자인지 문자열인지 판별하는 코드 구현

모두 숫자로 되어있으면 "숫자 입니다" 표시 후 숫자로 표현
문자열이 섞여 있으면 "모두 숫자가 아닙니다." 출력 

```
Scanner sc = new Scanner(System.in);
String input;
boolean b = false;
		
System.out.print("입력하세요 : ");
input = sc.next();                                // 숫자 입력
```
```
for (int i = 0; i < input.length(); i++) {
    char ch = input.charAt(i);		                //	문자 하나하나 빼오기(String Class 함수 이용)
    //ASCII code 
    int asc = (int)ch;				                    // 아스키 코드 값으로 대입
    if(asc < 48 && asc > 57) {		                //문자의 아스키 코드 값이 나온다.
      b = false;
      break;
    }
}
```

```
if(b != false) {                                  // 판별
    System.out.println("숫자가 아닙니다.");
}
else {
    System.out.println("숫자입니다.");
    int number = Integer.parseInt(input);         //  문자열을 정수로 변환 후 출력
    System.out.println(number);
}
```

### 2. 정수인지 실수인지 판별하는 코드 구현

숫자를 입력 하였을때 정수이면 "정수 입니다." 를 출력하고
아닐 경우 "실수 입니다." 출력

```
System.out.print("입력하세요 : ");                 // 숫자 입력
input = sc.next();
		
		
for (int i = 0; i < input.length(); i++) {        // 1번과 같은 방법으로 input의 문자열 한개씩 추출
    char ch = input.charAt(i);
    if(ch =='.') {				                        //.9823 이런 경우도 있을 수 있기 때문에 체크를 해야한다.
      b = false;
    }
    else {
      b = true;
    }
}
```

```
if(b != false) {
    System.out.println("실수 입니다.");
}
else {
    System.out.println("정수 입니다.");
}
```
### 3. 대문자 -> 소문자 , 소문자 -> 대문자 문자열로 변환

String Class의 함수인 charAt을 이용하여 문자를 추출하고 ASCII Code 값을 이용하여 해당 값이 대문자인지 소문자인지

판별을 해준다. 이후 만약 대문자라면 값의 아스키 코드값에 +32를 해주면된다.(아스키 코드의 대문자와 소문자의 값의 차는 32이다.)

마찬가지로 소문자라면 -32를 해주면 된다. 아래 코드는 우선 대문자에서 소문자로만 변환해주는 코드이다.

```
System.out.print("문자열 입력: ");
String str = sc.next();
String result ="";

↓코드 = 한문자씩 산출
for (int i = 0; i < str.length(); i++) {
	char c = str.charAt(i);			// <=> indexOf 함수
	if((int)c>=65 && (int)c<=90) {		// 대문자의 경우
		int n = (int)c+32;		// 대문자 변환 부분
		result = result + (char)n;
	}
	else {		//소문자의 경우
		result = result + (char)c;
	}
}
			

```
위의 코드시 *형변환* 을 주의 하자.
