<%-- 
    Document   : employeeupdate
    Created on : 09-05-2019, 13:29:34
    Author     : aamandajuhl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            
            User employee = (User) session.getAttribute("employee");
            String message = (String) request.getAttribute("message");

            if (message != null)
            {
                out.println(message + "<br><br>");
            }
            
        %>
        <form action="Fog" method="POST">
            Navn<br>
            <input type="text" name="name" value="<%=employee.getName()%>" minlength="2" required> <br><br>
            E-mail<br>
            <input type="text" name="email" value="<%=employee.getEmail()%>" minlength="6" required> <br><br>
            Adresse<br>
            <input type="text" name="address" value="<%=employee.getAddress()%>" minlength="5" required> <br><br>
            Postnummer<br>
            <input type="text" name="zipcode" value="<%=employee.getZipcode()%>" minlength="4" maxlength="4" required> <br><br>
            Telefonnummer<br>
            <input type="text" name="phone" value="<%=employee.getPhone()%>" minlength="8" maxlength="8" required> <br><br>
            <br><br><button name="command" value="employeeinfo">Opdater</button> <br><br>
        </form>
    </div>
</center>

</body>
</html>
