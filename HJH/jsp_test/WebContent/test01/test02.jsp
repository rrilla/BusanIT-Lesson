<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
out.print("aaa<br>");
request.setAttribute("name", "홍길동");//속성정보 실어줌 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 내장객체</title>
</head>
<body>
<h2>Request Test</h2>
<form action="../test02" method="post">	<!-- 폼의 데이터는 파라메타값으로 전송 -->
나이 : <input type="text" name="age"/><br>
성별 :
	<input type="radio" name="gender" value="M">남자
 	<input type="radio" name="gender" value="F">여자<br>	<!-- radio버튼=같은 이름이면 하나의 값만 가져감 -->
취미 :
	<input type="checkbox" name="hobby" value="게임" />게임
	<input type="checkbox" name="hobby" value="수영" />수영
	<input type="checkbox" name="hobby" value="잠자기" />잠자기
	<input type="checkbox" name="hobby" value="공부" />공부
	<input type="submit" value="전송" />
</form>
</body>
</html>