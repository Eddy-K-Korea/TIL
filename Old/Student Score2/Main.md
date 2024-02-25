# Main

```java
import java.util.Scanner;

import dao.StudentDao;

public class Main {

	public static void main(String[] args) {

		/*
		 학생 프로그램 업그레이드
		 
		 Dto(상속 받을 필요 없음), Dao
		 File, main 으로 구조 변경
		 ArrayList로 변경
		 */
		
		Scanner sc = new Scanner(System.in);
		
		StudentDao dao = new StudentDao();
		
		int choice;
		while(true) {
			System.out.println("--------------메뉴");
			System.out.println("1. 학생 정보 입력");
			System.out.println("2. 학생 정보 삭제");	
			System.out.println("3. 학생 정보 검색");	
			System.out.println("4. 학생 정보 수정");	
			System.out.println("5. 모든 학생 정보 출력");	
			System.out.println("6. 과목 총점");
			System.out.println("7. 과목 평균");
			System.out.println("8. 정렬");
			System.out.println("9. 데이터의 저장");
			System.out.println("10. 프로그램 종료");
			System.out.println("메뉴를 선택해 주세요");
			System.out.print(">>");
			choice = sc.nextInt();
			
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
					dao.subSum();
					break;
				case 7:
					dao.subAvg();
					break;
				case 8:
					dao.sortStudent();
					break;
				case 9:
					dao.saveData();
					break;
				case 10:
					System.out.println("이용해 주셔서 감사합니다.");
					System.exit(0);
					break;	
			}
		}
	}
}
```
