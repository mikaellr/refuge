<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Refuge</title>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <link rel="stylesheet" href="<c:url value = "/css/refuge.proto.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/design.css"/>" />
        <link rel="icon" type="image/png" href="<c:url value="/favicon.png"/>" />
        <link rel="shortcut icon" type="image/png" href="<c:url value="/favicon.png"/>" />
        <script src="<c:url value="/script/jquery.min.js"/>"></script>
        <script src="<c:url value="/script/script.js"/>"></script>
    </head>
    <body>
        <main>
            <header>
                <img id="banner" src="<c:url value="/img/header.jpg"/>" alt="bannière">
            </header>
            <nav id="menu">
                <ul>
                    <li class="menu_logo"><img src="<c:url value="/img/logo_100.png"/>" alt="logo patte de chien"></li>
                    <li class="menu_item"><a class="" href="<c:url value="/"/>">Accueil</a></li>
                    <li class="menu_item"><a class="" href="<c:url value="/"/>">Adopter</a></li>
                    <li class="menu_item"><a class="" href="<c:url value="/"/>">Contact</a></li>
                    <li class="login"><a class="" href="<c:url value="/"/>">Se connecter</a></li>
                    <li class="login"><a class="" href="<c:url value="/"/>">Se déconnecter</a></li>
                </ul>
            </nav>

            <div>
                <hr/>
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
            </div>

            <!-- end of header -->
