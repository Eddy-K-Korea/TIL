### 제어문 (break, continue)

#### 1. break : 탈출(loop : for, while, do while)을 빠져나올때 사용.
	   단독으로 사용이 불가하며 순환문이나 switch와 같이 사용한다.
	   
```
for(int i = 0; i < 10; i++) {
	System.out.println("i = "+ i);
	if(i == 4) {
		break;
	}
}
System.out.println("탈출 후 ");
```
위 코드는 for문을 이용해 10번 반복을 하는 반복문이다. 이때 i가 4번째일때 break를 통해 반복이 멈추고 loop를 빠져나온다.
또한, 아래 코드는 배열에 저장되어있는 값을 찾을때 사용된 코드이다.
```
char charArr[] = {'A','B','C', 'D','E'};

for(int i = 0; i< charArr.length; i++) {
	System.out.println(i + ":" + charArr[i]);
	if(charArr[i] == 'C') {
		System.out.println("C문자를 찾았습니다.");
		break;
	}
}
```

C문자를 찾으면 반복문을 빠져나와 더이상 코드가 진행되지 않는다.

```
for(int i = 0; i < 10; i++) {
	for(int j = 0; j < 5; j++) {
		if(i == 4 && j == 3) {
			break;			// break 하나당 그 포함된 loop를 탈출한다
		}
	}
}
```
위 코드는 i가 9까지 반복되며 i당 j가 5개가 생성된다. 단 i가 4일때만 j가 3개까지만 생성이되고 break를 통해 두번째 for문을 빠져나간다.

#### 이중for문 한번에 탈출 방법

1. loop문의 갯수에 맞게 break 설정
```
boolean b = false;
for(int i = 0; i < 10; i++) {
  System.out.println("i = "+ i);
  for(int j = 0; j < 5; j++) {
    System.out.println("	j = "+ j);
    if(i == 4 && j == 3) {
      b = true;					
    }
    if(b== true) {
      break;
    }
  }
  if(b== true) {
    break;
  }
}
```

2. 키워드를 활용(goto)
```
loopout:for(int i = 0; i < 10; i++) {
        System.out.println("i = "+ i);
          for(int j = 0; j < 5; j++) {
            System.out.println("	j = "+ j);
            if(i == 2 && j == 1) {
              break loopout;						
            }
          }
        }
```

loopout이라고 이름을 지정해 주고 마지막 break문에 이름을 넣어주면 해당 loop를 한번에 빠져 나온다.


#### 2. continue : 생략 , 그 다음처리를 생략한다. for, while과 같이 사용한다.(단독 사용X)

```
int numArr[] = new int[3];
int n;
		
for(int i = 0; i < numArr.length; i++) {
  System.out.print((i+1) + "번째 수 : ");
  n = sc.nextInt();
  if(n<0) {
    System.out.println("음수입니다.");
    continue;
  }
  numArr[i] = n;
}
for (int i = 0; i < numArr.length; i++) {
  System.out.println("numArr[" + i + "] =" + numArr[i]);
}
```


위의 코드는 i번째 수를 배열에 순서대로 내가 입력한 값을 넣는순서로 처리한다. 입력받은 수가 음수일경우

음수라고 알려주고 continue를 사용하여 아랫부분은 스킵하였다.(배열에 값이 대입되지 않았다.)

이후 i의 2번째 순서로 다시 반복되게 된다.

```
int w = 0;
int arrNum[] = new int[3];
while(w<3) {
  System.out.print((w+1)+"번째 수 : ");
  n = sc.nextInt();
			
  if(n<0) {
    System.out.println("음수입니다.");
    continue;
  }
  arrNum[w] = n;
  w++;
}
		
for (int i = 0; i < arrNum.length; i++) {
  System.out.println("arrNum[" + i + "] =" + arrNum[i]);
}
```
위 코드는 n에 양수를 넣을때 까지 while문을 반복한다. n에 양수값을 넣으면 배열에 값이 저장이되고

반복이 종료가 된다.
