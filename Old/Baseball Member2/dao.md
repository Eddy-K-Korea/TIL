# dao

```java
import java.util.ArrayList;
import java.util.Scanner;

import dto.Batter;
import dto.Human;
import dto.Pitcher;
import file.FileProc;

public class MemberDaoEx {

	Scanner sc = new Scanner(System.in);
	//배열
//	private Pitcher pitcher[];			해당 배열로 따로따로 관리할 수 도 있지만 human [] 으로 한번에 불러 올수 있다.
//	private Batter batter[];
	
	// 생성시 배열 할당  (변수20개) 객체를 생성한것이 아님 loadData로 인해 멤버 변수로 만들어야한다.
	private ArrayList<Human> list = new ArrayList<Human>();
	
	private int memberNumber;
	
	FileProc fp;
	
	public MemberDaoEx() {
		  
		fp = new FileProc("baseball");
		fp.createFile();
		this.loadData();
		
		if(list.size() > 0 && list != null){
			memberNumber = list.get(list.size()-1).getNumber();
			if(memberNumber >= 2000) {
				memberNumber = memberNumber - 1000;
			}
			else {	
				memberNumber = memberNumber + 1;
			}
		}	
	}

	//list에서 제일 마지막 선수의 number 취득
	
	public void insert() {
		
		//투수 / 타자
		System.out.print("투수(1) / 타자(2) = ");
		int pos = sc.nextInt();
		
		//human
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.print("나이 = ");
		int age = sc.nextInt();
		
		System.out.print("신장= ");
		double height = sc.nextDouble();
		
		//Human h = null;  //   우선 null 로 잡는다. 배열만 잡아놨기 때문에
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
			list.add(new Pitcher(memberNumber, name, age, height, win, lose, defence));
		}
		
		
		
		//타자 2000 ~  (투수와는 다른 방법)
		else if(pos ==2 ) {
			
			Batter bat = new Batter(); // 기본 생성자
			
			//선수 등록 번호
			bat.setNumber(memberNumber +1000);
			
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
			
			list.add(bat);
			
		}
		memberNumber++;
					//시퀀스 넘버 한번 지정해주고 +1 해서 지정
					// 배열에 넣고 증가해서 또 넣을 수 있도록
	}
	public void delete() {
		System.out.print("삭제하고 싶은 선수명 = ");
		String name = sc.next();
		
		if(name.equals("")) {
			System.out.println("이름을 정확히 입력해 주십시오.");
			return;  //void 함수 일경우 조건문이 팅겨져 나간다.  
		}
		int findIndex = search(name);		
		if(findIndex == -1) {	//못찾았을 경우
			System.out.println("선수명단에 없습니다. 삭제 할 수 없습니다.");
			return;
		}
		
		//삭제
		list.remove(findIndex);
		
		
				
				
	}
	public void select() {
		System.out.print("검색하고 싶은 선수명 = ");
		String name = sc.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수명단에 없습니다.");
			return;
		}
		System.out.println("번호: "+ list.get(findIndex).getNumber());
		System.out.println("이름: "+ list.get(findIndex).getName());
		System.out.println("나이: "+ list.get(findIndex).getAge());
		System.out.println("신장: "+ list.get(findIndex).getHeight());
		
		if(list.get(findIndex) instanceof Pitcher) {
			System.out.println("승리: "+ (((Pitcher) list.get(findIndex)).getWin()));
			System.out.println("패전: "+ ((Pitcher)list.get(findIndex)).getLose());
			System.out.println("방어율: "+ ((Pitcher)list.get(findIndex)).getDefence());
		}
		else if(list.get(findIndex) instanceof Batter) {
			System.out.println("타수: "+((Batter)list.get(findIndex)).getBatcount());
			System.out.println("안타수: "+((Batter)list.get(findIndex)).getHit());
			System.out.println("타율: "+((Batter)list.get(findIndex)).getHitAvg());
		}
	}
	
	public void update() {
		System.out.print("수정하고 싶은 선수명 = ");
		String name = sc.next();
		
		int findIndex = search(name);
		if(findIndex == -1) {
			System.out.println("선수명단에 없습니다.");
			return;
		}
		
		Human human = list.get(findIndex);
		
		if(human instanceof Pitcher) {
			System.out.print("승: ");
			int win = sc.nextInt();
			
			System.out.print("패: ");
			int lose = sc.nextInt();
			
			System.out.print("방어율: ");
			double defence = sc.nextDouble();
			
			Pitcher pit = (Pitcher)human;
			pit.setWin(win);
			pit.setLose(lose);
			pit.setDefence(defence);
			
			
		}
		else if(human instanceof Batter) {
			System.out.print("타수: ");
			int batCount = sc.nextInt();
			
			System.out.print("안타수: ");
			int hit = sc.nextInt();
			
			System.out.print("타율: ");
			double hitAvg = sc.nextDouble();
			
			Batter bat = (Batter)human;
			bat.setBatcount(batCount);
			bat.setHit(hit);
			bat.setHitAvg(hitAvg);
			
		}
		
	}
	
	public void allPrint() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	public int search(String name) {   // 한명 검색할때 사용
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			if(name.equals(list.get(i).getName())) {
				index = i;			//만약 여러명일 경우 배열에 넣어주고 return값을 준다.
				break;
			}
		}
		return index;
	}
	
	public void saveData() {
		// 번호-이름-나이-키-승수-패수-방어율 (투수의 경우)(토큰을 넣는게 좋다)
		
		String datas[] = new String[list.size()];   
		
		for (int i = 0; i < datas.length; i++) {
			datas[i] = list.get(i).toString();		//over ride 되어서 pitcher 와 batter의 toString이 넘어온다
		}
		fp.saveData(datas);
	}
	
	public void loadData() {
		// 이 함수를 하기 전에 배열이 먼저 만들어져 있어야 한다.
		String datas[] = fp.loadData();
		/*
		 datas = Pitcher, Batter 섞여 들어가 있다. -> Human[]
		 		객체 생성
		 		값을 저장
		 */
		
		for (int i = 0; i < datas.length; i++) {
			
			
			String data[] = datas[i].split("-"); // "-" 잘라서 data[]배열에 넣기
			Human human = null;
			int title = Integer.parseInt(data[0]);
			if(title < 2000) {		// 투수
				
				human = new Pitcher(Integer.parseInt(data[0]), 
												data[1], 
									Integer.parseInt(data[2]), 
									Double.parseDouble(data[3]), 
									Integer.parseInt(data[4]),
									Integer.parseInt(data[5]), 
									Double.parseDouble(data[6]));
				
						
				
			}
			else {
				human = new Batter(Integer.parseInt(data[0]), 
												data[1], 
									Integer.parseInt(data[2]), 
									Double.parseDouble(data[3]), 
									Integer.parseInt(data[4]),
									Integer.parseInt(data[5]), 
									Double.parseDouble(data[6]));
				
			}
			list.add(human);
		}// for end (이렇게 적어놓는다)
	}
	
	
	//타율 순위출력 되도록 1위 ~ n
	public void hitAvgSorting() {
		// 등록된 사람 정렬할 배열에 담아 구하기
		ArrayList<Human> sortList = choicePosition(2);
	
		//정렬
		Human obj = null;
		for (int i = 0; i < sortList.size()-1; i++) {
			for (int j = i+1; j < sortList.size(); j++) {
				Batter b1 = (Batter)sortList.get(i);
				Batter b2 = (Batter)sortList.get(j);
				if(b1.getHitAvg() < b2.getHitAvg()) {
					obj = sortList.get(i); 
					sortList.set(i, sortList.get(j));
					sortList.set(j, obj);
				}
			}
		}
		for (int i = 0; i < sortList.size(); i++) {
			System.out.println(sortList.get(i).toString());
		}
	}
	
	//방어율 순위출력 1위 ~ n
	public void defenceSorting() {
		
	//	Human ballMan[] = choicePosition(1);
		ArrayList<Human> sortList = choicePosition(1);
		
		Human obj = null;
		for (int i = 0; i < sortList.size()-1; i++) {
			for (int j = i+1; j < sortList.size(); j++) {
				Pitcher p1 = (Pitcher)sortList.get(i);
				Pitcher p2 = (Pitcher)sortList.get(j);
				if(p1.getDefence() > p2.getDefence()) {
					obj = sortList.get(i);
					sortList.set(i, sortList.get(j));
					sortList.set(j, obj);
							
				}
			}
		}
		for (int i = 0; i < sortList.size(); i++) {
			System.out.println(sortList.get(i).toString());
		}
		
	}
	
	public ArrayList<Human> choicePosition(int num) {
		
		ArrayList<Human> reList = new ArrayList<Human>();
		
		for (int i = 0; i < list.size(); i++) {			//전체 Array리스트를 돌려준다
			Human human = list.get(i);
			if(num ==1) {	// pit
				if(human.getNumber() < 2000) {
					reList.add(human);
				}
			}
			else if(num ==2) {	// batter
				if(human.getNumber() > 2000) {
					reList.add(human);
				}
			}
		}
		return reList;
	}
}
```

## 이전 배열을 이용하여 값을 관리 하였으면 이번에는 ArrayList를 통해 데이터를 관리 하였다.
