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
                    <li class="menu_logo"><a class="" href="<c:url value="/"/>"><img src="<c:url value="/img/logo_100.png"/>" alt="logo patte de chien"></a></li>
                    <li class="menu_item"><a class="" href="<c:url value="/"/>">Accueil</a></li>
                    <li class="menu_item"><a class="" href="<c:url value="/animals"/>">Adopter</a></li>
                    <li class="menu_item"><a class="" href="<c:url value="/contact"/>">Contact</a></li>

                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                                <li class="menu_item"><a class="" href="<c:url value="/gestion/"/>">Gestion</a></li>
                            <li class="login"><a class="" href="<c:url value="/logout"/>">Se déconnecter</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="login"><a class="" href="<c:url value="/login"/>">Se connecter</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
            <div>
            <c:if test="${sessionScope.user != null}">
                <span>${sessionScope.user.firstName} ${sessionScope.user.lastName} </span>
            </c:if>
            </div>
            <!-- end of header -->
