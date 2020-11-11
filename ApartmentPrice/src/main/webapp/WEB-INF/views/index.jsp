<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>����</title>
</head>
<body>
	<div>
		<form action="/result" method="post">
			<div>
				��¥ :
				<select name="year">
					<c:forEach var="i" begin="2000" end="2020">
						<option value=${i }>${i}</option>
					</c:forEach>
				</select>
				-
				<select name="mon">
					<c:forEach var="i" begin="1" end="12">
						<option value=${i }>${i}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				���� :
				<select name="locationCd">
					<c:forEach var="item" items="${locationList }">
						<option value=${item.external_cd }>${item.local_nm }</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<input type="submit" value="�˻�">
			</div>
		</form>
	</div>
</body>
</html>