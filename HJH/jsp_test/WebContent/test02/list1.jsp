<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="../join.do">회원등록 폼</a>
	<table>
		<tr>
			<th>mno</th>
			<th>id</th>
			<th>name</th>
			<th>gender</th>
			<th>job</th>
		</tr>
		<c:forEach items="${list }" var="vo">
			<tr>
				<td>${vo.mno }</td>
				<td>${vo.id }</td>
				<td>${vo.name }</td>
				<td>${vo.gender }</td>
				<td>${vo.job }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>