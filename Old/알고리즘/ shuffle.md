### 4대 알고리즘 
- 셔플 (shuffle)
- 정렬 (효율)
- 트리
- 탐색 (속도)

그 중 아래 코드는 셔플에 관련된 코드이다.
트럼프 카드를 무작위로 섞어서 52장을 랜덤 숫자, 무늬, 카드 번호 로 출력하였다.

1. 초기화
```java
int r_num[] = new int[52];			      // 랜덤 번호 
boolean swit[] = new boolean[52];			//중복을 체크를 위한 설정
int r;
int w;
```

2. 랜덤 번호 배정 (중복 되지 않게 배정)
```java
for (int i = 0; i < swit.length; i++) {
  swit[i] = false;
  }
w =0;
while(w<52) {						            //중복없이 swit배열에 랜덤하게 숫자 다 넣기
    r = (int)(Math.random()*52);	  // 0 ~ 51
    if(swit[r]==false) {
      swit[r]=true;
      r_num[w] = r;
      w++;
    }
}
```

3. 카드 무늬 와 실제 카드 번호 초기화
```java
int cardNum;  // 1 ~ 13
int cardPic;	// 0 ~3  0: 스페이드 1: 다이아 2: 하트 3: 클로버
```
4. 카드 번호 (A ,J,Q, K가 있어 설정을 해줘야한다.)
```
for(int i = 0; i <r_num.length; i++) {
			
cardNum = r_num[i] % 13 + 1; // 1 ~13
    if(cardNum == 1) {	//A
      System.out.print("card number:"+r_num[i]+ ", "+"A");
    }
    else if(cardNum == 11) {	//J
      System.out.print("card number:"+r_num[i]+ ", "+"J");
    }
    else if(cardNum == 12) {	// Q
      System.out.print("card number:"+r_num[i]+ ", "+"Q");
    }
    else if(cardNum == 13) {	//K
      System.out.print("card number:"+r_num[i]+ ", "+"K");
    }
    else {	// 그 외의 수
      System.out.print("card number:"+r_num[i]+ ", "+cardNum);
    }
```

5. 무늬 설정
```java
cardPic = r_num[i] /13;		// 0 ~ 3
			
switch(cardPic) {
    case 0:
      System.out.println(" 스페이드");
      break;
    case 1:
      System.out.println(" 다이아");
      break;
    case 2:
      System.out.println(" 하트");
      break;
    case 3:
      System.out.println(" 클로버");
      break;
  }
}              // for(int i = 0; i <r_num.length; i++) 의 괄호 닫힘이다.
```

위의 코드는 "랜덤 숫자, 카드 번호, 카드 무늬" 순으로 출력이 된다.
