<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>---before----</h3>
	<jsp:forword page="/forwardex.jsp"/>
		<jsp:param name="number" value="10"/>
	<jsp:forword/>
	<h3>----after----</h3>
</body>
</html>