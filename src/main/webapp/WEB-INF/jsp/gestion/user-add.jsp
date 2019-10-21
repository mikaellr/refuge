

<h2>Ajout utilisateur</h2>

<form class="std" method="post" action="<c:url value="/gestion/user-add"/>">
    <input type="hidden" name="id" value="${item.id}"/>
    <label>Prénom:</label>
    <input type="text" name="firstName" value="${item.firstName}"/>
    <label>Nom: </label>
    <input type="text" name="lastName" value="${item.lastName}"/>
    <label>Email: </label>
    <input type="text" name="email" value="${item.email}"/>
    <label>Phone: </label>
    <input type="text" name="phone" value="${item.phone}"/>
    <label>Mot de passe: </label>
    <input type="password" name="password" value=""/>
    <label>Confirm:</label>
    <input type="password" name="confirm" value=""/>
    <input type="submit" value="ajouter"/>
</form>

