# 자료구조 Tree
## 비선형 구조
#### 비선형 구조는 자료가 계층적으로 구성이 되어있다. 선형구조인 (스택, 큐, 리스트 등)은 비선형 구조와 구조도 다르지만 용도도 다르게 쓰인다.
#### 일반적으로 선형구조는 자료를 저장하고 꺼내는것에 초점이 맞춰져 있다. 그에 반면 비선형 구조는 표현에 초점이 맞춰져 있다. 대표적으로 컴퓨터의 폴더나 조직도, 족보 등이있다.

![Tree 기본 용어 및 모양](https://user-images.githubusercontent.com/65350890/85537608-14736580-b64f-11ea-91d6-9bcd7713bf32.png)
![Tree 종류](https://user-images.githubusercontent.com/65350890/85537731-2ead4380-b64f-11ea-8324-7389fbf5660b.png)

### Tree 구현 및 탐색
- 간단하게 이진 트리 구현과 전위탐색을 작성해 보았다.

##### 기본 Node 생성과 Left , right Node 생성 코드
```java
public class Tree {

	public Object data;
	public Tree left;
	public Tree right;

	public Tree() {     
	}
	
	public Tree(Object item) {    //데이터값을 가지고 left, right Node의 생성을 위한 생성자
		left = null;
		right = null;
		data = item;
	}

	public void makeLeftNode(Tree subNode) {      // 왼쪽 Node를 생성하는 메소드
		if (this.left != null) {
			this.left = null;
		}
		this.left = subNode;
	}

	public void makeRightNode(Tree subNode) {     // 오른쪽 Node를 생성하는 메소드
		if (this.right != null) {
			this.right = null;
		}
		this.right = subNode;
	}

	public Object getData() {     // Node의 값을 얻는 메소드
		return this.data;
	}

	public Tree getLeftSubNode() {      // Left Node 가 무엇인지 출력해주는 메소드
		return this.left;
	}

	public Tree getRightSubNode() {     // right Node 가 무엇인지 출력해주는 메소드
		return this.right;
	}
}
```
##### Tree 구현 및 탐색
```java
import tree.Tree;

public class Main {

	public static void main(String[] args) {

		Tree n1 = new Tree(1);
		Tree n2 = new Tree(2);
		Tree n3 = new Tree(3);
		Tree n4 = new Tree(4);
		Tree n5 = new Tree(5);
		
		n1.makeLeftNode(n2);
		n1.makeRightNode(n3);
		n2.makeLeftNode(n4);
		n2.makeRightNode(n5);
		
		preOrder(n1);
		
	}
	public static void preOrder(Tree root) {
		if(root != null) {
			System.out.println(root.data);
			preOrder(root.left);
			preOrder(root.right);
		}
	}
}
```
### 총 5개의 Node를 생성하여 n1 Node를 Root로 하여 좌,우측 Node를 생성하였다. 아래 그림은 위 소스코드의 Tree 모형이다.

![구현한 Tree 모형](https://user-images.githubusercontent.com/65350890/85539024-64066100-b650-11ea-9179-12ecaf4e431f.png)

### preOrder 메소드는 전위탐색방법으로 Tree의 모든 값을 출력해준다. 전위 탐색은 Root -> Left -> Right 방향으로 탐색을하며, 생성한 Tree를 전위탐색하면
### 1 -> 2 -> 4 -> 5 -> 3 의 순서로 값이 출력이 된다.
