# 이진탐색 

## 정의
이진 탐색(binary search)는 정렬되어 있는 자료들의 집합에서 특정 자료를 찾고자 할 때 많이 사용되며 매우 빠른 탐색 알고리즘이다.<br>
값의 집합의 가운데와 비요하여 찾고자하는 값이 가운데 값보다 작으면 가운데 값이하의 값들중에서 다시 가운데 값과 비교하고 만약 가운데 값보다
찾고자하는 값이 크다면 큰쪽에서 의 가운데 값과 비교하여 탐색하는 방식이다.

<b>탐색 속도 = O(logN) 이다. 빠르지만 조건으로 값들이 정렬이 되어 있어야한다.</b>


## 재귀함수를 이용한 이진탐색 구현

```java
public static int binarySearch(String [] items, String target, int begin, int end) {
		if(begin>end) {
			return -1;
		}
		else {
			int middle = (begin+end)/2;
			int compResult = target.compareTo(items[middle]); ★ compareTo 함수 = 타켓이 매개변수보다 작을경우 음수 , 같을경우 0 , 클경우 양수의 값을 준다.
			if(compResult == 0) {
				return middle;
			}
			else if(compResult<0) {
				return binarySearch(items, target, middle-1, end);
			}
			else {
				return binarySearch(items, target, middle+1, end);
			}
		}
	}
```
