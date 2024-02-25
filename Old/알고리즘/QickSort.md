# 퀵 정렬
## 정렬 방법
1. pivot을 선정하여 전체 데이터를 pivot을 기준으로 나눈다. (pivot은 맨 마지막값)(분할)
2. 각 데이터 정렬
3. 합병(퀵 정렬에서는 큰 구현이 없다)
- 마지막값 pivot선정 -> pivot보다 작은애들 왼쪽에 배치 나머지 오른쪽 배치(partition) -> 정렬

#### Pivot 선정
- 사실 첫번째값이나 마지막 값을 피봇으로 선택하는것은 그다지 좋은 방법은 아니다.
	 	그러나 첫번째, 마지막, 가운데 값의  중간값을 피봇을 선택 후 정렬하여도 시간 복잡도가 달라지지 않음
	 	랜덤하게 피봇 선택 -> 입력에대한 최ㅏ악은 없지만, 최악의 실행은 있을 수 있다.

### 시간 복잡도
- 최악: n^2 (정렬된 데이터의 가장 큰수가 pivot 으로 선정될 경우)
- 최선: nlogn ( 밸랜스 맞게 분할이 되어 정렬이 될경우)
- 평균적인 시간복잡도를 계산하여도 nlogn의 시간 복잡도의 결과가 나온다.

## 1. 퀵 정렬
```java
public static void quickSort(int[]A,int p, int r) {
	if(p<r) {
		int q = partition(A,p,r);  // q = pivot의 위치 
		quickSort(A, p, q-1);
		quickSort(A, q+1, r);
	}else {
		return;
	}
}
```  
- partition 함수의 역할 : pivot을 정하고 피봇보다 작은값들은 왼쪽에 배치, 큰수를 오른쪽에 배치 그리고 피봇값을 return

##2. Partition 메소드
```java
	public static int partition(int[]A, int p , int r) {
		int i = p-1;
		int x = A[r];
		int tmp;
		for (int j = p; j < r ; j++) {
			if(A[j]<=x) {
				i++;
				tmp = A[i];
				A[i]=A[j];
				A[j]=tmp;
			}
		}
		tmp = A[i+1];
		A[i+1] = A[r];
		A[r]=tmp;
		return i+1;
	}
```
- [작은 값][큰         값][피봇] -> [작은 값][피봇][큰          값] 으로 바꾸어야한다.
- 우선 [작은 값][큰         값][피봇]을 우선적으로 진행
- 변수 i = 피봇보다 작은 값들 중 마지막 값,,, j는 현재 검사하려는 값

## 확인
```java
	public static void main(String[] args) {
		int A[]= {67,3,14,5,19,77,33};
		quickSort(A, 0, 6);
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]+",");
		}
	}
```  
결과 = 3,5,14,19,33,67,77
