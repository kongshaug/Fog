<%-- 
    Document   : index
    Created on : 01-05-2019, 10:15:36
    Author     : aamandajuhl
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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
            <form action="Fog" method="POST">
                <button name="command" value="shop">Gå til shoppen</button>
            </form>
            <br>
            <%
                String message = (String) request.getAttribute("message");

                if (message != null)
                {
                    out.println(message + "<br><br>");
                }
            %>

            <form action="Fog" method="POST">
                E-mail<br>
                <input type="text" name="email" placeholder="Indtast e-mail" value="" minlength="6" required> <br><br>
                Adgangskode<br>
                <input type="password" name="password" placeholder="Indtast adgangskode" value="" minlength="4" required> <br><br>
                <button name="command" value="login">Log ind</button>
            </form>
            <form action="Fog" method="POST">
                <br><br><button name="command" value="newuser">Opret ny bruger</button>
            </form>



        </div>
    </center>
</body>
</html>
