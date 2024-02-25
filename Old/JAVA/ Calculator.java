package main;

import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		// 계산기
		int num1, num2;
		String oper;
		int result = 0;
		
		String strNum1, strNum2;
		
		//TODO: 입력
		strNum1 = numberInput("첫번째 수");
		oper = operInput();
		strNum2 = numberInput("두번째 수");
		
		//문자열 -> 숫자
		num1 = Integer.parseInt(strNum1);
		num2 = Integer.parseInt(strNum2);
		
		//TODO: 계산
		result = calProcess(num1, num2, oper);
		
		//TODO: 결과		
		System.out.println(num1 + " "+ oper + " "+ num2+ "= " + result);

	}
	
	static int calProcess(int n1, int n2, String oper) {
		int r = 0;
		switch(oper) {
			case "+":
				r = n1 + n2;
				break;
			case "-":
				r = n1 - n2;
				break;
			case "*":
				r = n1 * n2;
				break;
			case "/":
				r = n1 / n2;
				break;
		}
		return r;
	}
	
	// 목적: 문자열 배열에 숫자를 판별하는 함수
	// return: true(숫자가 아님) false(모두 숫자)
	static boolean numberOk(String snum) {
		boolean noNumber = false;
		for (int i = 0; i < snum.length(); i++) {
			char c = snum.charAt(i);
			if((int)c < 48 || (int)c > 57) {
				noNumber = true;
				break;
			}
		}
		return noNumber;
	}
	
	// 숫자를 입력 받고 숫자가 아닌 경우 다시 입력 받는 함수
	static String numberInput(String num12) {
		Scanner sc = new Scanner(System.in);
		String strNum1;
		while(true) {
			System.out.print(num12 +" = ");
			strNum1 = sc.next();
			boolean b = numberOk(strNum1);
			if( b== true) {
				System.out.println("숫자가 아닙니다. 다시 입력해 주십시오");
				continue;
			}
			break;
		}
		return strNum1;
	}
	
	// 연산자를 입력 받았을때 맞는지 판별하고 아닐 경우 다시 입력받는 함수
	static String operInput() {
		Scanner sc = new Scanner(System.in);
		String oper;
		while(true) {
			System.out.print("(+,-,*,/) =");
			oper = sc.next();
			if(oper.equals("+") || oper.equals("-") || oper.equals("*") || oper.equals("/")) {
				break;
			}
			System.out.println("연산 기호가 아닙니다. 다시 입력해주세요");
		}
		return oper;
	}
}
