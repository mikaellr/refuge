

<c:if test="${usermsg != null}">
    ${usermsg}
</c:if>

<h2>Ajout utilisateur</h2>

<form class="std" method="post" action="<c:url value="/gestion/user-add"/>">
    <label>Prénom:</label>
    <input type="text" name="firstName" value="${firstName}"/>
    <label>Nom: </label>
    <input type="text" name="lastName" value="${lastName}"/>
    <label>Email: </label>
    <input type="text" name="email" value="${email}"/>
    <label>Phone: </label>
    <input type="text" name="phone" value="${phone}"/>
    <label>Mot de passe: </label>
    <input type="password" name="password" value=""/>
    <label>Confirm:</label>
    <input type="password" name="confirm" value=""/>
    <input type="submit" value="ajouter"/>
</form>

