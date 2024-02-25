# Count Cell 픽셀 숫자 세기 with 재귀함수

## 이미지의 픽셀을 세는 알고리즘
- 연결되어있는 픽셀이 몇개인지 카운팅하는 메소드를 구현한다.
![픽셀](https://user-images.githubusercontent.com/65350890/86790689-a776bb80-c0a3-11ea-8895-ad4e26c9818f.PNG)

```java
private static int BACKGROUND_COLOR = 0;
	private static int IMAGE_COLOR = 1;
	private static int ALREADY_COUNTED = 2;
	private static int N = 8;
	private static int[][] grid =  {
			{1,0,0,0,0,0,0,1},
			{0,1,1,0,0,1,0,0},
			{1,1,0,0,1,0,1,0},
			{0,0,0,0,0,1,0,0},
			{0,1,0,1,0,1,0,0},
			{0,1,0,1,0,1,0,0},
			{1,0,0,0,1,0,0,1},
			{0,1,1,0,0,1,1,1}
	};
```
선언 된것과 같이 흰색= 0, 픽셀 = 1 이다. Already_count는 재귀함수를 통해 숫자를 카운팅하고 색을 변환하여 중복 카운팅 되지 않게 하기 위함이다.

```java
public static int countCell(int x, int y) {
		if(x<0 || x>=N || y<0 || y>=N) {    // grid의 외부 값은 카운팅하지 않는다.
			return 0;
		}
		else if(grid[x][y] != IMAGE_COLOR) {    // 흰색배경일 경우 카운팅하지 않는다.
			return 0;
		}
		else {
			grid[x][y] = ALREADY_COUNTED;     // 이미지 픽셀일 경우 다른색으로 변환을 우선해주고 아래 재귀함수들을 통해 카운팅한다.
			return 1 + countCell(x-1, y+1) + countCell(x, y+1)+ countCell(x+1, y+1)   //현재 픽셀을 카운팅하여 1 + 이후 다음 좌표의 픽셀을 확인한다.
			+ countCell(x-1, y) + countCell(x+1, y)+ countCell(x-1, y-1) + countCell(x, y-1)
			+ countCell(x+1, y-1);
		}
	}
```
대각선도 연결된 것으로 간주 하기 때문에 현재 좌표 주변을 모두 재귀함수로 확인 및 카운팅 하여 더해준다.

```java
public static void main(String[] args) {

		int count = countCell(1, 5);
		System.out.println(count);
				
	}
```
(1,5)의 좌표에 연결된 모든 픽셀(묶음)을 카운팅하면 13개가 나온다.
