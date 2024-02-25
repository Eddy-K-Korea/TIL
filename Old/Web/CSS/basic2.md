# 틀 잡기
## Css를 활용하여 틀잡기
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#fullScreen{
	width: 800px;
	height: 500px;
	background-color: red;
}
#left{
	width: 400px;
	height: 500px;
	background-color: orange;
	float: left;
}

#right{
	width: 400px;
	height: 500px;
	background-color: yellow;
	float:left;
}
#leftup{
	width: 300px;
	height: 300px;
	background-color: blue;
	margin-left: 10px;
}

#leftdown{
	width: 250px;
	height: 200px;
	background-color: olive;
}
#rightup{
	width: 350px;
	height: 200px;
	background-color: black;
	
}
#rightdown{
	width: 200px;
	height: 300px;
	background-color: gray;
}
</style>
</head>
<body>

<div id="fullScreen">
	<div id ="left">
		<div id="leftup">
		</div>
		<div id="leftdown">
		</div>
	</div>
	
	<div id ="right">
		<div id="rightup">
		</div>
		<div id="rightdown">
		</div>
	</div>
</div>
</body>
</html>
```
div로 묶은다음 큰틀안에 작은 틀을 넣어주고 그 묶은 div들의 css를 설정하여 틀을 잡아준다.

## 틀잡기 예시 2
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
header{
	background-color: orange;
	width: 700px;
	height: 100px;
	margin: 10px auto;
	padding: 5px;
	border: 1px solid;
}
main{
	background: #a6ae24;
	width: 500px;
	height: 150px;
	margin: 0 auto;
	padding: 20px 0 20px 0;
	border-bottom: 1px solid;
}

footer{
	background-color: #75c1e3;
	margin: 30px auto;
	padding: 20px;
	width: 650px;
	height: 50px;
	border: 3px dotted;
}

#box1{
	background-color: #ff8000;
	width: 250px;
	height: 100px;
	float: left;
}
#box2{
	background-color: #be8011;
	width: 250px;
	height: 100px;
	float: left;
}
</style>
</head>
<body>
<header>
	<p> header 영역</p>
</header>

<main>
	<p> main 영역 </p>
	<div id="box1">
	box1
	</div>
	<div id="box2">
	box2
	</div>

</main>

<footer>

<p>footer 영역</p>
</footer>


</body>
</html>
```
위의 예시에서 사실 div사이의 여백은 보통은 주지 않는다.

![image](https://user-images.githubusercontent.com/65350890/87286045-492e5a80-c533-11ea-8781-909b4628e1a6.png)

## 박스 특수처리(그라데이션)
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#d1{
	width: 100%;
	height: 80px;
	background:linear-gradient(to bottom,blue,white);   //그라데이션을 주어 설정할 수 있다.
	border-radius: 15px 15px; // 끝부분에 곡선처리
	box-shadow: 2px 2px 2px 2px gray; //박스의 그림자 처리
	padding: 10px;
	
}
p.p1{
	font-style: italic;
}

</style>
</head>
<body>

<div id="d1">
<p class="p1">CSS를 사용한 장식 테두리<br>
좋아하는 메세지를 작성합니다.</p>

</div>

</body>
</html>
```
![image](https://user-images.githubusercontent.com/65350890/87286329-b215d280-c533-11ea-9ea7-62df9412358f.png)

## 배경이미지 고정
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
	background-image: url('city.jpg');
	background-repeat: no-repeat;
	background-position: right bottom;
	background-attachment: fixed;  /* 오른쪽 아래 시작을 고정하도록 한다.  */
	
}

</style>
</head>
<body>

<h1>징기스칸 어록</h1>
<pre>
집안이 나쁘다고 탓하지 말라.
나는 아홉 살 때 아버지를 잃고 마을에서 쫓겨났다.

가난하다고 말하지 말라.
나는 들쥐를 잡아먹으며 연명했고,
목숨을 건 전쟁이 내 직업이고 내 일이었다.

작은 나라에서 태어났다고 말하지 말라.
그림자 말고는 친구도 없고 병사로만 10만.
백성은 어린애, 노인까지 합쳐 2백만도 되지 않았다.

배운게 없다고 힘이 없다고 탓하지 말라.
나는 내 이름도 쓸 줄 몰랐으나 남의 말에 귀 기울이면서
현명해지는 법을 배웠다.

너무 막막하다고, 그래서 포기해야겠다고 말하지 말라.
나는 목에 칼을 쓰고도 탈출했고,
뺨에 화살을 맞고 죽었다 살아나기도 했다.

적은 밖에 있는 것이 아니라 내 안에 있었다.
나는 내게 거추장스러운 것은 깡그리 쓸어버렸다.

나를 극복하는 그순간 나는 징기스칸이 되었다.
</pre>
</body>
</html>
```

## 배경이미지(웹사이즈 크기), 박스 반투명 설정
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
body{
	width: 100%;
	background: url('back.jpg') no-repeat center center fixed;
	-webkit-background-size: cover;	  /* 웹크기에 따라 줄어들고 커지고 하는 옵션 */
	-moz-background-size:cover; 
	-o-background-size:cover;
	background-size: cover;         -------------------여기까지--------------
}
.main{
	width: 800px;
	background-color: rgba(255,255,255,0.5);  /*rgba(rgb알파 를 통해 빛의 세기까지 조절) 맨마지막 수치를 통해 반투명 효과를 설정*/
	margin: 1em auto;
	padding: 1em;

}

</style>
</head>
<body>

<div class="main">
Bit Camp에 오신것을 환영합니다.
</div>
</body>
</html>
```
![image](https://user-images.githubusercontent.com/65350890/87286682-1cc70e00-c534-11ea-8ac3-ea7d9f086be5.png)
