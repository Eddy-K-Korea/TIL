# 기본 형태

```html
<p id='demo'></p>
<br>
<button type="button">click</button>

<script type="text/javascript">
$(function() {
	$("button").on("click",function(){  //버튼(이벤트)를 제외하고 ajax를 사용하면 페이지 열자마자 데이터를 불러온것을 출력할 수 있다
		$.ajax({
			/////////////////////////////////////////////////// send 데이터
			url:"data.jsp", // 값을 넘기거나 값을 가져올 경로
			type: "get", //servlet(doGet, doPost) 으로 간다.
		//	data: "t1=XYZ&t2=하하하",
			data: {t1:"XYZ", t2:"하하하"},  //위의 data와 둘다 가능
			///////////////////////////////////////////////////
			/////////////////////////////////////////////////// recv
			success:function(data,status,xhr){
			//	alert('success');
				$("#demo").html(data);
				alert(status);
				alert(xhr);
			},
			error:function(xhr, status, error){
				alert('error');
			},
			complete:function(xhr,status){	//통신 다 디되는지 확인 함수
			//	alert('통신종료');
			}
			///////////////////////////////////////////////////
		});
	});
});

</script>
```
- html에서는 원래 값을 보낼 수는 있어도 받을 수 없다. 하지만 Ajax를 통해 값을 불러들여 출력할 수 있다.
