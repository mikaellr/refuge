<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>refuge</title>
    <link rel="stylesheet" href="<c:url value = "/css/refuge.proto.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="menu.jsp"/>
</header>
<main>
    <h1>Refuge</h1>


    <h2>${animals.size()} animaux</h2>

    <c:forEach items="${animals}" var="animal">
        <a class="thumbnail" href="animal?id=${animal.id}">

            <c:choose>
                <c:when test="${animal.hasPhoto()}">
                    <img src="photo?id=${animal.id}" alt="photo"/>
                </c:when>
                <c:otherwise>
                    <img src="<c:url value = "/img/notfound.jpg"/>" alt="photo"/>
                </c:otherwise>
            </c:choose>
            <p>${animal.name}</p>
        </a>
    </c:forEach>

    <h2>${users.size()} utilisateurs</h2>

    <table>
        <thead>
        <tr>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Tél.</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td> ${user.email}</td>
                <td> ${user.phone}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.active}">
                            active
                        </c:when>
                        <c:otherwise>
                            inactive
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
        </tbody>

    </table>


    <h2>${species.size()} espèces</h2>

            <select>
            <c:forEach items="${species}" var="specie">
                <optgroup label="${specie.name}">
                    <c:forEach items="${specie.races}" var="race">
                        <option value="${race.id}">${race.name}</option>
                    </c:forEach>
                </optgroup>
            </c:forEach>
            </select>

    <h2>${colors.size()} colors</h2>

            <select>
                <c:forEach items="${colors}" var="color">
                    <option>${color.name}</option>
                </c:forEach>
            </select>



</main>
<footer>
    <hr/>
    <%= new Date() %>%>
</footer>
</body>
</html>
