# Slide

### UI중 하나

- 이미지를 슬라이더를 통해 투명도를 주는 함수를 구현

```html
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

<h1>Slider Fade</h1>

<img alt="" src="./images/p1.jpg">
<img alt="" src="./images/p2.jpg">
<img alt="" src="./images/p3.jpg">

<p>slider 키를 좌우로 움직이면, 이미지의 투명도를 조절할 수 있습니다.</p>

<div id="slider1" style="width: 250px"></div>

<p id="opacity"></p>  //슬라이더의 정도 표기

<script type="text/javascript">
$(function() {
	$("#slider1").slider({
		animate: true,  
		range: "min",
		value: 100,
		slide: function(event, ui){ //함수를 통해 이벤트를 줄 수 있다.
			
			$('img').css("opacity",ui.value/100);
			
			$("#opacity").text(ui.value);
		}
	})
	$("img").show("drop", options, 500, callback );
});

</script>

</body>
```

