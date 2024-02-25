# Oracle
### - DataBase 관리하는 시스템중 하나이다. 데이터베이스 시스템 시장에서 독보적인 선두를 차지하고 있다.

## 오라클 설치
#### - Oracle 11g Express Edition 11g 를 이용한다.

Port for 'Oracle Database Listener': 1521 (디비접속하기 위한 리스너 포트) <br>
Port for 'Oracle Services for Microsoft Transaction Server': 2030(오라클 서비스포트)<br>
Port for 'Oracle HTTP Listener': 8080(오라클XE GUI 환경 인터페이스 포트)<br>
설치후 나오는 정보로 HTTP가 8080으로 추후 다른 프로그램의 포트 충돌이 일어나지 않도록 오라클의 포트나 새로 설치할 포트에 주의 해야한다.

#### - SQL Plus 콘솔창을 실행시켜 conn 을 입력한다.

user name 과 password에 모두 system을 기입하면 connected 문구가 출력된다.

#### - 오라클이 계속 실행이되면 컴퓨터에 무리가 가기 때문에 '컴퓨터 관리'에서 '서비스 및 응용프로그램' 서비스를 클릭하여 Oracle의 실행을
자동에서 수동으로 변경해준다.

#### - HR 계정도 사용하기 위해 수정을 한다.
SQL> alter user hr  <br>
  2  identified by hr <br>
  3  account unlock; <br>
#### - hr 계정에 접속을 하면( SQL> conn hr/hr) Connected.가 되는 것을 확인 할 수 있다.
같은 방법으로 scott 계정도 alter를 이용하여 변경하고 사용하기 위해 준비를한다.

## SQL Developer tool 설치
#### - 오라클 홈페이지에서 SQL developer를 설치한다.
#### - 왼쪽 상단 + 모양의 문양을 클릭하여 새로운 접속을 위한 창을 실행한다.
#### - 새로 만들기/데이터베이스 접속이 실행되며 ,  접속이름은 프로젝트 명처럼 자유작명.
#### 사용자 이름은 sys , 비밀번호는 설치하면서 SQL 설치하면서 작성했던 것으로 기재. SID는 xe 그리고 테스트 실행
#### - 이후 접속을 누르면 생성이된다.






  
