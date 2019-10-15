<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Refuge</title>
    <link rel="stylesheet" href="<c:url value = "/css/refuge.proto.css"/>"/>
</head>
<body>

<jsp:include page="menu.jsp"/>

    <form action="login" method="POST">
        <label>Email : <input type="text" id="email" name="email" /></label>
        <br/>
        <label>Password : <input type="text" id="password" name="password" /></label>
        <br/>
        <input type="submit" />
    </form>


</body>
</html>