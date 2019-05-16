<%-- 
    Document   : material
    Created on : 14-05-2019, 13:02:21
    Author     : sofieamalielandt
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Material"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            
            Material material = (Material) session.getAttribute("material");
            List<String> material_classes = (List<String>) session.getAttribute("material_classes");
            String message = (String) request.getAttribute("message");

            if (message != null)
            {
                out.println(message + "<br><br>");
            }

        %>
        <form action="Fog" method="POST">
            Navn<br>
            <input type="text" id="name" name="name" value="<%=material.getName()%>" disabled="disabled"> <br><br>
            Enhed<br>
            <input type="text" id="unit" name="unit" value="<%=material.getUnit()%>" disabled="disabled"> <br><br>
            Kategori<br>
            <select id="material_class" name="material_class" disabled="disabled">
                <%
                    for (String m : material_classes)
                    {
                        if (m.equals(material.getMaterial_class()))
                        {
                            out.println("<option selected> " + m + "</option>");
                        } else
                        {
                            out.println("<option> " + m + "</option>");
                        }

                    }
                %>
            </select>
            <br><br>
            Pris<br>
            <input type="text" id="price" name="price" value="<%=material.getPrice()%>" disabled="disabled"> <br><br>

            <button id="save" name="command" value="materialupdate" style="display:none;">Gem</button>
        </form>

        <button id="update" onclick="update()">Opdater</button> <br><br>

    </div>
</center>
<script>
    function update() {
        document.getElementById("name").removeAttribute("disabled");
        document.getElementById("unit").removeAttribute("disabled");
        document.getElementById("material_class").removeAttribute("disabled");
        document.getElementById("price").removeAttribute("disabled");
        document.getElementById("save").style.display = "inline-block";
        document.getElementById("update").style.display = "none";
    }
</script>
</body>
</html>
