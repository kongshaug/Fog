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

            <br><br>
            <select name="type">
                <option disabled selected>Vælg tagtype</option>            
                <%
                    List<RoofType> slopedroofs = (List<RoofType>) session.getAttribute("slopedroofs");
                    List<RoofType> flatroofs = (List<RoofType>) session.getAttribute("flatroofs");

                    for (RoofType ro : flatroofs)
                    {
                        out.println("<option value=\"" + ro.getId() + "\" class=\"fladt\">" + ro.getName() + "</option>");
                    }

                    for (RoofType r : slopedroofs)
                    {
                        out.println("<option value=\"" + r.getId() + "\" class=\"rejsning\">" + r.getName() + "</option>");
                    }

                %>
            </select>

            <br><br>
            <select name="slope" id="hældning">
                <option disabled selected>Vælg hældning</option>
                <%                    for (int i = 15; i <= 45; i = i + 5)
                    {
                        out.println("<option> " + i + "</option>");
                    }
                %>
            </select>

            <br><br>
            <button name="command" value="calculate">Beregn carport</button>

            <script>

                function disable()
                {
                    var flatoptions = document.querySelectorAll(".fladt");

                    flatoptions.forEach(function (x)
                    {
                        x.hidden = false;
                    });

                    var slopeoptions = document.querySelectorAll(".rejsning");

                    slopeoptions.forEach(function (x)
                    {
                        x.hidden = true;
                    });

                    document.getElementById("hældning").disabled = true;
                }

                function enable()
                {
                    var flatoptions = document.querySelectorAll(".fladt");

                    flatoptions.forEach(function (x)
                    {
                        x.hidden = true;
                    });
                    var slopeoptions = document.querySelectorAll(".rejsning");

                    slopeoptions.forEach(function (x)
                    {
                        x.hidden = false;
                    });

                    document.getElementById("hældning").disabled = false;
                }

            </script>
        </form> 
    </body>
</html>
