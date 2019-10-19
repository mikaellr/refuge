
<h2>User :</h2>

    <form method="post" action="">
        <label>Prénom: </label>
        <input type="hidden" name="id" value="${item.id}"/>
        <input type="text" name="firstName" value="${item.firstName}"/>
        <input type="text" name="lastName" value="${item.lastName}"/>
        <input type="text" name="email" value="${item.email}"/>
        <input type="text" name="phone" value="${item.phone}"/>
        <input type="submit"/>
    </form>



<c:choose>
    <c:when test="${item.active}">
        <form method="post" action="<c:url value="/gestion/user-deactivate"/>">
            <input type="hidden" name="id" value="${item.id}"/>
            <input type="submit" value="deactivate"/>
        </form>
    </c:when>
    <c:otherwise>
        <form method="post" action="<c:url value="/gestion/user-activate"/>">
            <input type="hidden" name="id" value="${item.id}"/>
            <input type="submit" value="activate"/>
        </form>
    </c:otherwise>
</c:choose>

<form method="post" action="<c:url value="/gestion/user-delete"/>">
    <input type="hidden" name="id" value="${item.id}"/>
    <input type="submit" value="delete"/>
</form>

