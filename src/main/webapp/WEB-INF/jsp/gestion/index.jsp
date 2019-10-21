
    <h1>Refuge / Gestion</h1>


    <ul>
        <li><a href="<c:url value="/gestion/users"/>">Liste des utilisateurs</a></li>
        <li><a href="<c:url value="/gestion/user-add"/>">Ajouter un utilisateur</a></li>
        <li><a href="<c:url value="/gestion/animals"/>">Liste des animaux</a></li>
        <li><a href="<c:url value="/gestion/animal-add"/>">Ajouter un animal</a></li>
        <li><a href="<c:url value="/gestion/contact-requests"/>">Liste des demandes de contact</a></li>
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

