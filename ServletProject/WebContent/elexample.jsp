<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	${param.id } / ${param.pw } <br>    
	${param["id"] } / ${param["pw"] } <br>
	${param }
	
	${param["-d"] } / ${param["new"] } <br>
	${header } <br>
	
	
	
</body>
</html>