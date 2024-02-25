## Exception (예외)

### 예외란?

- 예외는 에러가 아니다! 예상치 못한 입력이나 상황에 발생하는 것이다.

#### 종류: 
1. number -> format 1 ~ 3 -> 'A' 숫자만 입력해야하는데 문자를 입력했을때
2. array -> index number [3] -> array[3]  배열인덱스가 없는데 호출하려 할때
3. class -> Scanner 못찾는 경우  Class가 없는 경우
4. file -> 없을 경우  


예외가 발생했을때 메세지를 출력하거나 예외를 실행 시켜줄 수 있다.

```
try{

예외1가 나올 수 있는 소스
예외2가 나올 수 있는 소스
			
}catch(예외클래스1 e){
메세지
}catch(예외클래스2 e){
메세지
}catch(예외클래스3 e){
메세지
}finally{	// 생략이 가능
// 무조건 실행
// 뒤처리
파일 close
Database 원상복구 	rollback -> undo
}			
```

이런 형식은 코드가 복잡해 질 수 있다. 따라서 함수를 이용하여 함수에 예외처리를 해준다.

```

static void func(){		
		int array[] = { 1, 2, 3 };
		try {				
			for (int i = 0; i < 4; i++) {
				System.out.println(array[i]);
			}
		}catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}		
}
```

함수 안에 try , catch구문을 작성하여 줄 수 있다.

```
static void func1() throws ArrayIndexOutOfBoundsException{		
		int array[] = { 1, 2, 3 };				
		for (int i = 0; i < 4; i++) {
			System.out.println(array[i]);
		}			
}
```
위의 소스코드 방식은 throws를 이용하여  예외처리하였다. 함수명 옆에 throws를 이용하여 예외를 처리해 줄 수 있다.


예외(Exception) 의 종류는 많다. 구글에서 검색 가능하다. 일일이 전부 외울 수 없으므로 이클립스의 도움을 받거나

예외처리시 Exception 소스코드만 적어주면 모든 예외가 처리된다.

```

// StringIndexOutOfBoundsException
		String str1 = "abc";
		
		try {
			str1.charAt(3);
		}catch (Exception e) {
			System.out.println("String문자열의 범위 초과");
		}
```
위 코드는 StringIndexOutOfBoundsException 예외 이나 catch (Exception e) 에서 Exception으로 예외를 처리 하였다.
