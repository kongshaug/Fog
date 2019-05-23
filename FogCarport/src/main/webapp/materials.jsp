<%-- 
    Document   : Materials
    Created on : 14-05-2019, 12:01:08
    Author     : sofieamalielandt
--%>
<%@page import="FunctionLayer.HelpingClasses.Material"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            List<Material> materials = (List<Material>) session.getAttribute("materials");
            String message = (String) request.getAttribute("message");
            if (message != null)
            {
                out.println(message + "<br><br>");
            }
        %>
        <form action="Fog" method="POST">
            <select name="material" id="material">
                <option disabled selected>Vælg Materiale</option>            
                <%                    for (Material m : materials)
                    {
                        out.println("<option value=\"" + m.getId() + "\">" + m.getName() + "</option>");
                    }
                %>
            </select>
            <br><br>
            <button name="command" value="material">Opdater materiale</button>
            <button name="command" value="deletematerial">Slet materiale</button>
            <br><br>
            OBS! Hvis materialer, tilknyttet en tagtype, slettes,<br>
            slettes tagtypen også!<br><br>
            <button name="command" value="materialadd">Tilføj et materiale</button>
            <br><br>
            <button name="command" value="rooftypes">Se tagtyper</button>
        </form>
    </div>
</center>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
