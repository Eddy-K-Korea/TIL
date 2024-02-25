### 제어문(Control Flow Statement)
  - 제어문이란, 프로그램 실행 흐름을 개발자가 원하는 방향으로 바꿀 수 있도록 해주는 것이다.
  - 일반적으로 조건식과 실행 구문인 중괄호(블록, { })으로 구성되어 있다.
#### 제어문의 종류
 - 조건문(decision-making statements): if문, switch문
 - 반복문(looping statements): for문, while문
 - 분기문(branching statements): break, continue, return
---

#### 조건문(if)

  - 조건식의 결과에 따라 블록 실행여부가 결정
  - 조건식에는 boolean 변수 또는 true/false 값을 산출하는 연산식( 논리 연산식, 비교 연산식 등)
  - 조건식이 true이면 블록 실행, false이면 실행하지 않음

 ◇ 조건 분기
 
  else , else if 를 이용하여 다양한 조건을 처리할 수 있게 할 수 있다.
  
```
                number = 85;
		
		if(number == 100) {
			System.out.println("100점 입니다.");
		}
		else if(number >= 90 && number < 100) {
			System.out.println("90점 이상입니다.");
		}
		else if(number >= 80 && number < 90) {
			System.out.println("80점 이상입니다.");
		}
		else {
			System.out.println("80점 미만 입니다.");
		}
```
  ◇ 이중 조건문
```
  if(number >=80 && number < 90) {
    if(number >= 85) {
      System.out.println("합격입니다.");
    }
    else {
      System.out.println("불합격입니다.");
    }
  }
```

위의 코드는 number 가 80점 이상 90점 미만이 true일 경우 다음 if 문으로 넘어간다.

다음 if문에서 조건이 true이면 합격, false는 모두 불합격 출력을 하게 된다.

---

#### 조건문(switch)

  - if문과 달리 변수가 어떤 값을 갖느냐에 따라 실행문이 선택
  - if문의 경우의 수가 많아질 수록 switch 문을 사용하는 것이 효과적
  - 괄호 안의 동일한 값을 갖는 case로 가서 실행문을 실행
  - 동일한 값의 case가 없으면 default로 가서 실행문 실행(생략 가능)
  - 변수 값에 따라 case가 실행된 후 제어문을 빠져나가기 위해 break; 사용

```
String operator;

switch (operator) {
  case "+":
  System.out.println("두 수의 합은 : " + (num1 + num2));
  break;
case "-":
  System.out.println("두 수의 뺄셈은 : " + (num1 - num2));
  break;
case "*":
  System.out.println("두 수의 합은 : " + (num1 * num2));
  break;
case "/":
  System.out.println("두 수의 합은 : " + (num1 / num2));
default:
  break;
}
```

operator의 값이 사칙 연산자중 입력이 되면 그 값을 가진 case의 출력값을 확인 할 수 있다.
예를 들어 내가 + 를 입력을 하면 case "+"의 출력 값인 "두 수의 합은 : (num1 + num2)"의 출력을 확인 할 수 있다.
