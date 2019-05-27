<%-- 
    Document   : partlist
    Created on : 23-04-2019, 15:24:04
    Author     : aamandajuhl
--%>

<%@page import="FunctionLayer.HelpingClasses.Carport"%>
<%@page import="FunctionLayer.HelpingClasses.Part"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<%    String carportDrawing = (String) request.getAttribute("carportDrawing");
%>
<center id="partlist" class="partlist">
    <form action="Fog" method="POST">
        <div>
            <button name="command" value="placeorder">Send forespørgsel</button>
            &nbsp;&nbsp;
            <button name="command" value="regretorder">Annuller forespørgsel</button>
        </div>
        <br><br>
    </form>
    <div>
        <%=carportDrawing%>
    </div>
</center>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
