# 어노테이션 이용

### xml방식말고 어노테이션 방식으로도 외부설정을 조립(바인드)할 수 있다.
1. 우선 console 과 exam 을 조립하는 xml 지시서를 어노테이션으로 변경
```xml
<bean id="console" class="spring.di.ui.GridExamConsole">
  <property name="exam" ref="exam"></property>  <!--console의 set에 exam을 결합하는 부분 -->
</bean>
```  
- 위의 코드중 ```<property name="exam" ref="exam"></property>``` 을 어노테이션으로 변경

### 스프링이 어노테이션을 사용하기위해 xml을 읽으려면 코드 추가가 필요하다
- 아래 Namespaces에서 context를 체크하여 ```xmlns:context="http://www.springframework.org/schema/context"```을 추가해준다
- 그러면 어노테이션을 사용 할 수 있다.

2. GridExamConsole에서 set에 해당하는 부분에 어노테이션을 사용하여 <bean id ="exam1" class="spring.di.emtity.NewlecExam" p:kor="10" p:eng="10"/> 을 결합
```java
@Autowired
@Override
public void setExam(Exam exam) {
  this.exam = exam;

}
```
- Autowire를 하게되면 스프링이 xml을 읽고 java클랫스 파일에서 autowired된 부분에 객체를 넣게 된다.



### 그러면 어떻게 인식을 하고 값을 넘기는가?
- 스프링은 객체의 자료구조가 동일한 (예: NewlecExam class)를 찾아가 읽게된다.
- 만약 xml에 동일한 자료구조의 코드가 있다면 어떻게 구분?
#### EX
```xml
<bean id ="exam1" class="spring.di.emtity.NewlecExam" p:kor="10" p:eng="10"/>
<bean id ="exam2" class="spring.di.emtity.NewlecExam" p:kor="20" p:eng="20"/>
```
- id 값으로 구분을 해놓는다

### Autowired한 부분에 @Qualifier을 추가하고 사용할 객체의 id를 설정해 준다
```java
@Autowired
@Qualifier("exam2")
@Override
public void setExam(Exam exam) {
  this.exam = exam;

}
```

- 이렇게 설정하면 exam2인 ```<bean id ="exam2" class="spring.di.emtity.NewlecExam" p:kor="20" p:eng="20"/>``` 의 값들이 NewlecExam에 설정되고 console과 결합하여 total, avg가 그에 맞게 출력된다.
