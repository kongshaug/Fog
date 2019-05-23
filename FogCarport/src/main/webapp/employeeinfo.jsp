<%-- 
    Document   : employeeupdate
    Created on : 09-05-2019, 13:29:34
    Author     : aamandajuhl
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            User employee = (User) session.getAttribute("employee");
            String message = (String) request.getAttribute("message");
            if (message != null)
            {
                out.println(message + "<br><br>");
            }
        %>
        <form action="Fog" method="POST">
            Navn<br>
            <input type="text" id="name" name="name" value="<%=employee.getName()%>" minlength="2" required disabled="disabled"> <br><br>
            E-mail<br>
            <input type="text" id="email" name="email" value="<%=employee.getEmail()%>" minlength="6" required disabled="disabled"> <br><br>
            Adresse<br>
            <input type="text" id="address" name="address" value="<%=employee.getAddress()%>" minlength="5" required disabled="disabled"> <br><br>
            Postnummer<br>
            <input type="text" id="zipcode" name="zipcode" value="<%=employee.getZipcode()%>" minlength="4" maxlength="4" required disabled="disabled"> <br><br>
            Telefonnummer<br>
            <input type="text" id="phone" name="phone" value="<%=employee.getPhone()%>" minlength="8" maxlength="8" required hidden="hidden"> <br><br>

            <button id="save" name="command" value="employeeupdate" style="display:none;">Gem</button>

        </form>
        <button id="update" onclick="update()">Opdater</button> <br><br>
        <form action="Fog" method="POST">

            <button id="delete" name="command" value="deleteemployee">Slet medarbejder</button>
        </form>
    </div>
</center>
<script>
    function update() {
        document.getElementById("name").removeAttribute("disabled");
        document.getElementById("email").removeAttribute("disabled");
        document.getElementById("address").removeAttribute("disabled");
        document.getElementById("zipcode").removeAttribute("disabled");
        document.getElementById("phone").removeAttribute("disabled");
        document.getElementById("save").style.display = "inline-block";
        document.getElementById("update").style.display = "none";
    }
</script>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
