
<h2>${users.size()} users</h2>

<table>
    <thead>
    <th>id</th>
    <th>firstName</th>
    <th>lastName</th>
    <th>email</th>
    <th>phone</th>
    <th>active</th>
    <th>rôle</th>
    <th>action</th>
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
