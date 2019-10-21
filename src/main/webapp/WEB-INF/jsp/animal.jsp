

<div class="row" style="padding-bottom:50px">
</div>
<div class="row">
	<div class="col-md-1">
	</div>
	<div class="col-md-3">
		<c:choose>
			<c:when test="${animal.hasPhoto()}">
				<img src="<c:url value="/photo?id=${animal.id}"/>" alt="photo"/>
			</c:when>
			<c:otherwise>
				<img src="<c:url value="/img/notfound.jpg"/>" alt="photo"/>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="col-md-6">
		<div class="row" style="padding:10px">
			<div class="col-md-3">
				<label>Nom :</label>
			</div>
			<div class="col-md-6">
				${animal.name}
			</div>
			<div class="col-md-3">
			</div>
		</div>
		<div class="row" style="padding:10px">
			<div class="col-md-3">
				<label>Âge :</label>
			</div>
			<div class="col-md-6">
				${animal.birthYear}
			</div>
			<div class="col-md-3">
			</div>
		</div>
		<div class="row" style="padding:10px">
			<div class="col-md-3">
				<label>Sexe :</label>
			</div>
			<div class="col-md-6">
				<c:choose>
					<c:when test="${animal.sex == 'm'.charAt(0)}">
						mâle
					</c:when>
					<c:when test="${animal.sex == 'f'.charAt(0)}">
						femelle
					</c:when>
					<c:otherwise>
						?
					</c:otherwise>
				</c:choose>
			</div>
			<div class="col-md-3">
			</div>
		</div>
		<div class="row" style="padding:10px">
			<div class="col-md-3">
				<label>Stérilisé :</label>
			</div>
			<div class="col-md-6">
				${animal.sterilized ? "oui" : "non"}
			</div>
			<div class="col-md-3">
			</div>
		</div>
		<div class="row" style="padding:10px">
			<div class="col-md-3">
				<label>Description</label>
			</div>
			<div class="col-md-6">
				${animal.description}
			</div>
		</div>
		<div class="row" style="padding:10px">
			<div class="col-md-3">

			</div>
			<div class="col-md-6">
				<a href="<c:url value="/contact-request?id=${animal.id}"/>"><button type="button">Adopter !</button></a>
			</div>
		</div>
	</div>
</div>


<div>
	<a href="<c:url value="/animals?species=${animal.species.id}"/>">${animal.species.name}</a>
	<c:if test="${animal.race != null}">
		/ <a href="<c:url value="/animals?species=${animal.species.id}&race=${animal.race.id}"/>">${animal.race.name}</a>
	</c:if>
</div>


<c:if test="${sessionScope.user != null}">
	<h2>Gestion :</h2>
	<a href="<c:url value="/gestion/animal?id=${animal.id}"/>">éditer</a>
</c:if>
