## String Class
---
- wrapper class이며, 문자열을 저장, 편집, 정보를 취득할 때 사용한다.

객체 생성방법
String str1 = new String("하이");
String str1 = ("하이");   위에 방식이 정석 , 아래 방식도 가능하다.

> 1. 비교 함수 (equals)
  ```
  String str5 = "hello";
  String str6 = "hell";
		
  str6 = str6 + "o";
		
  if(str5 == str6) {
    System.out.println("str5와 str6는 같은 문자열 입니다.");
  }
  else {
    System.out.println("str5와 str6는 다른 문자열 입니다.");
  }
		
  boolean b = str5.equals(str6);
  if(b== true) {
    System.out.println("str5와 str6는 같은 문자열 입니다.");
  }
  ```
  
  equals 함수를 사용하면 정확하게 문자열이 같은지 확인 할 수 있다.
  ('==' 를 사용한 if문은 다른 문자열이라고 출력한다.)
  
> 2. 지정문자의 위치 (indexOf, lastIndexOf)
  ```
  char cArr[] = {'a','b','c'};
		
  String str7 = "abcdefghabcd";
  int n;
  n = str7.indexOf("d");
  System.out.println("n = "+ n);  // d는 3번째 위치한다. n = 3 의 출력값을 보여준다.
				
  n = str7.lastIndexOf("d");
  System.out.println("n = "+ n); 
  ```
  lastIndexOf는 뒤에서 부터 d가 몇번째 인지 알려준다.
  
> 3. 길이 (length) -> len
  ```
  int Array[] = {1,2,3,4,5};
  System.out.println("배열의 길이 "+ Array.length);
		
  str7 = "I can do it";
  int len = str7.length();                  // 함수이기 때문에 뒤에 ()가 붙는다.
  System.out.println("문자열 길이 "+ len);
  ```
> 4. 수정 (replace)
  ```
  String str8 = "A*B**C";
  String repStr = str8.replace("*", "+");
  System.out.println("repstr: "+repStr);
  ```
  replace(기존 문자, 바꿀 문자) 로 함수를 사용하며, 보통 replace(" ", "")의 형식으로 공백을 지울때 사용한다.
  
  공백을 지우는 이유는 공백으로인해 문자를 탐색하지 못할때 문자열의 모든 공백을 지우고 탐색하도록 한다. 주로 빅데이터에 사용됨
  
> 5. split (token을 이용하여 문자열을 자른다)
```
String str9 = "홍길동-25-2001/10/23-180.3";
String splitArr[] = str9.split("-");
  for (int i = 0; i < splitArr.length; i++) {
    System.out.println(splitArr[i]);
  }
  
  => 홍길동
     25
     2001/10/23
     180.3
```
aaa-bbb-ccc 여기서 ' - ' 를 token으로 지정한다.(일부 다른 부호로 변경 가능함) 토큰형식으로 문자열을 지정하는 이유는
database 에 이런 형식으로 저장되기 때문에

> 6. substring : 범위를 통해 문자열 취득
```
String str10 = "abcdefghij";
String subStr = str10.substring(0, 4);		    // 처음부터 4번째 전까지 불러온다.
System.out.println("subStr = "+ subStr);
		
subStr = str10.substring(4);		              // 지정해준 숫자번재부터 끝까지 불러온다.
System.out.println("subStr = "+ subStr);
```

> 7. toUpperCase , toLowerCase (모든문자를 대문자로 변경), (모든 문자를 소문자로 변경)
```
String str11 = "abcDEF";
String upStr = str11.toUpperCase();
System.out.println("upStr = "+ upStr);
		=> ABCDEF  출력
    
String lowStr = str11.toLowerCase();
System.out.println("lowStr = "+ lowStr);
    => abcdef 출력
```
> 8. trim : 앞뒤의 공백을 없애주는 함수 
```
String str12 = "                 JAVA C언어 python      ";
String trimStr = str12.trim();
System.out.println("trimStr = "+ trimStr+ "-_");
=> JAVA C언어 python 앞뒤 공백 없이 출력
```
해당 함수는 우리가 아이디를 입력 했을때 공백이 있을경우 공백을 제거해주고 인식을 하게 해준다. 또한 네어버나 검색 포털사이트에
검색창에 공백있지 단어를 검색하여도 가능한 이유가 trim의 함수를 이용 했기 때문이다.

> 9. valueOf : 문자열을 숫자로 , 숫자를 문자열로 변경할때 사용한다.
```
int num = 123;
long lo = 1234L;
double d = 123.3456;
		
String is = String.valueOf(num);
String is1 = String.valueOf(lo);
String is2 = String.valueOf(d);
		
System.out.println(is + " " + is1+ " "+ is2);
=> 모두 문자열로 변경되어 출력된다.
		
int number = 234;
String numStr = number + "";	  // 어떤 자료형이든 끝에 문자열을 넣어주면 자동을 바뀐다. 이 방식을 더 많이 사용
```
> 10. contains 탐색
```
String str13 = "서울시 강남구";
boolean b1 = str13.contains("서울");
System.out.println("b1 = "+ b1);
		=> "서울"이라는 단어가 있으면 true 없으면 false 를 출력한다. 해당 코드는 true 이다.
```
> 11. charAT 탐색
```
String str14 = "가나다라마";
char ch = str14.charAt(3);
System.out.println("ch = "+ ch);
=> '라'를 출력한다.
```
charAT(값)의 값 다음 문자가 출력이 된다.
