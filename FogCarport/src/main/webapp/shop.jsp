<%-- 
    Document   : formular
    Created on : 23-04-2019, 11:07:25
    Author     : benja
--%>

<%@page import="java.util.List"%>
<%@page import="FunctionLayer.RoofType"%>
<%@page import="javafx.scene.control.RadioButton"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
    </head>
    <body>
        <form action="Fog" method="POST">
            Bredde: <br>
            <input type="number" name="depth" value="200" min="200"><br>
            Dybde: <br>
            <input type="number" name="width" value="200" min="200"><br><br>
            <label> <input type="radio" name="roof" id="flat" value="flat" onclick="disable()"><span>Fladt tag</span></label>
            <label> <input type="radio" name="roof" id="sloped" value="sloped"onclick="enable()"><span>Tag med rejsning</span></label>

            <select name="type">
                <option disabled selected>Vælg tagtype</option>            
                <%
                    List<RoofType> roofs = (List<RoofType>) session.getAttribute("roofs");
                    RoofType ro = (RoofType) session.getAttribute("roof");

                    out.println("<option id= \"f\" > " + ro.getId() + ro.getName() + "</option>");

                    for (RoofType r : roofs)
                    {
                        out.println("<option class=\"s\">" + r.getId() + r.getName() + "</option>");
                    }

                %>
            </select>

            <script>

                function disable()
                {
                    var options = document.querySelectorAll('option.s'), i = 0, l = options.length;
                    for (; i < l; i++)
                    {
                        options[i].disabled = true;
                    }

                    document.getElementsByClassName("s")
                    document.getElementById("f").disabled = false;
                    document.getElementById("h").disabled = true;

                }

                function enable()
                {
                    document.getElementById("f").disabled = true;
                    document.getElementById("s").disabled = false;
                    document.getElementById("h").disabled = false;
                }
               
            </script>
            <select name="slope" id="h">
                <option disabled selected>Vælg hældning</option>
                <%                    
                    for (int i = 15; i <= 45; i = i + 5)
                    {
                        out.println("<option> " + i + "</option>");
                    }
                %>
            </select>

            <button name="command" value="calculate">Beregn carport</button>
        </form> 
    </body>
</html>
