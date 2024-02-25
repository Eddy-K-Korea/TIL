# dto
```java
public class Student {

	private String name;
	private int age;
	private int korea;
	private int math;
	
	public Student() {
	}

	public Student(String name, int age, int korea, int math) {
		super();
		this.name = name;
		this.age = age;
		this.korea = korea;
		this.math = math;
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

	public int getKorea() {
		return korea;
	}

	public void setKorea(int korea) {
		this.korea = korea;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return name + "-" + age + "-" + korea + "-" + math;
	}
}
```
