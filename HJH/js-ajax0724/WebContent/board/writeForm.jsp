<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div.container {
		width: 900px;
		margin: 0  auto;    /* 1은 위아래, 2는 왼쪽오른쪽 */
		background-color: white;
	}
	body {
		background-color: lightgray;
	}
	div.list-container table {
		width: 800px;
	}
	div.list-container table th {
		/* width: 200px; */
	}
	div.input-container {
		margin-top: 60px;
	}
	
	div.controls{
		margin-bottom: 50px;
	}
	div.container {
		padding: 80px;
	}
</style>
</head>
<body>
<div class="container">

	<div class="list-container">
	
		<div class="controls">
			<button type="button" id="btnList">글목록 갱신</button>	<!-- 보통 소문자이나,id는 낙타표기법 -->
			<button type="button" id="btnDelete">글 삭제</button>
		</div>
	
		<form id="frmList">
		<table id="tblList" border="1">
			<thead>
				<tr>
					<th>
						<input type="checkbox" id="allCheck" >
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
		<form id="frm" >
			<!-- 글 수정 위해 글번호 배치할 용도. -->
			<input type="hidden" name="bno" id="bno">
			<table>
				<tr>
					<th>제목</th>
					<td><input type="text" name="title" id="title" ></td>
					<!-- required 무조건 입력하게,미입력시 말풍선 (브라우저에서 검증)-->
					
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="30" name="content" id="content" ></textarea></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" id=writer></td>
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
	
<script src="../js/jquery-3.5.1.min.js"></script>  <!-- 메서드 더보기 https://api.jquery.com/category/ajax/ -->
<script>
	$('#btnModify').on('click', function () {
		
		var str = $('#frm').serialize();	//자동으로 쿼리스트링(name=입력값)으로 전송
		console.log(str);
		 
		$.ajax({
			url: '../api/board',
			method: 'PUT',
			data: str,
			success: function(result) {	//result라는 변수로 응답값을 받음
				alert(result);
			
				getList()
			},
			error: function (e) {
				alert('에러에러에러에러에러에ㅓㄹ에ㅓ레어에ㅓㄹ에ㅓ레어레어레어레어레얼에러');
			}
				
			//data: 'title=sdf&content=sdf&writer=sdf'	//serialize()가 이걸 전송해줌
		}); 
		
		$(this).reset();
		
	});


	//전체 선택용 체크박스에 클릭 이벤트 연결
	$('#allCheck').on('click', function () {
		// 전체선택 체크박스가 체크된 상태일 경우
		if ($(this).prop('checked')) {
			
			//모든 삭제선택 체크박스를 체크해줌
			$('input:checkbox[name="bno"]').prop('checked', true);
		}else {//전체선택 체크박스가 체크 해제된 상태일 경우
			$('input:checkbox[name="bno"]').prop('checked', false);
		}
		
	});


	// 정적 이벤트 연결
	$('#btnDelete').on('click', function () {
		var isRemove = confirm('진짜 지움?');
		
		if (!isRemove) {
			return;
		}
		
		var str = $('#frmList').serialize();
		console.log(str);
		
		$.ajax({
			url: '../api/board',
			method: 'DELETE',
			data: str,
			success: function (result){
				alert(result);
				getList();
			},
			error: function () {
				alert('에러에ㅓ레어레어레어레ㅓㅇ레ㅓㅇ레ㅓ에러에러에ㅓ레어레어레ㅓ에러에러ㅔ어레어레어렝')
			}
		});
	});
	
	$('#tblList').on('click', 'input:radio[name="modify"]',function () {
		//closest()가장 가까운 부모찾아감
		var bno = $(this).closest('tr').find('td:nth-child(3)').html();
		var writer = $(this).closest('tr').find('td:nth-child(4)').html();
		var content = $(this).closest('tr').find('td:nth-child(2) > input').data('content');
		var title = $(this).closest('tr').find('td:nth-child(5)  a').html();
		
		$('#bno').val(bno);
		$('#writer').val(writer);
		$('#content').val(content);
		$('#title').val(title);
	});


	//a 태그에 동적 이벤트 연결
	$('#tblList').on('click', 'a',function () {
		//a태그의 data 속성 bno값 가져오기
		var bno = $(this).data('bno');
		console.log('bno : '+bno);
		
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
			success: function(result){
				console.log(result);
				
				processList(result);
			}
		});
	};

	function processList(list) {
		var str = '';
		
		for (let board of list) { /*  <a href="#" data-bno=></a>  ,   data-bno??*/
			str += '<tr>';
			str += '<td><input type="checkbox" name="bno" value="' + board.bno + '"></td>' + board.bno + '</td>';
			str += '<td><input type="radio" name="modify" data-content="' + board.content + '" ></td>';
			str += '<td>' + board.bno + '</td>';
			str += '<td>' + board.writer + '</td>';
			str += '<td><a href="#" data-bno="' + board.bno + '">' + board.title + '</a></td>';
			str += '<td>' + board.write_date + '</td>';
			str += '</tr>';
		}
		
		$('#tblList > tbody').html(str);
	}	// processList()

	$('#btnList').click(function () {	//click했을 때 일어날 함수 정의
		getList();
	});


	$('#frm').submit(function (event) {
		//event.preventDefault(); // from 객체의 기본동작인 action 요청을 막음
		
		//var title = $('input[name="title"]').val();
		
		var str = $(this).serialize();	//자동으로 쿼리스트링(name=입력값)으로 전송
		console.log(str);
		 
		$.ajax({
			url: '../api/board',
			method: 'POST',
			data: str,
			success: function(result) {	//result라는 변수로 응답값을 받음
				alert(result);
			
				getList()
			},
			error: function (e) {
				alert('에러에러에러에러에러에ㅓㄹ에ㅓ레어에ㅓㄹ에ㅓ레어레어레어레어레얼에러');
			}
				
			//data: 'title=sdf&content=sdf&writer=sdf'	//serialize()가 이걸 전송해줌
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