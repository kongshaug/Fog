<%-- 
    Document   : employee
    Created on : 01-05-2019, 10:17:36
    Author     : aamandajuhl
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>

<form action="Fog" method ="POST"> 
    <center class="order">
        <div>
            <h1>Alle ordrer</h1>
            <%                String message = (String) request.getAttribute("message");
                if (message != null)
                {
                    out.println(message + "<br><br>");
                }
            %>
            <input type="text" name="search" placeholder="S�g e-mail" value=""> 
            <br><br><button name="command" value="employee">S�g</button><br><br>
            <%
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                for (Order o : orders)
                {
                    out.println("<button name=\"command\" value=\"employeeorder\" onclick=\"setId(" + o.getOrder_id() + ")\">" + o + "</button>");
                }
            %>
            <input type="hidden" id="order_id" name="selected" value="">
        </div>
        <script>
            function setId(Id) {
                document.getElementById('order_id').value = Id;
            }
        </script>
    </center>
    <br><br><br>
    <div class="footer">
        <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
    </div>
</form>
</html>
