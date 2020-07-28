<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String name = (String)request.getAttribute("name");		//request값은 안가져온다
	String age=request.getParameter("age");
	String gender=request.getParameter("gender");	
	String[] hobbys=request.getParameterValues("hobby");	//1.파라메타값이 한개 이상 values로 받아옴 / 2.배열로 받아야함
	//out.print("이름 :"+name+"<br>");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
이름 : <%=name %><br>
나이 : <%=age %><br>
성별 : <%=gender %><br>	
<%   				/* 값 정확히모름, 유효성 검사 위해 java 코드사용 */
	String str="";
	if(hobbys!=null){
		for(String h:hobbys){
			str+=h+" ";
		}
		out.print("취미 : "+str+"<br>");
	}
%>
취미 : <%=str %><br>
</body>
</html>