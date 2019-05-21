<%-- 
    Document   : rooftype
    Created on : 16-05-2019, 13:22:37
    Author     : sofieamalielandt
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.HelpingClasses.Material"%>
<%@page import="FunctionLayer.HelpingClasses.RoofType"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file = "header.jsp" %>
<center class="index" id="index"> 
    <br><br><br>
    <div>
        <img src="images/logo.png" width="30%"><br>
        <%            
            RoofType rooftype = (RoofType) session.getAttribute("rooftype");
            List<Material> materials = (List<Material>) session.getAttribute("materials");

            String message = (String) request.getAttribute("message");
            if (message != null)
            {
                out.println(message + "<br><br>");
            }
        %>
        <form action="Fog" method="POST">
            Tagtype navn<br>
            <input type="text" id="name" name="name" value="<%=rooftype.getName()%>" disabled="disabled"> 
            <br><br>
            1. Materiale (Ved tag med rejsning: beklædning til tagryggen)<br>
            <select id="m1" name="material1" disabled="disabled">
                <%
                    for (Material m : materials)
                    {
                        if (m.getMaterial_class().equals("tag"))
                        {
                            if (rooftype.getM1().getId() == m.getId())
                            {
                                out.println("<option selected value=\"" + m.getId() + "\"> " + m.getName() + "</option>");
                            } else
                            {
                                out.println("<option value=\"" + m.getId() + "\"> " + m.getName() + "</option>");
                            }
                        }
                    }
                %>
            </select>
            <br><br>
            2. Materiale (Valgfri)<br>
            <select id="m2" name="material2" disabled="disabled">
                <option selected value="0"> - </option>
                <%
                    if (rooftype.getM2() != null)
                    {
                        for (Material m : materials)
                        {
                            if (m.getMaterial_class().equals("tag"))
                            {
                                if (rooftype.getM2().getId() == m.getId())
                                {
                                    out.println("<option selected value=\"" + m.getId() + "\"> " + m.getName() + "</option>");
                                } else
                                {
                                    out.println("<option value=\"" + m.getId() + "\"> " + m.getName() + "</option>");
                                }
                            }
                        }
                    }
                %>
            </select>
            <br><br>
            <select id="roof_class" name="roof_class" disabled="disabled">
                <%
                    if (rooftype.getRoof_class()
                            .equals("slope"))
                    {
                        out.println("<option selected value=\"slope\">Tag med rejsning</option>");
                        out.println("<option value=\"flat\">Fladt tag</option>");
                    } else
                    {
                        out.println("<option value=\"slope\">Tag med rejsning</option>");
                        out.println("<option selected value=\"flat\">Fladt tag</option>");
                    }

                %>
            </select>
            <br><br>
            <button id="save" name="command" value="updaterooftype" style="display:none;">Gem</button>
        </form>
        <button id="update" onclick="update()">Opdater</button> <br><br>
    </div>
</center>
<script>
    function update() {
        document.getElementById("name").removeAttribute("disabled");
        document.getElementById("m1").removeAttribute("disabled");
        document.getElementById("m2").removeAttribute("disabled");
        document.getElementById("save").style.display = "inline-block";
        document.getElementById("update").style.display = "none";
    }
</script>
</body>
</html>