<%-- 
    Document   : employeeupdate
    Created on : 09-05-2019, 13:17:31
    Author     : aamandajuhl
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<form action="Fog" method ="POST"> 
    <center class="order">
        <div>
            <h1>Alle medarbejdere</h1>
            <input type="text" name="search" placeholder="Søg e-mail" value=""> 
            <br><br><button name="command" value="employeelist">Søg</button><br><br>

            <%                
                List<User> users = (List<User>) session.getAttribute("users");
                for (User u : users)
                {
                    out.println("<button name=\"command\" value=\"employeelist\" onclick=\"setId(" + u.getId() + ")\">" + u + "</button>");
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
</body>
</html>
