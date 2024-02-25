package main;

import java.util.Scanner;

public class Mission2 {

	public static void main(String[] args) {
/*
 성적 통계 프로그램
 통계를 산출할 학생의 수 입력
 동적할당 -> student[학생수][4]
 예) 홍길동 90 100 85
  	일지매 100 95 90
 이름, 과목점수 입력
 >> 숫자 입력
 >> 숫자로 입력이 안되었다면 다시 입력
 
 1 ~ 100사이의 점수
 범위를 초과하면 "범위를 초과했습니다. 다시입력하세요" 출력
 
 국어, 수학, 영어 최고 점수
 
 국어, 수학, 영어 최저 점수
 
 모든 점수의 총합
 학생 수에 따른 평균
 */
		
		//학생수 입력
		Scanner sc = new Scanner(System.in);
		
		int studentNum = 0;
		String name;
		String korea = "";
		String eng = "";
		String math = "";
		int w = 0;
		int koreamax=0;
		int koreamin =100;
		int mathmax=0;
		int mathmin =100;
		int englishmax=0;
		int englishmin =100;
		
		System.out.print("학생 수를 입력하세요: ");
		studentNum = sc.nextInt();
		String list[][] = new String[studentNum][4];
    
		//이름,  //과목 점수 입력 (숫자로 입력)
    
		for (int i = 0; i < list.length; i++) {
			System.out.print("이름을 입력하세요: ");
			name = sc.next();
			list[i][0] = name;
		}
		while (w<list.length) {
			while(true) {
				System.out.print("국어: ");
				korea = sc.next();
				boolean check = false;
				for(int j = 0; j < korea.length(); j++) {
					char c = korea.charAt(j);				// 아스키 코드로
					if((int)c < 48 || (int)c > 57) {
						check = true;
						break;
					}
				}
				if(check == true) {
					System.out.println("잘못 입력 하셨습니다. 다시 입력해 주십시오");
					continue;
				}
				int numKorea = Integer.parseInt(korea);
				if(numKorea < 0 || numKorea > 100) {
					System.out.println("범위를 초과한 점수 입니다. 다시 입력해 주세요.");
					continue;
				}
				break;
				
				
			}
			
			while(true) {
				System.out.print("영어: ");
				eng = sc.next();
				boolean check = false;
				for(int j = 0; j < eng.length(); j++) {
					char c = eng.charAt(j);				// 아스키 코드로
					if((int)c < 48 || (int)c > 57) {
						check = true;
						break;
					}
				}
				if(check == true) {
					System.out.println("잘못 입력 하셨습니다. 다시 입력해 주십시오");
					continue;
				}
				int numEng = Integer.parseInt(eng);
				if(numEng < 0 || numEng > 100) {
					System.out.println("범위를 초과한 점수 입니다. 다시 입력해 주세요.");
					continue;
				}
				break;
				
			}
			while(true) {
				System.out.print("수학: ");
				math = sc.next();
				boolean check = false;
				for(int j = 0; j < math.length(); j++) {
					char c = math.charAt(j);				// 아스키 코드로
					if((int)c < 48 || (int)c > 57) {
						check = true;
						break;
					}
				}
				if(check == true) {
					System.out.println("잘못 입력 하셨습니다. 다시 입력해 주십시오");
					continue;
				}
				int numMath = Integer.parseInt(math);
				if(numMath < 0 || numMath > 100) {
					System.out.println("범위를 초과한 점수 입니다. 다시 입력해 주세요.");
					continue;
				}
				break;
			}
			
		}
		
		int studentCount[][] = new int[list.length][3];
		for (int i = 0; i < list.length; i++) {
			studentCount[i][0] = Integer.parseInt(list[i][1]);	//국어
			studentCount[i][1] = Integer.parseInt(list[i][2]);	//영어
			studentCount[i][2] = Integer.parseInt(list[i][3]);	//수학
		}
		int max = studentCount[0][0];    //국어 최고 점수
		for (int i = 0; i < list.length; i++) {
			if(max <studentCount[i][0]) {
				max = studentCount[i][0];
			}
		}
		int min = studentCount[0][1];	 // 영어		
		for (int i = 0; i < list.length; i++) {
			if(min>studentCount[i][1]) {
				min = studentCount[i][1];
			}
		}
    
		//과목의 최고 점수
    
		System.out.print("국어 최고점수: "+koreamax);
		System.out.println();
		System.out.print("수학 최고점수: "+mathmax);
		System.out.println();
		System.out.print("영어 최고점수: "+englishmax);
		System.out.println();
    
		//과목의 최저 점수
    
		System.out.print("국어 최저점수: "+koreamin);
		System.out.println();
		System.out.print("수학 최저점수: "+mathmin);
		System.out.println();
		System.out.print("영어 최저점수: "+englishmin);
		System.out.println();
    
		//모든 점수의 총합
    
		int score;
		int sum = 0;
		double avg =0;
		for (int i = 0; i < list.length; i++) {
			for (int j = 1; j < list[0].length; j++) {
				score = Integer.parseInt(list[i][j]);
				sum = sum + score;
				avg = sum/studentNum/list[0].length;
			}
		}	
		System.out.println("모든 점수의 총합은 : "+ sum);
	
		//학생수에 따른 평균
    
		System.out.println("모든 점수의 평균은 : "+avg);
		
		
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[0].length; j++) {
				System.out.print(list[i][j]+" ");
			}
		}
		sc.close();
	}
}
