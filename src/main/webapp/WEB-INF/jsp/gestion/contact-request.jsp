

<h2>Demande de contact</h2>

<ul>
<li>${item.id}</li>
<li>${item.firstName}</li>
<li>${item.lastName}</li>
<li>${item.email}</li>
<li>${item.phone}</li>
<li>${item.date}</li>
<li>${item.treated}</li>
<li>${item.animal.name}</li>
</ul>

<p>Message:</p>
<p>${item.message}</p>


<hr/>

        <form method="post" action="<c:url value="/gestion/contact-request"/>">
            <input type="hidden" name="id" value="${item.id}"/>
            <select id="treated" name="treated">
                <option value="true">traitée</option>
                <option value="false">non traitée</option>
            </select>
            <input type="submit" value="Modifier"/>
        </form>
