<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setAttribute("name", "Kyung");
	session.setAttribute("name", "Yeun");
	application.setAttribute("name", "Woong");
%>

	${requestScope.name } <br>
	${sessionScope.name } <br>
	${applicationScope.name } <br>
	${name }
</body>
</html>