# CheckBox

- JS와 비슷한 기능
- 버튼을 누르면 값을 얻어오고 셋팅하는 소스코드


- 일반적으로 체크박스는 name으로 잡아서 값을 넘겨준다.

```html
<input type="checkbox" id="che1" name="ch">그림 그리기	<!-- name은 같은 것으로 잡고 값을 넘겨주고 id는 다른것을 사용 -->
<input type="checkbox" id="che2" name="ch">음악 듣기
<br>
<button type="button" id="chBtn">체크</button>

<script type="text/javascript">
$('#chBtn').click(function() {
	//getter 2가지 방법
	let check = $('#che1').is(":checked"); // 그림 그리기 체크의 체크상태를 true/false 상태로 값을 check 변수에 넘겨준다.
	alert("check:"+check);

  let check = $("input:checkbox[id='che1']").is(":checked"); 
	alert("check:"+check); 
	
	//setter 
	$("#che1").prop("checked", true);   /* 체크 버튼을 누르면 che1 이 체크가 된다 */
});
</script>
```

# Radio 체크

- 버튼을 누르면 값을 받아오고 하는 코드


- 보통 리스트에 담아서 사용 (앞에 리스트 목록 표시를 없애주는 함수도 있으니 검색)
- name으로 묶어줘야 동시다발적이 체크를 막아 줄 수 있다.

```html
<ul>
	<li><input type="radio" name="radio_test" value="사과" checked>사과</li>  // 처음 체크 상태 설정(checked)
	<li><input type="radio" name="radio_test" value="배">배</li>
	<li><input type="radio" name="radio_test" value="바나나">바나나</li>
</ul>
<button type="button" id="choice">선택</button>

<script type="text/javascript">
$(function() {
	$('#choice').click(function() {
		//getter
		let radioVal = $("input[name='radio_test']:checked").val(); /* 라디오 버튼의 체크된 값을 가져오기 */
		alert(radioVal); 
		
		//setter
		$('input[name="radio_test"]').val(["배"]); //버튼을 누르면 '배'의 체크로 이동
	});
});
</script>
```


