## Sorting (정렬) 
- 4대 알고리즘
- 정의: 숫자의 크기에 따라서 순번대로 배치하는 처리.
- 오름차순 정렬(큰값이 뒤로), 내림차순 정렬(제일 큰 값이 맨 앞으로)이 있다.		
- 종류 : 선택, 버블, 합병, 퀵 (효율이 1등)


> 코딩을 할때는 정렬만 되면 되기 때문에 가장 쉬운 선택 정렬을 학습한다.

```java
int number [] = {1, 5, 2, 4, 3};
int temp;  // swap용 변수
		
for (int i = 0; i < number.length-1; i++) {										 				
    for (int j = i + 1; j < number.length; j++) {	           
      if(number[i]<number[j]) { 		                        
        temp = number[i];
        number[i] = number[j];
        number[j] = temp;
      }
    }
}
```

i의 범위인 number.length -1 은 선택정렬 처리시 맨마지막 수는 비교할 대상이 없기 때문에 -1을 하여준다.

이중 for문의 j = i + 1 은 j가 0번째 배열부터 비교가 아닌 1번째 배열의 수부터 비교 하기때문에 +1을 한다.

예) i = 0 일때 number[0] 과 number[1] 을 비교해야하기 때문에 j 는 +1 이 되는것이다.

그 다음 처리는 temp를 이용하여 배열의 값을 if문의 조건에 맞게 위치조정한다.

if조건문의 조건에서 부등호 > 는 오름차순 < 는 내림차순 으로 정렬 할 수 있다.
