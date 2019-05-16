<%-- 
    Document   : addmaterial
    Created on : 14-05-2019, 14:08:54
    Author     : sofieamalielandt
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            List<String> material_classes = (List<String>) session.getAttribute("material_classes");
            String message = (String) request.getAttribute("message");

            if (message != null)
            {
                out.println(message + "<br><br>");
            }

        %>
        <form action="Fog" method="POST">
            Materiale navn<br>
            <input type="text" id="name" name="name" value="" placeholder="Indtast navn"> <br><br>
            Enhed<br>
            <input type="text" id="unit" name="unit" value="" placeholder="Indtast enhed"> <br><br>
            Kategori<br>
            <select id="material_class" name="material_class">
                <option disabled selected value="0">Vælg kategori</option>  
                <%                    for (String m : material_classes)
                    {
                        out.println("<option> " + m + "</option>");
                    }
                %>
            </select>
            <br><br>
            Pris<br>
            <input type="number" id="price" name="price" value="" placeholder="Indtast pris"> <br><br>

            <button id="save" name="command" value="addmaterial">Tilføj materiale</button><br><br>
        </form>
    </div>
</center>
</body>
</html>