

<h2>${contactRequests.size()} demandes de contact</h2>

<table>
    <thead>
    <th>id</th>
    <th>firstName</th>
    <th>lastName</th>
    <th>date</th>
    <th>treated</th>
    <th>animal</th>
    <th>action</th>
    </thead>
    <tbody>
    <c:forEach items="${contactRequests}" var="item">
        <tr>
            <td>${item.id}</td>
            <td>${item.firstName}</td>
            <td>${item.lastName}</td>
            <td>${item.date}</td>
            <td>${item.treated}</td>
            <td>${item.animal.name}</td>
            <td><a href="<c:url value="/gestion/contact-request?id=${item.id}"/>"">edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
