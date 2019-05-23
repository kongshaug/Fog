<%-- 
    Document   : addrooftype
    Created on : 16-05-2019, 10:57:00
    Author     : aamandajuhl
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
            Tagtype navn<br>
            <input type="text" id="name" name="name" value="" placeholder="Indtast navn"> <br><br>
            1. Materiale (Ved tag med rejsning: beklædning til tagryggen)<br>
            <select name="material1">
                <option disabled selected>Vælg materiale</option>  
                <%
                    for (Material m : materials)
                    {
                        if (m.getMaterial_class().equals("tag"))
                        {
                            out.println("<option value=\"" + m.getId() + "\"> " + m.getName() + "</option>");
                        }
                    }
                %>
            </select>
            <br><br>
            2. Materiale (Valgfri)<br>
            <select  name="material2">
                <option disabled selected>Vælg materiale</option>  
                <option value="0"> - </option>
                <%
                    for (Material m : materials)
                    {
                        if (m.getMaterial_class().equals("tag"))
                        {
                            out.println("<option value=\"" + m.getId() + "\"> " + m.getName() + "</option>");
                        }
                    }
                %>
            </select>
            <br><br>
            <select name="roof_class">
                <option disabled selected>Vælg kategori</option>  
                <option value="flat">Fladt tag</option>
                <option value="slope">Tag med rejsning</option>
            </select>
            <br><br>
            <button name="command" value="rooftypeadd">Tilføj tagtype</button><br><br>
        </form>
    </div>
</center>
<br><br><br>
<div class="footer">
    <p>&copy; Copyright 2019 Amanda Juhl Hansen, Sofie Amalie Landt & Benjamin Kongshaug&nbsp;&nbsp;</p>
</div>
</body>
</html>
