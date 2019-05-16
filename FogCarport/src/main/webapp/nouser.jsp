<%-- 
    Document   : nouser
    Created on : 06-05-2019, 10:12:51
    Author     : sofieamalielandt
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
                <button name="command" value="noUser">Log ind</button>
            </form>
            <form action="Fog" method="POST">
                <br><br><button name="command" value="newuser">Opret ny bruger</button>
            </form>
            
            

        </div>
    </center>
</body>
</html>