# java로 DI 구현해보기
- spring을 사용하기 이전에 java로 어떤 형식인지 감을 잡아본다

### 1. 우선 인터페이스를 사용하여 사용할 메소드를 설정한다.
#### - Main

```java
package spring.di;

import spring.di.emtity.NewlecExam;
import spring.di.entity.Exam;
import spring.di.ui.ExamConsole;
import spring.di.ui.GridExamConsole;
import spring.di.ui.InlineExamConsole;

public class program {

	public static void main(String[] args) {
		
		Exam exam = new NewlecExam(); // 인터페이스를 사용하여 사용할 메소드 객체를 생성
		ExamConsole console = new InlineExamConsole(exam); //  exam 을 DI 하였다  그리고 아래 조립할수도 있고 위에 조립하여 모양을 바꿀 수 있다.
	//	ExamConsole console = new GridExamConsole(exam);
		console.print();
	}

}
```
### 2. exam의 인터페이스 구현
```java
package spring.di.entity;

public interface Exam {
	int total();
	float avg();
}
```
### 3. 인터페이스에 오버라이드한 사용한 메소드 구현
```java
package spring.di.emtity;

import spring.di.entity.Exam;

public class NewlecExam implements Exam {

	private int kor;
	private int eng;
	private int math;
	private int com;
	
	@Override
	public int total() {
		// TODO Auto-generated method stub
		return kor +eng + math +com;
	}

	@Override
	public float avg() {
		// TODO Auto-generated method stub
		return total()/4.0f;
	}

}
```

- 이렇게 구현하여 exam의 newlecExam을 생성하여 exam의 객체는 유지하면서 구현할 메소드를 사용할 수 있다.(뒤의 객체만 바꿔줄 수 있다)


- 이렇게 구한 total과 avg를 각각 다른 방식으로 찍어보려고 한다. 
- 그래서 ExamConsole 인터페이스를 활용하여 생성한 객체는 유지한채 뒤의 메소드부분만 다르게 해서 조립해준다.

### 4. ExamConsole 인터페이스 생성
```java
package spring.di.ui;

public interface ExamConsole {
	void print();
}
```
### 5. inline방식으로 출력할 메소드 구현
```java
package spring.di.ui;

import spring.di.entity.Exam;

public class InlineExamConsole implements ExamConsole {

	private Exam exam;
	
	public InlineExamConsole(Exam exam) {
		this.exam = exam;
	}

	@Override
	public void print() {
		System.out.printf("total is %d, avg is %f\n", exam.total(),exam.avg()); //출력할 메소드 구현
	}

}
```
### 6. grid방식으로 출력할 메소드 구현
```java
package spring.di.ui;

import spring.di.entity.Exam;

public class GridExamConsole implements ExamConsole {

private Exam exam;
	
	public GridExamConsole(Exam exam) {
		this.exam = exam;
	}
	
	@Override
	public void print() {
		System.out.println("----------------");
		System.out.println("total      avg ");
		System.out.println("----------------");
		System.out.printf(" %3d      %3.2f \n", exam.total(), exam.avg());
		System.out.println("-----------------");
	}

}
```

- 아까 맨위 메인에서 보면 알 수 있듯이 생성된 console에 사용하고 싶은 메소드가 있는 객체를 조립하여 출력을 할 수 있다.
```java
	ExamConsole console = new InlineExamConsole(exam); //  exam 을 DI 하였다  그리고 아래 조립할수도 있고 위에 조립하여 모양을 바꿀 수 있다.
	ExamConsole console = new GridExamConsole(exam);
		console.print();
```

# DI 값 설정하기

### NewlecExam 에 kor, eng, math, com 의 변수는 선언 되었지만 값을 넣어줄 setter가 없다. 값을 설정해주기 위해 setter을 넣어준다
```java
public int getKor() {
	return kor;
}

public void setKor(int kor) {
	this.kor = kor;
}

public int getEng() {
	return eng;
}

public void setEng(int eng) {
	this.eng = eng;
}

public int getMath() {
	return math;
}

public void setMath(int math) {
	this.math = math;
}

public int getCom() {
	return com;
}

public void setCom(int com) {
	this.com = com;
}
```	
### setting.xml 에서 값들을 넣어준다
```xml
<bean id ="exam" class="spring.di.emtity.NewlecExam"> <!-- 일반적으로 태그를 사용하여 진행할때-->
		<property name="kor">		<!-- 프로퍼티 설정 그리고 name은 변수명(set은 적어주지 않는다) value를 태그안 또는 밖에 적어준다 -->
			<value>10</value>
		</property>
		<property name="eng" value="10"></property>
		<property name="math" value="10"></property>
		<property name="com" value="10"></property>
</bean>		
```
- 이렇게 값을 넣어줄 수 있다.

## DI 생성자를 통해 값을 넣기
### NewlecExam에 값을 넣어 생성할 수 있는 생성자를 추가해 준다
```java
public NewlecExam() {
}



public NewlecExam(int kor, int eng, int math, int com) {
	this.kor = kor;
	this.eng = eng;
	this.math = math;
	this.com = com;
}

```

### setting.xml 에서 생성자를 통한 값을 넣어준다
```xml
<bean id ="exam" class="spring.di.emtity.NewlecExam">
		<!-- 생성자로 접근하여 값을 넣어줄때 -->
		<constructor-arg name="kor" type="int" value="10"></constructor-arg> <!--  name 으로 직접 변수명을 적어줄 수 있다. type은 자료형 -->
		<constructor-arg index="1" value="20"></constructor-arg>
		<constructor-arg index="3" value="30"></constructor-arg>
		<constructor-arg index="2" value="40"></constructor-arg> 
</bean>	
```
- index를 통해 정해진 순서에 맞게 값을 넣어줄 수 있다. value="30"은 인덱스가 3이기 때문에 com의 변수의 값으로 설정된다. 
- index대신에 name을 통해 값을 넣어줄 수 있다.(명시적으로 변수명을 적어주기 때문에 이 방법이 더 좋다)


### bean 아래에 태그를 만들지 않고 bean 태그에 바로 값 대입하기
```xml
<bean id ="exam" class="spring.di.emtity.NewlecExam" p:kor="10" p:eng="10"/> 
```
- 위의 코드 처럼 한번에 값을 넣어줄 수 있다. p라는 지정자를 통해서 값이 들어간다
- 단, 위와 같이 적용하기 위해서는 namespace 코드를 상단에 추가해야한다

```xml
xmlns:p="http://www.springframework.org/schema/p" 
```
- 위의 태그는 아래 source 옆 Namespaces에서 P 를 체크해 주면 자동으로 추가가 된다.

## DI에서 콜렉션 사용하기

## 1. 기본 방법
### Main 에서 ArrayList를 사용하기 위해 우선 setting.xml에서 생성해준다
```xml
<bean id ="exams" class="java.util.ArrayList">	<!-- 내장함수인 util의 ArrayList를 접근하고 main에서 불러주기위해 id를 지정해준다 -->
	<constructor-arg>	<!--생성자를 만드는 태그사용 -->
		<list>	<!-- list를 만듬 -->
			<bean id ="exam" class="spring.di.emtity.NewlecExam" p:kor="1" p:eng="1"/>	
			<!-- 리스트에 넣을 객체 및 값을 list태그안에 넣는다  1. NewlecExam의 kor과 eng의 값을 넣은 객체를 add -->
			<ref bean="exam"/>
			<!-- exam 객체 (total, avg) add -->
		</list>
	</constructor-arg>
</bean> 
```
### main에서 불러준다
```java
List<Exam> exams = (List<Exam>)context.getBean("exams");	//setting.xml 에서 id가 exams를 불러온다
for (Exam e : exams) {
	System.out.println(e);
}
```
## 2. namespace 방법 이용
### namespace에서 라이브러리를 사용하여 리스트를 더 간단하게 생성할 수 있다.
```xml
xmlns:util="http://www.springframework.org/schema/util"
```
- 코드 추가
```xml
<util:list id="exams" list-class="java.util.ArrayList">	 <!-- 내장함수인 util의 ArrayList를 접근하고 main에서 불러주기위해 id를 지정해준다 -->
	<bean id ="exam" class="spring.di.emtity.NewlecExam" p:kor="1" p:eng="1"/> <!-- 생성사 생성 태그와 list 태그를 생성하지않고 바로 넣어줄 수 있다.-->
	<ref  bean="exam"/>
</util:list>
```
