<%@page import="dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");//
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usebean 사용 </title>
</head>
<body>
<jsp:useBean id="vo" class="vo.MemberVO"/>  <!-- MemberVO vo = new MemberVO(); -->
<jsp:setProperty property="*" name="vo"/>	<!-- vo를 가진bean을 다 set한다 -->

<%
	MemberDao.getInstance().insert(vo);
	response.sendRedirect("list.jsp");
%>
</body>
</html>