
<h2>${users.size()} users</h2>

<table>
    <thead>

    </thead>
    <tbody>
    <c:forEach items="${users}" var="item">
    <tr>
        <td>${item.id}</td>
        <td>${item.firstName}</td>
        <td>${item.lastName}</td>
        <td>${item.email}</td>
        <td>${item.phone}</td>
        <td>${item.active}</td>
        <td>${item.role.name}</td>
        <td><a href="<c:url value="/gestion/user?id=${item.id}"/>"">edit</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>

<h2>Ajout</h2>

<form method="post" action="">
    <input type="hidden" name="id" value="${item.id}"/>
    <label>Prénom: </label>
    <input type="text" name="firstName" value="${item.firstName}"/>
    <label>Nom:</label>
    <input type="text" name="lastName" value="${item.lastName}"/>
    <label>Email:</label>
    <input type="text" name="email" value="${item.email}"/>
    <label>Phone:</label>
    <input type="text" name="phone" value="${item.phone}"/>
    <label>Mot de passe:</label>
    <input type="password" name="password" value=""/>
    <label>Confirm:</label>
    <input type="password" name="confirm" value=""/>
    <input type="submit" value="ajouter"/>
</form>

