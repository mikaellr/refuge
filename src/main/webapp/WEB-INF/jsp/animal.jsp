<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Refuge</title>
<link rel="stylesheet" href="<c:url value = "/css/refuge.proto.css"/>" />
</head>
<body>

<jsp:include page="menu.jsp"/>


<h2>Animal ${animal.name}</h2>
		<c:choose>
	    <c:when test="${animal.hasPhoto()}">
			<img src="photo?id=${animal.id}" alt="photo"/>
	    </c:when>    
	    <c:otherwise>
			<img src="notfound.jpg" alt="photo"/>
	    </c:otherwise>
		</c:choose>

<ul>
	<li>${animal.name}</li>
	<li>${animal.description}</li>
	<li>${animal.species.name}</li>
	<li>${animal.color}</li>
</ul>




</html>