<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

		<div class="row">
			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#">메뉴1</a></li>
					<li><a href="#">메뉴2</a></li>
					<li><a href="#">메뉴3</a></li>
					<li><a href="#">메뉴4</a></li>
				</ul>
			</div>
			<div class="col-md-10">
				<h1>회원가입 폼</h1>
				<hr>

				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">회원가입 폼</h3>
					</div>
					
					<form action="join.do" method="post">
					<div class="panel-body">
						
						<fieldset>
							<legend>기본정보 </legend>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">아이디</label>
								<div class="col-xs-7 col-lg-7">
									<input type="text" id="id" name="id" class="form-control" placeholder="아이디">
								</div>	
								<div class="col-xs-3 col-lg-3">
									<button type="button" id="btnOverappedId" class="btn btn-warning">아이디 중복 확인</button>
									<div id="msg">아이디 중복 확인</div>
								</div>
								
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">비밀번호</label>
								<div class="col-xs-10 col-lg-10">
									<input type="password" id="pw" name="pw" class="form-control" placeholder="비밀번호">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">이름</label>
								<div class="col-xs-10 col-lg-10">
									<input type=text id="name" name="name" class="form-control" placeholder="이름">
								</div>
							</div>
						</fieldset>
						
					</div>
					<div class="panel-footer">
						<button type="submit" id="btnJoin" class="btn btn-warning">가입</button>
						<button type="reset" class="btn btn-danger">취소</button>
					</div>
					</form>
				</div>
				<!-- /글내용 panel -->			
			</div>
		</div>
		
<script type="text/javascript">
$("#btnJoin").on('click',function(){
	var id=$("#id").val();
	if(id==""){
		alert("아이디를 입력하세");
		id.focus();
		return false;
	}
	return true;
	
});
	
}
$("#btnOverappedId").on('click',function(){
	$.ajax({
		type:"post",
		url:"overappedId.do",
		data:{"id":$("#id").val()},
		async:false,
		dataType:"text",
		success:function(data,textStatus){
			if(data=='usable'){
				$("#msg").html("사용가능한 아이디")
			}else if(data=='not usable'){
				$("#msg").html("사용불가능한 아이디")
			}
		},error:function(data,textStatus){
			alert("error")
		},complete:function(data,textStatus){
			
		}
	});
});

</script>

<%@ include file="../include/footer.jsp"%>









