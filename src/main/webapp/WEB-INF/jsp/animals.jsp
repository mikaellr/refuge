
<script>
	$(document).ready(function() {
		$("#species").change(function(evt) {
			var idSpecies = $("#species").val();
			var url = "<c:url value="/races?id="/>" + idSpecies;
			$.ajax({
				"url" : url,
				"method" : "get",
				"success" : function(data) {
					$("#race").empty().append($('<option value=""></option>'));
					for (var key in data) {
						$("#race").append(
								$("<option>" + data[key].name + "</option>")
										.attr("value", data[key].id)
						)
					}
				},
				"error" : function() {
				}
			});
		})
	})

</script>



<h1>Recherche d'un animal à adopter :</h1>

<form method="get" action="<c:url value="/animals"/>">
	<select id="species" name="species">
		<option value=""></option>
		<c:forEach items="${species}" var="speciesItem">
		<option value="${speciesItem.id}" ${speciesItem.id==selectedSpeciesId ? 'selected="selected"' : ''} >${speciesItem.name}</option>
		</c:forEach>
	</select>
	<select id="race" name="race">
		<option value=""></option>
		<c:forEach items="${races}" var="raceItem">
		<option value="${raceItem.id}" ${raceItem.id==selectedRaceId ? 'selected="selected"' : ''} >${raceItem.name}</option>
		</c:forEach>
	</select>
	<input type="hidden" name="all" value="true"/>
	<input type="submit" value="chercher"/>
</form>



<h2>Résultats :</h2>

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
