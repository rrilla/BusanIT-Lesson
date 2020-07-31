<%@page import="vo.MemberVO"%>
<%@page import="dao.MemberDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
	List<MemberVO> list = MemberDao.getInstance().selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="joinForm.jsp">회원등록 폼</a>
	<table>
		<tr>
			<th>mno</th>
			<th>id</th>
			<th>name</th>
			<th>gender</th>
			<th>job</th>
		</tr>
		<%
			for(int i=0; i<list.size(); i++){
				MemberVO vo=list.get(i);
		%>
		<tr>
			<td><%=vo.getMno() %></td>
			<td><%=vo.getId() %></td>
			<td><%=vo.getName() %></td>
			<td><%=vo.getGender() %></td>
			<td><%=vo.getJob() %></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>