
<h1>Résultats :</h1>

<p>${animals.size()} animaux correspondent à votre recherche.</p>

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
