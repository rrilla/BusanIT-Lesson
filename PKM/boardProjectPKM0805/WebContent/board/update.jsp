<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
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
				<h1>글수정 폼</h1>
				<hr>

				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">글수정 폼</h3>
					</div>
					<form action="update.do" method="post" enctype="multipart/form-data">
					<input type="hidden" name="bno" value="${board.bno}">
					<input type="hidden" name="pageNum" value="${param.pageNum}">
					<input type="hidden" name="ex_filename" value="${board.image_name}">
					<div class="panel-body">

						<fieldset>
							<legend>기본정보 </legend>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">글제목</label>
								<div class="col-xs-10 col-lg-10">
									<input type=text id="title" name="title" class="form-control" value="${board.title }">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">글내용</label>
								<div class="col-xs-10 col-lg-10">
									<textarea rows="5" id="content" name="content" class="form-control">${board.content}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">작성자</label>
								<div class="col-xs-10 col-lg-10">
									<input type=text id="writer" name="writer" class="form-control" value="${board.writer }" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">파일첨부</label>
								<div class="col-xs-10 col-lg-10">
									<input type=file id="filename" name="filename" class="form-control">
								</div>
							</div>
						</fieldset>

					</div>
					<div class="panel-footer">
						<button type="submit" id="btnUpdate" class="btn btn-warning">글수정</button>
						<button type="reset" class="btn btn-danger">취소</button>
						<button type="button" id="btnList" class="btn btn-info">목록보기</button>
					</div>
					</form>
				</div>
				<!-- /글내용 panel -->

			</div>
		</div>
<script type="text/javascript">
$("#btnUpdate").on('click',function(){
	var title=$("#title").val();
	if(title==""){
		alert("title을 입력하세요");
		$("#title").focus();
		return false;
	}
	return true;
});

$("#btnList").on('click',function(){
	location.href="list.do";
});
</script>

<%@ include file="../include/header.jsp" %>










