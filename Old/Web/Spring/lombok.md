# 롬복

## - 롬복은 jar 파일을 다운받고 pom.xml에서도 의존성을 추가해야한다

#### 롬복 다운로드 url : https://projectlombok.org

## - 롬복 라이브러리 의존 관계 설정 pom.xml
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### 롬복을 활용하면 약간의 애너테이션 설정만으로 소스코드가 컴파일될 때 자동으로 추가 코드를 만들 수 있다.

## 롬복 애너테이션
|애너테이션|설명|
|:---:|---|
|@getter/ @setter|객체의 게터와 세터를 생성한다.|
|@ToString|toString() 메서드를 생성한다.|
|@EqualsAndHashCode|자바의 equals()메서드와 hashCode()메서드를 생성한다.|
|@NoArgsConstructor|인자가 없는 기본 생성자를 생성한다.|
|@RequiredArgsConstructor|@NonNull이 적용된 필드값이나 final로 선언된 필드값만 인자로 받는 생성자를 생성한다.|
|@AllArgsConstructor|객체의 모든 필드값을 인자로 받는 생성자를 생성한다.|
|@Data|@ToString, @Getter, @Setter, @EqualsAndHashCode, @RequiredArgsConstructor 애너테이션을 합쳐놓은 애너테이션이다.|
|@Builder|빌더 패턴을 사용할 수 있도록 코드를 생성한다.|
|@Log|자동으로 생기는 log라는 변수를 이용해서 로그를 출력할 수 있다.|
