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
        <br>
        <%
            if (session.getAttribute("errorMessageShed") != null)
            {
        %>
        <p><%=session.getAttribute("errorMessageShed")%></p>
        <%
                session.removeAttribute("errorMessageShed");
            }
        %>

        <form action="Fog" method="POST">
            Bredde: <br>
            <input type="number" pattern="[0-2000]*" name="depth" value="240" min="240" max="750"><br>
            Dybde: <br>
            <input type="number" pattern="[0-2000]*" name="width" value="240" min="240" max="800"><br><br>
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
                <%                    for (int i = 15;
                            i <= 45; i = i + 5)
                    {
                        out.println("<option> " + i + "</option>");
                    }
                %>
            </select>

            <br>
            <br>
            <label> <input type="radio" name="shed" id="withShed" value="shed"><span>skur</span></label> 
            <br>
            <br>
            <p>OBS! Skuret skal have min. 15 cm udhæng på alle sider <br>
                og skal derfor være mindst 30 cm smallere og kortere end carporten</p>
            Bredde af skur: <br>
            <input type="number" pattern="[0-2000]*" name="shedDepth" value="210" min="210" max="720"><br>
            Dybde afskur: <br>
            <input type="number" pattern="[0-2000]*" name="shedWidth" value="210" min="210" max="770"><br><br>

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
