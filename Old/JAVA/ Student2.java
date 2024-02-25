package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		//data load
		String student[][] = dataLoad();

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
			
			System.out.println("메뉴를 선택해 주세요");
			System.out.print(">>");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:			
					insert(student);
					break;
				case 2:
					delete(student);
					break;
				case 3:
					search(student);
					break;
				case 4:
					update(student);
					break;
				case 5:
					allPrint(student);
					break;
				case 6:
					chapSum(student);
					
					break;
				case 7:
					chapAvg(student);
					break;
				case 8:
					sorting(student);
				break;
				case 9:
					dataSave(student);
				break;
			}
		}
	}
	
	//create 
	static void insert(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		int findIndex = -1;		//만약 0 이면 데이터가 다 차있을 경우 빈 곳이 없으므로 제일 첫번째에 데이터를 덮어 씌우게 된다.
		for (int i = 0; i < student.length; i++) {
			if(student[i][0] == null || student[i][0].equals("")) {
				findIndex = i;
				break;
				
			}
		}
		System.out.println("findIndex:" + findIndex);
		
		System.out.print("이름:");
		String name = sc.next();
		
		System.out.print("나이:");
		String age = sc.next();
		
		System.out.print("영어:");
		String eng = sc.next();
		
		System.out.print("수학:");
		String math = sc.next();
		
		student[findIndex][0] = name;
		student[findIndex][1] = age;
		student[findIndex][2] = eng;
		student[findIndex][3] = math;
		
		System.out.println("입력 완료: "+ student[findIndex][0]);
	}
	
	static void delete(String student[][]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 학생의 이름 =");
		String name = sc.next();
		
		int findIndex = getSearchIndex(student, name); // getSearchIndex의 함수는 아래 참고 
		
		if(findIndex != -1) {
			student[findIndex][0] = "";
			student[findIndex][1] = "";
			student[findIndex][2] = "";
			student[findIndex][3] = "";
			System.out.println("데이터를 삭제 하였습니다.");
		}
		else {
			System.out.println("데이터를 찾을 수 없습니다.");
		}
	}
	static void search(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("검색할 학생의 이름 = ");
		String name = sc.next();
		
		int findIndex = getSearchIndex(student, name);
		if(findIndex != -1) {
			System.out.println("데이터를 찾았습니다.");
			System.out.println("이름: "+student[findIndex][0]);
			System.out.println("나이: "+student[findIndex][1]);
			System.out.println("영어: "+student[findIndex][2]);
			System.out.println("수학: "+student[findIndex][3]);
		}
		else {
			System.out.println("데이터를 찾을 수 없습니다.");
		}
	}
	static void update(String student[][]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("수정할 학생의 이름 = ");
		String name = sc.next();
		
		int findIndex = getSearchIndex(student, name);
		if(findIndex != -1) {
			System.out.println("수정 데이터를 입력해 주세요.");
			System.out.println("영어:");
			String eng = sc.next();
			
			System.out.println("수학:");
			String math = sc.next();
			
			student[findIndex][2] = eng;
			student[findIndex][3] = math;
			System.out.println("수정 완료");
		}
		else {
			System.out.println("데이터를 찾을 수 없습니다.");
		}
	}
	static void allPrint(String student[][]) {
		for (int i = 0; i < student.length; i++) {
			if(student[i][0] == null || student[i][0].equals("") ==false) {
				System.out.print(student[i][0]+" "+student[i][1]+" "+student[i][2]+" "+student[i][3]+" ");
				System.out.println();
			}
		}	
	}
	
	static void chapSum(String student[][]) {
		Scanner sc = new Scanner(System.in);
		//학생 수 구하기
		int count = 0;
		for (int i = 0; i < student.length; i++) {
			if(!student[i][0].equals("")) { // student[i][0].equals("") ==false
				count++;
			}
		}
		System.out.println("학생 수:"+count);
		
		int arrSum[] = new int [count];	//합계를 구할 배열 생성
		
		//영어 수학 선택
		System.out.print("합계를 구할 과목 선택 1.영어, 2.수학  선택: ");
		int num = sc.nextInt();
		
		int sum = 0;
		for (int i = 0; i < student.length; i++) {	// 영어 student[][2] 수학 student[][3]
			if(!student[i][0].equals("")) {
				sum = sum + Integer.parseInt(student[i][num+1]); // 유저는 1번 2번으로 입력 num에서 1을 더해준다
			}
		}
		
		if(num ==1) System.out.print("영어의 총점: ");
		else 		System.out.print("수학의 총점: ");
		
		System.out.println(sum + "점 입니다.");
	}
	
	static void chapAvg(String student[][]) {
		Scanner sc = new Scanner(System.in);
		//학생 수 구하기
		int count = 0;
		for (int i = 0; i < student.length; i++) {
			if(!student[i][0].equals("")) { // student[i][0].equals("") ==false
				count++;
			}
		}
		System.out.println("학생 수:"+count);
		
		int arrSum[] = new int [count];
		
		//영어 수학 선택
		System.out.print("평균을 구할 과목 선택 1.영어, 2.수학  선택: ");
		int num = sc.nextInt();
		
		int sum = 0;
		for (int i = 0; i < student.length; i++) {	// 영어 student[][2] 수학 student[][3]
			if(!student[i][0].equals("")) {
				sum = sum + Integer.parseInt(student[i][num+1]); // 유저는 1번 2번으로 입력 num에서 1을 더해준다
			}
		}
		
		double avg= (double)sum / arrSum.length;
		
		if(num ==1) System.out.print("영어의 평균: ");
		else 		System.out.print("수학의 평균: ");
		
		System.out.println(avg + "점 입니다.");
	}
	
	static void sorting(String student[][]) {
		// 원본 데이터를 지키기위해 복사를 위해 배열을 하나 새로 만든다.
		String sortDatas[][] = new String[student.length][student[0].length];
		
		for (int i = 0; i < student.length; i++) {
			for (int j = 0; j < student[0].length; j++) {
				sortDatas[i][j] = student[i][j];
			}
		}
		
		//student[][2] 영어점수
		String temp[] = null;
		for (int i = 0; i < sortDatas.length -1; i++) {
			for (int j = i +1; j < sortDatas.length; j++) {
				if(!sortDatas[i][2].equals("")&& !sortDatas[j][2].equals("")) {
					int num1 = Integer.parseInt(sortDatas[i][2]);
					int num2 = Integer.parseInt(sortDatas[j][2]);
					if(num1 > num2) {
						temp = sortDatas[i];  // 한줄을 통째로 가져와서 바꿔준다.
						sortDatas[i] = sortDatas[j];
						sortDatas[j] = temp;
					}
			
				}
			}
		}
		for (int i = 0; i < sortDatas.length; i++) {
			if(!sortDatas[i][2].equals("")) {
			System.out.println(sortDatas[i][0]+" "+sortDatas[i][1]+" "+sortDatas[i][2]+" "+sortDatas[i][3]+" ");
		
			}
		}
	}
	static void dataSave(String student[][]) {
		/*
		 이름
		 나이
		 영어
		 수학
		 
		 세로로 저장하면 불편하다.
		 이름-나이-영어-수학 
		 이렇게 가로로 저장하는게 편하다.
		 
		 */
		int count = 0;
		for (int i = 0; i < student.length; i++) {
			if(!student[i][0].equals("")) { // student[i][0].equals("") ==false
				count++;
			}
		}
		
		String saveData[] = new String[count]; // 배열로 한줄 씩 저장
		for (int i = 0; i < saveData.length; i++) {
			saveData[i] = student[i][0] + "-" + student[i][1]+ "-"+ student[i][2] + "-" +student[i][3];
		}
		
		for (int i = 0; i < saveData.length; i++) {
			System.out.println(saveData[i]); // 확인용
		}
		
		
		File file = new File("d:\\temp\\student.txt");
		
		try {
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			for (int i = 0; i < saveData.length; i++) {
				pw.println(saveData[i]);
			}
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	static String [][] dataLoad() {
		String str[] = null;
		//파일 읽기
		File file = new File("d:\\temp\\student.txt");
		try {
			FileReader fr = new FileReader(file);
			//데이터 갯수 파악
			int count =0;
			String s;
			BufferedReader br = new BufferedReader(fr);
			while((s = br.readLine())!= null) {
				count++;
			}
			br.close();
			//할당
			str = new String [count];
			
			//데이터를 저장
			int i = 0;
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			while((s= br.readLine())!=null) {
				str[i] = s;
				i++;
			}
			//student[][] return
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (int i = 0; i < str.length; i++) {
//			System.out.println(str[i]);
//		}
		//student[][] <- str[]
		
		String student[][] = new String [20][4]; //더 추가로 할수도 있기 때문에
		for (int i = 0; i < student.length; i++) {
			for (int j = 0; j < student[i].length; j++) {
				student[i][j] = "";
			}
		}
		
		
		for (int i = 0; i < str.length; i++) {
			String s = str[i]; // 홍길동-나이-영어-수학 이런형식으로 넘어오게됨
			String split[] = s.split("-");// s의 문자열을 "-"을 지워주는 split 함수를 이용하여 split[]배열에 하나씩 담기게해줌
			
			student[i][0] = split[0];
			student[i][1] =	split[1];	
			student[i][2] =	split[2];
			student[i][3] = split[3];
		}
		
		return student;
		
	}
	
	static int getSearchIndex(String student[][], String name) {	//유틸리티 데이터 (중간에 필요한 데이터)
		int findIndex = -1; // 못 찾았을 경우 0 이나오는데 배열의 0번지는 값이 있다. (잘못된 값을 출력한다)
		for (int i = 0; i < student.length; i++) {
			String n = student[i][0];
			if(n.equals(name)) {
				findIndex = i;
				break;
			}
		}
		return findIndex;
	}
}


