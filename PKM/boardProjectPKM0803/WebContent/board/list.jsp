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
				<h1>글목록</h1>
				<hr>
				<c:if test="${not empty session_id }">
					<button type="button" class="btn btn-primary pull-right">글쓰기</button>
				</c:if>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>번 호</th>
							<th>제 목</th>
							<th>글쓴이</th>
							<th>조회수</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${list }" var="board">
						<tr>
							<td>${board.bno }</td>
							<td>
								<a href="read.do?bno=${board.bno}">${board.title }</a>
							</td>
							<td>${board.writer }</td>
							<td>${board.readcount }</td>
							<td><fmt:formatDate value="${board.writedate}"/></td>
						</tr>
					</c:forEach>
					</tbody>
				</table>

				<ul class="pagination">
					<li class="disabled"><a href="#">« 이전</a></li>
					<li class="active"><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#">다음 »</a></li>
				</ul>

			</div>
		</div>


<%@ include file="../include/footer.jsp" %>