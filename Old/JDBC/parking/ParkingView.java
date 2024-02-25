package com.bit.parking;
import java.util.Scanner;

public class ParkingView {
	
	private ParkingController controller;
	private Scanner scanner;
	ParkingDTO dto = new ParkingDTO();
	
	public ParkingView(int length) {
		controller = new ParkingController();
		scanner = new Scanner(System.in);
	}
	
	public void showMenu() {
		
		
		//System.out.println(service); // println은 객체명을 전달받으면 안에서 .toString이 자동으로 붙어서 출력
		while(true) {
			// 주차장의 현황판
			System.out.println(controller+ "\ncmd > ");
			String cmd = scanner.nextLine();
			
			if(cmd.equals("exit")) {
				break;
			}
			if(cmd.equals("in")) {
				System.out.println("차 들어온다. > ");
				String carNo = scanner.nextLine();
				ParkingController pc = new ParkingController();
				dto = pc.getCarInfo(carNo);
				if(dto.getTicket()==3) {
					System.out.println("정기 차량 입니다.");
				}else {
					System.out.println("일반 차량 입니다.");
				}
					
			}
			if(cmd.equals("out")) {
				System.out.println("차 나간다. > ");
				String carNo = scanner.nextLine();
				if(!dto.getCarNo().equals(carNo)) {
					System.out.println("차량 정보 없음.");
				}else {
					
					System.out.println("요금은" + controller.calculatePrice() + "입니다.");
				}
			}
			
			if(cmd.equals("ticket")) {
				System.out.println("정기권 등록. > ");
				String carNo = scanner.nextLine();
				int num = 3;
				dto.setTicket(num);
				dto.setCarNo(carNo);
			}
		
		} // end while
		
		System.out.println("terminated");
		scanner.close();
	}
		
		
		
		
}

