<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
<title>Insert title here</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<style type="text/css">
body,html{
	position: relative;
	width: 100%;
	height: 100%;
	margin: 0px;
}
.wrapper{
	position: relative;
	height: 100%;
	width: 100%;
}
.wrapper::after{
	position: fixed;
	width: 100%;
  	height: 100%;
  	content: "";
	background: url("https://github.com/byunginK/TIL/blob/master/Web/login/WebContent/images/sanbg.jpg?raw=true") no-repeat center;
	background-size: cover;
	top: 0;
  	left: 0;
  	z-index: -1;
  	opacity: 0.5;
  	-webkit-background-size: cover;	  
	-moz-background-size:cover; 
	-o-background-size:cover;
  	
}
.banner{
	position: absolute;
	width: 100%;
	text-align: center;
	margin-top:-130px;
	font-size: 20px;
}
.contents{
	position: relative;
	width: 400px;
	height: 860px; 
	left: 50%;
	margin-left: -212px;
	margin-top: 200px;
	background-color: white;
	padding: 20px;
	border-radius: 40px;
	box-shadow: 1.5px 1.5px 1.5px 1.5px #959C9B;
}
.birth{
	display: inline-block;
	width: 400px;
	height: 50px;
	
	
} 
.bir_yy{
	position: absolute;
	
}
.bir_mm{
	position: absolute;
	left:140px;
}
.bir_dd{
	position: absolute;
	left:210px;
}
.gender{
	/* position: absolute; */
	
}
.checkEmail{
	/* position: absolute; */
}
.chcekPhone{
	/* position: absolute; */
}


.btn{
	position: relative;
	width:200px;
	margin-top: 30px;
	margin-left: 50%;
	left: -100px;
	
}
.signUpBtn{
	position:absolute;
	width: 200px;
	height: 50px;
	background-color: #19aa8d;
	color: white;
	font-size: 16px;
	font-weight: bold;
	border-radius: 6px;
	border:none;
	cursor: pointer;
}
.footer{
	position: relative; 
	width: 100%;
	height:8%;
	text-align: center;
	/* margin: auto; */
	bottom: 0;
	padding: auto;
	top: 10%;
	background: linear-gradient(to bottom,#BCC8C2,#828A86);
	opacity: 0.7;
	font-size: 17px;
}
.fo{
	position:absolute;
	width:300px;
	padding-left: 50%;
	left: -150px;
}
._id input{
	width: 290px;
	height: 30px;
	font-size: 18px;
}
._pw input {
	width: 290px;
	height: 30px;
	font-size: 18px;
}
._rpw input {
	width: 290px;
	height: 30px;
	font-size: 18px;
}
._name input {
	width:  290px;
	height: 30px;
	font-size: 18px;
}
.bir_yy input {
	width: 100px;
	height: 30px;
	font-size: 17px;
}
.sel {
	width: 60px;
	height: 36px;
	font-size: 17px;
}
.bir_dd input {
	width: 100px;
	height: 30px;
	font-size: 17px;
}
.checkEmail input {
	width: 290px;
	height: 30px;
	font-size: 18px;
}
.checkPhone input {
	width: 290px;
	height: 30px;
	font-size: 18px;
}
.check_btn{
	width: 70px;
	height: 30px;
	background-color: #19aa8d;
	color: white;
	font-size: 11px;
	font-weight: bold;
	border-radius: 6px;
	border:none;
	cursor: pointer;
}

</style>
</head>
<body onload="$('.contents').fadeIn(2000); $('.footer').show(2000);">
<div class="banner">
		<h1>회원가입</h1>
</div>
<div class="wrapper">
	<div class="contents">
		<form id="frm" method="get">
		<input type="hidden" name=work value="am">
			<div class="_id">
				<h3>아이디</h3>
				<input type="text" id="id" name="id" maxlength="20">
				<button type="button"  class ="check_btn" id="check_id" >중복 확인</button>
				<p id="checkedId" style="height: 10px"></p>
			</div>		
			<div class="_pw">
				<h3>비밀번호</h3>
				<input type="password" name="pwd" maxlength="20">
			</div>
			<div class="_rpw">
				<h3>비밀번호 재확인</h3>
				<input type="password" name="rpw" maxlength="20">
				<p id="checkedId" style="height: 10px;color: red"></p>
			</div>
			<div class="_name">
				<h3>이름</h3>
				<input type="text" name="name" maxlength="20">
			</div>
			<h3>생년월일</h3>
			<div class="birth">
				<div class="bir_yy">
				<input type="text" id="yy" name="yy" placeholder="년(4자)" class="int" maxlength="4">
				</div>
				<div class="bir_mm">
					<select id="mm" class="sel" aria-label="월" name="mm">
						<option value="" selected="selected">월</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
				</div>
				<div class="bir_dd">
					<input type="text" id="dd" name="dd" placeholder="일"  class="int" maxlength="2" size="7">
				</div>
			</div>
			
			<div class="gender">
				<h3>성별</h3>
				<select id="_sex" class="sel" name="sex">
					<option value="" selected="selected">성별</option>
					<option value="M">남자</option>
					<option value="F">여자</option>
				</select>
			</div>
			<div class="checkEmail">
				<h3>본인 확인 이메일(선택)</h3>
				<input type="text" name="email" placeholder="선택입력" maxlength="100">
			</div>
			<div class="checkPhone">
				<h3>휴대전화</h3>
				<input type="tel"name="phoneNo" placeholder="전화번호 '-'없이 입력" maxlength="16">
			</div>
			</form>
	</div>
	<div class='btn'>
				<button type="button" id="suBtn"  class="signUpBtn">가입하기</button>
	</div>
	
	<div class="footer">
		<h4 class="fo">가입하면 이제부터 함께</h4>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	$("#suBtn").mouseover(function() {
		$(this).css("background", '#179b81');
	});
	$('#suBtn').mouseout(function() {
		$(this).css("background", '#19aa8d');
	});
	$('#suBtn').mousedown(function() {
		$(this).css("outline", '3px solid #22C2DB');
	});
	$('#suBtn').mouseup(function() {
		$(this).css('outline','none');
	});
	$('.contents').hide();
	
	
	
	$("#check_id").click(function() {
		if($("#id").val().trim()==""){
			$("#checkedId").css("color", "#ff0000");
			$("#checkedId").html("아이디를 입력하세요");
		}else{
			$.ajax({
				url:"memberLogin",
				type:"get",
				datatype : "json",
				data:"id="+$("#id").val().trim()+"&work=checkId",
				success: function(data) {
					//alert('success');
					//alert(data);
					
					var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
					var t = data.replace(regExp, "");
					//alert(t);
					if(t == "YES"){
						//alert("check: YES");
						$("#checkedId").css("color", "#0000ff");
						$("#checkedId").html('사용할 수 있는 id입니다');
					}else{
						//alert("check: NO");
						$("#checkedId").css("color", "#ff0000");
						$("#checkedId").html('사용 중인 id입니다');
						$("#id").val("");
					} 	
				},
				error: function() {
					alert('error');
				}
			});
		}	
	});
	
	$("#suBtn").click(function() {
		/* $("#frm").attr("action","memberLogin").submit();*/
		$.ajax({
			url:"memberLogin",
			type:"get",
			datatype : "json",
			data:$("#frm").serialize(),
			success: function(data) {
			//	alert(data);
				var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
				var t = data.replace(regExp, "");
				if(t=="add"){
					alert("회원가입 되었습니다.");
					location.href = "login.jsp";
				}else{
					alert("회원 가입 실패");
				}
			},
			error: function() {
				alert("error");
			}
		});
	});
	
});	

</script>
</body>
</html>