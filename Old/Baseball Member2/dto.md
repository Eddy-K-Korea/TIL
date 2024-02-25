# dto

## Human

```java
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

## Pitcher
```java
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
```

## Batter
```java
public class Batter extends Human {

	private int batcount; //타수
	private int hit;
	private double hitAvg; // 타율  0.0 ~ 1.0
	
	public Batter() {
		super();
		
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
