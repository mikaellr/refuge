
<h2>${animals.size()} animaux</h2>

<table>
    <thead>
    <th>id</th>
    <th>nom</th>
    <th>année naissance</th>
    <th>sexe</th>
    <th>stérilisé</th>
    <th>adoptable</th>
    <th>espèce</th>
    <th>race</th>
    <th>action</th>
    </thead>
    <tbody>
    <c:forEach items="${animals}" var="item">
    <tr>
        <td>${item.id}</td>
        <td>${item.name}</td>
        <td>${item.birthYear}</td>
        <td>${item.sex}</td>
        <td>${item.sterilized}</td>
        <td>${item.adoptable}</td>
        <td>${item.species.name}</td>
        <td><c:if test="${animal.race != null}">${animal.race.name}</c:if></td>
        <td><a href="<c:url value="/gestion/animal?id=${item.id}"/>">edit</a></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
