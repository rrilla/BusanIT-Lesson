<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function setPopUpStart(obj){
	if(obj.checked==true){
		var expireDate=new Date();
		expireDate.setMonth(expireDate.getMonth()+1);
		document.cookie="notShowPop="+"true"+";path=/;expires="+expireDate.toGMTString();
		window.close();
	}
}
</script>
</head>
<body>
알림 팝업 창입니다
<br><br><br>
<form>
<input type="checkbox" onClick="setPopUpStart(this)">오늘 더 이상 팝업창 띄우지 않기
</form>
</body>
</html>




