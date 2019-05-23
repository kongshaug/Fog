<%-- 
    Document   : viewdrawing
    Created on : 09-05-2019, 15:28:40
    Author     : sofieamalielandt
--%>

<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page import="FunctionLayer.HelpingClasses.Carport"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<%    Order order = (Order) session.getAttribute("order");
    String roofDrawing = (String) session.getAttribute("roofDrawing");
%>
<center id="partlist" class="partlist">
    <form action="Fog" method="POST">
        <div>
            <%
                if (user.getRole().equals(Role.CUSTOMER))
                {
                    out.println("<button name=\"command\" value=\"customerorder\">Gå tilbage</button>");
                } else
                {

                    out.println("<button name=\"command\" value=\"employeeorder\">Gå tilbage</button>");
                }
            %>
            <input type="hidden" id="order_id" name="selected" value="<%=order.getOrder_id()%>">
        </div>
        <br><br>
    </form>
    <div>
        <%=roofDrawing%>
    </div>
</center>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
