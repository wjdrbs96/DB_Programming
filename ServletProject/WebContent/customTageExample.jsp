<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setAttribute("name", "wjdrbs");
	%>
	<mytag:line />
	오늘날짜는 : <mytag:time />
	<mytag:line />

	<mytag:sum num2="2" num1="5" /> <br>
	<mytag:chart>${name }</mytag:chart>
</body>
</html>