# Table (테이블)

## 테이블 생성
```html
<table border="1" style="width: 800px; height: 100%"> 
<col width="600"><col width="200">
	<tr>
		<td colspan="2">1</td>
	<!-- 	<td>2</td> -->
	</tr>	

	<tr>
		<td>1</td>
		<td>2</td>
	</tr>	
	<tr>
		<td>1</td>
		<td>2</td>
	</tr>	
	<tr>
		<td>1</td>
		<td>2</td>
	</tr>	
</table>
```
```
<table> 테크는 테이블을 생성 border은 테투리 및 테이블의 선 굵기를 결정, style은 크기를 결정한다.<br>
하위에 컬럼의 크기를 따로 설정해 줄 있다. <tr>은 row <tr>안에서 <th>(행의 head) , <td>(행안의 내용) 
```

## 테이블 크기 및 정렬
```html
<div align="center">
 <table border="1" style="width:50%">	
 	<tr>
 		<th>번호</th><th>성</th><th>이름</th><th>나이</th>
 	</tr>
 	<tr>
 		<th>1</th><td align="center">홍</td><td>길동</td><td>24</td>
 	</tr>
 	<tr>
 		<th>2</th><td align="center">일</td><td>지매</td><td>21</td>
 	</tr>
 	<tr>
 		<th>3</th><td align="center">성</td><td>춘향</td><td>17</td>
 	</tr>
 </table>
</div>
```
 - div로 묶어서 테이블 전체의 정렬을 할 수 있다.
 - <td>안에서 align을 통해 내용의 정렬을 할 수 있다.
 
 ## 테이블 행,열 합침
 
 ```html
 <table border="1" style="width: 50%">
	<tr>
		<th>성</th>
		<th>이름</th>
		<th>나이</th>
		<th colspan="3">전화번호</th>
		<!-- <th>전화번호</th> -->
		
	</tr>
	<tr>
		<td>홍</td>
		<td>길동</td>
		<td>24</td>
		<td>123-4567</td>
		<td>032-480-246</td>
		<td>054-458-741</td>
		
	
	</tr>
</table>

<br><br>

<div align ="right">
<table border="1">
	<caption>주소록</caption>
	<tr>
		<th>번호</th>
		<td>1</td>
	</tr>
	<tr>
		<th>성</th>
		<td>홍</td>
	</tr>
	<tr>
		<th>이름</th>
		<td>길동</td>
	</tr>
	<tr>
		<th rowspan="2">전화번호</th>
		<td>123-4567</td>
	</tr>
	<tr>
	<!-- 	<th>전화번호</th> -->
		<td>234-5678</td>
	</tr>

</table>
</div>
```
colspan과 rowspan 을 통해 행과 열을 합칠 수 있다. 
- 합칠 갯수를 숫자로 결정한다.

## EX
```html
<table border="5" width="50%" bgcolor = "#cccc00" cellpadding= "10" rules="rows">
<col width="200"><col width="300"><col width="300">
	<caption>가격표</caption>
	<tr>
		<th>크기</th><th>내용량</th><th>가격</th>
	</tr>
	<tr>
		<th>보통</th><td>500g</td><td>4,800원</td>
	</tr>
	<tr>
		<th>대</th><td>800g</td><td>7,200원</td>
	</tr>
	<tr>
		<th>특대</th><td><font color="red"><b>1,200g</b></font></td><td><font color="red"><b>10,000원</b></font></td>
	</tr>

</table>
```
cellpadding의 경우 각 칸의 크기를 키워준다. rules는 테이블 내부의 선을 제거해준다.(rows는 세로선, cols는 가로선, none은 모든 선, rules=group thead,tbody,tfoot의 경계선)

![표1](https://user-images.githubusercontent.com/65350890/86756668-84391580-c07d-11ea-9642-5e7cdae954f4.png)

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#c2 {
	text-align: left;
}
</style>
</head>
<body>
<h2>고교 수영부 기록</h2>

<table border="1" style="width: 300px; height: 600px'">
<caption id="c1">■수영기록</caption>
	<tr style="background-color: lime;">
		<th>종목</th><th>거리</th><th>성별</th><th>기록</th>
	</tr>
	<tr>
		<th>자유형</th><th>100m</th><td align="center">남자</td><td align="right">50초60</td>
	</tr>
	<tr>
		<th>자유형</th><th>100m</th><td align="center">여자</td><td align="right">55초56</td>
	</tr>
	<tr>
		<th>자유형</th><th>200m</th><td align="center">남자</td><td align="right">1분49초23</td>
	</tr>
	<tr>
		<th>자유형</th><th>200m</th><td align="center">여자</td><td align="right">1분59초56</td>
	</tr>
	<tr>
		<th>배형</th><th>100m</th><td align="center">남자</td><td align="right">55초16</td>
	</tr>
	<tr>
		<th>배형</th><th>100m</th><td align="center">여자</td><td align="right">1분01초13</td>
	</tr>
	<tr>
		<th>배형</th><th>200m</th><td align="center">남자</td><td align="right">1분59초74</td>
	</tr>
	<tr>
		<th>배형</th><th>200m</th><td align="center">여자</td><td align="right">2분10초46</td>
	</tr>


</table>
</body>
```
![표2](https://user-images.githubusercontent.com/65350890/86757072-d37f4600-c07d-11ea-85e9-44838d38d71f.png)

