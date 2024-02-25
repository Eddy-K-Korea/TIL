# (Dao) DeleteClass
```java
import java.util.Scanner;

import DaoInterface.DaoImpl;
import dto.Human;
import singleton.SingletonCls;

public class DeleteClass implements DaoImpl {

	Scanner scan = new Scanner(System.in);
	
	public DeleteClass() {
	}
	
	@Override
	public void process() {
		SingletonCls sc = SingletonCls.getInstance();
		
		System.out.print("삭제하고 싶은 선수명 = ");
		String name = scan.next();

		if (name.equals("")) {
			System.out.println("이름을 정확히 입력해 주십시오.");
			return; 
		}
		int findIndex = SelectClass.search(name);	  //search 메서드 호출
		if (findIndex == -1) { 
			System.out.println("선수명단에 없습니다. 삭제 할 수 없습니다.");
			return;
		}
		// 삭제
		Human h = sc.list.remove(findIndex);
		System.out.println(h.getName()+"의 데이터가 삭제 되었습니다.");
	}
}
```
