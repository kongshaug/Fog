<%-- 
    Document   : employee
    Created on : 01-05-2019, 10:17:36
    Author     : aamandajuhl
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<form action="Fog" method ="POST"> 
    <center class="order">
        <div>
            <h1>Alle ordrer</h1>
            <input type="text" name="search" placeholder="Søg e-mail" value=""> 
            <br><br><button name="command" value="employee">Søg</button><br><br>

            <%                
                List<Order> orders = (List<Order>) session.getAttribute("orders");
                for (Order o : orders)
                { 
                    out.println("<button name=\"command\" value=\"employeeorder\"" + o.getOrder_id() + "\">" + o + "</button>");
                }
            %>
        </div>
    </center>
</form>
</html>
