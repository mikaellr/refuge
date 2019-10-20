
    <script>
        $(document).ready(function() {
            console.log("ready");
            $("#species").change(function(evt) {
                var idSpecies = $("#species").val();
                var url = "<c:url value="/races?id="/>" + idSpecies;
                console.log("changed id species:" + idSpecies);
                console.log("changed id url:" + url);
                $.ajax({
                    "url" : url,
                    "method" : "get",
                    "success" : function(data) {
                        $("#races").empty();
                        console.log(data);
                        for (var key in data) {
                            $("#races").append(
                                $("<option>" + data[key].name + "</option>")
                                    .attr("value", data[key].id)
                            )
                        }
                    },
                    "error" : function() {
                        console.log("échec chargement races")
                    }

                });
            })
        })
    </script>


    <h1>Refuge</h1>

    <h2>Recherche d'un animal à adopter :</h2>

    <form method="post" action="<c:url value="/animals"/>">
    <select id="species" name="species">
        <c:forEach items="${species}" var="speciesItem">
            <option value="${speciesItem.id}">${speciesItem.name}</option>
        </c:forEach>
    </select>
    <select id="races" name="races">
    </select>
    <input type="submit" value="chercher"/>
    </form>

