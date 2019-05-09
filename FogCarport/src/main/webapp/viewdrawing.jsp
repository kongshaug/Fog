<%-- 
    Document   : viewdrawing
    Created on : 09-05-2019, 15:28:40
    Author     : sofieamalielandt
--%>

<%@page import="FunctionLayer.HelpingClasses.Order"%>
<%@page import="FunctionLayer.HelpingClasses.Carport"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>
<%        
    Order order = (Order) session.getAttribute("order");
    String roofDrawing = (String) session.getAttribute("roofDrawing");
%>
<center id="index" class="index">
    <form action="Fog" method="POST">
        <div>
            <%
                if(user.getRole().equals(Role.CUSTOMER))
                {
                    out.println("<button name=\"command\" value=\"customerorder\">Gå tilbage</button>");
                }
                else{
                    
                    out.println("<button name=\"command\" value=\"employeeorder\">Gå tilbage</button>");
                }
            %>
            <input type="hidden" id="order_id" name="selected" value="<%=order.getOrder_id()%>">
        </div>
        <br><br>
    </form>
    <%=roofDrawing%>
</center>
</body>
</html>
