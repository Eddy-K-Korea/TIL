# ajax (fetch API)

이전 제이쿼리를 사용하는 ajax도 있지만 최신 API인 fetch를 사용하여 ajax를 사용할 수있다.

```html
<script>
  function fetchPage(name){ //name은  데이터의 변수명
   //fetch안에 들어갈 변수는 html 또는 데이터를 가져올 경로 그리고 then은 데이터를 가지러 간 후 callback 함수
   fetch(name).then(function(response){  
   // text()는 경로에서 가져온 데이터 그 다음 then은 그 데이터로 실행할 함수 이다.
      response.text().then(function(text){
      // 여기서는 article 태그에 text데이터를 삽입한다
        document.querySelector('article').innerHTML = text;
      })
    });
  }
  </script>
```


### 예제
1. 본문
```html
  <ol id="nav">
 
  </ol>
```  

2. ajax 부분

```html
<script>
// list 파일에서 ajax하여 데이터를 읽어온다.
 fetch('list').then(function(response){
    response.text().then(function(text){
//가져온 문자열의 데이터를 , 단위로  끊어서 배열에 넣어준다.
      var items = text.split(',');
 // i와 tags는 반복문을 돌려주는 용도와 추가할 태그를 담기위한 변수
      var i = 0;
      var tags = '';
      while(i<items.length){
        var item = items[i];
        item = item.trim();
        //추가할 <li>태그를 적어준다 <a>와 그리고 값이 들어가야할 자리에는 item을 넣어준다('' 와""를 적절하게 사용하여 변수 대입)
        var tag = '<li><a href="#!'+item+'" onclick="fetchPage(\''+item+'\')">'+item+'</a></li>';
        tags = tags + tag;
        i = i + 1;
      }
      //id가 nav인 태그에 삽입
      document.querySelector('#nav').innerHTML = tags;
    })
  });
  
  </script>
```
3. list 파일
```html
html,css,javascript,ajax
```
- 리스트의 값들


---

## ajax의 호환

fetch API가 호환이 되지 않는 브라우저의 경우 polyfill을 설정하여 ajax 를 적용할 수 있다.

https://github.com/github/fetch

해당 깃허브의 fetch.js가 있다. 그리고 스크립트를 추가해 주면 위에서 사용하였던 ajax fetch API를 이용할 수 있다.

```html
 <script src="../fetch.js"></script>
```
-만약 호환이 되는 브라우저의 경우 해당 설정을 하여도 다운로드만되고 활성화 되지 않는다.
