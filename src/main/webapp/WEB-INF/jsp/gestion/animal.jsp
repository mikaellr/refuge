
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





<h2>User :</h2>

    <form method="post" action="<c:url value="/gestion/animal"/>">
        <input type="hidden" name="id" value="${item.id}"/>
        <label>Nom: </label>
        <input type="text" name="name" value="${item.name}"/>
        <label>Description: </label>
        <textarea name="description">${item.description}</textarea>
        <label>: </label>
        <input type="text" name="birthYear" value="${item.birthYear}"/>

        <input type="radio" id="male" name="sex" value="m" <c:if test="${item.sex == 'm'.charAt(0)}">checked="checked"</c:if>/>
        <label for="male">mâle</label>
        <input type="radio" id="female" name="sex" value="f" <c:if test="${item.sex == 'f'.charAt(0)}">checked="checked"</c:if>/>
        <label for="female">femelle</label>


        <input type="checkbox" id="sterilized" name="sterilized" value="" <c:if test="${item.sterilized == true}">checked="checked"</c:if>/>
        <label for="sterilized">sterilized</label>

        <input type="checkbox" id="adoptable" name="adoptable" value="" <c:if test="${item.adoptable == true}">checked="checked"</c:if>/>
        <label for="adoptable">adoptable</label>


        <select name="species" id="species">
            <option value=""></option>
            <c:forEach items="${speciesAll}" var="speciesItem">
                <option value="${speciesItem.id}" ${speciesItem.id==selectedSpeciesId ? 'selected="selected"' : ''} >${speciesItem.name}</option>
            </c:forEach>
        </select>
        <select id="race" name="race">
            <option value=""></option>
            <c:forEach items="${racesAll}" var="raceItem">
                <option value="${raceItem.id}" ${raceItem.id==selectedRaceId ? 'selected="selected"' : ''} >${raceItem.name}</option>
            </c:forEach>
        </select>

        <input type="submit"value="Modifier"/>
    </form>

<hr/>

