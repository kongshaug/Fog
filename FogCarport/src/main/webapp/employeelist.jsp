<%-- 
    Document   : employeeupdate
    Created on : 09-05-2019, 13:17:31
    Author     : aamandajuhl
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<form action="Fog" method ="POST"> 
    <center class="order">
        <div>
            <h1>Alle medarbejdere</h1>
            <input type="text" name="search" placeholder="S�g e-mail" value=""> 
            <br><br><button name="command" value="employeelist">S�g</button><br>
            <%                String message = (String) request.getAttribute("message");
                if (message != null)
                {
                    out.println(message + "<br><br>");
                }

                List<User> users = (List<User>) request.getAttribute("users");
                for (User u : users)
                {
                    out.println("<button name=\"command\" value=\"employeeinfo\" onclick=\"setId(" + u.getId() + ")\">" + u + "</button>");
                }
            %>
            <input type="hidden" id="user_id" name="selected" value="">
        </div>

        <script>
            function setId(Id) {
                document.getElementById('user_id').value = Id;
            }
        </script>
    </center>
</form>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
