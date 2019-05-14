<%-- 
    Document   : partlist
    Created on : 23-04-2019, 15:24:04
    Author     : aamandajuhl
--%>

<%@page import="FunctionLayer.HelpingClasses.Carport"%>
<%@page import="FunctionLayer.HelpingClasses.Part"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file = "header.jsp" %>
<%        Carport carport = (Carport) session.getAttribute("carport");
    String roofDrawing = (String) session.getAttribute("roofDrawing");
%>
<center id="index" class="index">
    <form action="Fog" method="POST">
        <div>
            <button name="command" value="placeorder">Send forespørgsel</button>
            &nbsp;&nbsp;
            <button name="command" value="regretorder">Annuller forespørgsel</button>
        </div>
        <br><br>
    </form>
    <%=roofDrawing%>
    
</center>
</body>
</html>
