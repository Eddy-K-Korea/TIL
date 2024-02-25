### 변수(Variable)

- #### 데이터를 담는 그릇(공간)

  변수의 값은 변경이 가능하고 하나의 변수에는 하나의 값만 저장할 수 있다. 값을 여러번 저장할 경우 마지막 저장하였던 값을 가지게 된다.
  
- #### 변수 선언 및 초기화

  변수를 사용하기위해서는 선언을 우선 해야하며, 값을 대입하는 것을 초기화라고 한다.
  
- #### 변수 작명 규칙

  1. *예약어*를 사용할 수 없다.
  2. 대,소문자 구분을 하며 숫자부터 작명 할 수 없다.
  3. 이름에 연산자를 사용할 수 없으며, **'_', '$'** 만 사용할 수 있다.
  4. 가능한 흔한 명칭은 피한다.
  5. 낙타 표기법을 따른다.
  
### 변수의 타입
  
* #### 기본형 자료 종류
1. 정수 <br>
  byte -> 1 byte = 8 bit ( -128 ~ 127 까지 표현 가능) <br>
  short -> 2 byte <br>
  int -> 4 byte = 32 bit (java의 정수 기본 자료형) <br>
  long -> 8 byte (초기화시 값 맨 끝 L을 붙여줘야함) <br>
  
2. 실수 <br>
  float -> 4 byte (초기화시 값 맨 끝 f를 붙여줘야함) <br>
  double -> 8 byte (java의 실수 기본 자료형) <br>
  
3. 문자형 <br>
  char -> 2 byte (ASCII Code 취급) 문자를 내부적으로 정수값으로 저장
  
4. 판정형 <br>
  boolean -> 1 byte (true/false 판별 )
  
  ![주석 2020-05-19 184303](https://user-images.githubusercontent.com/65350890/82311670-fdfb3e00-9a00-11ea-96c5-f9104a1df9b9.png)
  
* ### 변수의 스왑(Swap)
  ```
    class swap{
      public static void main(String[] args){
        
        int x;
        int y;
        int num;
        
        x = 1;
        y = 2;
        
        num = x;                    -> num에도 x 값이였던 1을 대입시킨다.
        x = y;                      ->  x 에 y 의 값을 대입하여 x = 2 의 값을 가진다.
        y = num;                    -> y에 아까 num의 값을 대입하여 1 의 값을 대입시킨다.
        
        x = 2;
        y = 1;
      }
    }
 ```
 
  
