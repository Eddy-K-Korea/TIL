# 캘린더 플러그

### 1. 우선 캘린더 플러그를 다운 받는다
- https://fullcalendar.io/
- css와 js 파일을 사용을 위해 넣어준다<br>
![image](https://user-images.githubusercontent.com/65350890/92919882-41742f00-f46c-11ea-8fd7-f35ef8622a1b.png)

### 2. 라이브러리를 끌어와서 링크를 걸어준다
```html
<!-- 캘린더의 css 와 기능 사용을 위한 js의 링크를 걸어준다 -->
<link href="./lib/main.css" rel="stylesheet" />
<script src="./lib/main.js"></script>
```

### 3. 캘린더의 데이터를 넣는 방법과 캘린더를 생성해준다
```html
<script type="text/javascript">
document.addEventListener("DOMContentLoaded",function(){  //라이브러리에서 캘린더를 불러온다

	let calendarEl = document.getElementById("calendar");
	
		//캘린더를 생성해준다. 
	let calendar = new FullCalendar.Calendar(calendarEl, {
  //헤더에 월 을 바꿀 수 있는 툴 생성
		headerToolbar:{
			left:"prev,next today",
			center:"title",
			right: 'dayGridMonth,timeGridWeek,timeGridDay,listMonth'  //월, 주, 일 , 리스트를 볼 수 있는 머튼 생성
		},
		initialDate: '2020-09-11', // new Date() 도 가능함 (처음 실행시 기준이 되는 날짜)
		locale: 'ko',		//한글 설정
		navLinks: true,		//네비게이션 날짜를 가르키는 설정
		businessHours: true,

		events:[	//json 형태로 데이터를 넣어줄 수 있다.
			{
				title: "비즈니스 점심약속",
				start: new Date(),
				constraint:'김사장님'
				
				},
			{
				title: "비즈니스 점심약속",
				//start: new Date(2020,9,12,4,30,0),
				start:'2020-09-12T16:30:00',
				constraint:'김사장님'
				
				},
			{
				title: "미팅",
				start: '2020-09-15T12:34:00',
				constraint:'비즈니스 시간',
				color:'#ff0000'
				
				},
			{
				title:'운동',
				start:"2020-09-14T09:00:00",
				end:"2020-09-18T18:00:00"		//기간을 할때 end 필요 그렇지 않으면 반드시 start 필수

			},
			{
				title:'데이트',
				start:"2020-09-25T18:30:00",
				constraint:'영화관람',
				display:'background',
				color:'#0000ff'
			}

			]
		
		
	});

	calendar.render();

	//event(날짜를 클릭시 반응 아래 일정 추가  또는 이동을 해준다)
	calendar.on("dateClick",function(info){
		//alert("dataClick");
		alert(info.dateStr);

		//일정 추가
		//location.href=

	});
  //추가한 이벤트를 누르면 실행한다 그리고 그 값도 가져 올 수 있다.
	calendar.on("eventClick",function(info){
	//	alert("eventClick");
	//	alert(info.event.title);
		alert(info.event.constraint);
	});

	//일정 추가
	calendar.addEvent({
		'title':'추가 이벤트',
		'start':"2020-09-27 12:20:00",
		"constraint":'내용무'

	});
});
 </script>
 ```
 
 ### 4. 아래 div 에 id값을 주면 위에서 id속성값을 지정해준곳으로 캘린더가 그려진다
 ```html
  
 <!-- 직접 그려지는 부분 -->
 <div id ="calendar"></div>
 ```
 
