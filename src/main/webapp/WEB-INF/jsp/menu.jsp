<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<c:url value="/"/>">Refuge</a>

<a href="<c:url value="/gestion/users"/>">gestion/users</a>


<a href="<c:url value="/rest/"/>">rest</a>

<c:choose>
    <c:when test="${sessionScope.user != null}">
        <a href="logout">logout</a>
        ${sessionScope.user}
    </c:when>
    <c:otherwise>
        <a href="login">login</a>
    </c:otherwise>
</c:choose>
<hr/>