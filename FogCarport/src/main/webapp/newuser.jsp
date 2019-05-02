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
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Fog</title>
    </head>
    <body>
    <center class="index" id="index"> 
        <br><br><br>
        <div>
            <img src="images/logo.png" width="30%"><br>
            <%
                String message = (String) request.getAttribute("message");

                if (message != null)
                {
                    out.println(message + "<br><br>");
                }

            %>
            <form action="Fog" method="POST">
                Navn<br>
                <input type="text" name="name" placeholder="Indtast navn" value="" minlength="2" required> <br><br>
                E-mail<br>
                <input type="text" name="email" placeholder="Indtast email" value="" minlength="6" required> <br><br>
                Adresse<br>
                <input type="text" name="address" placeholder="Indtast adresse" value="" minlength="5" required> <br><br>
                Postnummer<br>
                <input type="text" name="zipcode" placeholder="Indtast postnummer" value="" minlength="4" maxlength="4" required> <br><br>
                Telefonnummer<br>
                <input type="text" name="phone" placeholder="Indtast telefonnummer" value="" minlength="8" maxlength="8" required> <br><br>
                Adgangskode<br>
                <input type="password" name="password" placeholder=" Indtast adgangskode" value="" minlength="4" required> <br><br>
                <br><button name="command" value="adduser">Opret bruger</button> <br><br>


            </form>
        </div>
    </center>
</body>
</html>
