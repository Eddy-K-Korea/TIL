package main;

import java.util.ArrayList;
import java.util.List;

import animal.Animal;
import animal.Cat;
import animal.Cow;
import animal.Dog;
import factory.AnimalFactory;

public class MainClass {

	public static void main(String[] args) {

		/*
		 
		 Singleton pattern
		 	중심이 되는 데이터 관리 template(list,Map)을 중심으로 어느 클래스에서나 접근이 용이하게 하기 위한 패턴.
		    instance가 한개의 의미.
		    
		 Factory pattern (공장 -> Object를 찍어낸다)
		 	원하는 클래스의 형태를 생성하기 위한 패턴.
		 	
		 interface, class 동물
		 
		 class		고양이, 강아지, 황소
		 
		 Animal ani = new Cat();
		 
		 */
		
		Animal ani1 = AnimalFactory.create("멍멍이");
		Animal ani2 = AnimalFactory.create("야옹이");
		Animal ani3 = AnimalFactory.create("황소");
		
		ani1.printDescript();
		ani2.printDescript();
		ani3.printDescript();
		
		((Dog)ani1).dogMethod();
		
		
		List<Animal> list = new ArrayList<Animal>();
		
		Animal ani = null;
		
		String aname[] = {"황소","황소","야옹이","멍멍이","멍멍이","멍멍이"};
		
		for (int i = 0; i < aname.length; i++) {
			ani = AnimalFactory.create(aname[i]);
			list.add(ani);
		}
		
		/*
		ani = AnimalFactory.create("황소");
		list.add(ani);
		ani = AnimalFactory.create("황소");
		list.add(ani);
		ani = AnimalFactory.create("야옹이");
		list.add(ani);
		ani = AnimalFactory.create("멍멍이");
		list.add(ani);
		ani = AnimalFactory.create("멍멍이");
		list.add(ani);
		ani = AnimalFactory.create("멍멍이");
		list.add(ani);
		*/
		
		for (int i = 0; i < list.size(); i++) {
			Animal a = list.get(i);
			a.printDescript();
			
			if(a instanceof Cat) {
				((Cat)a).catMethod();
			}
			else if(a instanceof Dog) {
				((Dog)a).dogMethod();
			}
			else if(a instanceof Cow) {
				((Cow)a).cowMethod();
			}
		}
	}	
}
