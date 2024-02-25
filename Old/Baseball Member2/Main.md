# Main Class
```java
import java.util.Scanner;

import dao.MemberDaoEx;

public class MainClass {
// view
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		MemberDaoEx dao = new MemberDaoEx();
		
		while(true) {
			
			System.out.println("BaseBall Member 관리");
			System.out.println("---------Manu---------");
			System.out.println("1. 선수 추가");
			System.out.println("2. 선수 삭제");
			System.out.println("3. 선수 검색");
			System.out.println("4. 선수 수정");
			System.out.println("5. 선수 모두보기");
			System.out.println("6. 데이터 저장");
			System.out.println("7. 타율 랭킹");
			System.out.println("8. 방어율 랭킹");
			System.out.println("9. 프로그램 종료");
			System.out.println("----------------------");
			System.out.print("메뉴를 선택해 주세요 >>");
			int choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					dao.insert();
					break;
				case 2:
					dao.delete();
					break;
				case 3:
					dao.select();
					break;
				case 4:
					dao.update();
					break;
				case 5:
					dao.allPrint();
					break;
				case 6:
					dao.saveData();
					break;
				case 7:
					dao.hitAvgSorting();
					break;
				case 8:
					dao.defenceSorting();
					break;
				case 9:
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
					break;
			}
		}
	}
}
```
