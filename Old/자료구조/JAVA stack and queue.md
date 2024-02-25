# 자료구조 
## Stack
### - FILO(first in last out)
- 실린더 형태로 생각할 수 있다. 
- 공(item)을 하나씩 넣으면(push) 1,2,3 순서로 들어간다.
- 공(item)을 하나씩 빼면 (pop) 3,2,1 순서로 들어왔던 반대 순서로 나오게 된다.

## 1. 배열로 스택 만들기
```java
public class ArrayStack {

	private int top; 
	private int maxSize; //스택의 최대 크기
	
	private Object stackArray[]; //여러 자료형 또는 클래스를 넣기위해 object로 배열을 잡는다.(섞어서 넣을 수 있다.)
	
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stackArray = new Object[maxSize];
		this.top = -1; // 처음에는 아무런 값이 없어서(배열이기 때문에)
	}
	
	//stack 공간이 비어있는 확인
	public boolean isEmpty() {
		return (this.top == -1);
	}
  
	//stack 공간이 다 차있는지 확인
	public boolean full() {
		return (this.top == maxSize -1);
	}
	
	//insert
	public void push(Object item) {
		if(full()) {
			System.out.println("스택 공간이 가득 차 있습니다.");
			return;
		}
		top++;
		stackArray[top] = item;
	}
	
	//delete
	public Object pop() {
		Object item = peek();
		top--;
		return item;
	}
	
	public Object peek() {
		if(isEmpty()) {
			System.out.println("스택공간이 비어있습니다.");
			return null;
		}
		return stackArray[top];
	}
}
```

## 2. ArrayList로 스택 만들기
```java
import java.util.ArrayList;

public class ArrayListStack {

	private ArrayList<Object> stackList;
	
	public ArrayListStack() {
		stackList = new ArrayList<Object>();
	}
	
	public boolean isEmpty() {
		return stackList == null;
	}
	
	public void push(Object item) {
		stackList.add(item);
	}
	
	public Object pop() {
		Object item = peek();
		stackList.remove(stackList.size()-1);
		return item;
	}
	
	public Object peek() {
		if(isEmpty()) {
			System.out.println("스택 공간이 비어 있습니다.");
		}
		return stackList.get(stackList.size() -1);
	}
}
```
- ArrayList의 경우 무한으로 저장이 가능하기 때문에 max와 full이 없다.
---
## Queue (큐)
### - FIFO(first in first out)
- 가로로 놓인 긴 통으로 형태를 생각할 수 있다.
- 공(item)을 1,2,3 순서로 push 한다.
- 공(item)이 1,2,3 순서로 다시 pop 할 수 있다.

(예제 추가예정)
