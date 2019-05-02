<%-- 
    Document   : placeorder
    Created on : 01-05-2019, 12:30:45
    Author     : aamandajuhl
--%>

<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fog</title>
    </head>
    <body>
        <form action="Fog" method="POST">
        <%
            String message = (String) request.getAttribute("message");
            Order order = (Order) session.getAttribute("order");
            out.println(message);
            out.println(order.getOrder_date());
        %>
        <button name="command" value="back">Gå til shop</button>
        </form>
    </body>
</html>
