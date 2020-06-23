<%@page import="javax.script.ScriptException"%>
<%@page import="javax.script.ScriptEngineManager"%>
<%@page import="javax.script.ScriptEngine"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input{
	width:50px;
	height:50px;
}
.output{
	height: 50px;
	background: #e9e9e9;
	font-size:24px;
	font-weight: bold;
	text-align: right;
	padding:0px 5px;
}
</style>
</head>
<body>
	<%
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		String operator = request.getParameter("operator");
		int iNum1 = num1 != null ? Integer.parseInt(num1) : 1;
		int iNum2 = num2 != null ? Integer.parseInt(num2) : 1;
		int result = iNum1 * iNum2;
	%>

	<div>
		<form action = "myJspExample2.jsp" method="get">
			<table>
			  <tr>
			  	<td class="output" colspan="4"><%= iNum1 %> * <%= iNum2 %> = <%= result %></td>
			  	<input type="hidden" name="num1" value=<%= result %>>
			  </tr>
			  <tr>
			  	<td><input type="submit" name="operator" value="CE"/></td>
			  	<td><input type="submit" name="operator" value="C"/></td>
			  	<td><input type="submit" name="operator" value="BS"/></td>
			  	<td><input type="submit" name="operator" value="/"/></td>
			  </tr>
			  <tr>
			  	<td><input type="submit" name="num2" value="7"/></td>
			  	<td><input type="submit" name="num2" value="8"/></td>
			  	<td><input type="submit" name="num2" value="9"/></td>
			  	<td><input type="submit" name="operator" value="*"/></td>
			  </tr>
			  <tr>
			  	<td><input type="submit" name="num2" value="4"/></td>
			  	<td><input type="submit" name="num2" value="5"/></td>
			  	<td><input type="submit" name="num2" value="6"/></td>
			  	<td><input type="submit" name="operator" value="-"/></td>
			  </tr>
			  <tr>
			  	<td><input type="submit" name="num2" value="1"/></td>
			  	<td><input type="submit" name="num2" value="2"/></td>
			  	<td><input type="submit" name="num2" value="3"/></td>
			  	<td><input type="submit" name="operator" value="+"/></td>
			  </tr>
			  <tr>
			  	<td></td>
			  	<td><input type="submit" name="num2" value="0"/></td>
			  	<td><input type="submit" name="dot" value="."/></td>
			  	<td><input type="submit" name="operator" value="="/></td>
			  </tr>
			</table>		
			
		</form>
	</div>
</body>
</html>