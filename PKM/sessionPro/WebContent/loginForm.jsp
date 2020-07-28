<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");
	String msg=request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function check(){
	var id=document.frmLogin.id.value;
	var pw=document.frmLogin.pw.value;
	if(id=="" || id.length==0){
		alert("아이디를 입력하세요");
		document.frmLogin.id.focus();
		return false;
	}
	if(pw=="" || pw.length==0){
		alert("비밀번호를 입력하세요");
		document.frmLogin.pw.focus();
		return false;
	}
	document.frmLogin.submit();
}
</script>
<body>
<h2>로그인 폼</h2>
<%
	if(msg!=null){
%>
<p><%=msg %>
<%
	}
%>
<form name="frmLogin" action="login" method="post">
<table>
<tr><th>아이디</th><td><input type="text" name="id"></td></tr>
<tr><th>비밀번호</th><td><input type="password" name="pw"></td></tr>
<tr><td colspan=2 align="center">
	<input type="button" value="login" onClick="check()">
	<input type="reset" value="reset">
</td></tr>
</table>
</form>
</body>
</html>



