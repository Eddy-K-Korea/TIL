## 암호화, 복호화 + (method 화)

배열을 이용하여 단편적인 암호화와 복호화를 구현 하였다.

#### 암호화

```

static String security(String src) {
		char[] abcCode =      // a ~z
			{ '`','~','!','@','#','$','%','^','&','*',
			'(',')','-','_','+','=','|','[',']','{',
			'}',';',':',',','.','/'};

			                    // 0 1 2 3 4 5 6 7 8 9
		char[] numCode = {'q','w','e','r','t','y','u','i','o','p'};
		String resultCode = "";
		for (int i = 0; i < src.length(); i++) {
			char ch = src.charAt(i);
			int n = (int)ch;
			
			//알파벳의 경우
			if(n >= 97 && n <= 122) {
				n = n - 97;  // n == 0
				resultCode = resultCode + abcCode[n];
			}
			// 숫자의 경우
			if(n>=48 && n <=57) {
				n = n - 48;  // n ==0
				resultCode = resultCode + numCode[n];
			}
		}
		return resultCode;
}
```

1. 암호로 입력된 문자열을 문자하나씩 추출하여 아스키 코드로 변경한다.
2. 아스키코드(n)은 조건을 걸어 알파벳일 경우와 숫자일 경우로 구분한다.
3. 조건에 걸러진 n 이 알파벳일 경우 -97 을 통해 0이 되고 abcCode[] 배열의 인덱스 넘버로 사용 할 수 있게 된다.
4. n이 숫자일 경우에도 인덱스 넘버로 사용 할 수 있게 되며, 각자 배열에 대입하여 그 인덱스의 값을 불러올 수 있다.
(예: n = 1 , abcCode[1] = '~'  그리고 n = 1, numCode[1] = 'w' )
5. 각 변환된 값을 resultCode에 합해줌으로써 최종 암호화를 return 받을 수 있다.


#### 복호화

```

static String deciphering(String src) {
		char[] abcCode = // a ~z
			{ '`','~','!','@','#','$','%','^','&','*',
			'(',')','-','_','+','=','|','[',']','{',
			'}',';',':',',','.','/'};

			// 0 1 2 3 4 5 6 7 8 9

		char[] numCode = {'q','w','e','r','t','y','u','i','o','p'};
		String baseCode = "";
		
		for (int i = 0; i < src.length(); i++) {
			char c = src.charAt(i);
			
			int index = 0;
			if(c >= 97 && c <=122) {		                //암호화된 숫자
				for (int j = 0; j < numCode.length; j++) {
					if(c == numCode[j]) {
						index = j;	// 0 ~9 index number
						break;
					}
				}
				index = index + 48;
				baseCode = baseCode + (char)index;
			}
			else {							//암호화된 알파벳
				for (int j = 0; j < abcCode.length; j++) {
					if(c == abcCode[j]) {
						index =j;
						break;
					}
				}
				index = index + 97;
				baseCode = baseCode + (char)index;
			}
		}
		return baseCode;
  
  }

```

1. 입력된 문자열을 문자로 하나씩 추출한다.
2. 문자를 아스키 코드값으로 우선 변환하여 알파벳으로 복호화 해야하는지 숫자로 복호화 해야하는지 판단한다.
3. 만약 입력된 문자열의 문자가 알파벳이라고 하면 numCode[]배열에 해당 문자가 있는지 for문을 돌려 전체 배열중 값을 if문으로 판정한다.
4. 일치하는 알파벳이라면 해당 배열의 순서가 결국 숫자로 복호화된 숫자랑 같기 때문에 index에 배열의 순서 값인 j 를 대입한다.
5. 해당 순서에 숫자아스키 코드값이 되기위한 필요 값이 +48이므로 index에 더해준다.
(예: q = 113(아스키코드) , 알파벳이므로 if 절 일치 , for문을 통해 numCode[0] = 'q'임을 찾아냄 j = 0 이므로 index = 0, 
숫자 0으로 +48 을 해주면 아스키코드(48 = 0)으로 문자로 변환되고 baseCode에 문자'0'으로 저장된다.

6. 알파벳도 동일한 방법을 이용하여 문자열에서 추출한 문자와 abcCode[]배열의 동일한 문자를 찾고 배열의 인덱스 j를 활용
7. 알파벳으로 변경하기 위해 그 수에 +97을 하여 나중에 문자로 변경

