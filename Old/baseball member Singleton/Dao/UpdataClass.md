# (Dao) UpdataClass
```java
import java.util.Scanner;

import DaoInterface.DaoImpl;
import dto.Batter;
import dto.Human;
import dto.Pitcher;
import singleton.SingletonCls;

public class UpdataClass implements DaoImpl {

	Scanner scan = new Scanner(System.in);

	public UpdataClass() {
	}
	
	@Override
	public void process() {
		SingletonCls sc = SingletonCls.getInstance();

		System.out.print("수정하고 싶은 선수명 = ");
		String name = scan.next();

		int findIndex = SelectClass.search(name);
		if (findIndex == -1) {
			System.out.println("선수명단에 없습니다.");
			return;
		}

		Human human = sc.list.get(findIndex);

		if (human instanceof Pitcher) {
			System.out.print("승: ");
			int win = scan.nextInt();

			System.out.print("패: ");
			int lose = scan.nextInt();

			System.out.print("방어율: ");
			double defence = scan.nextDouble();

			Pitcher pit = (Pitcher) human;
			pit.setWin(win);
			pit.setLose(lose);
			pit.setDefence(defence);

		} else if (human instanceof Batter) {
			System.out.print("타수: ");
			int batCount = scan.nextInt();

			System.out.print("안타수: ");
			int hit = scan.nextInt();

			System.out.print("타율: ");
			double hitAvg = scan.nextDouble();

			Batter bat = (Batter) human;
			bat.setBatcount(batCount);
			bat.setHit(hit);
			bat.setHitAvg(hitAvg);

		}
	}
}
```
