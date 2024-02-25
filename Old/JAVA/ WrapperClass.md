## Wrapper Class
#### - 일반 자료형을 클래스화 한것.
```
  일반자료형				class
  boolean				Boolean
		 
  byte					Byte
  short					Short
  int					Integer (★)
  long					Long
		 
  float					Float
  double	                        Double  (★)
		 
  char					Character
  char[]				String (★★★★★)
```

### 1. 숫자 -> 문자열
123 + "";    123.456 + ""; (문자열로 바뀜) => "123.456"


### 2. 문자열 -> 숫자
```
		String strNum = "234";
		int cnum = Integer.parseInt(strNum);
		System.out.println("cnum = "+(cnum+ 1));		//연산을 통해 문자열에서 정수 값으로 바뀐것을 확인
		
		String dstrNum = "123.456";
		double dnum = Double.parseDouble(dstrNum);
		System.out.println("dnum = "+ (dnum + 0.5));
```

### 3. 10진수 -> 2진수

```
int n10 = 12;
String n2 = Integer.toBinaryString(n10);
System.out.println("n2: "+ n2);
```

### 4. 2진수 -> 10진수

```
n10 = Integer.parseInt(n2, 2);		// 1. 앞은 변경할 변수 , 2. 앞의 변수의 진수
System.out.println("n10: "+n10);
```

### 5. 10진수 -> 16진수

```
n10 = 333;
String n16 = Integer.toHexString(n10);
System.out.println("n16: "+n16);
```

### 6. 16진수 -> 10진수

```
n16 = "8cd";
n10 = Integer.parseInt(n16,16);
System.out.println("n10: "+n10);
```

