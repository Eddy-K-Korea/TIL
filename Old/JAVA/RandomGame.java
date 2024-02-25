package main;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {

		// random 넘버를 ( 1 ~ 100) 뽑기
		// 유저로 부터 입력
		// 예를 들어 유저가 50을 입력하였을때 랜덤숫자 보다 작으면 "너무 작습니다" 크다면 " 너무 큽니다."
		// 유저가 뽑힌 랜덤의 숫자를 맞추는 프로그램
		// 기회는 10번
		// 맞췄을 경우 축하 or 실패
		// 기회가 끝난후 게임 reply 여부를 묻고 재실행 or 종료
		Scanner sc = new Scanner(System.in);
		
		int ranNum;
		int userNum;
		int userNum2;
		boolean b = false;

		ranNum = (int) (Math.random() * 99);

  loopout:while (true) {
			System.out.print("숫자를 입력하세요: ");
			userNum = sc.nextInt();
			if (userNum < 0) {
				System.out.print("잘못입력하였습니다. 다시 입력해 주세요");
				continue;
			}
			for (int i = 0; i < 9; i++) {
				if (userNum < ranNum) {
				System.out.println("너무 작습니다.");
				System.out.print("숫자를 입력하세요: ");
				userNum = sc.nextInt();
				} else if (userNum > ranNum) {
					System.out.println("너무 큽니다.");
					System.out.print("숫자를 입력하세요: ");
					userNum = sc.nextInt();
				}
				else if (ranNum == userNum) {
						System.out.println("정답입니다.");
						break;
				}	
			}
			if(ranNum !=userNum){
				System.out.println("숫자를 맞추지 못하였습니다.");
			}
			while(b==false) {
			System.out.println("다시 플레이 하시겠습니까? 1. Y / 2. N");
			System.out.print(">");
			userNum2 = sc.nextInt();
				if(userNum2 ==1) {
					continue;
				}
				else if(userNum2==2) {
					System.out.println("이용해 주셔서 감사합니다.");
					break loopout;
				}
				else {
					System.out.println("잘못 입력하였습니다.");
					b = false;
				}
			}
			sc.close();
		}
	}
}
