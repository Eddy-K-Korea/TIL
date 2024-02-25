package com.bit.parking;


public class ParkingDTO {
	
	private String carNo;
	private long inTime;
	private long outTime;
	private long price;
	private int ticket;
	
	
	public ParkingDTO() {
	}
	
	public ParkingDTO(String carNo, int ticket) {
		super();
		this.carNo = carNo;
		this.ticket = ticket;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	
	public String getCarNo() {
		return carNo;
	}
	
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	
	public long getInTime() {
		return inTime;
	}
	
	public void setInTime(long inTime) {
		this.inTime = inTime;
	}
	
	public long getOutTime() {
		return outTime;
	}
	
	public void setOutTime(long outTime) {
		this.outTime = outTime;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	
}
