# Calendar 내부 함수

## Calendar 클래스 호출 방법

1. Calendar cal = new GregorianCalendar();

2. Calendar cal = Calendar.getInstance();

### 날짜 

- getter
```java
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH)+1;  
int day = cal.get(Calendar.DATE);
``` 
month 에서 월을 0 ~ 11로 출력을 하기 때문에  +1 을 해주어야 우리가 보는  1월 ~ 12월로 표기할 수 있다.

- setter
```java
cal.set(Calendar.YEAR,2021);
cal.set(Calendar.MONTH,Calendar.JANUARY);
cal.set(Calendar.DATE, 25);


year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH)+1;   // 0 ~ 11월로 표시
		day = cal.get(Calendar.DATE);
```
setter을 이용하여 연, 월 , 일 의 값을 대입하고 get을 이용해 출력을하면 원하는 날짜를 얻을 수 있다.

### 요일

```java
int weekday = cal.get(Calendar.DAY_OF_WEEK);

//1 ~ 7 / 일(1) 월(2) 화(3) 수(4) 목(5) 금(6) 토(7)
switch(weekday) {
			case 2:
				System.out.println("월요일 입니다.");
}
```
요일은 숫자로 나오며 일 ~ 토 까지 1부터 7의 숫자로 표현된다.

### 지정한 달의 마지막 날짜 
- 2월의 경우 28일 29일 나머지 달의 경우 30일 또는 31일로 그 달의 마지막 날을 알 수 있다.
```java
int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
```

#### 참고 (달력만들기)
```java
cal.set(Calendar.DATE, 1);                                  // 1일				 
		weekday = cal.get(Calendar.DAY_OF_WEEK);             //1 ~ 7 /월 화 수 목 금 토 일
		int up = (weekday - 1)%7;                             // 달력의 위의 빈칸 개수
		System.out.println("윗쪽 빈칸의 수: "+up);
		
		lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);    //달의 마지막 날짜 구하기
		System.out.println("lastDay: " + lastDay);
		
		//밑의 빈칸
		cal.set(Calendar.DATE, lastDay);
		weekday = cal.get(Calendar.DAY_OF_WEEK);
		
		int down = 7 - weekday;
		System.out.println("밑쪽의 빈칸의 수 : "+ down);
		System.out.println();
		
		//달력 출력
		year = 2021;
		month = 5;
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DATE, 1);
		
		int startday = cal.get(Calendar.DAY_OF_WEEK);                 //요일을 구하는거
		int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);      //마지막
		int _date = 1;
		
		System.out.println(year + "년"+month+ "월");
		System.out.println("----------------------------------------------------");
		
		String week_day = "일월화수목금토";
		
		for (int i = 0; i < week_day.length(); i++) {
			char c = week_day.charAt(i);
			System.out.print(c + "\t");
		}
		System.out.println();
		
		System.out.println("----------------------------------------------------");
		
		//윗쪽 빈칸
		for (int i = 1; i < startday; i++) {
			System.out.print("*"+"\t");
		}
		
		//날짜
		for (int i = 0; i < lastday; i++) {
			System.out.print(_date+"\t");
			if((_date + startday-1)%7 == 0) {
				System.out.println();
			}
			_date++;
		}
		//밑 빈칸
		for (int i = 0; i < (7-(startday+lastday -1) %7); i++) {
			System.out.print("*"+"\t");
		}
		System.out.println();
		System.out.println("----------------------------------------------------");
	}
```
