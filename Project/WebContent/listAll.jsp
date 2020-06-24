<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>

<link href="https://fonts.googleapis.com/css?family=Jua"
	rel="stylesheet">
<title>Insert title here</title>
</head>
<style>
	#margin {
		margin-top : 50px;
		margin-bottom: 20px;
	}
</style>
<body>

	<div class="container">

		<form action="search" method="post" class="form-inline">
			<div id="margin" class="form-group">
				<select name="select" class="form-control">
					<option value="default"}>default</option>
					<option value="department">학과</option>
				</select> <input type="text" size=20 class="form-control" name="search"
					placeholder="검색">
				<button type="submit" class="btn btn-primary">조회</button>
			</div>
		</form>

		<table class="table table-hover table table-striped">
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>학과</th>
			</tr>

			<c:forEach items="${list}" var="list">
				<tr>
				    <th><a href="http://localhost:8081/Project/login?id=${list.getMemberId()}">${list.getLoginId()}</a></th>
					<th>${list.getName()}</th>
					<th>${list.getDepartment()}</th>
				</tr>
			</c:forEach>
		</table>


	</div>

</body>
</html>