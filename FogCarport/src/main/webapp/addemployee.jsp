<%-- 
    Document   : addemployee
    Created on : 09-05-2019, 12:35:05
    Author     : sofieamalielandt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file = "header.jsp" %>
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
            <input type="password" name="password" value="1234" minlength="4" required disabled="disabled">
            <br>(Altid 1234 for nye medarbejdere)
            <br><br>
            <select name="role">
                <%                    
                    out.println("<option> " + Role.EMPLOYEE + "</option>");
                    out.println("<option> " + Role.ADMIN + "</option>");

                %>
            </select>
            <br><br><button name="command" value="addemployee">Opret medarbejder</button> <br><br>


        </form>
    </div>
</center>
</body>
</html>
