<%-- 
    Document   : customer
    Created on : 06-05-2019, 14:30:16
    Author     : sofieamalielandt
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>

<form action="Fog" method ="POST"> 
    <center class="order">
        <div>
            <h1>Order historik</h1>

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
