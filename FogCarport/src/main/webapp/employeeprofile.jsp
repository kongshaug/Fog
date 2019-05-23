<%-- 
    Document   : employeeprofile
    Created on : 14-05-2019, 11:15:19
    Author     : sofieamalielandt
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            String message = (String) request.getAttribute("message");

            if (message != null)
            {
                out.println(message + "<br><br>");
            }
        %>
        <form action="Fog" method="POST">
            Navn: 
            <%=user.getName()%><br><br>
            E-mail: 
            <%=user.getEmail()%><br><br>
            Adresse: 
            <%=user.getAddress()%>, <%=user.getZipcode()%><br><br>

            Telefonnummer: 
            <%=user.getPhone()%><br><br>

            Adgangskode &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ny adgangskode
            <input type="password" id="oldpassword" name="oldpassword" value="<%=user.getPassword()%>" minlength="4" required disabled="disabled"> 
            &nbsp;&nbsp;
            <input type="password" id="newpassword" name="newpassword" value="<%=user.getPassword()%>" onclick="password()" minlength="4" required disabled="disabled"> 
            <br><br>
            <button id="save" name="command" value="employeeupdatepassword" style="display:none;">Gem</button>
        </form>
        <button id="update" onclick="update()">Opdater</button> <br><br>
    </div>
</center>
<script>
    function update() {
        document.getElementById("oldpassword").removeAttribute("disabled");
        document.getElementById("newpassword").removeAttribute("disabled");
        document.getElementById("save").style.display = "inline-block";
        document.getElementById("update").style.display = "none";
    }

    function password() {
        document.getElementById("newpassword").value = '';
        document.getElementById("oldpassword").value = '';
    }
</script>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
