<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>매물 결과</title>
</head>
<body>
	<c:forEach items="${data}" var="item">
		<div>
			${item.apartment_name}
		</div>
	</c:forEach>
	<div>
		
	</div>
</body>
</html>