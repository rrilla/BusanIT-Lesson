<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form id="frm" >
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="5" cols="30" name="content"></textarea></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td colspan=2 align="center">
					<input type="submit" value="글저장">
					<input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>
	</form>
	
	
	
	
	
	
<script src="../js/jquery-3.5.1.min.js"></script>  <!-- 메서드 더보기 https://api.jquery.com/category/ajax/ -->
<script>
	$('#frm').submit(function (event) {
		//event.preventDefault(); // from 객체의 기본동작인 action 요청을 막음
		
		$.ajax({
			url: 'boardApi',
			method: 'post'
		});
		
		return false;
	});

</script>
</body>
</html>