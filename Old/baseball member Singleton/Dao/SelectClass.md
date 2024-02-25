# (Dao) SelectClass 

```java
import java.util.Scanner;

import DaoInterface.DaoImpl;
import dto.Batter;
import dto.Human;
import dto.Pitcher;
import singleton.SingletonCls;

public class SelectClass implements DaoImpl {

	Scanner scan = new Scanner(System.in);

	@Override
	public void process() {
		SingletonCls sc = SingletonCls.getInstance();

		System.out.print("검색하고 싶은 선수명 = ");
		String name = scan.next();

		int findIndex = search(name);
		if (findIndex == -1) {
			System.out.println("선수명단에 없습니다.");
			return;
		}
		System.out.println("번호: " + sc.list.get(findIndex).getNumber());
		System.out.println("이름: " + sc.list.get(findIndex).getName());
		System.out.println("나이: " + sc.list.get(findIndex).getAge());
		System.out.println("신장: " + sc.list.get(findIndex).getHeight());

		if (sc.list.get(findIndex) instanceof Pitcher) {
			System.out.println("승리: " + (((Pitcher) sc.list.get(findIndex)).getWin()));
			System.out.println("패전: " + ((Pitcher) sc.list.get(findIndex)).getLose());
			System.out.println("방어율: " + ((Pitcher) sc.list.get(findIndex)).getDefence());
		} else if (sc.list.get(findIndex) instanceof Batter) {
			System.out.println("타수: " + ((Batter) sc.list.get(findIndex)).getBatcount());
			System.out.println("안타수: " + ((Batter) sc.list.get(findIndex)).getHit());
			System.out.println("타율: " + ((Batter) sc.list.get(findIndex)).getHitAvg());
		}
	}

	★ 아래 유틸리티는 select처럼 검색이고 static을 이용하여 어디서든지 불러쓸 수 있게 한다.
  
	public static int search(String name) {
		SingletonCls sc = SingletonCls.getInstance();

		int index = -1;
		for (int i = 0; i < sc.list.size(); i++) {
			Human h = sc.list.get(i);
			if (name.equals(h.getName())) {
				index = i;
				break;
			}
		}
		return index;
	}
}
```
