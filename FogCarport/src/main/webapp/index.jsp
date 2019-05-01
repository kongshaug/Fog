<%-- 
    Document   : index
    Created on : 01-05-2019, 10:15:36
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
            String message = (String) session.getAttribute("message");
        %>
        <form action="Fog" method="POST">
            <input type="text" name="email" placeholder="email" value="" minlength="6" required> <br><br>
            <input type="password" name="password" placeholder="adgangskode" value="" minlength="4" required> <br><br>
            <button name="command" value="login">Login</button>
        </form>
        <form action="Fog" method="POST">
            <button name="command" value="newuser">Opret ny bruger</button>
        </form>
        <%
            if (message != null)
            {
                out.println(message);
                session.removeAttribute("message");
            }
        %>
    </body>
</html>
