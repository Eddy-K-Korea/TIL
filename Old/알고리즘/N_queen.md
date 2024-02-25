# 체스판에 queen 서로 겹치지 않게 놓기

### -> 체스판 위에 가로,세로,대각선을 갈 수 있는 queen을 서로 겹치지 않게 배치하는 알고리즘 
#### - 해당 알고리즘은 상태공간트리(해가 존재한다면, 트리의 한 노드에 반드시 해가 있음)
![1](https://user-images.githubusercontent.com/65350890/87042177-42030600-c22e-11ea-8f68-70caedce2db4.PNG)
---
#### - 상태공간트리 탐색 = 깊이 우선 탐색(트리의 모든 노드를 탐색할 필요는 없음),겹치게 놓는 queen은 제외하고 탐색
#### - 따라서 Backtracking 하여 탐색
![2](https://user-images.githubusercontent.com/65350890/87042211-51824f00-c22e-11ea-8ca6-f3b3e2912ebc.PNG)
---
#### 코드 구현
- 만약 내가 4X4의 체스판에 말을 놓는다고 가정한다면
```java
int N = 4;
```
1. 내가 현재 어떤 노드의 단계에서 탐색하고 있는지 매개변수를 통해 단계를 표시한다(level은 행이된다) 또한, 성공인지 실패인지 확인을 위해 return값은 bool로 한다.
```java
public static boolean queens(int level) {
}
```
2. level(행)에서 내가 어디에 말을 놓을지 cols[N+1]에 표시를 한다.(cols[]는 열이 된다)(배열이기 때문에 +1)
```java
int cols[] = new int [N+1];
```
3. 트리의 노드가 언제 자식노드가 없을지 확인해야한다. 그러면 이전에 놓인 말과 가로,세로,대각선에 말이 있는 경우 배제. 
```java
public static boolean promising(int level) {
		for (int i = 1; i < level; i++) {             //level 단계이전까지 전부 확인위한 for문
			if(cols[i] == cols[level]) {   // i번째 행의 이미 놓인 말의 열과(queen메소드에서 한번 전체 말을 놓는다)과 level번째 놓으려는 말의 열이 같으면
				return false;       // 놓을 수 없다
			}else if(level-i == Math.abs(cols[level]-cols[i])){   //대각선의 말 확인, level행의 cols[level]열의 말과 i행의 cols[i]열의 말의 거리와
                                                            //(가로),행의차(세로)거리가 같으면 대각선이라는 의미이다 
				return false;                     // 위의 두가지 경우는 모두 말을 놓을 수 없는 상황의 조건이다
			}
		}
		return true;
	}
```
- 대각선의 위치 그림
![3](https://user-images.githubusercontent.com/65350890/87044890-226ddc80-c232-11ea-9969-1c7609405f91.PNG)

4. level(단계)와 N이 같으면 모든 말을 겹치지 않게 놓았다는 의미가 된다.(모든 더이상 자식 노드가 없는 노드까지 탐색)
```java
else if(level == N) {
			for (int i = 1; i <=N; i++) {
				System.out.println("("+i+","+cols[i]+")");    // 배치를 확인하는 용도
			}
			return true;
```      
5. level+1행에 말을 각각열에 놓고 재귀함수 호출하여 확인
```java
else {
			for (int i = 1; i <= N; i++) {
				cols[level+1] = i;  //각 열에 말 놓기
				if(queens(level+1)) { //재귀함수 호출하여 다음 level 확인
					return true;
				}
			}
			return false;
		}
```
### 최종 알고리즘 구현
```java
public class N_Queens {
	public static int N = 4;  // 4X4 체스판 
	public static int cols[] = new int [N+1];
	
	public static boolean queens(int level) {
		if(!promising(level)) {
			return false;
		}else if(level == N) {
			for (int i = 1; i <=N; i++) {
				System.out.println("("+i+","+cols[i]+")");
			}
			return true;
		}else {
			for (int i = 1; i <= N; i++) {
				cols[level+1] = i;
				if(queens(level+1)) {
					return true;
				}
			}
			return false;
		}
	}
	public static boolean promising(int level) {
		for (int i = 1; i < level; i++) {
			if(cols[i] == cols[level]) {
				return false;
			}else if(level-i == Math.abs(cols[level]-cols[i])){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		queens(0);  // 첫 노드부터 시작
	}
}
```
