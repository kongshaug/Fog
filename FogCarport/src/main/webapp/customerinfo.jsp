<%-- 
    Document   : customerinfo
    Created on : 14-05-2019, 10:24:53
    Author     : sofieamalielandt
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
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
            <input type="text" id="name" name="name" value="<%=user.getName()%>" minlength="2" required disabled="disabled"> <br><br>
            E-mail<br>
            <input type="text" id="email" name="email" value="<%=user.getEmail()%>" minlength="6" required disabled="disabled"> <br><br>
            Adresse<br>
            <input type="text" id="address" name="address" value="<%=user.getAddress()%>" minlength="5" required disabled="disabled"> <br><br>
            Postnummer<br>
            <input type="text" id="zipcode" name="zipcode" value="<%=user.getZipcode()%>" minlength="4" maxlength="4" required disabled="disabled"> <br><br>
            Telefonnummer<br>
            <input type="text" id="phone" name="phone" value="<%=user.getPhone()%>" minlength="8" maxlength="8" required disabled="disabled"> <br><br>

            Adgangskode &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ny adgangskode
            <input type="password" id="oldpassword" name="oldpassword" value="<%=user.getPassword()%>" minlength="4" required disabled="disabled"> 
            &nbsp;&nbsp;
            <input type="password" id="newpassword" name="newpassword" value="<%=user.getPassword()%>" onclick="password()" minlength="4" required disabled="disabled"> 
            <br><br>
            <button id="save" name="command" value="customerupdate" style="display:none;">Gem</button>
        </form>
            
        <button id="update" onclick="update()">Opdater</button> <br><br>
        
        <form action="Fog" method="POST">
            <button id="delete" name="command" value="deletecustomer">Slet bruger</button>
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
</body>
</html>
