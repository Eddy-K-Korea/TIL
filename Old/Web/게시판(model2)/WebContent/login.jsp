<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script src="http://lab.alexcican.com/set_cookies/cookie.js" type="text/javascript" ></script>
<style type="text/css">
body,html {
	position: relative;
	height: 100%;
	width: 100%;
	margin: 0px; 
}
.wrapper{
	position: relative;
	height: 100%;
	width: 100%;
}
.wrapper::after{
	position: absolute;
	width: 100%;
  	height: 100%;
  	content: "";
	background: url("https://github.com/byunginK/TIL/blob/master/Web/login/WebContent/images/sanbg.jpg?raw=true") no-repeat center;
	background-size: cover;
	top: 0;
  	left: 0;
  	z-index: -1;
  	opacity: 0.5;
}
.login_content{
 	position:absolute;
	top:50%; left:50%;
    transform: translate(-50%, -50%);
	width:400px;
	height:450px;
	text-align: center;
	border-radius: 40px;
	box-shadow: 2px 2px 2px 2px gray;
	background-color: white;
}
.title{
	text-align: center;
	margin-top: 20px;
}
.log_info{
	min-height: 35px;
	box-shadow: none !important;
	border-width: 0 0 1px 0;
	border-radius: 0;
	font-size: 20px;
}
.login_input{
	position: relative;
	width: 100%;
	height: 56%; 
	margin: 0;
}
._id{
	position: absolute;
	margin-top: 30px;
	margin-left: 20%;
	
}
._saveId{
	position: absolute;
	margin-left: 20%;
	margin-top: 70px;
	
}
._pw{
	position: absolute;
	margin-top: 110px;
	margin-left: 20%;
}
.btnclass{
	position: absolute;
	color: white;
	font-size: 20px;
	font-weight: bold;
	background: #19aa8d;
	border-radius: 3px;
	border: none;
	width: 230px; 
	height:50px;
	text-align: center;
	margin-left:-115px;
	margin-top: 165px;
}
.pw_hint{
	position: absolute;
	bottom: 15%;
	border: 1px solid;
	width: 100%;
	height: 80px;
	border-left: none;
	border-right: none;
	border-top: none;
	border-color: #dee4e7;
}
.create_id{
	position: absolute;
	width: 100%;
	bottom: 7%;
}
a{
	text-decoration: none;
}

</style>

</head>

<body onload="$('.login_content').fadeIn(2000);">

<div class="wrapper">
	<div class="login_content">
			<form id="frm" method="post">
			<h1 class="title">산과 함께 Login </h1>
			<div class="login_input">
				<div class="_id"><i class="fa fa-user"></i><input type="text" class="log_info" id="id" name="id" placeholder="Uername" required="required"></div>
				<div class="_saveId"><input type="checkbox" value="" id="chk_save_id">Save id</div>
				<div class="_pw"><i class="fa fa-lock"></i><input type="Password" class="log_info" id="pwd" name="pwd" placeholder="Password" required="required"></div>
				<button type="button" id="_btnLogin" class="btnclass">로그인</button>
			</div>
				<div class="pw_hint">
					<p class="hint-text"><a href="#">Forgot Password?</a></p>
				</div>
			<div class="create_id">지금 부터 우리와 함께하기 <a href="memberLogin?work=signup"><i>회원가입</i></a></div>
			</form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function() {
	$("#_btnLogin").mouseover(function() {
		$(this).css("background", '#179b81');
	});
	$('#_btnLogin').mouseout(function() {
		$(this).css("background", '#19aa8d');
	});
	$('.login_content').hide();

	$("#_btnLogin").click(function() {
		if($("#id").val().trim() == ""){
			alert("id를 입력해 주십시오");
			$("#id").focus();
		}
		else if($("#pwd").val().trim() == ""){
			alert('password를 입력해 주십시오.');
			$("#pwd").focus();
		}
		else{
		//	$("#frm").attr('action',"memberLogin").submit();
			$.ajax({
				url:"memberLogin",
				type:"post",
				datatype : "json",
				data:$("#frm").serialize(),
				success: function(data) {
				//	alert(data);
					var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;
					var t = data.replace(regExp, "");
					if(t=="fail"){
						alert("로그인 실패");
						
					}else{
						alert('환영합니다');
						location.href="conBbs?work=movblist";
					}
				},
				error: function() {
					alert("error");
				}
			});
		}
	});
	
	// cookie: String 	-> id
	// session: Object	-> login 개인정보
	let user_id = $.cookie("user_id");	// cookie를 산출
	if(user_id != null){	// 저장된 쿠키가 있을때
		//alert("cookie 있음");
		$("#id").val(user_id);
		$("#chk_save_id").attr("checked","checked");
	}
	
	$('#chk_save_id').click(function() {
		if($("#chk_save_id").is(":checked")){	//체크 되었을때
			// 쿠키저장
			if($("#id").val().trim() == ""){
				alert("id를 입력해 주십시오");
				$("#chk_save_id").prop("checked",false);
			}else{
				$.cookie("user_id",$("#id").val().trim(),{expires:7,path:'/'})
			}
		}
		else{
			// 쿠키삭제
			$.removeCookie('user_id',{path:'/'});
		}
		
	}); 
});


</script>


</body>
</html>