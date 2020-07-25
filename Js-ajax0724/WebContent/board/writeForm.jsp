<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	background-color: lightgray;
}

div.container {
	width: 900px;
	margin: 0 auto;
	background-color: white;
}

div.list-container table {
	width: 800px;
}
div.list-container table th {
/* 	width: 200px; */
}

div.input-container {
	margin-top: 60px;
}

div.controls {
	margin-bottom: 50px;
}
div.container {
	padding: 30px;
}
</style>
</head>
<body>
<div class="container">

	<div class="list-container">
		
		<div class="controls">
			<button type="button" id="btnList">글목록 갱신</button>
			<button type="button" id="btnDelete">글 삭제</button>
		</div>
	
		<form id="frmList">
		<table id="tblList" border="1">
			<thead>
				<tr>
					<th>
						<input type="checkbox" id="allCheck">
						삭제
					</th>
					<th>수정</th>
					<th>글번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		</form>
	</div>

	<div class="input-container">
		<form id="frm">
			<!-- 글수정을 위한 글번호 배치할 용도 -->
			<input type="hidden" name="bno" id="bno">
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" id="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="30" name="content" id="content"></textarea></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" id="writer"></td>
				</tr>
				<tr>
					<td colspan=2 align="center">
						<input type="submit" value="글저장">
						<input type="button" value="글수정" id="btnModify">
						<input type="reset" value="다시쓰기">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>

<script src="../js/jquery-3.5.1.min.js"></script>
<script>
	$('#btnModify').on('click', function () {
		
		var str = $('#frm').serialize();
		console.log(str);
		
		$.ajax({
			url: '../api/board',
			method: 'PUT',
			data: str,
			success: function (result) {
				alert(result);
				
				getList();
			},
			error: function (e) {
				alert('에러 발생!');
			}
		});
		
		$(this).reset();
		
	});


	// 전체선택용 체크박스에 클릭 이벤트 연결
	$('#allCheck').on('click', function () {
		// 전체선택 체크박스가 체크된 상태일경우
		if ($(this).prop('checked')) {
			
			// 모든 삭제선택 체크박스를 체크해줌
			$('input:checkbox[name="bno"]').prop('checked', true);
			
		} else {// 전체선택 체크박스가 체크 해제된 상태일경우
			
			$('input:checkbox[name="bno"]').prop('checked', false);
		}
	});


	// 정적 이벤트 연결
	$('#btnDelete').on('click', function () {
		var isRemove = confirm('정말 삭제하시겠습니까?');
		
		if (!isRemove) {
			return;
		}
		
		var str = $('#frmList').serialize();
		console.log(str);
		
		$.ajax({
			url: '../api/board',
			method: 'DELETE',
			data: str,
			success: function (result) {
				alert(result);
				
				getList();
			},
			error: function () {
				alert('삭제 에러 발생!')
			}
		});
		
	});

	// 라디오버튼에 동적 이벤트 연결
	$('#tblList').on('click', 'input:radio[name="modify"]', function () {
		
		var bno = $(this).closest('tr').find('td:nth-child(3)').html();
		var writer = $(this).closest('tr').find('td:nth-child(4)').html();
		var content = $(this).closest('tr').find('td:nth-child(2) > input').data('content');
		var title = $(this).closest('tr').find('td:nth-child(5) a').html();
		
		$('#bno').val(bno);
		$('#writer').val(writer);
		$('#content').val(content);
		$('#title').val(title);
	});
	

	// a 태그에 동적 이벤트 연결
	$('#tblList').on('click', 'a', function () {
		// a 태그의 data 속성 bno 값 가져오기
		var bno = $(this).data('bno');
		console.log('bno : ' + bno);
		
		$.ajax({
			url: '../api/board',
			method: 'GET',
			data: 'bno=' + bno,
			success: function (result) {
				console.log(result);
				alert(result.content);
			}
		});
		
	});


	function getList() {
		$.ajax({
			url: '../api/board',
			method: 'GET',
			success: function (result) {
				console.log(result);
				
				processList(result);
			}
		});
	}

	function processList(list) {
		var str = '';
		
		for (let board of list) {
			str += '<tr>';
			str += '<td><input type="checkbox" name="bno" value="' + board.bno + '"></td>';
			str += '<td><input type="radio" name="modify" data-content="' + board.content + '"></td>';
			str += '<td>' + board.bno + '</td>';
			str += '<td>' + board.writer + '</td>';
			str += '<td><a href="#" data-bno="' + board.bno + '">' + board.title + '</a></td>';
			str += '<td>' + board.write_date + '</td>';
			str += '</tr>';
		}
		
		$('#tblList > tbody').html(str);
	} // processList()

	$('#btnList').click(function () {
		getList();
	});


	$('#frm').submit(function (event) {
		//event.preventDefault(); // form객체의 기본동작인 action 요청을 막음
		
		//var title = $('input[name="title"]').val();
		
		var str = $(this).serialize();
		console.log(str);
		
		$.ajax({
			url: '../api/board',
			method: 'POST',
			data: str,
			success: function (result) {
				alert(result);
				
				getList();
			},
			error: function (e) {
				alert('에러 발생!');
			}
		});
		
		$(this).reset();
		
		return false;
	});
</script>
<script>
	getList();
</script>
</body>
</html>




