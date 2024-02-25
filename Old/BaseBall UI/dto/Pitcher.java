package dto;

public class Pitcher extends Human {

	private int win;
	private int lose;
	private double defence; // 0.0 ~10.0
	
	public Pitcher() {
		
	}
	
	
	public Pitcher(int number, String name, int age, double height, int win, int lose, double defence) {
		super(number, name, age, height);	//human 의 기본 생성자 호출(만약 안에 아무것도 없다면)
		this.win = win;
		this.lose = lose;
		this.defence = defence;
		
	}


	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}
	public void setLose(int lose) {
		this.lose = lose;
	}
	public double getDefence() {
		return defence;
	}
	public void setDefence(double defence) {
		this.defence = defence;
	}


	@Override
	public String toString() {
		return super.toString() + "-" + win + "-" + lose + "-" + defence;
	}
	
	
}
