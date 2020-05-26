<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Complete</h3>
	<%
	String name = request.getParameter("name");
	int n = Integer.parseInt(name);
	%>
	
	<%= n %>
	
</body>
</html>