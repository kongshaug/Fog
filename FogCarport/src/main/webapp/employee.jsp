<%-- 
    Document   : employee
    Created on : 01-05-2019, 10:17:36
    Author     : aamandajuhl
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form action="Legohouse" method ="POST"> 
    <center id="order">
        <input type="text" name="email" placeholder="Søg e-mail" value="" minlength="6" required> 
        <br><br><button name="command" value="search"></button>
        <ul>
            <li><p><h1>Alle ordrer</h1></p></li>
            <% 
                List<Order> allorders = (List<Order>) session.getAttribute("allorders");
                for (Order o : allorders)
                {
                    out.println("<li><a href=\"Legohouse?command=employeeorder&selected=" + o.getOrder_id() + "\">" + o + "</a></li>");
                }
            %>
        </ul>
    </center>
</form>
</html>
