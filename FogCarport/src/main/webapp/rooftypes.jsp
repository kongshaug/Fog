<%-- 
    Document   : rooftypes
    Created on : 16-05-2019, 13:12:05
    Author     : sofieamalielandt
--%>

<%@page import="FunctionLayer.HelpingClasses.RoofType"%>
<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Material"%>
<%@page import="FunctionLayer.HelpingClasses.Material"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            
            List<RoofType> rooftypes = (List<RoofType>) request.getAttribute("rooftypes");
            String message = (String) request.getAttribute("message");
            if (message != null)
            {
                out.println(message + "<br><br>");
            }
        %>
        <form action="Fog" method="POST">
            <select name="rooftype" id="rooftype">
                <option disabled selected>Vælg tagtype</option>            
                <%
                    for (RoofType r : rooftypes)
                    {
                        out.println("<option value=\"" + r.getId() + "\">" + r.getName() + "</option>");
                    }
                %>
            </select>
            <br><br>
            <button name="command" value="rooftype">Opdater tagtype</button>
            <button name="command" value="deleterooftype">Slet tagtype</button>
            <br><br>
            OBS! For at tilføje en tagtype, skal der først <br>
            tilføjes materiale(r) til tagtypen <br><br>
            <button name="command" value="addrooftype">Tilføj en tagtype</button>
        </form>
    </div>
</center>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>

