<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=pageLoad;
function pageLoad(){
	notShowPop=getCookieValue();
	if(notShowPop!="true"){
		window.open("popUp.jsp","pop",
				"width=400,height=500,history=no,resizable=no,status=no,menubar=no");
	}
}
function getCookieValue(){
	var result="false";
	if(document.cookie !=""){
		cookie=document.cookie.split(";");
		for(var i=0; i<cookie.length; i++){
			element=cookie[i].split("=");
			value=element[0];
			value=value.replace(/^\s*/,'');
			if(value=="notShowPop"){
				result=element[1];
			}
		}
	}
	return result;
	
}
function deleteCookie(){
	document.cookie="notShowPop="+"false"+";path=/;expires=-1";
}
</script>
</head>
<body>
<form>
	<input type="button" value="쿠키 삭제" onClick="deleteCookie()">
</form>
</body>
</html>


