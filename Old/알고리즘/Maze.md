# Maze (미로찾기) with 재귀함수

## 재귀함수를 이용하여 미로(x,y)를 시작하여 출구(N,N)을 찾는 알고리즘이다.

![미로](https://user-images.githubusercontent.com/65350890/86788903-a47acb80-c0a1-11ea-866c-e19f4f28e5fb.PNG)

위와 같은 미로의 방식으로 이중배열을 이용하여 만들 수 있다.

```java
private static int N = 8;
	private static int[][] maze =  {
			{0,0,0,0,0,0,0,1},
			{0,1,1,0,1,1,0,1},
			{0,0,0,1,0,0,0,1},
			{0,1,0,0,1,1,0,0},
			{0,1,1,1,0,0,1,1},
			{0,1,0,0,0,1,0,1},
			{0,0,0,1,0,0,0,1},
			{0,1,1,1,0,1,0,0}
	};
```
- 총 8X8의 미로이다.
- 길 = 0, 장애물 = 1 로 표기하였다.
- 최종 출구는 8,8이다.

## 길 찾는 메소드
```java
private static final int PATHWAY_COLOUR = 0;
	private static final int WALL_COLOUR = 1;
	private static final int BLOCKED_COLOUR = 2;
	private static final int PATH_COLOUR = 3;
	
	public static boolean findMazePath(int x, int y) {
		if(x<0 || y<0 || x>=N || y>=N) {         // 미로 범위 이외의 값을 입력했을때 false
			return false;
		}
		else if(maze[x][y] != PATHWAY_COLOUR) {     // 길 (0)이 아닌곳을 갔을때 false
			return false;
		}
		else if(x==N-1 && y==N-1) {     // 출구에 도착하였을때 true
			return true;
		}
		else {
			maze[x][y] = PATH_COLOUR;     // 길(0)인곳을 도착하였을때 그 길은 우선 3으로 변환하고 아래 재귀함수를 통해 다시 길을 찾는다.
			if(findMazePath(x-1, y) || findMazePath(x, y+1) || findMazePath(x+1, y) || findMazePath(x, y-1)){
				return false;
			}
			maze[x][y] = BLOCKED_COLOUR;  // 위의 조건에서 모두 false일 경우 결국 현재의 길은 2의 값으로 변환되고 false가 된다.
			return false;
		}
	}
```
상수로 표기한 부분에서 2가지 더 추가 하였다. PATH_COLOUR는 0인 길을 한번 지나온 길을 표기한다. 이렇게 표기하는 이유는 한번 지나간 후 막다른 길에 다다랐을때 다시 되돌아와야한다.
만약 아예 되돌아 오지 못하게 하면 출구를 찾을 수 없기 때문에 한번 되돌아 오게한다. 그리고 되돌아온 후 그 길은 BLOCKED_COLOUR로 표기하고 다시는 갈 수 없게 표기한다.
```java
public static void printMaze() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		
		printMaze();
		findMazePath(0, 0);
		System.out.println();
		printMaze();
	}
  ```
  작성한 메소드를 실행하면 아래와 같이 미로가 바뀌어 있다.
  ```
00000001
01101101
00010001
01001100
01110011
01000101
00010001
01110100

  ↓
  
22222221
21121121
22212221
21221122
21112211
21222121
22212221
21112130
```
  
