
    <h1>Refuge / Gestion</h1>


    <ul>
        <li><a href="<c:url value="/gestion/animal-add"/>">Ajout animal</a></li>
        <li><a href="<c:url value="/gestion/users"/>">Liste utilisateurs</a></li>
        <li><a href="<c:url value="/gestion/user-add"/>">Ajout utilisateur</a></li>
    </ul>

    <hr/>

    <h2>${animals.size()} animaux</h2>

    <c:forEach items="${animals}" var="animal">
        <a class="thumbnail" href="<c:url value="/animal?id=${animal.id}"/>">

            <c:choose>
                <c:when test="${animal.hasPhoto()}">
                    <img src="<c:url value="/photo?id=${animal.id}"/>" alt="photo"/>
                </c:when>
                <c:otherwise>
                    <img src="<c:url value = "/img/notfound.jpg"/>" alt="photo"/>
                </c:otherwise>
            </c:choose>
            <p>${animal.name}</p>
        </a>
    </c:forEach>

