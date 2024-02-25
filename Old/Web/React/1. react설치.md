# React 설치 가이드
### 여러가지 방법이 있지만 toolchain 방법 중 create-react-app을 활용하여 react install
## 1. cmd 창에서 npm install -g create-react-app 실행 (-g는 글로벌 인스톨로 로컬어디에서나 사용가능하게 다운로드하는것)
```cmd
npm install -g create-react-app
```
>### __npm과 npx의 차이는 npm은 프로그램을 설치, npx는 임시로 설치후 지우는것(용량, 최신버전을 사용한다라는 이점)__
<br>

## 2. 설치된 리액트 폴더에 create-react-app . 을 실행(.은 현재 폴더를 의미함)
```cmd
create-react-app .
```

## 기본적인 리액트 모듈 및 index.js가 생성된다.

---
# React 배포 가이드

## 1. npm run build 은 배포할때 사용하는 명령어(실제 서비스할때는 build폴더안에 위치시킨다)
```cmd
npm run build
```

## 2. npx serve -s build 를 통해 서버를 실행한다.
```cmd
npx serve -s build
```

