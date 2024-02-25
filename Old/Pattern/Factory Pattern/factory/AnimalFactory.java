package factory;

import animal.Animal;
import animal.Cat;
import animal.Cow;
import animal.Dog;

public class AnimalFactory {

	// Factory 로 원하는 Class를 생성하기 위한 목적	
	public static Animal create(String animalName) {
		
		if(animalName.equals("")) {
			System.out.println("생성할 클래스가 없습니다.");
		}
		else if(animalName.equals("야옹이")) {
			return new Cat();
		}
		else if(animalName.equals("멍멍이")) {
			return new Dog();
		}
		else if(animalName.equals("황소")) {
			return new Cow();
		}
		return null;
	}
}
