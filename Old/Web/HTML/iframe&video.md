# iframe

## 웹페이지 안에 웹페이지 (특정 사이트나, 유튜브)

```html
<h3>index2</h3>
<iframe src="https:www.naver.com" width="300" height="300"></iframe>

<iframe src="index.html" width="300" height="300"></iframe>
```
- 첫번째 iframe은 네이버를 가져오는 코드이다. (단 최근 몇몇 사이트는 보안상의 이유로 끌어올 수 없다)
- 두번째 내가 만들어 놓은 html사이트를 불러 온다.

## 비디오 넣기
```html
<video width="400" controls="controls">
	<source src="mov_bbb.mp4" type="video/mp4">

</video>
```
같은 폴더안에 있는 비디오 파일을 불러오는 코드이다. controls를 통해 계속 리플레이 할 수 있게 만들 수도 있다.

