<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="msg" value="${param.msg }"/>
<c:if test="${not empty msg }">
<script type="text/javascript">
	alert(${msg});
</script>
</c:if>
	<form action="join.do" method="post">
		<table>
			<tr><th>아디 - </th><td><input type="text" name="id"></td></tr>
			<tr><th>비번 - </th><td><input type="password" name="pw"></td></tr>
			<tr><th>이름 - </th><td><input type="text" name="name"></td></tr>
			<tr><th>성별 - </th>
				<td>
					<input type="radio" name="gender" value="M"/>남자
					<input type="radio" name="gender" value="F" />여자<!-- 같은 name을 주면 하나의 그룹이됨 -->
				</td>
			</tr>
			<tr><th>직업 - </th>
				<td>
					<select name="job">
						<option value="학생">학생</option>	
						<option value="회사원">회사원</option>	
						<option value="공무원">공무원</option>	
						<option value="교사">교사</option>	
					</select>
				</td>
			</tr>
			<tr><th>인사말</th>
					<td>
						<textarea cols="30" rows="5" name="intro"></textarea>
					</td>
				</tr>
			<tr><td colspan=2 align="center">
					<input type="submit" value="가입할래" />
					<input type="reset" value="다시쓸래" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>