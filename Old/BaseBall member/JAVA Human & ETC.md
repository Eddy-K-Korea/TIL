# Human Class
```java
package dto;
// DTO (Data Transfer Object)(VO : value Object)
public class Human {

	private int number; // 선수등록번호(sequence number)(중복안됨)
	private String name; // 선수 이름
	private int age; // 나이
	private double height; // 신장

	public Human() {
	}

	public Human(int number, String name, int age, double height) {
		super();
		this.number = number;
		this.name = name;
		this.age = age;
		this.height = height;
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return number + "-" + name + "-" + age + "-" + height;
	}
}
```
마지막 toString 은 "-"을 통해 데이터세이브때 이용 할 수 있도록 편집 하였다.

# Pitcher Class
```java
package dto;

public class Pitcher extends Human {

	private int win;
	private int lose;
	private double defence; // 0.0 ~10.0
	
	public Pitcher() {
		
	}
	
	
	public Pitcher(int number, String name, int age, double height,int win, int lose, double defence) {
		super(number,name,age,height);
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
```
OverRide를 통해 toString의 메서드를 생성하였고 이것도 마찬가지로 "-"토큰으로 추가하여 편집하였다. 또한 return시 super.toString(human)

toString 불러오기를 통해 전체 문자열을 가지고 오도록 구현 하였다.

# Batter Class
```java
package dto;

public class Batter extends Human {

	private int batcount; //타수
	private int hit;
	private double hitAvg; // 타율  0.0 ~ 1.0
	
	public Batter() {
		
	}
	
	public Batter(int number, String name, int age, double height, int batcount, int hit, double hitAvg) {
		super(number, name,age, height);
		this.batcount = batcount;
		this.hit = hit;
		this.hitAvg = hitAvg;
	}

	
	public int getBatcount() {
		return batcount;
	}
	public void setBatcount(int batcount) {
		this.batcount = batcount;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public double getHitAvg() {
		return hitAvg;
	}
	public void setHitAvg(double hitAvg) {
		this.hitAvg = hitAvg;
	}
	@Override
	public String toString() {
		return super.toString() + "-" + batcount + "-" + hit + "-" + hitAvg;
	}
	
	public void allclean() {		//한번에 set을 불러올수 있다.
		setNumber(0);
		setName("");
		setAge(0);
		setHeight(0.0);
		setBatcount(0);
		setHit(0);
		setHitAvg(0.0);
	}
}
```
Pitcher와 마찬가지로 생성자를 전부 생성하도록 하고 Over Ride 를 통해서 문자열 전부를 불러오도록 한다. 아래 allclean()메서드는

DAO클래스에서 삭제에 사용하기 위해 생성하였다.

