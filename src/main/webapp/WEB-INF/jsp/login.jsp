<%@page contentType="text/html;charset=UTF-8" language="java" %>


    <form action="<c:url value="/login"/>" method="POST">
        <label>Email : <input type="text" id="email" name="email" /></label>
        <br/>
        <label>Password : <input type="password" id="password" name="password" /></label>
        <br/>
        <input type="submit" value="Se connecter"/>
    </form>

