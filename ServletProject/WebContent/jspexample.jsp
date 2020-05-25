<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setAttribute("name", "wjdrbs");
		session.setAttribute("name", "Yeun");
		application.setAttribute("name", "Woong");
	%>

	${requestScope.name }
	<br> ${sessionScope.name }
	<br> ${applicationScope.name }
	<br> ${name }
	<br> ${empty param.value ? "숫자를 입력하세요" : "param.value" }

</body>
</html>