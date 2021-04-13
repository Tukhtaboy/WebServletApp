<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Question List</title>
</head>
<body>
	<c:forEach items="${listQuestion}" var="list">
		<div>${list.question}</div>
	</c:forEach>
</body>
</html>