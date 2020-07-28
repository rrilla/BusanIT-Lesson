<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>로그인 결과</h2>
<%=session.getAttribute("id") %>님
환영합니다.!!!!!<br>

<a href="result2.jsp">다음 페이지</a>
</body>
</html>