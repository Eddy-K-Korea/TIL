# DAO Class
```java
import java.util.Scanner;

import dto.Batter;
import dto.Human;
import dto.Pitcher;

public class MemberDao {

	Scanner sc = new Scanner(System.in);
	private Human human[];
	
	private int memberNumber;
	private int memberCount; 		// human 의 배열의 인덱스 넘버를 지정
	
	public MemberDao() {
		human = new Human[20];   	// 생성시 배열 할당  (변수20개) 객체를 생성한것이 아님
		memberNumber = 1000;
		memberCount = 0;
	}

```
DAO 클래스의 생성자에 선수들에게 부여할 number와 정보를 담을 배열을 선언해준다. 

memberCount는 배열 인덱스를 정해줄 변수이므로 0으로 쵝화 해준다.

```java
public void insert() {
		//투수 / 타자
		System.out.println("투수(1) / 타자(2) = ");
		int pos = sc.nextInt();
		
		//human
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.print("나이 = ");
		int age = sc.nextInt();
		
		System.out.print("신장= ");
		double height = sc.nextDouble();
		
		Human h = null;  //   우선 null 로 잡는다. 배열만 잡아놨기 때문에
		//투수 1000 ~ 
		if(pos ==1) {
			//win
			System.out.print("승= ");
			int win = sc.nextInt();
			//lose
			System.out.print("패= ");
			int lose = sc.nextInt();
			
			//defense
			System.out.print("방어율= ");
			double defence = sc.nextDouble();
			h = new Pitcher(memberNumber, name, age, height, win, lose, defence);  ★★
		}
		
		
		
		//타자 2000 ~  (투수와는 다른 방법)
		else if(pos ==2 ) {
			
			Batter bat = new Batter(); // 기본 생성자
			
			//선수 등록 번호
			bat.setNumber(memberNumber + 1000);
			
			bat.setName(name);
			bat.setAge(age);
			bat.setHeight(height);
			
			
			//타수
			System.out.print("타수= ");
			int batcount = sc.nextInt();
			bat.setBatcount(batcount);   		//bat.setBatcount(sc.nextInt()); 도 가능
			
			//안타수
			System.out.print("안타수= ");
			int hit = sc.nextInt();
			bat.setHit(hit);
			
			//타율
			System.out.print("타율= ");
			double hitAvg = sc.nextDouble();
			bat.setHitAvg(hitAvg);
			
			h = bat;
			
		}
		
		human[memberCount] = h;		//배열에 받은 정보를 넣어준다.
		
		memberNumber++;			//시퀀스 넘버 한번 지정해주고 +1 해서 지정
		memberCount++;			// 배열에 넣고 증가해서 또 넣을 수 있도록
	}
```
insert 부분에서 공통적으로 들어가는 human의 정보는 먼저 받고 투수인지 타자인지 구별하여 정보를 받아야한다.

if조건문으로 투수/타자를 구분한뒤 스캐너로 값을 입력 받는다. 투수쪽 소스코드를 보면 맨 마지막에 Pitcher의 생성자를 통해

human의 정보도 Pitcher에 한번에 넣는것을 볼 수 있다. 그리고 human h = null; 값을 잡아준다. (아직 생성을 제대로 하지 않았으므로 null로 잡는다)

> Pitcher의 클래스에서 생성자 부분에 human의 정보도 받을 수 있도록 아래와 같이 설정해 준다.
```java
public Pitcher(int number, String name, int age, double height, int win, int lose, double defence) {
		super(number, name, age, height);	//human 의 기본 생성자 호출(만약 안에 아무것도 없다면)
		this.win = win;
		this.lose = lose;
		this.defence = defence;
		
	}
```
```java
public int search(String name) {   // 한명 검색할때 사용
		int index = -1;
		
		for (int i = 0; i < human.length; i++) {
			if(human[i]!=null) {
				if(name.equals(human[i].getName())) {
					index = i;			//만약 여러명일 경우 배열에 넣어주고 return값을 준다.
					break;
				}
			}
		}
		return index;
	}
```
select, update, delete를 하기 위해서는 우선 이름으로 검색을 해야한다. 따라서 search라는 공통 메서드를 생성하여 

이름으로 정보를 찾는 소스코드를 구현한다. 몇번째 배열에 위치했는지 인덱스 번호를 찾아서 return값으로 돌려준다.


```java
public void delete() {
		System.out.print("삭제하고 싶은 선수명 = ");
		String name = sc.next();
		
		if(name.equals("")) {
			System.out.println("이름을 정확히 입력해 주십시오.");
			return;  //void 함수 일경우 조건문이 팅겨져 나간다.  
		}
		int findIndex = search(name);		
		if(findIndex == -1) {			//만약 0이라면 못찾았을 경우 0의 자리에 내용을 덮어 씌우게 된다.
			System.out.println("선수명단에 없습니다. 삭제 할 수 없습니다.");
			return;
		}
		
		//삭제
		if(human[findIndex] instanceof Pitcher) {
			Pitcher p = (Pitcher)human[findIndex];
			p.setNumber(0);
			p.setName("");
			p.setAge(0);
			p.setHeight(0.0);
			p.setWin(0);
			p.setLose(0);
			p.setDefence(0.0);
		}
		else if(human[findIndex] instanceof Batter) {
			Batter b = (Batter)human[findIndex];
			b.allclean();  			// Batter 클래스에 함수를 만들어 놓은것을 이용
		}
	}
```

마지막 Batter의 삭제 부분에서 b.allclean() 함수는 위에 Pitcher의 길어진 소스코드를 대신하여 Batter의 클래스에 

미리 만들어둔 메서드 이다. 아래를 참조하면

```java
public void allclean() {		//한번에 set을 불러올수 있다.
		setNumber(0);
		setName("");
		setAge(0);
		setHeight(0.0);
		setBatcount(0);
		setHit(0);
		setHitAvg(0.0);
	}
```
해당 메서드만을 불러오면 DAO 클래스에서는 소스코드의 가독성이 좋아진다.

```java
public void select() {
		System.out.print("검색하고 싶은 선수명 = ");
		String name = sc.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수명단에 없습니다.");
			return;
		}
		System.out.println("번호: "+ human[findIndex].getNumber());
		System.out.println("이름: "+ human[findIndex].getName());
		System.out.println("나이: "+ human[findIndex].getAge());
		System.out.println("신장: "+ human[findIndex].getHeight());
		
		if(human[findIndex] instanceof Pitcher) {
			System.out.println("승리: "+ ((Pitcher)human[findIndex]).getWin());
			System.out.println("패전: "+ ((Pitcher)human[findIndex]).getLose());
			System.out.println("방어율: "+ ((Pitcher)human[findIndex]).getDefence());
		}
		else if(human[findIndex] instanceof Batter) {
			System.out.println("타수: "+((Batter)human[findIndex]).getBatcount());
			System.out.println("안타수: "+((Batter)human[findIndex]).getHit());
			System.out.println("타율: "+((Batter)human[findIndex]).getHitAvg());
		}
	}
```
search 메서드를 통해 이름이 일치하는 human배열을 찾고 instanceOf 를 통해 Pitcher 인지 Batter 인지 그에 따른 

정보를 출력할 수 있도록 한다.

```java
public void update() {
		System.out.print("수정하고 싶은 선수명 = ");
		String name = sc.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수명단에 없습니다.");
			return;   ★
		}
		
		if(human[findIndex] instanceof Pitcher) {
			System.out.print("승: ");
			int win = sc.nextInt();
			
			System.out.print("패: ");
			int lose = sc.nextInt();
			
			System.out.print("방어율: ");
			double defence = sc.nextDouble();
			
			Pitcher pit = (Pitcher)human[findIndex];
			pit.setWin(win);
			pit.setLose(lose);
			pit.setDefence(defence);
		}
		else if(human[findIndex] instanceof Batter) {
			System.out.print("타수: ");
			int batCount = sc.nextInt();
			
			System.out.print("안타수: ");
			int hit = sc.nextInt();
			
			System.out.print("타율: ");
			double hitAvg = sc.nextDouble();
			
			Batter bat = (Batter)human[findIndex];
			bat.setBatcount(batCount);
			bat.setHit(hit);
			bat.setHitAvg(hitAvg);
			
		}
		
	}
```
삭제와 검색에도 있지만 위에 소스코드에서 ★ 표시된 곳의 return은 해당 메서드에서는 continue와 비슷한 역할을 한다.

void 메서드에서 함수가 끝나게끔 해서 실행을 멈춘다.

```java
public void allPrint() {
		for (int i = 0; i < human.length; i++) {
			if(human[i] != null && !human[i].getName().equals("")) {    //human[i].getName().equals("") 빈문자 인 경우
				System.out.println(human[i].toString());
			}
		}
	}
```
모든 선수 출력을 해주는 메서드 이며, 배열로 프로그램 구현 하였기 때문에 null이 아닌경우와 "" 빈문자일 경우를 제외하고 출력해준다.

```java
public void saveData() {
		
		int len = 0;			
		for (int i = 0; i < human.length; i++) {
			if(human[i] != null) {		
				len++;
			}
		}
		
		String datas[] = new String[len];   //우선 배열의 갯수에 맞춰서 생성
		
		for (int i = 0; i < datas.length; i++) {
			datas[i] = human[i].toString();		//over ride 되어서 pitcher 와 batter의 toString이 넘어온다
		}
		fp.saveData(datas);
	}
```
파일로 데이터를 저장할때 "-"토큰을 이용하는게 좋다. 예를 들어 (번호-이름-나이-키-승수-패수-방어율)

1. 세이브는 내용이 있는 부분 까지만 하기 때문에 우선 내용이 있는 배열의 길이까지만을 구해주는 len 의 길이를 파악한다.
2. 배열의 len(내용이있는 길이)까지 생성(할당)한다.
3. datas[] 배열에 human[]의 toString 을 넘겨준다. OverRide가 되기 때문에 Pitcher와 Batter의 toString이 넘어온다.( 중요 )
4. FileProc의 saveData메서드를 불러와 해당 내용을 텍스트파일에 쓰는 소스코드를 진행한다.

```java
public void loadData() {
		// 이 함수를 하기 전에 배열이 먼저 만들어져 있어야 한다.
		String datas[] = fp.loadData();
		/*
		 datas = Pitcher, Batter 섞여 들어가 있다. -> Human[]
		 		객체 생성
		 		값을 저장
		 */
		
		for (int i = 0; i < datas.length; i++) {
			//datas[0~n-1]
			//datas[0] = 1000-홍길동-24-178.1-10-3-1.43
			//datas[1] = 2001-일지매-21-180.1-21-18-0.8
			//datas[2] = 1002-장길산-21-190.3-20-4-0.43
			
			String data[] = datas[i].split("-"); // "-" 잘라서 data[]배열에 넣기
			
			int title = Integer.parseInt(data[0]);
			if(title < 2000) {		// 투수
				
				human[i] = new Pitcher(Integer.parseInt(data[0]), 
												data[1], 
									Integer.parseInt(data[2]), 
									Double.parseDouble(data[3]), 
									Integer.parseInt(data[4]),
									Integer.parseInt(data[5]), 
									Double.parseDouble(data[6]));
						
				
			}
			else {
				human[i] = new Batter(Integer.parseInt(data[0]), 
												data[1], 
									Integer.parseInt(data[2]), 
									Double.parseDouble(data[3]), 
									Integer.parseInt(data[4]),
									Integer.parseInt(data[5]), 
									Double.parseDouble(data[6]));
			}
		}
	}
```
1. FileProc의 loadData를 불러와 텍스트에 적혀있는 내용을 불러오고 return된 배열을 datas[]에 다시 담는다.
2. split 메서드를 이용해 토큰을 잘라 data배열에 차례대로 대입한다
3. data[0]의 정보는 선수 넘버이므로 2000보다 작으면 투수 크면 타자이다. 해당 조건을 이용하여 human배열에 자료형에 맡게 변환하여 넣어준다.

```java
public void hitAvgSorting() {
		// 등록된 사람 정렬할 배열에 담아 구하기
		Human ballMan[] = choicePosition(2);
		
		//정렬
		Human obj = null;
		for (int i = 0; i < ballMan.length-1; i++) {
			for (int j = i+1; j < ballMan.length; j++) {
				Batter b1 = (Batter)ballMan[i];
				Batter b2 = (Batter)ballMan[j];
				if(b1.getHitAvg() < b2.getHitAvg()) {
					obj = ballMan[i]; 
					ballMan[i] = ballMan[j];
					ballMan[j] = obj;
				}
			}
		}
		for (int i = 0; i < ballMan.length; i++) {
			System.out.println(ballMan[i].toString());
		}
	}
```
1. 타율 정렬을 위해 우선 타자별 투수별 인원을 배열에 담는 choicePosition()메서드를 불러온다. (아래 참조) 

투수 타자가 랜덤으로 담겨있기때문에 구분을해주고 정렬을 해주어야 한다.

2. 인스턴스를 담기위해 비어있는 Human obj를 null값으로 잡아주고 정렬문에 사용하는 for문을 불러온다.
3. 우선 타율정렬로 타자를 불러와야한다. Batter의 객체를 생성하고 ballMan을 형변환 하여 넣어준다.
4. 각 객체의 타율을 비교하고 내림차순으로 정렬 해준다.

```java
public void defenceSorting() {
		
		Human ballMan[] = choicePosition(1);
		
		Human obj = null;
		for (int i = 0; i < ballMan.length-1; i++) {
			for (int j = i+1; j < ballMan.length; j++) {
				Pitcher p1 = (Pitcher)ballMan[i];
				Pitcher p2 = (Pitcher)ballMan[j];
				if(p1.getDefence() > p2.getDefence()) {
					obj = ballMan[i];
					ballMan[i] = ballMan[j];
					ballMan[j] = obj;
							
				}
			}
		}
		for (int i = 0; i < ballMan.length; i++) {
			System.out.println(ballMan[i].toString());
		}
		
	}
```
타율과 마찬가지로 투수 방어율 오름차순 정렬이다. 방식은 아까와 동일하다.

```java
public Human [] choicePosition(int num) {
		int count = 0;
		
		for (int i = 0; i < human.length; i++) {
			if(human[i] != null) {
				if(num ==1) {
					if(human[i].getNumber() < 2000) {	//투수
						count++;
					}
					
				}else {
					if(human[i].getNumber() >= 2000){// 타자
						count++;
					}
				}
			}
		}
		Human[] choiceHuman = new Human[count];	//사람 수만큼 배열 생성
		
		// 배열에 사람 수만큼만 넣기
		int m = 0; // i 로 같이 돌리면 human의 길이만큼 너무 많이 돌아간다.
		for (int i = 0; i < human.length; i++) {
			if(human[i] != null) {
				if(num ==1) {
					if(human[i].getNumber() < 2000) {
						choiceHuman[m] = human[i];
						m++;
					}
				}
				else {
					if(human[i].getNumber() >= 2000) {
						choiceHuman[m] = human[i];
						m++;
					}
				}
			}
		}
		return choiceHuman;
	}
```
1. 사람 수만큼 배열을 생성해야 함으로 선수넘버를 조건으로 count 한다.
2. 배열을 할당하고 타자따로 투수 따로 choiceHuman 배열에 넣어준다. i를 같이 안쓰고 m 을 쓰는 이유는  nullPoint가 발생하기 때문이다.
3. Human 배열을 return값으로 돌려준다.
