<%-- 
    Document   : customer
    Created on : 06-05-2019, 14:30:16
    Author     : sofieamalielandt
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>

<form action="Fog" method ="POST"> 
    <center class="order">
        <div>
            <h1>Order historik</h1>
            <%                
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                for (Order o : orders)
                {
                    out.println("<button name=\"command\" value=\"customerorder\" onclick=\"setId(" + o.getOrder_id() + ")\">" + o + "</button>");
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
