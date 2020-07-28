<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- HTML주석 -->
<%-- jsp주석 --%>
<%=age %>
<%=getAge() %> <!-- 표현식 - 변수의값,함수의값 주로 출력할 때 사용 -->

<%!	/* 클래스 전역(jsp는 한페이지가 클래스)에서 사용가능한 변수나 함수 선언, 전역변수(어디든 사용가능) */
	int age=10; //클래스 소속의 변수
	int getAge(){
		return age;
	}
%>
<%	/* 자바코드를 쓰는곳, 변수(밑에서는 사용가능) */
	String name="홍길동";
	// java 주석
	/* java 여러줄 주석 */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 기본</title>
</head>
<body>

</body>
</html>