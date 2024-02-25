# 합병 정렬

## 분할 정복법
- 분할: 해결하고자 하는 문제를 작은 크기의 동일한 문제들로 분할 (예: 최댓값을 구하기위해 전체범위에서 반을 갈라 각각최대 값을 구하고 두 값을 비교)
- 정복: 각각의 작은 문제를 순환적으로 해결(재귀함수)
- 합병: 작은 문제의 해를 합하여 원래 문제에 대해 해를 구함

### 합병정렬
1. 데이터가 저장된 배열을ㅇ 절반으로 나눔
2. 각각 순환적으로 정렬
3. 정렬된 두개의 배열을 합침

- 합병하는 단계가 가장 중요하다.(실제로 sort가 일어나는 단계)

- 시간복잡도는 n log n 이다. (효율은 

## 1. 합병정렬 코드
```java
public static void mergeSort(int [] A, int p, int r) {        // A[p ... r]을 정렬
	if(p<r) {
		int q = (p+r)/2;                           // p,r의 중간지점
		mergeSort(A, p, q);
		mergeSort(A, q+1, r);
		merge(A, p, q, r);
	}
}	
``` 

```java
public static void merge(int data[], int p, int q, int r) {
	int i = p, j = q+1, k=p;
	int[] tmp = new int [data.length];
	while(i<=q && j<=r) {
		if(data[i]<=data[j]) {	    // 한쪽이 끝날때 까지
			tmp[k++]=data[i++];     // 다음 배열의 인덱스로 하나씩 이동
		}else {
			tmp[k++]=data[j++];
		}
	}
	while(i<=q) {	    // 실행 조건 앞쪽 리스트에 값이 남아있다 -> 그대로 값을 tmp에 저장
		tmp[k++]=data[i++];
	}
	while(j<=r) {	    // 뒷쪽 리스트에 데이터가 남아있다. (위 while과 이것과 둘중 하나만 실행)
		tmp[k++]=data[j++];
	}
	for(int i1 = p; i1<=r; i1++) {	    // tmp의 정렬된 배열을 다시 원래 배열인 data에 넘겨준다.
		data[i1]=tmp[i1];
	}
}
```  

```java
public static void main(String[] args) {
	int [] array = {45,2,67,90,4,0};    // 예시
		
	mergeSort(array, 0, array.length-1);
	for (int i = 0; i < array.length; i++) {
		System.out.print(array[i]+",");
	}
}
```  
정상적으로 정렬이 되는것을 확인 할 수 있다.
