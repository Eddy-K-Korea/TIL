# Main
```java
package main;

import java.util.Scanner;

import DaoClass.AllPrintClass;
import DaoClass.DeleteClass;
import DaoClass.FileSaveClass;
import DaoClass.InsertClass;
import DaoClass.SelectClass;
import DaoClass.UpdataClass;
import DaoInterface.DaoImpl;
import fileLoad.FileLoadClass;

public class MainClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		(new FileLoadClass()).process();
		
		while(true) {
			
			DaoImpl dao = null;
			
			System.out.println("---------Manu---------");
			System.out.println("1. 선수 추가");
			System.out.println("2. 선수 삭제");
			System.out.println("3. 선수 검색");
			System.out.println("4. 선수 수정");
			System.out.println("5. 선수 모두보기");
			System.out.println("6. 데이터 저장");
			System.out.println("0. 프로그램 종료");
			System.out.println("----------------------");
			System.out.print("메뉴를 선택해 주세요 >>");
			int choice = scan.nextInt();
			
			switch(choice) {
				case 1:
					dao = new InsertClass();
					break;
				case 2:
					dao = new DeleteClass();
					break;
				case 3:
					dao = new SelectClass();
					break;
				case 4:
					dao = new UpdataClass();
					break;
				case 5:
					dao = new AllPrintClass();
					break;
				case 6:
					dao = new FileSaveClass();
					break;
				case 0:
					System.exit(0);
					break;
			}
			dao.process();
		}
	}
}
```
메뉴에서 인터페이스를 통해 처음 null값을 잡아놓고 dao라는 객체를 만들어준다. 

choice된 Class를 따라 process()가 마지막에 진행되어 처리까지 이루어진후 while 반복문을 통해 다시 맨처음에는 null값으로 돌아가 다른 처리를

할 수 있다.
