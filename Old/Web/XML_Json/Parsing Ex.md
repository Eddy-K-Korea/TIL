# 예제 (외부 파일에서 데이터 추출)
- 서울시 공공 버스 데이터 샘플 XML에서 데이터 추출
```xml
<SebcBusStopEng>
<list_total_count>1743</list_total_count>
<RESULT>
<CODE>INFO-000</CODE>
<MESSAGE>정상 처리되었습니다</MESSAGE>
</RESULT>
<row>
<MAIN_KEY>BE_LiST25-0342</MAIN_KEY>
<MNG_NO>04-236</MNG_NO>
<NAME_ENG>Ttukseom Station Exit 8</NAME_ENG>
<H_ENG_CITY/>
<H_ENG_GU/>
<H_ENG_DONG/>
</row>
<row>
<MAIN_KEY>BE_LiST25-0343</MAIN_KEY>
<MNG_NO>04-237</MNG_NO>
<NAME_ENG>Ttukseom Station Exit 1</NAME_ENG>
<H_ENG_CITY/>
<H_ENG_GU/>
<H_ENG_DONG/>
</row>
<row>
<MAIN_KEY>BE_LiST25-0344</MAIN_KEY>
<MNG_NO>04-238</MNG_NO>
<NAME_ENG>Ttukseom Station Exit 3, 5</NAME_ENG>
<H_ENG_CITY/>
<H_ENG_GU/>
<H_ENG_DONG/>
</row>
<row>
<MAIN_KEY>BE_LiST25-0345</MAIN_KEY>
<MNG_NO>04-239</MNG_NO>
<NAME_ENG>Ttukseom Station Exit 4</NAME_ENG>
<H_ENG_CITY/>
<H_ENG_GU/>
<H_ENG_DONG/>
</row>
<row>
<MAIN_KEY>BE_LiST25-0346</MAIN_KEY>
<MNG_NO>04-240</MNG_NO>
<NAME_ENG>Seongsu Station</NAME_ENG>
<H_ENG_CITY/>
<H_ENG_GU/>
<H_ENG_DONG/>
</row>
</SebcBusStopEng>
```
### 추출
```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p id="demo"></p>

<script type="text/javascript">
let xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {
	if(this.readyState == 4 && this.status == 200){
		nodeValueFunc(this);
	}
}
// 아래 open의 내용은 똑같으며, url에 외부파일의 경로를 대입
xhttp.open("GET","http://openapi.seoul.go.kr:8088/sample/xml/SebcBusStopEng/1/5/",true);  
xhttp.send();

function nodeValueFunc(xml) {
//	alert('nodeValueFunc');
	let arr, txt, xmlDoc;
	txt = "";
	xmlDoc = xml.responseXML;
	
	arr = xmlDoc.getElementsByTagName("NAME_ENG");  // 추출할 태크명 
	for (i = 0; i < arr.length; i++) {
		txt += arr[i].childNodes[0].nodeValue + "<br>";
	}
	document.getElementById('demo').innerHTML = txt;
}

</script>
</body>
</html>
```
결과는 name_eng의 값들이 출력


# 예제 2 
```xml
<?xml version="1.0" encoding="UTF-8" ?>

<rss version="2.0"
	xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
	xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:taxo="http://purl.org/rss/1.0/modules/taxonomy/"
	xmlns:activity="http://activitystrea.ms/spec/1.0/" >
<channel>
	
		
			<title><![CDATA[노진화의 문화콘텐츠 & 브랜드스토리마케팅]]></title>
			<link>https://blog.naver.com/vecomm</link>
			
				<image>
					<url><![CDATA[https://blogpfthumb-phinf.pstatic.net/MjAxODA2MDZfMjM1/MDAxNTI4MjcxNDg2NDc0.6irCFcEd4Xu7OI_mATy9ZLI1oFfNzSTvaoABz1Q...]]></url>
					<title><![CDATA[노진화의 문화콘텐츠 & 브랜드스토리마케팅]]></title>
					<link>https://blog.naver.com/vecomm</link>
				</image>
			
			<description><![CDATA[노진화: 인문학. 브랜드. 마케팅. 커뮤니케이션. 콘텐츠.  심리학. 자녀양육. 사진....아직도 호기심이 너무 많은/ 애장품1호 라이카Q]]></description>
			<language>ko</language>
			<generator>Naver Blog</generator>
			<pubDate>Wed, 15 Jan 2020 12:07:19 +0900</pubDate>

			
				<item>
					<author>vecomm</author>
					<category><![CDATA[인문ㅣ역사]]></category>
					<title><![CDATA[레빈을 통해 톨스토이의 생각을 엿보다]]></title>
					<link>https://blog.naver.com/vecomm/221736002720</link>
					<guid>https://blog.naver.com/vecomm/221736002720</guid>
					<description><![CDATA[오랜만의 글이다.다듬어지고, 완성되기를 기다리는 일은 이.......]]></description>
					<pubDate>Fri, 13 Dec 2019 12:45:06 +0900</pubDate>
					<tag><![CDATA[]]></tag>
					<activity:verb>http://activitystrea.ms/schema/1.0/post</activity:verb>
					<activity:object-type>http://activitystrea.ms/schema/1.0/blog-entry</activity:object-type>
				</item>
			
				<item>
```
위의 xml은 더 많은 데이터를 가지고 있지만 편의상 일부만 올렸다.

```html
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p id="demo"></p>

<script type="text/javascript">
let xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {
	if(this.readyState == 4 && this.status == 200){
		nodeValueFunc(this);
	}
}
xhttp.open("GET", 'vecomm.xml', true);  // 끌어올 파일의 경로를 url에 넣어줌
xhttp.send();

function nodeValueFunc(xml) {
	let txt;
	txt = "";
	xmlDoc = xml.responseXML;
	
	arr = xmlDoc.getElementsByTagName('item');
	for (i = 0; i < arr.length; i++) {
		txt += arr[i].childNodes[5].childNodes[0].nodeValue + "<br>"; 
// item 태그의 title을 불러오는 목적으로 arr 배열에 item의 object를 넣고 for문을 돌려준다. 첫번재 childNodes[5] = title이고 ,그 안에 또 태그로 감싸져 있기 때문에
// 그 태그의 0번째가 title의 값이 있다. 따라서 arr[i].childNodes[5].childNodes[0].nodeValue 으로 값을 추출 할 수 있다.
	}
	document.getElementById('demo').innerHTML = txt;
}
</script>
</body>
</html>
