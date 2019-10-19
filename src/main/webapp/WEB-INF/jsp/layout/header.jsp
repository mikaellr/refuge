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

        <a href="<c:url value="/"/>">Refuge</a>
        <a href="<c:url value="/gestion/"/>">gestion/</a>
        <a href="<c:url value="/gestion/users"/>">gestion/users</a>

        <c:choose>
            <c:when test="${sessionScope.user != null}">
                <a href="<c:url value="/logout"/>">logout</a>
                ${sessionScope.user}
            </c:when>
            <c:otherwise>
                <a href="<c:url value="/login"/>">login</a>
            </c:otherwise>
        </c:choose>


        (<a href="<c:url value="/rest/"/>">rest</a>)
        <hr/>

        </header>
        <main>


