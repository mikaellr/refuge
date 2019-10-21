<form class="std" action="<c:url value="/contact-request"/>" method="post">

    <input type="hidden" id="id" name="id" value="${animal.id}"/>

    <label for="firstName">Prénom</label>
    <input type="text" id="firstName" name="firstName" />

    <label for="lastName">Nom</label>
    <input type="text" id="lastName" name="lastName" />

    <label for="email">Email</label>
    <input type="text" id="email" name="email" />

    <label for="phone">Phonem</label>
    <input type="text" id="phone" name="phone" />

    <label for="message">Message</label>
    <textarea id="message" name="message"></textarea>

    <input type="submit" value="demander"/>

</form>