# dao
### 이전 합쳐져있던 클래스와 메서드를 객체화 시켰으며 배열을 ArrayList를 통해 데이터를 관리할 수 있도록 수정

```java
import java.util.ArrayList;
import java.util.Scanner;

import dataPg.FileProc;
import dto.Student;

public class StudentDao {

	private ArrayList<Student> list = new ArrayList<Student>();
	private FileProc fp;
	Scanner sc = new Scanner(System.in);
	
	public StudentDao() {
		fp = new FileProc("Student list");
		this.loadData();
	}
	
	public void insert() {
		System.out.print("이름: ");
		String name = sc.next();
		System.out.print("나이: ");
		int age = sc.nextInt();
		System.out.print("국어점수: ");
		int korea = sc.nextInt();
		System.out.print("수학점수: ");
		int math = sc.nextInt();
		
		list.add(new Student(name, age, korea, math));
	}
	
	public void delete() {
		System.out.print("삭제할 학생 이름: ");
		String name = sc.next();
		
		int findIndex = search(name);
		
		if(findIndex != -1) {
			list.remove(findIndex);
			System.out.println("데이터를 삭제하였습니다.");
		}
		else {
			System.out.println("입력하신 학생은 없습니다.");
			return;
		}
	}
	
	public void select() {
		System.out.print("검색할 학생 이름: ");
		String name = sc.next();
		
		int findIndex = search(name);
		
		if(findIndex != -1) {
			System.out.println("데이터를 찾았습니다.");
			System.out.println("이름: "+list.get(findIndex).getName());
			System.out.println("나이: "+list.get(findIndex).getAge());
			System.out.println("국어점수: "+list.get(findIndex).getKorea());
			System.out.println("수학점수: "+list.get(findIndex).getMath());
		}
		else {
			System.out.println("입력하신 학생은 없습니다.");
			return;
		}
	}
	
	public void update() {
		System.out.print("수정할 학생의 이름: ");
		String name = sc.next();
		
		int findIndex = search(name);
		
		Student stu = list.get(findIndex);
		
		if(findIndex != -1) {
			System.out.println("수정할 데이터를 입력하세요.");
			System.out.print("국어점수: ");
			int korea = sc.nextInt();
			System.out.print("수학점수: ");
			int math = sc.nextInt();
			
			stu.setKorea(korea);
			stu.setMath(math);
		}
		else {
			System.out.println("입력하신 학생은 없습니다.");
			return;
		}
	}
	
	public void allPrint() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	public void subSum() {
		System.out.println("합계를 구할 과목을 고르시오. 1.국어 / 2.수학  ");
		System.out.print(">>>");
		int choice = sc.nextInt();
		int sum = 0;
		if(choice ==1) {
			for (int i = 0; i < list.size(); i++) {
				sum = sum + list.get(i).getKorea();
			}
			System.out.println("국어점수 합계: "+sum);
		}
		else if(choice ==2){
			for (int i = 0; i < list.size(); i++) {
				sum = sum + list.get(i).getMath();
			}
			System.out.println("수학점수 합계: "+sum);
		}
	}
	
	public void subAvg() {
		System.out.println("평균를 구할 과목을 고르시오. 1.국어 / 2.수학  ");
		System.out.print(">>>");
		int choice = sc.nextInt();
		int sum = 0;
		double avg = 0;
		if(choice ==1) {
			for (int i = 0; i < list.size(); i++) {
				sum = sum + list.get(i).getKorea();
				avg = (double)sum / list.size();
			}
			System.out.println("국어 점수 평균: "+avg);
		}
		else if(choice ==2){
			for (int i = 0; i < list.size(); i++) {
				sum = sum + list.get(i).getMath();
				avg = (double)sum / list.size();
			}
			System.out.println("수학 점수 평균: "+avg);
		}
	}
	
	public void sortStudent() {
		
		ArrayList<Student> sortList = new ArrayList<Student>();
		
		System.out.println("정렬할 과목을 고르시오. 1.국어 / 2.수학");
		System.out.print(">>>>>>");
		int choice = sc.nextInt();
		
		for (int i = 0; i < list.size(); i++) {
			sortList.add(list.get(i));
		}
		
		if(choice == 1) {
			Student stu = null;
			for (int i = 0; i < list.size()-1; i++) {
				for (int j = i+1; j < list.size(); j++) {
					Student s1 = sortList.get(i);
					Student s2 = sortList.get(j);
					if(s1.getKorea()> s2.getKorea()) {
						stu = sortList.get(i);
						sortList.set(i, sortList.get(j));
						sortList.set(j, stu);
					}
				}
			}
			for (int i = 0; i < sortList.size(); i++) {
				System.out.println(sortList.get(i).toString());
			}
		}
		else if(choice == 2) {
			Student stu = null;
			for (int i = 0; i < list.size()-1; i++) {
				for (int j = i+1; j < list.size(); j++) {
					Student s1 = sortList.get(i);
					Student s2 = sortList.get(j);
					if(s1.getMath()> s2.getMath()) {
						stu = sortList.get(i);
						sortList.set(i, sortList.get(j));
						sortList.set(j, stu);
					}
				}
			}
			for (int i = 0; i < sortList.size(); i++) {
				System.out.println(sortList.get(i).toString());
			}
		}
	}
	
	public int search(String name) {
		int findIndex = -1;
		for (int i = 0; i < list.size(); i++) {
			if(name.equals(list.get(i).getName())) {
				findIndex = i;
			}
		}
		return findIndex;
	}
	
	public void saveData() {
		ArrayList<String> datas = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			datas.add((list.get(i).toString()));
		}
		fp.dataSave(datas);
	}
	
	public void loadData() {
		
		ArrayList<String> datas = fp.dataLoad();
		
		
		for (int i = 0; i < datas.size(); i++) {
			
			String data[] = datas.get(i).split("-");
			Student student = null;
			student = new Student(data[0],Integer.parseInt(data[1]), Integer.parseInt(data[2]),Integer.parseInt(data[3]));
			list.add(student);
		}
		
	}
}
```
