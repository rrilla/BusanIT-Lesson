<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-theme.css" rel="stylesheet">
<link href="css/custom2.css" rel="stylesheet">
<style>
header {
	height: 100px;
	background-color: rgba(125, 211, 242, 0.5);
	border-radius: 15px;
	padding: 10px;
	margin: 10px 0;
	font-family: 'Nanum Gothic', sans-serif;
	text-align: center;
}

footer {
	font-family: 'Nanum Gothic', sans-serif;
	text-align: center;
}
</style>
<script src="js/jquery-3.5.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<body>

	<div class="container">
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"> <img src="images/logo.png"
					width="200" height="40">
				</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="list.html">게시판</a></li>
					<c:choose>
						<c:when test="${empty session_id }">
							<li><a href="#" id="login_a">로그인</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#" id="logout_a">로그아웃(${session_id })</a></li>
						</c:otherwise>	
					</c:choose>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">드롭다운 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#">서브메뉴 1</a></li>
							<li><a href="#">서브메뉴 2</a></li>
							<li><a href="#">서브메뉴 3</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="검색">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>

			</div>
		</nav>
		<header>
			<h2>회원전용 게시판</h2>
		</header>
		
<!-- Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Login</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label>아이디</label> 
						<input type="text" id="mo_id" class="form-control" placeholder="id">
					</div>
					<div class="form-group">
						<label>비밀번호</label> 
						<input type="text" id="mo_pw" class="form-control" placeholder="pw">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="btnLogin" class="btn btn-warning">로그인</button>
					<button type="button" id="btnCancel" class="btn btn-danger">취소</button>
					<button type="button" id="btnJoin" class="btn btn-primary">등록</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
			<!-- 모달 콘텐츠 -->
		</div>
		<!-- 모달 다이얼로그 -->
	</div>
	<!-- 모달 전체 윈도우 -->	
	
<script type="text/javascript">
var modal=$("#loginModal");

$('#login_a').on('click', function(event) {
	//$(this).next().fadeToggle();
	event.stopPropagation(); // 부모요소에 동일한 클릭이벤트 전파를 막기
	modal.modal('show'); // 모달 대화상자 띄우기
});

$('#logout_a').on('click', function (event) {
	event.stopPropagation(); // 부모요소에 동일한 클릭이벤트 전파를 막기
	//location.href="logout.do"
	//$('#myModal').modal('show'); // 모달 대화상자 띄우기
	//return false;
	$.ajax({
		type:"get",
		url:"logout.do",
		async:false,
		dataType:"text",
		success:function(data,textStatus){
			if(data=="success"){
				alert("로그 아웃");
				location.href="list.do";
			} 
		},error:function(data,textStatus){
			alert("error")
		},complete:function(data,textStatus){}
	});
});



$("#btnLogin").on('click',function(){
	var query={"id":$("#mo_id").val(), "pw":$("#mo_pw").val()};
	$.ajax({
		type:"post",
		url:"login.do",
		async:false,
		data:query,
		dataType:"text",
		success:function(data,textStatus){
			if(data=="success"){
				alert("로그인 성공");
				modal.modal("hide")
				location.href="list.do";
			}else if(data=="password error"){
				alert("패스워드가 틀림")
			}else{
				alert("아이디가 없음")
			}
		},error:function(data,textStatus){
			alert("error")
		},complete:function(data,textStatus){}
	});
});
</script>











		