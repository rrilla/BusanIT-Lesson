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
				<h1>글 상세보기</h1>
				<hr>

				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">글 상세보기</h3>
					</div>
					<div class="panel-body">

						<fieldset disabled>
							<legend>기본정보 </legend>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">글번호</label>
								<div class="col-xs-10 col-lg-10">
									<input type="text" class="form-control" value="${board.bno}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">글제목</label>
								<div class="col-xs-10 col-lg-10">
									<input type=text class="form-control" value="${board.title}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">글내용</label>
								<div class="col-xs-10 col-lg-10">
									<textarea rows="5" class="form-control">${board.content}</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">이미지</label>
								<div class="col-xs-10 col-lg-10">
									<img src="images/temp/${board.image_name }" width="320" height="240">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">조회수</label>
								<div class="col-xs-10 col-lg-10">
									<input type=text class="form-control" value="${board.readcount}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">작성일</label>
								<div class="col-xs-10 col-lg-10">
									<input type=text class="form-control" value="${board.writedate}">
								</div>
							</div>
							<div class="form-group">
								<label class="col-xs-2 col-lg-2 control-label">작성자</label>
								<div class="col-xs-10 col-lg-10">
									<input type=text class="form-control" value="${board.writer}">
								</div>
							</div>
						</fieldset>

					</div>
					<div class="panel-footer">
					<c:if test="${session_id==board.writer }">
						<button type="button" id="btnUpdateForm" class="btn btn-warning">수정</button>
						<button type="button" id="btnDelete" class="btn btn-danger">삭제</button>
					</c:if>
						<button type="button" id="btnList" class="btn btn-info">목록보기</button>
					</div>
				</div>
				<!-- /글내용 panel -->

				<div class="panel panel-default">
					<div class="panel-heading panel-comment">
						<span class="glyphicon glyphicon-comment"></span> 댓글 <span>15</span>
						<button id="btnAddComment" type="button" class="btn btn-primary btn-xs pull-right">댓글쓰기</button>
					</div>
					<div class="panel-body comment-body">

						<ul class="media-list">
							<li class="media"><a class="pull-left" href="#"><img
									src="../ch03/pic.jpg"></a>
								<div class="media-body">
									<div>
										<strong class="text-primary">user01</strong> <small
											class="pull-right text-muted">2020-07-31</small>
									</div>
									<p>반가워요~~! Cras sit amet nibh libero, in gravida nulla.
										Nulla vel metus scelerisque ante sollicitudin commodo. Cras
										purus odio, vestibulum in vulputate at, tempus viverra turpis.</p>
								</div></li>
							<li class="media" style="margin-left: 70px;"><a
								class="pull-left" href="#"><img src="../ch03/pic.jpg"></a>
								<div class="media-body">
									<div>
										<strong class="text-primary">user01</strong> <small
											class="pull-right text-muted">2020-07-31</small>
									</div>
									<p>반가워요~~! Cras sit amet nibh libero, in gravida nulla.
										Nulla vel metus scelerisque ante sollicitudin commodo. Cras
										purus odio, vestibulum in vulputate at, tempus viverra turpis.</p>
								</div></li>
							<li class="media" style="margin-left: 140px;"><a
								class="pull-left" href="#"><img src="../ch03/pic.jpg"></a>
								<div class="media-body">
									<div>
										<strong class="text-primary">user01</strong> <small
											class="pull-right text-muted">2020-07-31</small>
									</div>
									<p>반가워요~~! Cras sit amet nibh libero, in gravida nulla.
										Nulla vel metus scelerisque ante sollicitudin commodo. Cras
										purus odio, vestibulum in vulputate at, tempus viverra turpis.</p>
								</div></li>
							<li class="media"><a class="pull-left" href="#"><img
									src="../ch03/pic.jpg"></a>
								<div class="media-body">
									<div>
										<strong class="text-primary">user01</strong> <small
											class="pull-right text-muted">2020-07-31</small>
									</div>
									<p>반가워요~~! Cras sit amet nibh libero, in gravida nulla.
										Nulla vel metus scelerisque ante sollicitudin commodo. Cras
										purus odio, vestibulum in vulputate at, tempus viverra turpis.</p>
								</div></li>
						</ul>

					</div>
				</div>
				<!-- /댓글 panel -->

			</div>
		</div>

<script type="text/javascript">
$("#btnUpdateForm").on('click',function(){
	location.href="updateForm.do?bno=${board.bno}";
});
$("#btnDelete").on('click',function(){
	location.href="delete.do?bno=${board.bno}";
});
$("#btnList").on('click',function(){
	location.href="list.do";
});

</script>

<%@ include file="../include/footer.jsp"%>





