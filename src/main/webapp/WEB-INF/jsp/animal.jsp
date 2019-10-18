

<h2>Animal ${animal.name}</h2>
		<c:choose>
	    <c:when test="${animal.hasPhoto()}">
			<img src="photo?id=${animal.id}" alt="photo"/>
	    </c:when>    
	    <c:otherwise>
			<img src="notfound.jpg" alt="photo"/>
	    </c:otherwise>
		</c:choose>

<ul>
	<li>${animal.name}</li>
	<li>${animal.description}</li>
	<li>${animal.species.name}</li>
	<li>${animal.color}</li>
</ul>


