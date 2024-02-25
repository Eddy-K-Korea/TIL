package main;

import java.util.Scanner;

public class SortingFunc2 {

static int[] userInput(boolean ud[]) {	      //return값을 배열로 해야하기 때문에 int[]로 하였으며 오름, 내림의 입력도 저장하기 위해
                                             // boolean ud[](return하지 않아도 주소값으로 자동으로 입력에 저장된다)을 파라미터로 받는다.
		Scanner sc = new Scanner(System.in);
		System.out.print("정렬할 갯수는 = ");
		int count = sc.nextInt();
		
		int number[] = new int[count];            //배열의 동적할당
    
		// 정렬할 숫자를 입력
		for(int i = 0; i < number.length; i++) {
			System.out.print((i+1)+"번째 수= ");
			number[i] =sc.nextInt();
		}
		System.out.print("1. 오름 / 2. 내림");		  
		int updown = sc.nextInt();
		ud[0] = (updown ==1 )?true:false;     //ud배열에 값을 넣어주게 된다.
		return number;
	}
  
  static void sorting(int number[], boolean updown) {     //아까 ud배열에 저장한 배열을 불러와 if 조건문에 사용
                                                          //usernumber로 처리된 배열을 파라미터로 받고 정렬시작
		for (int i = 0; i < number.length-1; i++) {
			for (int j = i+1; j < number.length; j++) {
				if(updown) {	                                  // userInput에서 오름을 선택하면 true값이 ud배열에 저장된다.
					if(number[i]> number[j]) {
						swap(number, i, j);
					}
				}else {			                                      //내림
					if(number[i]<number[j]) {
						swap(number, i, j);
					}	
				}
			}
		}
	}
  
  static void swap(int number[],int i, int j) {           //위쪽 sorting 메서드의 swap 부분 메서드화
		int temp = number[i];
		number[i] = number[j];
		number[j] = temp;
	}
  
  static void result(int number[], boolean updown) {	//가상변수에 배열을 다 넣는 경우는 배열내 값을 바꿀때  
                                                      //그냥 updown 처럼 넣는것은 그 주소만 받을때 address 할당만 필요할때
		String msg ="";
		if(updown) msg = "오름";
		else	   msg = "내림";
		System.out.println(msg+"차순으로 정렬하였으며,");
		for (int i = 0; i < number.length; i++) {
			System.out.println(i+ ":"+number[i]);
		}
	}
  
// ------------------------------------------------------------- 함수 끝 메인 함수 시작

public static void main(String[] args) {

  int number[] = null;
  boolean updown [] = new boolean [1];          // address 에 의한 할당을 하기 위해서 미리 만들어둠
  
  number = userInput(updown);        // 1. 입력 (배열에 true, false를 넣어야하기 때문에 위에 만들어둔 배열 인수로 대입)
  sorting(number, updown[0]);        // 2. 정렬 (배열[0]에 저장되어있는 값 인수로 대입)
  result(number, updown[0]);         // 3. 출력 
  
}
