<%-- 
    Document   : newuser
    Created on : 01-05-2019, 10:54:05
    Author     : aamandajuhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fog</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("message");
        %>
        <form action="Fog" method="POST">
            <input type="text" name="name" placeholder="navn" value="" minlength="2" required> <br><br>
            <input type="text" name="email" placeholder="email" value="" minlength="6" required> <br><br>
            <input type="text" name="address" placeholder="adresse" value="" minlength="5" required> <br><br>
            <input type="text" name="zipcode" placeholder="postnummer" value="" minlength="4" maxlength="4" required> <br><br>
            <input type="text" name="phone" placeholder="telefon" value="" minlength="8" maxlength="8" required> <br><br>
            <input type="password" name="password" placeholder="adgangskode" value="" minlength="4" required> <br><br>
            <button name="command" value="adduser">Opret bruger</button> <br><br>

            <%
                if (message != null)
                {
                    out.println(message);
                }
            %>
        </form>
    </body>
</html>
