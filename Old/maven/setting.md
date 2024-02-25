
# Maven 설치 및 설정

1. maven download
- apache-maven 알집을 다운로드한다

2.cmd 에서 maven이 깔린 폴더의 bin 폴더까지 위치 경로를 잡아준다.

3. path를 잡아주기 위해 내컴퓨터의 환경변수 path에 추가를 해준다.
- %M2_Home%\bin 으로 경로 추가

4. 그리고 시스템 변수에 M2_Home 으로 name 과 경로를 maven이 설치된 폴더까지 잡아주고 추가해준다.
![4](https://user-images.githubusercontent.com/65350890/90767426-2e1beb00-e328-11ea-8a2e-b8548d30d86f.PNG)

## Maven으로 프로젝트 생성

1. 프로젝트를 설치할 폴더 생성

2. cmd 창에서 maven을 실행하여 설치를 하기위해 명령어를 입력해 준다.
![5](https://user-images.githubusercontent.com/65350890/90767595-6f13ff80-e328-11ea-9354-92efccb04316.PNG)

- 맨 끝의 archetypeArtifactId 는 다른사람이 만들어놓은 maven의 틀을 가져온다는 의미
- 그 다음 끝의 artifactId 는 내가 생성할 프로젝트 명
- groupId는 패키지명과 비슷하다고 생각 (중복방지를 위한)(주로 도메인주소)
- archetype:generate 는 새로운 maven틀을 만든다는 의미

3. 명령어 실행<br>
![1](https://user-images.githubusercontent.com/65350890/90768595-eb5b1280-e329-11ea-9998-a7371f4f72c1.PNG)

4. interactive 모드 설정은 처음에 잡아줬는지 실행한껀지 물어보는 내용<br>
![2](https://user-images.githubusercontent.com/65350890/90768638-fb72f200-e329-11ea-8efa-44a2b2f23bd8.PNG)

5. 버전과 최종 생성 완료<br>
![3](https://user-images.githubusercontent.com/65350890/90768764-29583680-e32a-11ea-8f86-6f27330d15bd.PNG)

- 이렇게 아까 지정한 경로에 maven의 프로젝트가 다운되어진것을 확인 할 수 있다.
